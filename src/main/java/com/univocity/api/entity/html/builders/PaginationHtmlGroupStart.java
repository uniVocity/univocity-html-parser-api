/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * This class defines the first step in the creation of a {@link PaginationHtmlGroup}. This is like a {@link HtmlGroup}
 * but is used only for {@link com.univocity.api.entity.html.HtmlPaginator}s. Creates an area in the HTML where the
 * paginator will look for elements to help in pagination. Elements outside the area will be ignored.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see com.univocity.api.entity.html.HtmlPaginator
 * @see HtmlGroupStart
 * @see PartialPaginationHtmlGroup
 * @see PaginationHtmlGroup
 */
public interface PaginationHtmlGroupStart {

	/**
	 * Specifies where on the HTML that the group will start. Any HTML before this starting element will be ignored by
	 * the parser when parsing fields creating from this group. Returns a {@link PartialPaginationHtmlGroup} which allows the further
	 * specification of exactly what element on the page the group will start at. {@link PartialPaginationHtmlGroup} also provides
	 * methods to define what element the group will end at.
	 *
	 * @param elementName the name of the HTML element that the group will start at
	 * @return a {@link PartialPaginationHtmlGroup} which is used to further specify the exact element where the group will start
	 * and where the group will end.
	 */
	PartialPaginationHtmlGroup startAt(String elementName);
}
