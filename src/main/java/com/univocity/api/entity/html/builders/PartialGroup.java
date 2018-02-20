/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * Allows further specification of exactly which element a {@link Group} starts at, as well
 * as where the group will end. This is the last step in creating a {@link Group}. This class is accessible from a
 * {@link GroupStart}
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see Group
 * @see ElementFilter
 * @see GroupStart
 * @see ElementFilterStart
 */
public interface PartialGroup extends ElementFilter<PartialGroup>, ElementFilterStart<PartialGroup> {

	/**
	 * Defines the HTML element where a {@link Group} will end. As it returns a {@link Group} itself, it allows
	 * the further specification of exactly which HTML element the group will end, as well as providing functionality
	 * to create fields from the group. If a closing element (such as `</table>`, `</div>`, etc) needs to be
	 * included into the group, use {@link #endAtClosing(String)}.
	 *
	 * @param elementName the name of the element that the group will end at
	 *
	 * @return a {@link Group} to specify the exact element where the group will close as well as allowing the
	 * addition of fields to the group.
	 */
	Group endAt(String elementName);

	/**
	 * Defines the closing HTML element (such as `</table>`, `</div>`, etc) where the {@link Group} will end and
	 * includes this closing element into the group.
	 *
	 * As it returns a {@link Group} itself, it allows further specification of exactly which HTML element the group
	 * ends with. It also provides functionality to create fields from the group. If the closing element should
	 * not be included in the group, use {@link #endAt(String)}.
	 *
	 * @param elementName the name of the closing element that the group will close at
	 *
	 * @return a {@link Group} to specify the exact element where the group will close as well as allowing the
	 * addition of fields to the group.
	 */
	Group endAtClosing(String elementName);

}
