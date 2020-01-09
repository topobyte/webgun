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

package de.topobyte.webgun.urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlashArgs
{

	private List<String> parts;

	private List<String> keys;
	private Map<String, String> map;

	public SlashArgs(List<String> parts)
	{
		this.parts = parts;

		init();
	}

	private void init()
	{
		keys = new ArrayList<>();
		map = new HashMap<>();

		int nArgs = parts.size() / 2;
		for (int i = 0; i < nArgs; i++) {
			String key = parts.get(i * 2);
			String value = parts.get(i * 2 + 1);
			keys.add(key);
			map.put(key, value);
		}
	}

	public List<String> getList()
	{
		return parts;
	}

	public List<String> generateList()
	{
		List<String> result = new ArrayList<>();
		for (String key : keys) {
			result.add(key);
			result.add(map.get(key));
		}
		return result;
	}

	public Map<String, String> getMap()
	{
		return map;
	}

	public void add(String key, String value)
	{
		keys.add(key);
		map.put(key, value);
	}

	public void set(String key, String value)
	{
		map.put(key, value);
	}

	public void addOrSet(String key, String value)
	{
		if (!map.containsKey(key)) {
			add(key, value);
		} else {
			set(key, value);
		}
	}

	public void remove(String key)
	{
		map.remove(key);
		keys.remove(key);
	}

}
