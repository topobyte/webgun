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

package de.topobyte.webgun.resolving.smart.factories;

import javax.servlet.http.HttpServletRequest;

import de.topobyte.webgun.resolving.pathspec.PathSpecOutput;
import de.topobyte.webpaths.WebPath;

public interface PathSpecFactory4<R, D, T1, T2, T3, T4>
{

	public R create(WebPath path, PathSpecOutput output,
			HttpServletRequest request, D data, T1 t1, T2 t2, T3 t3, T4 t4);

}