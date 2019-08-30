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
import java.util.Map;

import de.topobyte.jsoup.ContentGeneratable;
import de.topobyte.pagegen.core.Context;
import de.topobyte.webgun.resolving.PathResolver;
import de.topobyte.webpaths.WebPath;

public class PathSpecResolver<T extends Context> implements PathResolver<T>
{

	protected List<PathSpecMapping> mappings = new ArrayList<>();

	protected void map(WebPath path, PathSpecFactory factory)
	{
		map(new PathSpec(path), factory);
	}

	protected void map(PathSpec spec, PathSpecFactory factory)
	{
		mappings.add(new PathSpecMapping(spec, factory));
	}

	@Override
	public ContentGeneratable getGenerator(WebPath path, T context,
			Map<String, String[]> parameters)
	{
		ContentGeneratable generator = null;

		PathSpecOutput pathSpecOutput = new PathSpecOutput();
		for (PathSpecMapping mapping : mappings) {
			if (mapping.getSpec().matches(path, pathSpecOutput)) {
				generator = mapping.getFactory().create(context, path,
						pathSpecOutput, parameters);
			}
		}

		return generator;
	}

}
