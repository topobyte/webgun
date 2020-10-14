// Copyright 2020 Sebastian Kuerten
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

package de.topobyte.webgun.exceptions;

public class InternalServerErrorException extends WebStatusException
{

	private static final long serialVersionUID = 1L;

	public static final int CODE = 500;

	public InternalServerErrorException()
	{
		super(CODE);
	}

	public InternalServerErrorException(String message)
	{
		super(CODE, message);
	}

	public InternalServerErrorException(Throwable cause)
	{
		super(CODE, cause);
	}

	public InternalServerErrorException(String message, Throwable cause)
	{
		super(CODE, message, cause);
	}

}
