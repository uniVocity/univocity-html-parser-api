/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.parsers.remote.*;

 /**
 * Contains information about the pagination process managed by a {@link HtmlPaginator} and made available to the user
 * through the {@link NextInputHandler} callback.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see RemoteContext
 * @see Paginator
 * @see NextInputHandler
 * @see PaginationContext
 */
public interface HtmlPaginationContext extends PaginationContext {

	/**
	 * Returns the root element of the HTML tree being processed by the parser. Typically this is the `<html>` element.
	 *
	 * @return the root node of the document being processed
	 */
	HtmlElement getCurrentPageRoot();
}
