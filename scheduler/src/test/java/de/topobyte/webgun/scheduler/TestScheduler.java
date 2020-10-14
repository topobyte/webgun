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

public class TestScheduler
{

	public static void main(String[] args)
	{
		Scheduler<SchedulerTask> cron = new Scheduler<>();
		cron.schedule(new MinutelyInvocationTimeFactory(10),
				new NopTask("foo"));
		cron.schedule(new MinutelyInvocationTimeFactory(30),
				new NopTask("bar"));
		cron.schedule(new MinutelyInvocationTimeFactory(50),
				new NopTask("cat"));
		cron.start();
	}

}
