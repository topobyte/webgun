// Copyright 2020 Sebastian Kuerten
//
// This file is part of webgun.
//
// webgun is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// webgun is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with webgun. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.webgun.scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scheduler<T extends NamedRunnable>
{

	final static Logger logger = LoggerFactory.getLogger(Scheduler.class);

	private List<NextInvocationTimeFactory> factories = new ArrayList<>();
	private Map<NextInvocationTimeFactory, T> map = new HashMap<>();
	private PriorityQueue<Entry<T>> entries = new PriorityQueue<>();

	private static final DateTimeFormatter formatter = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss");

	private Thread schedulerThread = null;
	private Set<Thread> threads = new HashSet<>();

	public synchronized void schedule(NextInvocationTimeFactory factory, T task)
	{
		factories.add(factory);
		map.put(factory, task);

		LocalDateTime next = factory.getNext();
		entries.add(new Entry<>(factory, next, task));

		if (schedulerThread != null) {
			schedulerThread.interrupt();
		}
	}

	public synchronized void start()
	{
		schedulerThread = new Thread(() -> {
			while (true) {
				LocalDateTime now = LocalDateTime.now();
				if (logger.isDebugEnabled()) {
					logger.debug("current time: " + formatter.format(now));

					logger.debug("Current queue");
					logQueue();
				}

				executeTasks(now);

				long sleep = check(now);
				if (logger.isDebugEnabled()) {
					logger.debug("sleeping for " + sleep);
				}
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					logger.info("Scheduler has been interrupted");
					if (stop) {
						logger.info(
								"Scheduler has been interrupted and is supposed to stop");
						return;
					}
				}
			}
		}, "Scheduler loop");
		schedulerThread.start();
	}

	private boolean stop = false;

	public synchronized void stop()
	{
		stop = true;
		factories.clear();
		entries.clear();
		map.clear();
		schedulerThread.interrupt();
		for (Thread thread : threads) {
			thread.interrupt();
		}
	}

	private synchronized void executeTasks(LocalDateTime now)
	{
		while (true) {
			Entry<T> next = entries.peek();
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("next in line: %s %s",
						formatter.format(next.time), next.task.getName()));
			}
			long diff = Duration.between(now, next.time).toMillis();
			if (diff > 0) {
				break;
			}
			entries.poll();
			execute(next);
			NextInvocationTimeFactory factory = next.factory;
			factory.executed();
			LocalDateTime nextInvocation = factory.getNext(now);
			if (nextInvocation != null) {
				entries.add(new Entry<>(factory, nextInvocation, next.task));
			} else {
				map.remove(factory);
				factories.remove(factory);
			}
		}
	}

	private class WrapperRunnable implements Runnable
	{

		private Runnable task;
		private Thread thread;

		public WrapperRunnable(Runnable task)
		{
			this.task = task;
		}

		@Override
		public void run()
		{
			task.run();
			threads.remove(thread);
			if (logger.isDebugEnabled()) {
				logger.debug("Number of active threads: " + threads.size());
			}
		}

	}

	private void execute(Entry<T> next)
	{
		if (logger.isDebugEnabled()) {
			logger.debug("execute: " + next.task.getName());
		}
		WrapperRunnable wrapper = new WrapperRunnable(next.task);
		Thread thread = new Thread(wrapper,
				String.format("Scheduled task '%s'", next.task.getName()));
		wrapper.thread = thread;
		threads.add(thread);
		thread.start();
	}

	private synchronized long check(LocalDateTime now)
	{
		if (logger.isDebugEnabled()) {
			logger.debug("running check");
		}
		if (factories.isEmpty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("no tasks scheduled");
			}
			return 1000 * 60;
		}

		long[] values = new long[factories.size()];

		for (int i = 0; i < factories.size(); i++) {
			NextInvocationTimeFactory factory = factories.get(i);
			LocalDateTime next = factory.getNext();
			values[i] = Duration.between(now, next).toMillis();
		}
		Arrays.sort(values);
		long shortest = values[0];

		if (logger.isDebugEnabled()) {
			logTimes(values);
		}

		return shortest;
	}

	public void logQueue()
	{
		for (Entry<T> entry : entries) {
			logger.debug(String.format("%s %s", formatter.format(entry.time),
					entry.task.getName()));
		}
	}

	public synchronized PriorityQueue<Entry<T>> getQueue()
	{
		PriorityQueue<Entry<T>> copy = new PriorityQueue<>();
		copy.addAll(entries);
		return copy;
	}

	private void logTimes(long[] values)
	{
		StringBuilder strb = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			strb.append(values[i]);
			if (i < values.length - 1) {
				strb.append(", ");
			}
		}
		logger.debug("times: " + strb);
	}

}
