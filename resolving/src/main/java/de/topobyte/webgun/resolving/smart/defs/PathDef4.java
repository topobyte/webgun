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

public class PathDef4<T1, T2, T3, T4> extends AbstractPathDef
{

	private ParameterMapper<T1> mapper1;
	private ParameterMapper<T2> mapper2;
	private ParameterMapper<T3> mapper3;
	private ParameterMapper<T4> mapper4;
	private String param1;
	private String param2;
	private String param3;
	private String param4;

	public PathDef4(PathSpec pathSpec, ParameterMapper<T1> mapper1,
			ParameterMapper<T2> mapper2, ParameterMapper<T3> mapper3,
			ParameterMapper<T4> mapper4)
	{
		super(pathSpec);
		this.mapper1 = mapper1;
		this.mapper2 = mapper2;
		this.mapper3 = mapper3;
		this.mapper4 = mapper4;
		param1 = pathSpec.getName(pathSpec.getParameterPosition(0));
		param2 = pathSpec.getName(pathSpec.getParameterPosition(1));
		param3 = pathSpec.getName(pathSpec.getParameterPosition(2));
		param4 = pathSpec.getName(pathSpec.getParameterPosition(3));
	}

	public String getLink(T1 t1, T2 t2, T3 t3, T4 t4)
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
				} else if (n == 2) {
					strb.append(mapper3.toString(t3));
				} else if (n == 3) {
					strb.append(mapper4.toString(t4));
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

	public T3 param3(PathSpecOutput output)
	{
		String s = output.getParameter(param3);
		return mapper3.fromString(s);
	}

	public T4 param4(PathSpecOutput output)
	{
		String s = output.getParameter(param4);
		return mapper4.fromString(s);
	}

}
