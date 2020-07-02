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

package de.topobyte.webgun.resolving;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import de.topobyte.webpaths.WebPath;

public class ListPathResolver<R, D> implements PathResolver<R, D>
{

	protected List<PathResolver<R, D>> resolvers;

	public ListPathResolver()
	{
		this.resolvers = new ArrayList<>();
	}

	public ListPathResolver(List<PathResolver<R, D>> resolvers)
	{
		this.resolvers = resolvers;
	}

	@Override
	public R getGenerator(WebPath path, HttpServletRequest request, D data)
	{
		R generator = null;

		for (PathResolver<R, D> resolver : resolvers) {
			generator = resolver.getGenerator(path, request, data);
			if (generator != null) {
				return generator;
			}
		}

		return null;
	}

}
