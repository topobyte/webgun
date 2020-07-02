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

import javax.servlet.http.HttpServletRequest;

import de.topobyte.webgun.resolving.PathResolver;
import de.topobyte.webpaths.WebPath;

public class PathSpecResolver<R, D> implements PathResolver<R, D>
{

	protected List<PathSpecMapping<R, D>> mappings = new ArrayList<>();

	protected void map(WebPath path, PathSpecFactory<R, D> factory)
	{
		map(new PathSpec(path), factory);
	}

	protected void map(PathSpec spec, PathSpecFactory<R, D> factory)
	{
		mappings.add(new PathSpecMapping<R, D>(spec, factory));
	}

	@Override
	public R getGenerator(WebPath path, HttpServletRequest request, D data)
	{
		R generator = null;

		PathSpecOutput pathSpecOutput = new PathSpecOutput();
		for (PathSpecMapping<R, D> mapping : mappings) {
			if (mapping.getSpec().matches(path, pathSpecOutput)) {
				generator = mapping.getFactory().create(path, pathSpecOutput,
						request, data);
				break;
			}
		}

		return generator;
	}

}
