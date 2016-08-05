/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * Provides the start of an {@link HtmlPath}.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlPath
 * @see PartialHtmlPath
 * @see BaseHtmlPath
 */
public interface BaseHtmlPathStart<T extends BaseHtmlPath<T>> {

	/**
	 * Used to specify what HTML element to match. This is the first step in creating a {@link HtmlPath} for a field.
	 * For example, if the specified element is "a", the parser will try to match "a" elements. As it returns a
	 * {@link BaseHtmlPath}, it means that further details about the given element and other elements can be defined
	 * so that a very specific path can be created for data that is required.
	 *
	 * @param elementName the name of the element that will be matched.
	 *
	 * @return a {@link BaseHtmlPath} so a path can be defined
	 */
	T match(String elementName);
}
