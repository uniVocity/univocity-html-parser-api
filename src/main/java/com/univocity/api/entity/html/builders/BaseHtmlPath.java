/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
interface BaseHtmlPath<T extends BaseHtmlPath<T>> {

	T followedByText(String text);

	T precededByText(String text);

	T followedBy(String elementName);

	T followedBy(String elementName, int distance);

	T followedImmediatelyBy(String elementName);

	T precededBy(String elementName);

	T precededBy(String elementName, int distance);

	T precededImmediatelyBy(String elementName);

	T childOf(String elementName);

	T containedBy(String elementName);

	T containedBy(String elementName, int depthLimit);

	T underHeader(String headerElementName);

	T under(String elementName);

	T parentOf(String elementName);

	T containing(String... pathOfElementNames);

	T containing(String elementName);

	T containing(String elementName, int depthLimit);

	T withText(String textContent);

	T withMatchingText(String textContent);

	/**
	 * Creates a path to a HTML element by the specified class names. One to many class names can be inserted as
	 * parameters. As an example, assume there is HTML that looks like this: {@code '<span class = "a b"></span>'}. A
	 * technique to create a path to this would be {@code 'path.match("span").classes("a","b")'}/
	 *
	 * @param firstCssClass class of a HTML element that a path will be created to
	 * @param otherCssClasses any other classes that the HTML element contains, optional.
	 * @return a {@link BaseHtmlPath} which allows the more HTML elements to be added to the path
	 */
	T classes(String firstCssClass, String... otherCssClasses);

	/**
	 * Creates a path to a HTML element by the specified attribute and value. For example, if there is HTML that looks
	 * like this: {@code '<table title="The Tables Have Turned"></table>'}, a way to create a path to the table element
	 * would be to write {@code 'path.match("table").attribute("title","The Tables Have Turned")'}.
	 *
	 * @param attributeName the name of the attribute
	 * @param attributeValue the value of the attribute
	 * @return a {@link BaseHtmlPath} which allows the more HTML elements to be added to the path
	 */
	T attribute(String attributeName, String attributeValue);

	/**
	 * Creates a path to a HTML element by the specified id. For example, if there is HTML that looks like this:
	 * {@code '<div id="coffee"></div>'} and the path code looks like {@code 'path.match("div").id("coffee")'}, the path
	 * will be at the div shown in the HTML snippet.
	 *
	 * @param idValue the id of an element that a path will be created to
	 * @return a {@link BaseHtmlPath} which allows the more HTML elements to be added to the path
	 */
	T id(String idValue);

}
