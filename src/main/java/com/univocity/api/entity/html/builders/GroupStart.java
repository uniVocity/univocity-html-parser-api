/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;

/**
 * Defines the first step in the creation of a {@link Group}. A group is created when {@link HtmlEntitySettings#newGroup()} is called.
 * {@link Group}s allow the definition of 'parsing areas', where fields created from the group will only return
 * values matched from inside the group. HTML elements outside of the group will be ignored by the parser, even if
 * the fields match elements in this outside area.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see Group
 * @see PartialGroup
 */
public interface GroupStart {
	/**
	 * Specifies where on the HTML the group will start. Any element before the starting element will be ignored by
	 * the parser when parsing fields created from this group. Returns a {@link PartialGroup} which allows further
	 * specification of exactly what element on the page the group will start at. {@link PartialGroup} also provides
	 * methods to define what element the group will end at (method {@link PartialGroup#endAt(String)} and
	 * {@link PartialGroup#endAtClosing(String)}).
	 *
	 * @param elementName the name of the HTML element that the group will start at
	 *
	 * @return a {@link PartialGroup} which is used to further specify the exact element where the group will start
	 * and where the group will end.
	 */
	PartialGroup startAt(String elementName);
}
