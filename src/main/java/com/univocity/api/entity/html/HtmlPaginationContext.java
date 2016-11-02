/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.net.*;
import com.univocity.parsers.remote.*;

import java.util.*;

/**
 * Contains information about the pagination process managed by a {@link HtmlPaginator} and made available to the user
 * through a {@link PaginationHandler} callback.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlPaginator
 * @see PaginationHandler
 * @see PaginationContext
 * @see UrlReaderProvider
 */
public interface HtmlPaginationContext extends PaginationContext {

	/**
	 * Returns the names of all request parameters configured in the current {@link HtmlPaginator}.
	 *
	 * @return a sequence of request field names bound to the paginator.
	 */
	Set<String> getRequestParameterNames();

	/**
	 * Returns the request parameters collected by the paginator as a map of request parameter names and values.
	 * Note that request parameters can have multiple values assigned to the same name.
	 *
	 * @return a map of request parameter names and their values.
	 */
	Map<String, String[]> getRequestParameters();

	/**
	 * Returns the {@link HttpResponse} object with all information returned by the remote server
	 * in its HTTP response message (which generated the current page).
	 *
	 * @return the {@link HttpResponse} received for the current page. Will be {@code null} if the
	 * pagination is running over local files.
	 */
	HttpResponse getCurrentPageResponse();

	/**
	 * Returns the {@link UrlReaderProvider} prepared by the parser to access the next page. It inherits all configuration
	 * options defined in the call to the previous page. Cookies set in the {@link HttpResponse} of the current page
	 * are automatically set into this request.
	 * You can alter its configuration before its {@link HttpRequest} is executed to
	 * fetch the next page.
	 *
	 * @return the {@link UrlReaderProvider} which will be used to fetch the next page. Will be {@code null} if the
	 * pagination is running over local files.
	 */
	UrlReaderProvider getNextPageRequest();

}
