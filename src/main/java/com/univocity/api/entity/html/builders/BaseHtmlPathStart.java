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
	 * Used to specify what HTML element the parser will retrieve data from. This is the first step in creating a {@link HtmlPath}
	 * for a field. For example, to get the text of all span elements on a HTML document, one would have to simply write:
	 *
	 *<p><hr><blockquote><pre>
	 *	//Set up
	 *HtmlEntityList htmlEntityList = new HtmlEntityList();
	 *HtmlParserSettings settings = new HtmlParserSettings(htmlEntityList);
	 *
	 *	//Matching Rule
	 *htmlEntityList.configureEntity("test").addField("allSpanElements").match("span").getText();
	 *</p></blockquote></pre><hr>
	 *
	 * <p>When the parser runs, it will match every span element on the HTML document run on and return it.</p>
	 *
	 * <p>Multiple match rules can be chained together which creates a more specific path. When the first match statement
	 * is found in a document it will look for the next match element that is contained in <strong>or</strong> a sibling of
	 * the matched element. An example can be shown by first showing a simple HTML document below: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *<div>
	 *	<h1>Bad Title</h1>
	 *</div>
	 *<article>
	 *	<h1>Good Title</h1>
	 *</article>
	 *<article>
	 *	<h1>Also Good Title</h1>
	 *</article>
	 *</p></blockquote></pre><hr>
	 *
	 * <p>A technique to only get the text from header elements inside of articles is:</p>
	 *
	 *<p><hr><blockquote><pre>
	 *htmlEntityList.configureEntity("test").addField("headers").match("article").match("h1").getText();
	 *</p></blockquote></pre><hr>
	 *
	 * <p>The parser will return "Good Title" and "Also Good Title". This is because the matching rules set will look for
	 * {@code <article>} elements and then {@code <h1>} inside these {@code <article>} elements or next (sibling of) the
	 * {@code <article>} elements.</p>
	 *
	 * <p>As the method returns a {@link BaseHtmlPath}, it means that further details about the given element and other elements
	 * can be defined so that a very specific path can be created for data that is required. </p>
	 *
	 * @param elementName the name of the element that will be matched.
	 *
	 * @return a {@link BaseHtmlPath} so a path can be defined
	 */
	T match(String elementName);
}
