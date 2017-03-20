/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;
import com.univocity.api.entity.html.builders.annotations.*;

/**
 * A {@code ContentReader} defines what content will be read from the last element matched in a {@link FieldPath} by
 * the {@link HtmlParser}. This is the final step of creating a path to a field created for an entity
 * (using {@link HtmlEntitySettings}).
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface ContentReader<T extends ContentHandler> {

	/**
	 * Used to get the text of a table header above a matched element. For example, given a HTML document like this:
	 *
	 * <hr>{@code
	 * <table>
	 * <tr>
	 * <th>a</th>
	 * <th>b</th>
	 * <th>c</th>
	 * </tr>
	 * <tr>
	 * <td>first</td>
	 * <td>second</td>
	 * <td>third</td>
	 * </tr>
	 * </table>
	 * }<hr>
	 *
	 * <p>To capture the header of column with text "second", a matching rule can be defined as:</p>
	 *
	 * <hr>
	 * {@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("tableHeader").match("table").match("td").withText("second").getHeadingText();
	 * }
	 * <hr>
	 *
	 * <p>A technique to get all table headers is to have these matching rules: </p>
	 *
	 * <hr>{@code
	 * entity.addField("tableHeader").match("table").match("td").getHeadingText();
	 * }<hr>
	 *
	 * <p>The parser will return each table header ({@code a},{@code b} and {@code c})
	 * in a separate row of entity {@code test}</p>
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T getHeadingText();

	/**
	 * Captures the text of a row above a matched element, where the position of that row is at a fixed position,
	 * relative to the start of the table. For example, given this HTML document:
	 *
	 * <hr>{@code
	 * <table>
	 * <tr> <td>Top header</td> </tr>
	 * <tr> <td>Middle Header</td></tr>
	 * <tr> <td>Bottom Header</td></tr>
	 * <tr> <td>A boring row</td></tr>
	 * </table>
	 * }<hr>
	 *
	 * <p>A technique of getting the text of the 3rd header is:</p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("fieldName").match("td").withText("A boring row").getHeadingText(3);
	 * }<hr>
	 *
	 * <p>The parser will return "Bottom Header" as it is at the third row of the table.</p>
	 *
	 * @param headingRowIndex index of the row whose text at the column above the matched element
	 *                        will be returned from
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T getHeadingText(int headingRowIndex);

	/**
	 * Specifies that the parser will return the text contained in the HTML element above the HTML element defined by
	 * the path. Only applicable to tables. For example, given the HTML document of:
	 *
	 * <hr>{@code
	 * <table>
	 * <tr> <th>heading1</th> <th>heading2</th> </tr>
	 * <tr> <td>one</td> <td>two</td> </tr>
	 * <tr> <td>a</td> <td>ab</td> </tr>
	 * </table>
	 * }<hr>
	 *
	 * <p>One technique to return the second row would be: </p>
	 *
	 * <hr>{@code
	 * entity.addField("tableHeader").match("td").withText("a*").getTextAbove();
	 * }<hr>
	 *
	 * <p>Which describes: match 'td' elements containing text starting with 'a', then get the text from the element at
	 * the same column in the row above.
	 *
	 * When the parser runs, it will return two records with field "tableHeader" populated with values
	 * 'one' and 'two', respectively.</p>
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T getTextAbove();

	/**
	 * Specifies that the parser will return the text contained in the HTML element at a given distance above
	 * a matched element. Only applicable to tables. For example, given the HTML document of:
	 *
	 * <hr>{@code
	 * <table>
	 * <tr> <th>Name</th> <th>Number</th> </tr>
	 * <tr> <td>Adam</td> <td>143</td> </tr>
	 * <tr> <td>Bob</td> <td>222</td> </tr>
	 * <tr> <td>Charlie</td> <td>973</td> </tr>
	 * </table>
	 * }<hr>
	 *
	 *
	 * <p>A technique to get the text from the second row's Number field is:</p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("text").match("td").precededBy("td").withText("Charlie").getTextAbove(2);
	 * }<hr>
	 *
	 * <p>The matching rule, described in English, is: match the td that has a td with text "Charlie" before it.
	 * This targets the td with '973' in it. Then, the rule states: get the text from the element that is 2 rows above.
	 * When the parser runs, it will return '143'.</p>
	 *
	 * @param numberOfRowsAbove the number of rows above a matched element whose text, at the corresponding column,
	 *                          will be returned.
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T getTextAbove(int numberOfRowsAbove);


	/**
	 * Specifies that the parser will return the content of a row, given it contains some expected text,
	 * above a matched element. Only applicable to tables. It is best used for when:
	 * <ol>
	 * <li>Text needs to be parsed from the column of some row above the matched element</li>
	 * <li>The number of rows above is variable or not known <strong>AND</strong>  </li>
	 * <li>There is a known set of possible strings that one of the rows above the matched element can contain.</li>
	 *
	 * </ol>
	 * For example, given these mismatching tables in a HTML document:
	 *
	 * <hr>{@code
	 * <table>
	 * <tr> <th>Gender</th> <td>Male</td>  <td>Female</td> </tr>
	 * <tr> <th>Age</th> <td>41</td>  <td>36</td></tr>
	 * <tr> <th>Name</th>  <td>Steven</td> <td>Mandy</td> </tr>
	 * </table>
	 * <table>
	 * <tr> <th>Age</th> <td>38</td>  <td>24</td></tr>
	 * <tr> <th>Gender</th> <td>Female</td>  <td>Male</td> </tr>
	 * <tr> <th>Name</th>  <td>Sarah</td> <td>Bob</td> </tr>
	 * </table>
	 * }<hr>
	 *
	 * <p>A technique to get the name and gender of each person in both tables is: </p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * //Creates path to any "Name" th elements
	 * PartialPath path = entity.newPath().match("th").withText("Name");
	 *
	 * //Matches each td in name row and gets the text
	 * path.addField("name").match("td").getText();
	 *
	 * //Matches td in above row that contains "Male" or "Female" and gets the text
	 * path.addField("gender").match("td").getTextAbove("Male", "Female");
	 * }<hr>
	 *
	 * <p>The code snippet above creates a path that matches th elements with text = "Name".
	 * From this path, it creates two fields: "name" and "gender", so each field starts matching every td element next
	 * to a "Name" th element.
	 * The "name" field simply matches the next td element returns the text inside it.
	 * The "gender" field matches the next td element, and then looks for rows above it that contain
	 * "Male" <strong>OR</strong> "Female" in the corresponding column, returning its text.</p>
	 *
	 * <p>The output that will return is:</p>
	 *
	 * <hr>{@code
	 * [Steven, Male]
	 * [Mandy, Female]
	 * [Sarah, Female]
	 * [Bob, Male]
	 * }<hr>
	 *
	 * @param firstAlternative  The text that the parser will attempt to find and grab in a row above the matched element
	 * @param otherAlternatives Optional extra strings to look for.
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T getTextAbove(String firstAlternative, String... otherAlternatives);

	/**
	 * Specifies that the parser will return the text contained within the HTML element defined by the path. For instance,
	 * say a field is added to an entity and the path is set to get text from a {@code <td>} tag. When the parser runs and hits
	 * {@code <td>goober<td>}, the parser will return the text inside the html tag, which is: "goober".
	 *
	 * <p>When this method is applied to an element that has children elements, each with their own text, it will combine
	 * everything into one string, with a space separating the text of each element. For example:</p>
	 *
	 * <hr>{@code
	 * <div>
	 * <h1>The Title</h1>
	 * <p>Text</p>
	 * <p>More Words</p>
	 * </div>
	 * }<hr>
	 *
	 * The following code should provide the "The Title Text More Words" {@code String} for an "allText" field when
	 * the parser is run.
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T getText();

	/**
	 * Specifies that the parser will return the text contained within the HTML elements matched by the path <strong>plus</strong>
	 * the text in the specified amount of <strong>following</strong> siblings. For example:
	 *
	 * <hr>{@code
	 * {@code <div>
	 * <h1>title</h1>
	 * <p>Cool Text</p>
	 * <p>More Cool Text</p>
	 * <footer>feet</footer>
	 * </div> }
	 * }<hr>
	 *
	 * <p>A technique to get the text of both the p elements is:</p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("text").match("p").precededBy("h1").getText(1);
	 * }<hr>
	 *
	 * <p>This code snippet creates a path to the first p element (the one preceded by a h1 element),
	 * then the parser gets the text of this first p element and includes the text of <strong>one</strong> next sibling,
	 * which is the second p element. The footer text is ignored as it is 2 siblings away from the targeted p element.
	 * </p>
	 *
	 * <p>Setting the number of siblings to {@code <= 0} is the equivalent of using {@link #getText()}.</p>
	 *
	 * @param numberOfSiblingsToInclude number of following siblings from the matched element whose text should be returned
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T getText(int numberOfSiblingsToInclude);


	/**
	 * Specifies that the parser will return the text from the HTML that occurs only in the HTML element specified by the path.
	 * For instance given an HTML document that look like {@code <p>Text <strong>not</strong> only in the p element</p>},
	 * a way to get only the text directly inside of the p element is:
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("ownText").match("p").getOwnText();
	 * }
	 * <hr>
	 *
	 * <p>When the parser runs, it will return "Text only in the p element"</p>
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents
	 * a path to a remote resource.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T getOwnText();

	/**
	 * Specifies that the parser will return the text from the HTML that occurs before the HTML element
	 * specified by the path. For instance, given an HTML document that looks like {@code '<div>before<span>hello</span>after</div>'},
	 * a way to get the text just before the span element is:
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("preceding").match("span").getPrecedingText();
	 * }<hr>
	 *
	 * <p>When the parser runs, it will return "before"</p>
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T getPrecedingText();

	/**
	 * Gets the text from the specified number of HTML elements placed before the element that is matched by the path.
	 * For example, given this HTML: {@code <h1>header</h1><p>more</p><p>text</p><footer>feet</footer>} a way to get
	 * the text of everything before the footer and after the h1 is:
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("fieldName").match("footer").getPrecedingText(2);
	 * }<hr>
	 *
	 * <p>This will return "more text" as the first p element is the first sibling, and the second p element is the second
	 * sibling. As the h1 element is third sibling, it is ignored by the parser. </p>
	 *
	 * @param numberOfSiblingsToInclude the number of elements preceding the element defined in the path that the text will
	 *                                  be returned from
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T getPrecedingText(int numberOfSiblingsToInclude);

	/**
	 * Gets the text from the HTML element that is placed directly after the HTML elements matched by the path. For
	 * instance, given an HTML document that looks like {@code '<div>before<span>hello</span>after<p>text</p></div>'},
	 * a way to get the text just after the span element is:
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("following").match("span").getFollowingText();
	 * }<hr>
	 *
	 * <p>When the parser runs, it will return "after".</p>
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T getFollowingText();

	/**
	 * Gets the text from the specified number of HTMl elements following the HTML element described in the path. For
	 * instance, given a HTML document looking like: {@code <h1>header</h1><p>text</p><footer>feet</footer><p>annoying</p>}.
	 * A technique to get the text of everything after the h1 element and before the last p element would be to write:
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("fieldName").match("h1").getFollowingText(2);
	 * }<hr>
	 *
	 * <p>The parser will return "text feet" as the first p element is the first sibling and the footer element is the
	 * second sibling. As the last p element is the 3rd sibling, it lies outside the range of the matching rules and will
	 * be ignored.</p>
	 *
	 * @param numberOfSiblingsToInclude the number of elements following the element defined in the path that the text will
	 *                                  be returned from
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T getFollowingText(int numberOfSiblingsToInclude);

	/**
	 * Specifies that the parser will return the value defined by the attribute of the HTML elements defined by the path.
	 * For example, a field is added to an entity and the path is set to get href values of links ({@code <a>}).
	 *
	 * * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("link").match("a").getAttribute("href");
	 * }<hr>
	 *
	 * When the parser runs and hits {@code <a href="https://www.google.com">a link</a>}, it will return
	 * "https://www.google.com".
	 *
	 * @param attributeName the name of the attribute of the element defined by the {@link FieldPath} where the value of
	 *                      the attribute will be returned by the parser.
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	T getAttribute(String attributeName);


	/**
	 * Gets the {@link HtmlElement} described by the path and passes it into {@link HtmlElementTransformation#transform(Object)}.
	 * This allows the supplied concrete implementation of the {@link HtmlElementTransformation} to define how the
	 * {@link HtmlElement} is transformed into a String and inserted into a row.
	 *
	 * @param transformation the transformation that will transform the matched HtmlElement into a String
	 *
	 * @return options to download content if the transformed text represents a path to a remote resource.
	 */
	T getElement(HtmlElementTransformation transformation);
}
