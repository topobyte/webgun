// Copyright 2019 Sebastian Kuerten
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

package de.topobyte.webgun.resolving.pathspec;

import java.util.HashMap;
import java.util.Map;

public class PathSpecOutput
{

	private Map<String, String> parameters = new HashMap<>();

	public String getParameter(String parameter)
	{
		return parameters.get(parameter);
	}

	public void putParameter(String key, String value)
	{
		parameters.put(key, value);
	}

	public void clear()
	{
		parameters.clear();
	}

}
