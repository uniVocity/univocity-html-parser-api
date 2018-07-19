/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.parsers.remote.*;

/**
 * A special purpose {@link Group}-like structure, used only for {@link com.univocity.api.entity.html.HtmlPaginator}s.
 * Defines an area in the HTML where the paginator will look for elements to be used for pagination handling.
 * Elements outside the area will be ignored.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 *
 * @see PaginationContext
 * @see Paginator
 */
public interface PaginationGroup extends ElementFilter<PaginationGroup>, ElementFilterStart<PaginationGroup>, PaginationParams, PathCopy<PaginationPath> {

	/**
	 * Associates a constant value to a request parameter. Parameter values are submitted in POST requests to load
	 * the next page.
	 *
	 * @param parameterName  the name that will be associated with the parameter, which will be sent in the request
	 *                       for the next page.
	 * @param parameterValue the value of the corresponding parameter
	 */
	void setRequestParameter(String parameterName, String parameterValue);

	/**
	 * Creates a new field on this `PaginationGroup` and returns a {@link PathStart} that allows the user to define
	 * a path to the field. Any fields added to the paginator and their values can be read from
	 * a {@link NextInputHandler} through its {@link PaginationContext}.
	 *
	 * @param fieldName name of the new field
	 *
	 * @return a {@link PathStart} to be used to define the path of the value for the given field
	 */
	PathStart addField(String fieldName);

}
