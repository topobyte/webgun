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
import java.util.Collections;
import java.util.List;

import com.slimjars.dist.gnu.trove.list.TIntList;
import com.slimjars.dist.gnu.trove.list.array.TIntArrayList;

import de.topobyte.webgun.resolving.pathspec.parts.ParameterPart;
import de.topobyte.webgun.resolving.pathspec.parts.Part;
import de.topobyte.webgun.resolving.pathspec.parts.StaticPart;
import de.topobyte.webpaths.WebPath;

public class PathSpec
{

	private boolean acceptAdditional;
	private TIntList positionsNormal = new TIntArrayList();
	private TIntList positionsParameters = new TIntArrayList();
	private List<String> names = new ArrayList<>();
	private List<Part> parts = new ArrayList<>();

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

	public boolean acceptsAdditional()
	{
		return acceptAdditional;
	}

	public int getNumStaticParts()
	{
		return positionsNormal.size();
	}

	public int getNumParameters()
	{
		return positionsParameters.size();
	}

	public String getName(int i)
	{
		return names.get(i);
	}

	public List<Part> getParts()
	{
		return Collections.unmodifiableList(parts);
	}

	public int getStaticPosition(int i)
	{
		return positionsNormal.get(i);
	}

	public int getParameterPosition(int i)
	{
		return positionsParameters.get(i);
	}

	private void addParameter(int i, String part)
	{
		if (part.startsWith(":") && part.endsWith(":")) {
			positionsParameters.add(i);
			String name = part.substring(1, part.length() - 1);
			names.add(name);
			parts.add(new ParameterPart(name));
		} else {
			positionsNormal.add(i);
			names.add(part);
			parts.add(new StaticPart(part));
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

	@Override
	public String toString()
	{
		StringBuilder strb = new StringBuilder();
		for (Part part : getParts()) {
			strb.append("/");
			if (part instanceof StaticPart) {
				strb.append(part.getName());
			} else if (part instanceof ParameterPart) {
				strb.append(":");
				strb.append(part.getName());
				strb.append(":");
			}
		}
		if (acceptAdditional) {
			strb.append("[/*]");
		}
		return strb.toString();
	}

}
