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

	/**
	 * Creates a path to the HTML element that has the specified element placed after it. For example, given this
	 * simple HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<div>
	 *<strong>before</strong><strong>after</strong>
	 *</div>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>One technique to get the text of the first'strong' element is:</p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("followed").match("strong").followedBy("strong").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>The matching rules in plain english are: get the text of a 'strong' element that has a 'strong' element placed
	 * after it. When the parsing process runs, it will return 'before'</p>
	 *
	 * @param elementName the name of the HTML element
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	T followedBy(String elementName);

	T followedBy(String elementName, int distance);

	T followedImmediatelyBy(String elementName);

	/**
	 * Creates a path to the HTML element that has the specified element placed before it. For example, given this
	 * simple HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<div>
	 *<strong>before</strong><strong>after</strong>
	 *</div>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>One technique to get the text of the second 'strong' element is:</p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("preceded").match("strong").precededBy("strong").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>The matching rules in plain english are: get the text of a 'strong' element that has a 'strong' element placed
	 * before it. When the parsing process runs, it will return 'after'</p>
	 *
	 * @param elementName the name of the HTML element
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	T precededBy(String elementName);

	T precededBy(String elementName, int distance);

	T precededImmediatelyBy(String elementName);

	T childOf(String elementName);

	T containedBy(String elementName);

	T containedBy(String elementName, int depthLimit);

	/**
	 * Creates a path to the HTML element that is under the specified header element of a table. For example, given
	 * a simple HTML document of:
	 *
	 *<p><hr><blockquote><pre>
	 *<table>
	 *<tr> <th><span>heading1</span></th> <th>heading2</th> </tr>
	 *<tr> <td>one</td> <td>two</td> </tr>
	 *<tr> <td>lorem</td> <td>ispum</td> </tr>
	 *</table>
	 *</p></blockquote></pre><hr>
	 *
	 *<p> One technique to only return the text under 'heading1' would be: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("underTableHeader").match("td").underHeader("span").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>When the parser runs, it will return 'one' and 'lorem'. If all data under the headers needed to be returned,
	 * only a simple change to the matching rules needs to be done: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *entity.addField("underTableHeader").match("td").underHeader("th").getText();
	 *</p></blockquote></pre><hr>'
	 *
	 * <p>Which will result in all the text apart from the table headers being returned.</p>
	 *
	 * @param headerElementName the header element of a table that will be matched
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	T underHeader(String headerElementName);

	T under(String elementName);

	T parentOf(String elementName);

	T containing(String... pathOfElementNames);

	T containing(String elementName);

	T containing(String elementName, int depthLimit);

	/**
	 * Creates a path to a HTML element that contains the specified text. Is case insensitive. It also supports the special characters of
	 * '*' and '?'.
	 *
	 * <p>* is used by the parser to match any characters. For instance, there is a short HTML document of '{@code <div> <span>abcdef</span> <span>kettle</span> </div>}'.
	 * One technique to match the first span would be to write:
	 * </p>
	 *
	 * <p><hr><blockquote><pre>
	 * path.match("span").withText("a*").getText();
	 * </p></blockquote></pre><hr>
	 *
	 * <p>
	 * The meaning of setting the matching text to 'a*' means 'match the element with text starting with 'a' with any
	 * characters after it'. Alternatives to match the text could be: '*f' (text ending with f) and 'a*f' (text starting
	 * with a and ending with f)
	 * </p>
	 *
	 * <p>? is used by the parser to match any one character. Using this simple HTML document('{@code <div> <span>abcdef</span> <span>abc</span> </div>}'),
	 * we can set the matching rules as:
	 * </p>
	 *
	 * <p><hr><blockquote><pre>
	 * path.match("span").withText("a?????").getText();
	 *</p></blockquote></pre><hr>
	 *
	 * <p>Which describes 'match the span element that has text that starts with 'a' and has 5 characters after the 'a'.
	 * Alternatives to match the text could be '?????f' (any 5 characters then a f) and 'ab??ef' ('a', followed by 'b',
	 * followed by any two characters, followed by 'e' and 'f') </p>
	 *
	 * @param textContent the string that will be matched, accounting for wildcard elements
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	T withText(String textContent);

	/**
	 * Like {@link #withText(String)} but case sensitive. Creates a path to a HTML element that contains the specified
	 * text. Also supports the special characters of '*' and '?'.
	 *
 	 * @param textContent the string that will be matched, accounting for wildcard elements
	 *
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	T withTextMatchCase(String textContent);

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
