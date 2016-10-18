/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;
import com.univocity.api.entity.html.builders.annotations.*;

import java.io.*;

/**
 * A {@code ContentReader} defines what content will be read from a {@link FieldPath} by the {@link HtmlParser}. This is the final
 * step of creating a path to a field created for an entity (using {@link HtmlEntitySettings}).
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface ContentReader {
	/**
	 * Used to get the text of a table header above a matched element. For example, given a HTML document like this:
	 *
	 * <p><hr><blockquote><pre><code>
	 * <table>
	 * <tr>
	 * 	<th>a</th>
	 * 	<th>b</th>
	 * 	<th>c</th>
	 * </tr>
	 * <tr>
	 * 	<td>first</td>
	 * 	<td>second</td>
	 * 	<td>third</td>
	 * </tr>
	 * </table>
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>To capture the header of column with text "second", a matching rule can be defined as:</p>
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntity entity = entities.configureEntity("test");
	 * entity.addField("tableHeader").match("table").match("td").withText("second").getHeadingText();
	 * </p></pre></blockquote><hr>
	 *
	 * <p>A technique to get all table headers is to have these matching rules: </p>
	 *
	 * <p><hr><blockquote><pre>
	 * entity.addField("tableHeader").match("table").match("td").getHeadingText();
	 * </p></pre></blockquote><hr>
	 *
	 * <p>The parser will return each table header ({@code a},{@code b} and {@code c})
	 * in a separate row of entity {@code test}</p>
	 */
	@Matcher(type = Matcher.Type.TABLE)
	void getHeadingText();

	/**
	 * Captures the text of a row above a matched element, where the position of that row is at a fixed position,
	 * relative to the start of the table. For example, given this HTML document:
	 *
	 * <p><hr><blockquote><pre><code>
	 * <table>
	 * 	<tr> <td>Top header</td> </tr>
	 * 	<tr> <td>Middle Header</td></tr>
	 * 	<tr> <td>Bottom Header</td></tr>
	 * 	<tr> <td>A boring row</td></tr>
	 * </table>
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>A technique of getting the text of the 3rd header is:</p>
	 *
	 * <p><hr><blockquote><pre><code>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntity entity = entities.configureEntity("test");
	 * entity.addField("fieldName").match("td").withText("A boring row").getHeadingText(3);
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>The parser will return "Bottom Header" as it is at the third row of the table.</p>
	 *
	 * @param headingRowIndex index of the row whose text at the column above the matched element
	 *                        will be returned from
	 */
	@Matcher(type = Matcher.Type.TABLE)
	void getHeadingText(int headingRowIndex);

	/**
	 * Specifies that the parser will return the text contained in the HTML element above the HTML element defined by
	 * the path. Only applicable to tables. For example, given the HTML document of:
	 *
	 * <p><hr><blockquote><pre><code>
	 * <table>
	 * <tr> <th>heading1</th> <th>heading2</th> </tr>
	 * <tr> <td>one</td> <td>two</td> </tr>
	 * <tr> <td>a</td> <td>ab</td> </tr>
	 * </table>
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>One technique to return the second row would be: </p>
	 *
	 * <p><hr><blockquote><pre><code>
	 * entity.addField("tableHeader").match("td").withText("a*").getTextAbove();
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>Which describes: match 'td' elements containing text starting with 'a', then get the text from the element at
	 * the same column in the row above.
	 *
	 * When the parser runs, it will return two records with field "tableHeader" populated with values
	 * 'one' and 'two', respectively.</p>
	 */
	@Matcher(type = Matcher.Type.TABLE)
	void getTextAbove();

	/**
	 * Specifies that the parser will return the text contained in the HTML element at a given distance above
	 * a matched element. Only applicable to tables. For example, given the HTML document of:
	 *
	 * <p><hr><blockquote><pre><code>
	 * <table>
	 * <tr> <th>Name</th> <th>Number</th> </tr>
	 * <tr> <td>Adam</td> <td>143</td> </tr>
	 * <tr> <td>Bob</td> <td>222</td> </tr>
	 * <tr> <td>Charlie</td> <td>973</td> </tr>
	 * </p></pre></blockquote><hr></code>
	 * </table>
	 *
	 * <p>A technique to get the text from the second row's Number field is:</p>
	 *
	 * <p><hr><blockquote><pre><code>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntity entity = entities.configureEntity("test");
	 * entity.addField("text").match("td").precededBy("td").withText("Charlie").getTextAbove(2);
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>The matching rule, described in English, is: match the td that has a td with text "Charlie" before it.
	 * This targets the td with '973' in it. Then, the rule states: get the text from the element that is 2 rows above.
	 * When the parser runs, it will return '143'.</p>
	 *
	 * @param numberOfRowsAbove the number of rows above a matched element whose text, at the corresponding column,
	 *                          will be returned.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	void getTextAbove(int numberOfRowsAbove);


	/**
	 * Specifies that the parser will return the text in the table row above the current element that contains the
	 * given text. Only applicable to tables. It is best used for when:
	 * <ol>
	 * <li>Text needs to be parsed from a row above</li>
	 * <li>The number of rows above is variable or not known <strong>AND</strong>  </li>
	 * <li>There is a known set of possible strings that the above element can contain.  </li>
	 *
	 * </ol>
	 * For example, given these mismatching tables in a HTML document:
	 *
	 * <p><hr><blockquote><pre><code>
	 * <table>
	 * 	<tr> <th>Gender</th> <td>Male</td>  <td>Female</td> </tr>
	 * 	<tr> <th>Age</th> <td>41</td>  <td>36</td></tr>
	 * 	<tr> <th>Name</th>  <td>Steven</td> <td>Mandy</td> </tr>
	 * </table>
	 * <table>
	 * 	 <tr> <th>Age</th> <td>38</td>  <td>24</td></tr>
	 * 	 <tr> <th>Gender</th> <td>Female</td>  <td>Male</td> </tr>
	 * 	 <tr> <th>Name</th>  <td>Sarah</td> <td>Bob</td> </tr>
	 * </table>
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>A technique to get the name and gender of each person in both tables is: </p>
	 *
	 * <p><hr><blockquote><pre><code>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntity entity = entities.configureEntity("test");
	 *
	 * //Creates path to both "Name" th elements
	 * PartialPath path = entity.newPath().match("th").withText("Name");
	 *
	 *  //Matches each td in name row and gets the text
	 * path.addField("name").match("td").getText();
	 *
	 * //Matches td in above row that contains "Male" or "Female" and gets the text
	 * path.addField("gender").match("td").getTextAbove("Male", "Female");
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>The code snippet above creates a path to the "Name" th element. It then creates two fields. Both fields
	 * matches every td element next to the "Name" element. The first field simply returns the text inside the td element.
	 * The second field searches the td's above the matched td until it finds "Male" <strong>OR</strong> "Female" and returns it.</p>
	 *
	 * <p>The output that will return is:</p>
	 *
	 *
	 * <p><hr><blockquote><pre><code>
	 * [Steven, Male]
	 * [Mandy, Female]
	 * [Sarah, Female]
	 * [Bob, Male]
	 * </p></pre></blockquote><hr></code>
	 *
	 * @param firstAlternative  The text that the parser will attempt to match in a row above
	 * @param otherAlternatives Optional extra strings that the parser will look for in an above row
	 */
	@Matcher(type = Matcher.Type.TABLE)
	void getTextAbove(String firstAlternative, String... otherAlternatives);

	/**
	 * Specifies that the parser will return the text contained within the HTML element defined by the path. For instance,
	 * say a field is added to an entity and the path is set to get text from a {@code <td>} tag. When the parser runs and hits
	 * {@code <td>goober<td>}, the parser will return the text inside the html tag, which is: "goober".
	 *
	 * <p>When get text is applied to an element that has children elements, each with their own text. It will combine
	 * the text into one string, with a space separating each element. An example can be shown by viewing the HTML
	 * document below:</p>
	 *
	 * <p><hr><blockquote><pre><code>
	 * <div>
	 * 	<h1>The Title</h1>
	 * 	<p>Text</p>
	 * 	<p>More Words</p>
	 * </div>
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>If getText() is ran on the {@code <div>} element, the parer return "The Title Text More Words".</p>
	 */
	void getText();

	/**
	 * Specifies that the parser will return the text contained within the HTML element defined by the path <strong>plus</strong>
	 * the text in the specified amount of <strong>following</strong> siblings. An example of using this can be shown by the
	 * following HTML:
	 *
	 * <p><hr><blockquote><pre><code>
	 * {@code <div>
	 *     <h1>title</h1>
	 *     <p>Cool Text</p>
	 *     <p>More Cool Text</p>
	 *     <footer>feet</footer>
	 * </div> }
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>A technique to get the text of both the p elements is:</p>
	 *
	 * <p><hr><blockquote><pre><code>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntity entity = entities.configureEntity("test");
	 * entity.addField("text").match("p").precededBy("h1").getText(1);
	 * </p></pre></blockquote><hr></code>
	 *
	 * <p>What this code snippet does, is it creates a path to the first p element (as the element is preceded by a h1
	 * element), then the parser gets text of the first p element and it's following sibling, which is the second p element.
	 * The footer text is ignored as it is 2 siblings away from the targeted p element.
	 * </p>
	 *
	 * <p>Setting the number of siblings to <= 0 is the equivalent of using {@link #getText()}.</p>
	 *
	 * @param numberOfSiblingsToInclude the number of following siblings from current element that the text will be returned from
	 */
	void getText(int numberOfSiblingsToInclude);

	/**
	 * Specifies that the parser will download content contained within the attribute of the HTML element defined by the
	 * path. This is useful for downloading binary files such as images and videos stored as 'src' attributes.
	 *
	 * <p>Content will be downloaded to the directory specified by
	 * {@link HtmlParserSettings#setDownloadContentDirectory(File)}. If download directory not set, the content will be
	 * stored in a temporary directory.</p>
	 *
	 * @param attributeName the name of the attribute where the value of which will be used to define the content that
	 *                      will be downloaded.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	void getContentFrom(String attributeName);

	/**
	 * Specifies that the parser will return the text from the HTML that occurs before the HTML element
	 * specified by the path. For instance, given an HTML document that looks like {@code '<div>before<span>hello</span>after</div>'},
	 * a way to get the text just before the span element is:
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntity entity = entities.configureEntity("test");
	 * entity.addField("preceding").match("span").getPrecedingText();
	 * </p></pre></blockquote><hr>
	 *
	 * <p>When the parser runs, it will return "before"</p>
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	void getPrecedingText();

	/**
	 * Gets the text from the specified number of HTML elements placed before the element that is described in the path.
	 * For example, given this HTML: {@code <h1>header</h1><p>more</p><p>text</p><footer>feet</footer>} a way to get
	 * the text of everything before the footer and after the h1 is:
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntity entity = entities.configureEntity("test");
	 * entity.addField("fieldName").match("footer").getPrecedingText(2);
	 * </p></pre></blockquote><hr>
	 *
	 * <p>This will return "textmore" as the first p element is the first sibling, and the second p element is the second
	 * sibling. As the h1 element is third sibling, it is ignored by the parser. </p>
	 *
	 * @param numberOfSiblingsToInclude the number of elements preceding the element defined in the path that the text will
	 *                                  be returned from
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	void getPrecedingText(int numberOfSiblingsToInclude);

	/**
	 * Gets the text from the HTML element that is placed directly after the HTML element specified by the path. For
	 * instance, given an HTML document that looks like {@code '<div>before<span>hello</span>after<p>text</p></div>'},
	 * a way to get the text just after the span element is:
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntity entity = entities.configureEntity("test");
	 * entity.addField("following").match("span").getFollowingText();
	 * </p></pre></blockquote><hr>
	 *
	 * <p>When the parser runs, it will return "after".</p>
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	void getFollowingText();

	/**
	 * Gets the text from the specified number of HTMl elements following the HTML element described in the path. For
	 * instance, given a HTML document looking like: {@code <h1>header</h1><p>text</p><footer>feet</footer><p>annoying</p>}.
	 * A technique to get the text of everything after the h1 element and before the last p element would be to write:
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntity entity = entities.configureEntity("test");
	 * entity.addField("fieldName").match("h1").getFollowingText(2);
	 * </p></pre></blockquote><hr>
	 *
	 * <p>The parser will return "textfeet" as the first p element is the first sibling and the footer element is the
	 * second sibling. As the last p element is the 3rd sibling, it lies outside the range of the matching rules and will
	 * be ignored.</p>
	 *
	 * @param numberOfSiblingsToInclude the number of elements following the element defined in the path that the text will
	 *                                  be returned from
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	void getFollowingText(int numberOfSiblingsToInclude);

	/**
	 * Specifies that the parser will return the value defined by the attribute of the HTML element defined by the path.
	 * For example, a field is added to an entity and the path is set to get href values of links (&lt;a>). When the
	 * parser runs and hits &lt;a href="https://www.google.com">a link&lt;/a>, the parser will return
	 * "https://www.google.com".
	 *
	 * @param attributeName the name of the attribute of the element defined by the {@link FieldPath} where the value of
	 *                      the attribute will be returned by the parser.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	void getAttribute(String attributeName);
}
