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
import java.util.List;

import de.topobyte.webpaths.WebPath;

public class SlashArgsUtil
{

	public static SlashArgs slashArgs(WebPath path, int numSkip)
	{
		List<String> remainder = new ArrayList<>();
		for (int i = numSkip; i < path.getNameCount(); i++) {
			remainder.add(path.getName(i));
		}
		return new SlashArgs(remainder);
	}

}
