/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * A class that allows further specification of exactly which element the {@link PartialPaginationHtmlGroup} starts at, as well
 * as where the group will end. This is the last step in creating a {@link PartialPaginationHtmlGroup}. This class is called by
 * {@link PaginationHtmlGroupStart}
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see PaginationHtmlGroup
 * @see BaseHtmlPath
 */
public interface PartialPaginationHtmlGroup extends BaseHtmlPath<PartialPaginationHtmlGroup>, BaseHtmlPathStart<PartialPaginationHtmlGroup> {

	/**
	 * Defines the HTML element where the {@link PaginationHtmlGroup} will end. As it returns a {@link PartialPaginationHtmlGroup}, it allows
	 * the further specification of exactly which HTML element the group will end, as well as providing functionality
	 * to set the elements that will be used to paginate. If the closing element needs to be included into the group,
	 * use {@link #endAtClosing(String)}.
	 *
	 * @param elementName the name of the element that the group will close at
	 * @return a {@link PaginationHtmlGroup} to specify the exact element where the group will closes as well as allowing the
	 * definition of what elements will be used to paginate.
	 */
	PaginationHtmlGroup endAt(String elementName);

	/**
	 * Defines the HTML element where the {@link PaginationHtmlGroup} will end and includes this closing element into the group.
	 * As it returns a {@link PaginationHtmlGroup} itself, it allows the further specification of exactly which HTML element the
	 * group will end, as well as providing functionality to set the elements that will be used to paginate. If the closing element should
	 * not be included in the group, use {@link #endAt(String)}.
	 *
	 * @param elementName the name of the element that the group will close at
	 * @return a {@link PaginationHtmlGroup} to specify the exact element where the group will closes as well as allowing the
	 * definition of what elements will be used to paginate.
	 */
	PaginationHtmlGroup endAtClosing(String elementName);
}
