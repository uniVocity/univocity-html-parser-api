/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * Provides the start of a {@link ElementFilter}. Essentially, the {@code ElementFilterStart} defines which HTML element
 * should be matched when the {@link com.univocity.api.entity.html.HtmlParser} is run. When the parser processes an
 * input HTML, it will run all filtering rules applied over the elements whose tag names match with the one provided
 * by the {@link #match(String)} method. Values from a matched element are extracted using the rules defined by
 * a {@link ContentReader}
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see FieldPath
 * @see PartialPath
 * @see ElementFilter
 */
public interface ElementFilterStart<T extends ElementFilter<T>> {
	/**
	 * Used to specify what HTML element the parser must match, filter (using the rules available from {@link ElementFilter})
	 * and/or retrieve data from (using the options provided by {@link ContentReader}. This is the first step in creating
	 * a {@link FieldPath} for a field. For example, to get the text of all span elements on a HTML document, one would
	 * have to simply write:
	 *
	 * <hr>{@code
	 * //Set up
	 * HtmlEntityList htmlEntityList = new HtmlEntityList();
	 *
	 * //Matching Rule
	 * htmlEntityList.configureEntity("test").addField("allSpanElements").match("span").getText();
	 * }<hr>
	 *
	 * <p>When the parser runs, it will match every span element found on the HTML document and return their text content.</p>
	 *
	 * <p>Multiple matching rules can be chained together which creates a more specific path. In a sequence of elements to
	 * be matched is defined, the the parser will look for the first matched element. If it is found it will then look
	 * for the next element to be matched, that must be contained by the first <strong>or</strong> be one of the following
	 * sibling of the matched element. An example can be shown by first showing a simple HTML document below: </p>
	 *
	 * <hr>{@code
	 * <div>
	 * <h1>Bad Title</h1>
	 * </div>
	 * <article>
	 * <h1>Good Title</h1>
	 * </article>
	 * <article>
	 * <h1>Also Good Title</h1>
	 * </article>
	 * }<hr>
	 *
	 * <p>A technique to only get the text from header elements inside of articles is:</p>
	 *
	 * <hr>{@code
	 * htmlEntityList.configureEntity("test").addField("headers")
	 * .match("article").match("h1").getText();
	 * }<hr>
	 *
	 * <p>The parser will return "Good Title" and "Also Good Title". This is because the matching rules set will look for
	 * {@code <article>} elements and then {@code <h1>} inside these {@code <article>} elements (or the next sibling of) the
	 * {@code <article>} elements.</p>
	 *
	 * <p>As this method returns an {@link ElementFilter}, the user can provide further rules about the given element,
	 * or other associated elements. Very specific paths can be created to capture data from virtually anywhere in a
	 * HTML document. </p>
	 *
	 * @param tagName tag name of the element that will be matched.
	 *
	 * @return a {@link ElementFilter} so a that filtering rules over HTML elements with the given tag name can be defined
	 */
	T match(String tagName);

	T match(String tagName, int occurrence);

	T matchFirst(String tagName);

	T matchLast(String tagName);

	T select(String cssQuery);


}
