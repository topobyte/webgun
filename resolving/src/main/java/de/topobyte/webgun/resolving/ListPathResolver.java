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
import java.util.Map;

import de.topobyte.jsoup.ContentGeneratable;
import de.topobyte.pagegen.core.Context;
import de.topobyte.webpaths.WebPath;

public class ListPathResolver<T extends Context> implements PathResolver<T>
{

	protected List<PathResolver<T>> resolvers;

	public ListPathResolver()
	{
		this.resolvers = new ArrayList<>();
	}

	public ListPathResolver(List<PathResolver<T>> resolvers)
	{
		this.resolvers = resolvers;
	}

	@Override
	public ContentGeneratable getGenerator(WebPath path, T context,
			Map<String, String[]> parameters)
	{
		ContentGeneratable generator = null;

		for (PathResolver<T> resolver : resolvers) {
			generator = resolver.getGenerator(path, context, parameters);
			if (generator != null) {
				return generator;
			}
		}

		return null;
	}

}
