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

	/**
	 * Gets the text of the table heading that is at the given index. For example, given this HTML document:
	 *
	 * <p><hr><blockquote><pre><code>
	 *<table>
	 *	<tr> <th>Top header</th> </tr>
	 *	<tr> <th>Middle Header</th></tr>
	 *	<tr> <th>Bottom Header</th></tr>
	 *	<tr> <td>A boring row</td></tr>
	 </table>
	 * </p></pre></blockquote><hr></code>
	 *
	 *<p>A technique of getting the text of the 2nd header is:</p>
	 *
	 * <p><hr><blockquote><pre><code>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("fieldName").match("td").getHeadingText(2);
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>The parser will return "Middle Header" as it is at the second heading index.</p>
	 *
	 * @param headingRowIndex the index of the row that the text will be returned from
	 */
	void getHeadingText(int headingRowIndex);

	/**
	 * Specifies that the parser will return the text contained in the HTML element above the HTML element defined by
	 * the path. For example, given the HTML document of:
	 *
	 * <p><hr><blockquote><pre><code>
	 *<table>
	 *<tr> <th>heading1</th> <th>heading2</th> </tr>
	 *<tr> <td>one</td> <td>two</td> </tr>
	 *<tr> <td>a</td> <td>ab</td> </tr>
	 *</table>
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>One technique to return the second row would be: </p>
	 *
	 * <<p><hr><blockquote><pre><code>
	 * entity.addField("tableHeader").match("td").withText("a*").getTextAbove();
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>Which describes: get the text above 'td' elements that contains text starting with 'a'. When the parser runs,
	 * it will return  'one' and 'two'.</p>
	 */
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

	/**
	 * Gets the text from the specified number of HTML elements placed before the element that is described in the path.
	 * For example, given this HTML: {@code <h1>header</h1><p>more</p><p>text</p><footer>feet</footer>} a way to get
	 * the text of everything before the footer and after the h1 is:
	 *
	 * <p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("fieldName").match("footer").getPrecedingText(2);
	 *</p></pre></blockquote><hr>
	 *
	 *<p>This will return "textmore" as the first p element is the first sibling, and the second p element is the second
	 * sibling. As the h1 element is third sibling, it is ignored by the parser. </p>
	 *
	 * @param numberOfSiblingsToInclude the number of elements preceding the element defined in the path that the text will
	 *                                  be returned from
	 */
	void getPrecedingText(int numberOfSiblingsToInclude);

	/**
	 * Gets the text from the HTML element that is placed directly after the HTML element specified by the path. For
	 * instance, given an HTML document that looks like {@code '<div>before<span>hello</span>after<p>text</p></div>'},
	 * a way to get the text just after the span element is:
	 *
	 * <p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("following").match("span").getFollowingText();
	 *</p></pre></blockquote><hr>
	 *
	 * <p>When the parser runs, it will return "after".</p>
	 */
	void getFollowingText();

	/**
	 * Gets the text from the specified number of HTMl elements following the HTML element described in the path. For
	 * instance, given a HTML document looking like: {@code <h1>header</h1><p>text</p><footer>feet</footer><p>annoying</p>}.
	 * A technique to get the text of everything after the h1 element and before the last p element would be to write:
	 *
	 * <p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("fieldName").match("h1").getFollowingText(2);
	 *</p></pre></blockquote><hr>
	 *
	 *<p>The parser will return "textfeet" as the first p element is the first sibling and the footer element is the
	 * second sibling. As the last p element is the 3rd sibling, it lies outside the range of the matching rules and will
	 * be ignored.</p>
	 *
	 * @param numberOfSiblingsToInclude the number of elements following the element defined in the path that the text will
	 *                                  be returned from
	 */
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
