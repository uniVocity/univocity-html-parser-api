/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import java.util.*;

/**
 * A class that contains information about HTML elements when the parser runs.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlElement {

	boolean isText();

	/**
	 * Returns the HTML tag name associated with the element. For instance the tag name of the element
	 * {@code <span title="fan" id="electric"></span>} would be "span".
	 *
	 * @return the associated HTML tag as a string
	 */
	String tagName();

	HtmlElement parent();

	HtmlElement[] children();

	String attribute(String attributeName);

	Set<String> attributeNames();

	String text();

	HtmlElement nextSibling();

	HtmlElement previousSibling();

	/**
	 * Returns a new instance of the HTML element.
	 *
	 * @return the newly created HTML element.
	 */
	HtmlElement grab();

	/**
	 * Returns the id of the HTML element or an empty string if the element does not have a id. For example: The HTML
	 * of {@code <span id="test"></span>} will return "test" when running id() on it.
	 *
	 * @return the element's id, or empty string if id non-existent
	 */
	String id();

	/**
	 * Returns a set of classes of the HTML element or an empty set if the element has no classes.
	 *
	 * @return HTML element's classes as a set or empty set if no classes
	 */
	Set<String> classes();

	boolean containsElementInHierarchy(HtmlElement element);
}
