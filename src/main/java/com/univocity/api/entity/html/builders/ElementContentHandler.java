/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import java.util.*;

/**
 * An {@code ElementContentHandler} allows values defined for capture by {@link ContentReader} methods to be returned as
 * actual values instead of needing to define {@link com.univocity.parsers.common.EntitySettings} and retrieving them
 * as a {@link com.univocity.parsers.common.Result}.
 */
public interface ElementContentHandler extends ContentHandler<ElementContentHandler> {

	/**
	 * Get all values found from taking the matching rules previously specified and
	 * applying them to the input.
	 * For Example, to get all of the P HTML element text from the input:
	 * <hr><code><pre>
	 * {@code
	 *     String input = "<h1>Header</h1><p>First P tag</p><span>Strong text</span><p>Second P tag</p><p>Third P tag</p>";
	 * 	   HtmlElement root = HtmlParser.parse(new StringReaderProvider(input));
	 * 	   List<String> allPTagText = root.query().match("p").getText().getValues();
	 * }
	 * </pre></code><hr>
	 * <p>Would result in {@code allPTagText} containing the values:
	 * {@code ["First P tag", "Second P tag", "Third P tag"]}</p>
	 *
	 * @return all of the values returned from using the matching rules.
	 */
	List<String> getValues();


	/**
	 * Get the first value found from taking the matching rules previously specified and
	 * applying them to the input.
	 * For Example, to get the text of the first P HTML element text from the input:
	 * <hr><code><pre>
	 * {@code
	 *     String input = "<h1>Header</h1><p>First P tag</p><span>Strong text</span><p>Second P tag</p><p>Third P tag</p>";
	 * 	   HtmlElement root = HtmlParser.parse(new StringReaderProvider(input));
	 * 	   String firstPTagText = root.query().match("p").getText().getValue();
	 * }
	 * </pre></code><hr>
	 * <p>Would result in {@code firstPTagText } containing the value {@code "First P tag"}</p>
	 *
	 * @return the very first value returned from using the matching rules.
	 */
	String getValue();

}
