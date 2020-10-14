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

public class TestSchedulerEverySecond
{

	public static void main(String[] args)
	{
		Scheduler<SchedulerTask> cron = new Scheduler<>();
		for (int i = 0; i < 60; i++) {
			cron.schedule(new MinutelyInvocationTimeFactory(i),
					new NopTask(String.format("second %02d", i)));
		}
		for (int i = 0; i < 6; i++) {
			cron.schedule(new MinutelyInvocationTimeFactory(i * 10),
					new NopTask(String.format("ten %d", i * 10)));
		}
		cron.start();
	}

}
