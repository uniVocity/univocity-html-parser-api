/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import java.util.*;

/**
 * An `ElementContentHandler` allows values defined for capture by a {@link ContentReader}'s methods to be returned as
 * actual values instead of needing to define an {@link com.univocity.parsers.common.EntitySettings} and retrieving them
 * as a {@link com.univocity.parsers.common.Result} with rows.
 */
public interface ElementContentHandler extends ContentHandler<ElementContentHandler> {

	/**
	 * Get all values from the nodes matched using a specific {@link ElementPath}.
	 *
	 * For example, to get the text of all of `p` elements from an input such as:
	 *
	 * ```html
	 * <h1>Header</h1>
	 * <p>First P tag</p>
	 * <span>Strong text</span>
	 * <p>Second P tag</p>
	 * <p>Third P tag</p>
	 * ```
	 *
	 * We can write the code to do the following:
	 *
	 * ```java
	 * String input = "<h1>Header</h1><p>First P tag</p><span>Strong text</span><p>Second P tag</p><p>Third P tag</p>";
	 * HtmlElement root = HtmlParser.parse(new StringReaderProvider(input));
	 * List<String> allPTagText = root.query().match("p").getText().getValues();
	 * ```
	 *
	 * The resulting `allPTagText` list will contain the values:
	 * ```
	 * ["First P tag", "Second P tag", "Third P tag"]
	 * ```
	 *
	 * @return all values matched by a given {@link ElementPath}.
	 */
	List<String> getValues();

	/**
	 * Get the first value from the first node matched using a specific {@link ElementPath}.
	 *
	 * For example, to get the text of the first of `p` element from an input such as:
	 *
	 * ```html
	 * <h1>Header</h1>
	 * <p>First P tag</p>
	 * <span>Strong text</span>
	 * <p>Second P tag</p>
	 * <p>Third P tag</p>
	 * ```
	 *
	 * We can write the code to do the following:
	 *
	 * ```java
	 * String input = "<h1>Header</h1><p>First P tag</p><span>Strong text</span><p>Second P tag</p><p>Third P tag</p>";
	 * HtmlElement root = HtmlParser.parse(new StringReaderProvider(input));
	 * String value = root.query().match("p").getText().getValue();
	 * ```
	 *
	 * The resulting value will contain:
	 *
	 * ```
	 * "First P tag"
	 * ```
	 *
	 * @return the very first value matched by a given {@link ElementPath}.
	 */
	String getValue();

}
