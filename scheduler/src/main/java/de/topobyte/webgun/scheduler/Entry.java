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

import lombok.Getter;

public class Entry<T> implements Comparable<Entry<T>>
{

	@Getter
	NextInvocationTimeFactory factory;
	@Getter
	LocalDateTime time;
	@Getter
	T task;

	public Entry(NextInvocationTimeFactory factory, LocalDateTime time, T task)
	{
		this.factory = factory;
		this.time = time;
		this.task = task;
	}

	@Override
	public int compareTo(Entry<T> other)
	{
		return time.compareTo(other.time);
	}

}