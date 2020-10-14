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

import java.time.LocalDateTime;

public class HourlyInvocationTimeFactory implements NextInvocationTimeFactory
{

	private int minutes;
	private int seconds;

	public HourlyInvocationTimeFactory(int minutes, int seconds)
	{
		this.minutes = minutes;
		this.seconds = seconds;
	}

	@Override
	public LocalDateTime getNext()
	{
		return getNext(LocalDateTime.now());
	}

	@Override
	public LocalDateTime getNext(LocalDateTime now)
	{
		LocalDateTime thisHour = now.withMinute(minutes).withSecond(seconds);
		if (thisHour.isAfter(now)) {
			return thisHour;
		}
		return thisHour.plusHours(1);
	}

	@Override
	public void executed()
	{
		// we don't care
	}

}
