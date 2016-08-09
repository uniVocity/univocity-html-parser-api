/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import java.io.*;

/**
 * This class allows what content will be read from a {@link HtmlPath} byt the {@link HtmlParser}. This is the final
 * step of creating a path.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlContentReader {

	/**
	 * Used to get the text of a table data's header. For example, given a HTML document like this:
	 *
	 * <p><hr><blockquote><pre><code>
	 *<table>
     *<tr>
	 *	<th>a</th>
	 *	<th>b</th>
	 *	<th>c</th>
     *</tr>
     *<tr>
	 *	<td>first</td>
 	 *	<td>second</td>
	 *	<td>third</td>
	 *</tr>
	 * </table>
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>A technique to get the table headers is to have these parsing rules: </p>
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntity entity = entities.configureEntity("test");
	 * entity.addField("tableHeader").match("table").match("td").getHeadingText();
	 * </p></pre></blockquote><hr>
	 *
	 * <p>When run through the parser, the parser will return each table header (a,b,c) in a separate row</p>
	 */
	void getHeadingText();

	void getHeadingText(int headingRowIndex);

	void getTextAbove();

	void getTextAbove(int numberOfElementsAbove);

	/**
	 * Specifies that the parser will return the text contained within the HTML element defined by the path. For instance,
	 * say a field is added to an entity and the path is set to get text from table data. When the parser runs and hits
	 * &lt;td>goober&lt;/td>, the parser will return the text inside the html tag, which is: "goober".
	 */
	void getText();

	void getText(int numberOfSiblingsToInclude);

	/**
	 * Specifies that the parser will download content contained within the attribute of the HTML element defined by the
	 * path. This is useful for downloading binary files such as images and videos stored as 'src' attributes.
	 *
	 * <p>Content will be downloaded to the directory specified by
	 * {@link com.univocity.api.entity.html.HtmlParserSettings#setDownloadContentDirectory(File)}. If download
	 * directory not set, the content will be stored in a temporary directory.</p>
	 *
	 * @param attributeName the name of the attribute where the value of which will be used to define the content that
	 *                      will be downloaded.
	 */
	void getContentFrom(String attributeName);

	/**
	 * Specifies that the parser will return the text from the HTML that occurs before the HTML element
	 * specified by the path. For instance, given an HTML document that looks like {@code '<div>before<span>hello</span>after</div>'},
	 * a way to get the text just before the span element is:
	 *
	 * <p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("preceding").match("span").getPrecedingText();
	 *</p></pre></blockquote><hr>
	 *
	 * <p>When the parser runs, it will return "before"</p>
	 */
	void getPrecedingText();

	void getPrecedingText(int numberOfSiblingsToInclude);

	/**
	 * Specifies that the parser will return the text from the HTML that occurs after the HTML element
	 * specified by the path. For instance, given an HTML document that looks like {@code '<div>before<span>hello</span>after</div>'},
	 * a way to get the text just after the span element is:
	 *
	 * <p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("following").match("span").getFollowingText();
	 *</p></pre></blockquote><hr>
	 *
	 * <p>When the parser runs, it will return "after"</p>
	 */
	void getFollowingText();

	void getFollowingText(int numberOfSiblingsToInclude);

	/**
	 * Specifies that the parser will return the value defined by the attribute of the HTML element defined by the path.
	 * For example, a field is added to an entity and the path is set to get href values of links (&lt;a>). When the
	 * parser runs and hits &lt;a href="https://www.google.com">a link&lt;/a>, the parser will return
	 * "https://www.google.com".
	 *
	 * @param attributeName the name of the attribute of the element defined by the {@link HtmlPath} where the value of
	 *                      the attribute will be returned by the parser.
	 */
	void getAttribute(String attributeName);
}
