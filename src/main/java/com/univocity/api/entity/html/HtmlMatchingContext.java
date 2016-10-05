/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import java.util.*;

/**
 * An abstract class that is used to return data matched by the {@link HtmlParser} during the parsing process.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 * @see HtmlParsingContext
 */
public abstract class HtmlMatchingContext implements HtmlParsingContext {

	/**
	 * Returns a Map of matched data where the value is the field name and the value is the data that was matched. Values
	 * are matched when the {@link HtmlParser} encounters a value defined by a path set by an {@link HtmlEntity}'s added
	 * field.
	 *
	 * @return a Map of data that was matched during parsing
	 */
	public abstract Map<String, String> getMatchedData();

	/**
	 * Returns the name of the {@link HtmlEntity} that the {@link HtmlParser} is using to parse the HTML document.
	 *
	 * @return the name that identifies the {@link HtmlEntity} being used by the {@link HtmlParser}
	 */
	public abstract String getEntityName();


	/**
	 * Returns the current node depth of the parser. Node depth is how many layers deep the currently visited HTML
	 * element is. For example given a simple HTML document like: {@code <div><span><span><div>}. When the parser visits
	 * the span element, the current node depth would be 1 (the node depth of div would be 0).
	 *
	 * @return the depth of the currently visited HTML element
	 */
	public abstract int currentNodeDepth();

	/**
	 * Returns the element that the Parser is currently visiting.
	 *
	 * @return the element that is currently being visited by the parser
	 */
	public abstract HtmlElement getCurrentElement();
}
