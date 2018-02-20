/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * This class defines the first step in the creation of a {@link PaginationGroup}. This is like a {@link Group}
 * but is used only for {@link com.univocity.api.entity.html.HtmlPaginator}s. Creates an area in the HTML where the
 * paginator will look for elements used for pagination processing. Elements outside the area will be ignored.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see com.univocity.api.entity.html.HtmlPaginator
 * @see GroupStart
 * @see PartialPaginationGroup
 * @see PaginationGroup
 */
public interface PaginationGroupStart {
	/**
	 * Specifies where on the HTML that the group will start. Any element before the starting element will be ignored by
	 * the parser when parsing fields created from this group. Returns a {@link PartialPaginationGroup} which allows further
	 * specification of exactly what element on the page the group will start at. The {@link PartialPaginationGroup}
	 * also provides methods to define what element the group will end at.
	 *
	 * @param elementName the name of the HTML element that the group will start at
	 * @return a {@link PartialPaginationGroup} which is used to further specify the exact element where the group will start
	 * and where the group will end.
	 */
	PartialPaginationGroup startAt(String elementName);
}