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

import java.util.ArrayList;
import java.util.List;

import de.topobyte.webpaths.WebPath;

public class LinkBuilder
{

	private String base;
	private List<Parameter> parameters = new ArrayList<>();

	public LinkBuilder(String base)
	{
		this.base = base;
	}

	public LinkBuilder(WebPath path, boolean isAbsolute)
	{
		this.base = isAbsolute ? "/" + path.toString() : path.toString();
	}

	public LinkBuilder addParameter(String key, String value)
	{
		parameters.add(new Parameter(key, value));
		return this;
	}

	public Link build()
	{
		List<Parameter> myParameters = new ArrayList<>(parameters);
		return new Link(base, myParameters);
	}

	@Override
	public String toString()
	{
		return build().toString();
	}

}
