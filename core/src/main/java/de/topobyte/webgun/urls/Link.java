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

package de.topobyte.webgun.urls;

import java.util.List;

import de.topobyte.webgun.util.ParameterUtil;

public class Link
{

	private String base;
	private List<Parameter> parameters;

	public Link(String base, List<Parameter> parameters)
	{
		this.base = base;
		this.parameters = parameters;
	}

	public String getBase()
	{
		return base;
	}

	public void setBase(String base)
	{
		this.base = base;
	}

	public List<Parameter> getParameters()
	{
		return parameters;
	}

	public void setParameters(List<Parameter> parameters)
	{
		this.parameters = parameters;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(base);
		if (!parameters.isEmpty()) {
			builder.append("?");
			for (int i = 0; i < parameters.size(); i++) {
				Parameter parameter = parameters.get(i);
				builder.append(parameter.getKey());
				builder.append("=");
				builder.append(ParameterUtil.encode(parameter.getValue()));
				if (i < parameters.size() - 1) {
					builder.append("&");
				}
			}
		}
		return builder.toString();
	}

}
