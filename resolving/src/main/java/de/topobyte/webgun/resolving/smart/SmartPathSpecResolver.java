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

package de.topobyte.webgun.resolving.smart;

import de.topobyte.webgun.resolving.pathspec.PathSpecFactory;
import de.topobyte.webgun.resolving.pathspec.PathSpecOutput;
import de.topobyte.webgun.resolving.pathspec.PathSpecResolver;
import de.topobyte.webgun.resolving.smart.defs.PathDef0;
import de.topobyte.webgun.resolving.smart.defs.PathDef1;
import de.topobyte.webgun.resolving.smart.defs.PathDef2;
import de.topobyte.webgun.resolving.smart.defs.PathDef3;
import de.topobyte.webgun.resolving.smart.defs.PathDef4;
import de.topobyte.webgun.resolving.smart.factories.PathSpecFactory1;
import de.topobyte.webgun.resolving.smart.factories.PathSpecFactory2;
import de.topobyte.webgun.resolving.smart.factories.PathSpecFactory3;
import de.topobyte.webgun.resolving.smart.factories.PathSpecFactory4;
import de.topobyte.webpaths.WebPath;
import jakarta.servlet.http.HttpServletRequest;

public class SmartPathSpecResolver<R, D> extends PathSpecResolver<R, D>
{

	protected void map(PathDef0 pathDef, PathSpecFactory<R, D> factory)
	{
		map(pathDef.getPathSpec(), factory);
	}

	protected <T> void map(PathDef1<T> pathDef,
			PathSpecFactory1<R, D, T> factory)
	{
		map(pathDef.getPathSpec(), (WebPath path, PathSpecOutput output,
				HttpServletRequest request, D data) -> {
			try {
				T param1 = pathDef.param(output);
				return factory.create(path, output, request, data, param1);
			} catch (Throwable e) {
				return null;
			}
		});
	}

	protected <T1, T2> void map(PathDef2<T1, T2> pathDef,
			PathSpecFactory2<R, D, T1, T2> factory)
	{
		map(pathDef.getPathSpec(), (WebPath path, PathSpecOutput output,
				HttpServletRequest request, D data) -> {
			try {
				T1 param1 = pathDef.param1(output);
				T2 param2 = pathDef.param2(output);
				return factory.create(path, output, request, data, param1,
						param2);
			} catch (Throwable e) {
				return null;
			}
		});
	}

	protected <T1, T2, T3> void map(PathDef3<T1, T2, T3> pathDef,
			PathSpecFactory3<R, D, T1, T2, T3> factory)
	{
		map(pathDef.getPathSpec(), (WebPath path, PathSpecOutput output,
				HttpServletRequest request, D data) -> {
			try {
				T1 param1 = pathDef.param1(output);
				T2 param2 = pathDef.param2(output);
				T3 param3 = pathDef.param3(output);
				return factory.create(path, output, request, data, param1,
						param2, param3);
			} catch (Throwable e) {
				return null;
			}
		});
	}

	protected <T1, T2, T3, T4> void map(PathDef4<T1, T2, T3, T4> pathDef,
			PathSpecFactory4<R, D, T1, T2, T3, T4> factory)
	{
		map(pathDef.getPathSpec(), (WebPath path, PathSpecOutput output,
				HttpServletRequest request, D data) -> {
			try {
				T1 param1 = pathDef.param1(output);
				T2 param2 = pathDef.param2(output);
				T3 param3 = pathDef.param3(output);
				T4 param4 = pathDef.param4(output);
				return factory.create(path, output, request, data, param1,
						param2, param3, param4);
			} catch (Throwable e) {
				return null;
			}
		});
	}

}
