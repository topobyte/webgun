// Copyright 2021 Sebastian Kuerten
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

package de.topobyte.webgun.resolving.smart.defs;

import de.topobyte.webgun.resolving.pathspec.PathSpec;
import de.topobyte.webgun.resolving.pathspec.PathSpecOutput;
import de.topobyte.webgun.resolving.pathspec.parts.ParameterPart;
import de.topobyte.webgun.resolving.pathspec.parts.Part;
import de.topobyte.webgun.resolving.pathspec.parts.StaticPart;
import de.topobyte.webgun.resolving.smart.mappers.ParameterMapper;

public class PathDef2<T1, T2> extends AbstractPathDef
{

	private ParameterMapper<T1> mapper1;
	private ParameterMapper<T2> mapper2;
	private String param1;
	private String param2;

	public PathDef2(PathSpec pathSpec, ParameterMapper<T1> mapper1,
			ParameterMapper<T2> mapper2)
	{
		super(pathSpec);
		this.mapper1 = mapper1;
		this.mapper2 = mapper2;
		param1 = pathSpec.getName(pathSpec.getParameterPosition(0));
		param2 = pathSpec.getName(pathSpec.getParameterPosition(1));
	}

	public String getLink(T1 t1, T2 t2)
	{
		StringBuilder strb = new StringBuilder();
		int n = -1;
		for (Part part : pathSpec.getParts()) {
			strb.append("/");
			if (part instanceof StaticPart) {
				strb.append(part.getName());
			} else if (part instanceof ParameterPart) {
				n += 1;
				if (n == 0) {
					strb.append(mapper1.toString(t1));
				} else if (n == 1) {
					strb.append(mapper2.toString(t2));
				}
			}
		}
		return strb.toString();
	}

	public T1 param1(PathSpecOutput output)
	{
		String s = output.getParameter(param1);
		return mapper1.fromString(s);
	}

	public T2 param2(PathSpecOutput output)
	{
		String s = output.getParameter(param2);
		return mapper2.fromString(s);
	}

}
