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

package de.topobyte.webgun.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ParameterUtil
{

	public static String encode(String name)
	{
		try {
			return URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return name;
		}
	}

	public static String get(Map<String, String[]> parameters, String name)
	{
		String[] values = parameters.get(name);
		if (values == null) {
			return null;
		}
		return values[0];
	}

	public static boolean hasValue(Map<String, String[]> parameters,
			String name, String value)
	{
		String[] values = parameters.get(name);
		if (values == null) {
			return false;
		}
		return values[0].equals(value);
	}

}
