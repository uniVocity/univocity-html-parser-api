/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;
import com.univocity.api.entity.html.builders.annotations.*;

/**
 * A `ContentReader` defines what content will be read from the last element matched in a {@link FieldPath} by
 * the {@link HtmlParser}. This is the final step of creating a path to a field created for an entity
 * (using {@link HtmlEntitySettings}).
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface ContentReader<T extends ContentHandler> {

	/**
	 * Used to get the text of a table header above a matched element. For example, given a HTML document like this:
	 *
	 *
	 * ```html
	 * <table>
	 *   <tr>
	 *     <th>a</th>
	 *     <th>b</th>
	 *     <th>c</th>
	 *   </tr>
	 *   <tr>
	 *     <td>first</td>
	 *     <td>second</td>
	 *     <td>third</td>
	 *   </tr>
	 * </table>
	 * ```
	 *
	 * To capture the header of the column with text "second", a matching rule can be defined as:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("tableHeader")
	 *     .match("table")
	 *     .match("td")
	 *         .withText("second")
	 *     .getHeadingText();
	 * ```
	 *
	 * To get all table headers:
	 *
	 * ```java
	 * entity.addField("tableHeader")
	 *     .match("table")
	 *     .match("td")
	 *     .getHeadingText();
	 * ```
	 *
	 * The parser will return each table header (`a`,`b` and `c`) in multiple separate rows of entity `test`
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T getHeadingText();

	/**
	 * Captures the text in the same column of the matched element, but in another row of the same table. The position
	 * of the row is relative to the start of the table. For example, given this HTML document:
	 *
	 * ```html
	 * <table>
	 *   <tr>
	 *     <td>Label over field</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Field value</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Another label over a field</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Another value</td>
	 *   </tr>
	 * </table>
	 * ```
	 *
	 * To get the text of the 3rd row one can write:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("fieldName")
	 *     .match("td")
	 *         .withText("Another value")
	 *     .getHeadingText(3);
	 * ```
	 *
	 * The parser will return "Another label over a field" as it is at the third row of the table.
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
	 * Captures the text contained in the row and column above the HTML element matched by
	 * the path. Only applicable to tables. For example, given the HTML table:
	 *
	 * ```html
	 * <table>
	 *   <tr>
	 *     <th>heading1</th>
	 *     <th>heading2</th>
	 *   </tr>
	 *   <tr>
	 *     <td>one</td>
	 *     <td>two</td>
	 *   </tr>
	 *   <tr>
	 *     <td>a</td>
	 *     <td>ab</td>
	 *   </tr>
	 * </table>
	 * }<hr>
	 *
	 * One technique to return the text in the second row could be:
	 *
	 * ```java
	 * entity.addField("tableHeader")
	 *     .match("td")
	 *         .withText("a*")
	 *     .getTextAbove();
	 * ```
	 *
	 * Which describes: match `td` elements containing text starting with "a", then get the text from the element at
	 * the same column in the row above.
	 *
	 * When the parser runs, it will return two records with field "tableHeader" populated with values
	 * "one" and "two", respectively.
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
	 * ```html
	 * <table>
	 *   <tr>
	 *     <th>Name</th>
	 *     <th>Number</th>
	 *   </tr>
	 *   <tr>
	 *     <td>Adam</td>
	 *     <td>143</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Bob</td>
	 *     <td>222</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Charlie</td>
	 *     <td>973</td>
	 *   </tr>
	 * </table>
	 * ```
	 *
	 *
	 * A technique to get the text from the second row's Number field is:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("text")
	 *     .match("td")
	 *         .precededBy("td")
	 *         .withText("Charlie")
	 *     .getTextAbove(2);
	 * ```
	 *
	 * The matching rule, described in English, is: "match the `td` that has a `td` with text "Charlie" before it."
	 * This targets the `td` with value '973' in it. Then, the rule states: "get the text from the element that is 2 rows above".
	 * When the parser runs, it will return "143".
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
	 * above a matched element. Only applicable to tables. It is best used when:
	 *
	 *  * Text needs to be parsed from the column of some row above the matched element
	 *  * The number of rows above is variable or unknown <strong>AND</strong>
	 *  * There is a known set of possible strings that one of the rows above the matched element can contain.
	 *
	 * For example, given these mismatching tables in a HTML document:
	 *
	 * ```html
	 * <table>
	 *   <tr>
	 *     <th>Gender</th>
	 *     <td>Male</td>
	 *     <td>Female</td>
	 *   </tr>
	 *   <tr>
	 *     <th>Age</th>
	 *     <td>41</td>
	 *     <td>36</td>
	 *   </tr>
	 *   <tr>
	 *     <th>Name</th>
	 *     <td>Steven</td>
	 *     <td>Mandy</td>
	 *   </tr>
	 * </table>
	 *
	 * <table>
	 *   <tr>
	 *     <th>Age</th>
	 *     <td>38</td>
	 *     <td>24</td>
	 *   </tr>
	 *   <tr>
	 *     <th>Gender</th>
	 *     <td>Female</td>
	 *     <td>Male</td>
	 *   </tr>
	 *   <tr>
	 *     <th>Name</th>
	 *     <td>Sarah</td>
	 *     <td>Bob</td>
	 *   </tr>
	 * </table>
	 * ```
	 *
	 * The code to get the name and gender of each person in both tables could be:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * //Creates path to any "Name" th elements
	 * PartialPath path = entity.newPath().match("th").withText("Name");
	 *
	 * //Matches each td in name row and gets the text
	 * path.addField("name").match("td").getText();
	 *
	 * //Matches td in above row that contains "Male" or "Female" and captures the text if it is one of these values.
	 * path.addField("gender")
	 *     .match("td")
	 *     .getTextAbove("Male", "Female");
	 * ```
	 *
	 * The code snippet above creates a path that matches `th` elements with text = "Name".
	 *
	 * From this path, it creates two fields: "name" and "gender", so each field starts matching every `td` element next
	 * to a "Name" `th` element.
	 *
	 * The "name" field simply matches the next `td` element returns the text inside it.
	 *
	 * The "gender" field matches the next `td` element, and then looks for rows above it that contain the text
	 * "Male" **OR** "Female" in the corresponding column, returning its text.
	 *
	 * The output produced is:
	 *
	 * ```
	 * [Steven, Male]
	 * [Mandy, Female]
	 * [Sarah, Female]
	 * [Bob, Male]
	 * ```
	 *
	 * @param firstAlternative  The text that the parser will attempt to find and collect from a row above the matched element
	 * @param otherAlternatives Optional additional strings to look for.
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T getTextAbove(String firstAlternative, String... otherAlternatives);

	/**
	 * Specifies that the parser will return the text contained within the HTML element defined by the path. For instance,
	 * say a field is added to an entity and the path is set to get text from a `<td>` tag. When the parser runs and hits
	 * `<td>goober<td>`, the parser will return the text inside the HTML tag, which is: "goober".
	 *
	 * When this method is applied to an element that has children elements, each with their own text, it will combine
	 * everything into one string, with a simple space separating the text of each element. For example:
	 *
	 * ```html
	 * <div>
	 * <h1>The Title</h1>
	 * <p>Text</p>
	 * <p>More Words</p>
	 * </div>
	 * ```
	 *
	 * The following code should provide the "The Title Text More Words" `String` for an "allText" field when
	 * the parser is run.
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("entity");
	 * entity.addField("field")
	 *     .match("div")
	 *     .getText();
	 * ```
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T getText();

	/**
	 * Specifies that the parser will return the text contained within the HTML elements matched by the path in **addition
	 * to** the text in the specified amount of **following** siblings. For example:
	 *
	 * ```html
	 * <div>
	 *   <h1>title</h1>
	 *   <p>Cool Text</p>
	 *   <p>More Cool Text</p>
	 *   <footer>feet</footer>
	 * </div>
	 * ```
	 *
	 * A technique to get the text of both `p` elements is:
	 *
	 * ```
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("text")
	 *     .match("p")
	 *         .precededBy("h1")
	 *     .getText(1);
	 * ```
	 *
	 * This code snippet creates a path to the first `p` element. Then the parser will get the text of this first `p`
	 * element and append the text of **one** next sibling (which is the second `p` element).
	 *
	 * The `footer` text is ignored as it is 2 siblings away from the targeted `p` element.
	 *
	 * Setting the number of siblings to `@code <= 0` is the equivalent of using {@link #getText()}.
	 *
	 * @param numberOfSiblingsToInclude number of following siblings counting, from the matched element, whose text
	 *                                  should be appended to the text of the matched element.
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T getText(int numberOfSiblingsToInclude);


	/**
	 * Specifies that the parser will return the text from HTML element specified by the path without including the text
	 * of its child nodes.
	 *
	 * For instance given an HTML document that looks like
	 *
	 * ```html
	 * <p>Text <strong>not</strong> only in the p element</p>
	 * ```
	 *
	 * We can get only the text directly inside of the `p` element with:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("ownText")
	 *     .match("p")
	 *     .getOwnText();
	 * ```
	 *
	 * When the parser runs, it will return "Text only in the p element"
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents
	 * a path to a remote resource.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T getOwnText();

	/**
	 * Specifies that the parser will return the text from the node that appears before the HTML element
	 * specified by the path. For instance, given an HTML document that looks like
	 *
	 * ```html
	 * <div>before<span>hello</span>after</div>
	 * ```
	 *
	 * We can get the text just before the `span` element with:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("preceding")
	 *     .match("span")
	 *     .getPrecedingText();
	 * ```
	 *
	 * When the parser runs, it will return "before"
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T getPrecedingText();

	/**
	 * Collects the text from the specified number of HTML elements placed before the element that is matched by the path.
	 * For example, given this HTML:
	 *
	 * ```html
	 * <h1>header</h1>
	 * <p>more</p>
	 * <p>text</p>
	 * <footer>feet</footer>
	 * ```
	 *
	 * A way to get the text of everything before the `footer` and after `h1` is:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("fieldName")
	 *     .match("footer")
	 *     .getPrecedingText(2);
	 * ```
	 *
	 * This will return "more text" as the first `<p>text</p>` element is the first sibling before the matched `footer`
	 * element, and the second `<p>more</p>` element is the second sibling before the matched `footer`.
	 * As the `h1` element is third sibling, it is ignored by the parser.
	 *
	 * @param numberOfSiblingsToInclude the number of elements preceding the element defined in the path whose text will
	 *                                  be returned from
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T getPrecedingText(int numberOfSiblingsToInclude);

	/**
	 * Gets the text from the HTML element that is placed directly after the HTML elements matched by the path. For
	 * instance, given an HTML document that has:
	 *
	 * ```html
	 * <div>before<span>hello</span>after<p>text</p></div>
	 * ```
	 *
	 * A way to get the text just after the `span` element is:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("following")
	 *     .match("span")
	 *     .getFollowingText();
	 * ```
	 *
	 * When the parser runs, it will return "after".
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T getFollowingText();

	/**
	 * Gets the text from the specified number of HTMl elements following the HTML element matched by the path. For
	 * instance, given a HTML document looking like:
	 *
	 * ```html
	 * <h1>header</h1>
	 * <p>text</p>
	 * <footer>feet</footer>
	 * <p>annoying</p>
	 * ```
	 *
	 * To get the text of everything after the `h1` element and before the last `p` element we can write:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("fieldName")
	 *     .match("h1")
	 *     .getFollowingText(2);
	 * ```
	 *
	 * The parser will return "text feet" as the first `p` element is the first sibling after the matched `h1` element,
	 * and the `footer` element is the second. As the last `p` element comes third, it lies outside the range of the
	 * matching rules and will be ignored.
	 *
	 * @param numberOfSiblingsToInclude the number of elements following the element defined in the path whose text will
	 *                                  be returned from
	 *
	 * @return options to transform the captured information, and/or to download content if the text represents a path
	 * to a remote resource.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T getFollowingText(int numberOfSiblingsToInclude);

	/**
	 * Captures the value of an attribute of the HTML elements matched by the path.
	 * For example, to get `href` values from links (i.e. `<a>`) such as in
	 *
	 *  ```html
	 *  <a href="https://www.google.com">a link</a>
	 *  ```
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("link")
	 *     .match("a")
	 *     .getAttribute("href");
	 * ```
	 *
	 * When the parser runs and it will return "https://www.google.com".
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
	 * Gets the {@link HtmlElement} described by the path and passes it to a custom
	 * {@link HtmlElementTransformation#transform(Object)}.
	 *
	 * This allows the user to provide a concrete implementation of the {@link HtmlElementTransformation} to define how
	 * the {@link HtmlElement} is transformed into a `String` and inserted into a row.
	 *
	 * @param transformation the transformation that will transform the matched {@link HtmlElement} into a `String`
	 *
	 * @return options to download content if the transformed text represents a path to a remote resource.
	 */
	T getElement(HtmlElementTransformation transformation);
}
