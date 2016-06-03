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

	T classes(String firstCssClass, String... otherCssClasses);

	T attribute(String attributeName, String attributeValue);

	T id(String idValue);

}
