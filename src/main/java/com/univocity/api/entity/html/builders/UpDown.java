/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * The `UpDown` interface groups rules that match elements under or above the current element matched by the parser.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface UpDown<T extends UpDown<T>> {

	/**
	 * Establishes that the parser should look 'up' the page for the given element. The parser will look for an
	 * element that appears before the current matched element (up in the hierarchy).
	 *
	 * @param elementName the tag name of the element
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	T upTo(String elementName);

	/**
	 * Establishes that the parser should look 'up' the page for the given element that is in the same table column as
	 * the previously matched element. Will only work if the previously matched element is a `<td>` or `<th>` element or
	 * exists inside a `<td>` or `<th>`.
	 *
	 * @param elementName the tag name of the element
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	T upToHeader(String elementName);

	/**
	 * Establishes that the parser should look 'down' the page for the given element. The parser will look for an
	 * element that appears after the current matched element (down in the hierarchy).
	 *
	 * @param elementName the tag name of the element
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	T downTo(String elementName);

	/**
	 * Establishes that the parser should look 'down' the page for the given element that is in the same table column as
	 * the previously matched element. Will only work if the previously matched element is a `<td>` or `<th>` element or
	 * exists inside a `<td>` or `<th>`.
	 *
	 * @param elementName the tag name of the element
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	T downToFooter(String elementName);

}
