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

public class PathDef1<T> extends AbstractPathDef
{

	private ParameterMapper<T> mapper;
	private String param;

	public PathDef1(PathSpec pathSpec, ParameterMapper<T> mapper)
	{
		super(pathSpec);
		this.mapper = mapper;
		param = pathSpec.getName(pathSpec.getParameterPosition(0));
	}

	public String getLink(T t)
	{
		StringBuilder strb = new StringBuilder();
		for (Part part : pathSpec.getParts()) {
			strb.append("/");
			if (part instanceof StaticPart) {
				strb.append(part.getName());
			} else if (part instanceof ParameterPart) {
				strb.append(mapper.toString(t));
			}
		}
		return strb.toString();
	}

	public T param(PathSpecOutput output)
	{
		String s = output.getParameter(param);
		return mapper.fromString(s);
	}

}
