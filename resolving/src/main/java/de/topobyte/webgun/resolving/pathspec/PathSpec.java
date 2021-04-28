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

import java.util.ArrayList;
import java.util.List;

import com.slimjars.dist.gnu.trove.list.TIntList;
import com.slimjars.dist.gnu.trove.list.array.TIntArrayList;

import de.topobyte.webpaths.WebPath;

public class PathSpec
{

	private boolean acceptAdditional;
	private TIntList positionsNormal = new TIntArrayList();
	private TIntList positionsParameters = new TIntArrayList();
	private List<String> names = new ArrayList<>();

	public PathSpec(String... parts)
	{
		this(false, parts);
	}

	public PathSpec(WebPath path)
	{
		this(false, path);
	}

	public PathSpec(boolean acceptAdditional, String... parts)
	{
		this.acceptAdditional = acceptAdditional;
		for (int i = 0; i < parts.length; i++) {
			String part = parts[i];
			addParameter(i, part);
		}
	}

	public PathSpec(boolean acceptAdditional, WebPath path)
	{
		this.acceptAdditional = acceptAdditional;
		for (int i = 0; i < path.getNameCount(); i++) {
			String part = path.getName(i);
			addParameter(i, part);
		}
	}

	private void addParameter(int i, String part)
	{
		if (part.startsWith(":") && part.endsWith(":")) {
			positionsParameters.add(i);
			names.add(part.substring(1, part.length() - 1));
		} else {
			positionsNormal.add(i);
			names.add(part);
		}
	}

	public boolean matches(WebPath path, PathSpecOutput output)
	{
		int nc = path.getNameCount();
		if (acceptAdditional) {
			if (nc < names.size()) {
				return false;
			}
		} else {
			if (nc != names.size()) {
				return false;
			}
		}
		if (path.isDir()) {
			return false;
		}

		for (int i = 0; i < positionsNormal.size(); i++) {
			int pos = positionsNormal.get(i);
			String value = path.getName(pos);
			if (!names.get(pos).equals(value)) {
				return false;
			}
		}

		output.clear();

		for (int i = 0; i < positionsParameters.size(); i++) {
			int pos = positionsParameters.get(i);
			String value = path.getName(pos);
			output.putParameter(names.get(pos), value);
		}

		return true;
	}

}
