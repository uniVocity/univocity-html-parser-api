/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;
import com.univocity.api.entity.html.builders.annotations.*;

/**
 * A `BasicElementFilter` establishes rules to select only those HTML elements that fit a certain criteria. The filtering
 * options provided by this `ElementFilter` are made available to the user once {@link ElementFilterStart#match(String)}
 * is called to target a specific HTML tag.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface BasicElementFilter<T extends BasicElementFilter<T>> {
	/**
	 * Establishes that the matched HTML element must have a given text placed in an element before it. For example,
	 * given this simple HTML document:
	 *
	 * ```html
	 * <span>first</span>
	 * <span>second</span>
	 * <span>third</span>
	 * ```
	 *
	 * We can get the text of the first 'span' element with:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("followed")
	 *     .match("span")
	 *         .followedByText("second")
	 *     .getText();
	 * ```
	 *
	 * The parser will return "first", as the element following the first `span` has the text "second"
	 *
	 * @param text the text contained in the element that is placed after the element being matched
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedByText(String text);


	/**
	 * Establishes that the matched HTML element must have a given text placed in an element after it. For example,
	 * given this simple HTML document:
	 *
	 * ```html
	 * <span>first</span>
	 * <span>second</span>
	 * <span>third</span>
	 * ```
	 *
	 * We can get the text of the last 'span' element with:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("preceded")
	 *     .match("span")
	 *         .precededByText("second")
	 *     .getText();
	 * ```
	 *
	 * The parser will return "third" from the third 'span' element, as the element preceding this element has the text "second"
	 *
	 * @param text the text contained in the element that is placed before the element being matched
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededByText(String text);

	/**
	 * Establishes that the matched HTML element should have a given element placed after it, at any distance. For
	 * example:
	 *
	 * ```html
	 * <div>
	 *   <strong>before</strong>
	 *   <strong>after</strong>
	 * </div>
	 * ```
	 *
	 * One technique to get the text of the first 'strong' element is:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("followed")
	 *     .match("strong")
	 *         .followedBy("strong").withText("after")
	 *     .getText();
	 * ```
	 *
	 * The matching rules in plain english are: get the text of a 'strong' element that has a 'strong' element placed
	 * after it, and that 'strong' element must have the text "after". When the parsing process runs, the value "before"
	 * will be assigned to the field `followed` of this `test` entity.
	 *
	 * @param elementName the name of the HTML element. **Note:**This finalizes the filtering rules applied over the
	 *                    current matched element. Additional filtering rules will take effect over this new element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedBy(String elementName);

	/**
	 * Establishes that the matched HTML element should have a given element at a given distance after it. The distance
	 * is a measure of how many siblings away the element is. For example, in this HTML:
	 *
	 * ```html
	 * <span>first</span>
	 * <span>second</span>
	 * <pre>surpriseHeader</pre>
	 * <strong>third</strong>
	 * ```
	 *
	 * The `pre` element sits 1 position away from the second `span` element and the `strong` element is 2 positions. A technique to
	 * get the text from the second `span` element would be:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("fieldName")
	 *     .match("span")
	 *         .followedBy("strong",2)
	 *     .getText();
	 * ```
	 *
	 * The parser will return "second" as the `strong` element is the second element after `<span>second</span>`.
	 * The first `span` element is ignored as the distance from it to the `strong` element is 3.
	 *
	 * @param elementName the name of the HTML element that should follow the current matched HTML element. **Note:**
	 *                    This finalizes the filtering rules applied over the current matched element. Additional
	 *                    filtering rules will take effect over this new element.
	 * @param distance    the number of siblings where the parser should find the given element after matching the
	 *                    current HTML element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedBy(String elementName, int distance);

	/**
	 * Establishes that the matched HTML element should have a given element placed directly after it.
	 *
	 * An example using this method can be described with the following HTML document:
	 *
	 * ```html
	 * <span>first</span>
	 * <span>second</span>
	 * <strong>third</strong>
	 * ```
	 *
	 * A technique to get the text of the second `span` element is:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("secondSpan")
	 *     .match("span")
	 *         .followedImmediatelyBy("strong")
	 *     .getText();
	 * ```
	 *
	 * This will only return the text from the second `span` element as it is followed immediately by a `strong` element.
	 * The first `span` element is followed immediately by a `span` element, so it will be ignored
	 *
	 * @param elementName the element expected to be directly after the current matched HTML element. **Note:**This
	 *                    finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedImmediatelyBy(String elementName);

	/**
	 * Establishes that the matched HTML element should have a given element placed before it, at any distance. For
	 * example, given this simple HTML document:
	 *
	 * ```html
	 * <div>
	 *   <strong>before</strong>
	 *   <strong>after</strong>
	 * </div>
	 * ```
	 *
	 * One technique to get the text of the second `strong` element is:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("preceded")
	 *     .match("strong")
	 *         .precededBy("strong")
	 *     .getText();
	 * ```
	 *
	 * The matching rules in plain english are: get the text of a `strong` element that has a `strong` element placed
	 * before it. When the parsing process runs, it will return "after"
	 *
	 * @param elementName the name of the element that must precede the current matched HTML element. **Note:**This
	 *                    finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededBy(String elementName);

	/**
	 * Establishes that the matched HTML element should have a given element at a given distance before it.
	 * The distance is a measure of how many siblings away the specified element is. For example:
	 *
	 * ```html
	 * <strong>first</strong>
	 * <h1>surprise header</h1>
	 * <span>first</span>
	 * <span>second</span>
	 * ```
	 *
	 * The `h1` element is 1 position away from the first `span`, and the `strong` element is at a distance of 2. A technique to
	 * get the text from the first `span` element would be:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("firstSpan")
	 *     .match("span")
	 *         .precededBy("strong",2)
	 *     .getText();
	 * ```
	 *
	 * The parser will return "first" as the `strong` element is 2 positions away from the matched `span` element.
	 * The second `span` element is ignored as the distance from it to the `strong` element is 3.
	 *
	 * @param elementName the name of the element that must precede the current matched HTML element. **Note:**This
	 *                    finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 * @param distance    the number of previous siblings before the currently matched HTML element where the parser should
	 *                    find the given element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededBy(String elementName, int distance);


	/**
	 * Establishes that the matched HTML element should have a given element placed directly before it. This can be showcased
	 * by looking at this small HTML snippet:
	 *
	 *  ```html
	 * <strong>first</strong>
	 * <span>match!</span>
	 * <span>ignore me</span>
	 * ```
	 *
	 * A technique to get the text of the first `span` element is:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("firstSpan")
	 *     .match("span")
	 *         .precededImmediatelyBy("strong")
	 *     .getText();
	 * ```
	 *
	 * This will only return the text "match!" from the first `span` element as it is followed immediately by a `strong` element.
	 * The second `span` element is preceded immediately by a `span` element, so it will be ignored
	 *
	 * @param elementName the name of the element that must directly precede the current matched HTML element.
	 *                    **Note:**This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededImmediatelyBy(String elementName);

	/**
	 * Establishes that the matched HTML element should be a child of a given element. A child is defined as a HTML
	 * element that is **directly** contained by another HTML element, i.e. child nodes of child nodes won't be looked at.
	 *
	 * For example, given this simple HTML document:
	 *
	 * ```html
	 * <div>
	 *   <h1>one</h1>
	 * </div>
	 * <article>
	 *   <h1>two</h1>
	 * </article>;
	 * ```
	 *
	 * One technique to get the text of the first 'h1' element is to write:
	 *
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("child")
	 *     .match("h1")
	 *         .childOf("div")
	 *     .getText();
	 * ```
	 *
	 * The matching rules, in plain English, can be described as: get the text of the `h1` element that is the child
	 * of a `div` element.
	 *
	 * @param elementName the name of the element that should be the parent of the current matched HTML element.
	 *                    **Note:**This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.PARENTS)
	T childOf(String elementName);

	/**
	 * Establishes that the matched HTML element should be in the hierarchy of a given element. For example, given this simple
	 * HTML document:
	 *
	 * ```html
	 * <div>
	 *   <span>I'm in a div!</span>
	 * </div>
	 * <article>
	 *   <p>
	 *     <span>I'm in an article!</span>
	 *   </p>
	 * </article>
	 *
	 * ```
	 *
	 * One technique to get the text of the `span` element inside of the 'p' element would be:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("followed")
	 *     .match("span")
	 *         .containedBy("p")
	 *     .getText();
	 * ```
	 *
	 * The matching rules can be described as "get the text of `span` elements in the hierarchy of a `p` element". When
	 * the parser runs, it will return "I'm in an article!"
	 *
	 * @param elementName the name of the element that should contain the current matched HTML element in its hierarchy.
	 *                    **Note:**This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.PARENTS)
	T containedBy(String elementName);

	/**
	 * Establishes that the matched HTML element should be in the hierarchy of a given element, up to a given limit of
	 * parent nodes to visit. This search depth limit restricts the number of times the parser can go up in the HTML hierarchy
	 * to search for a given element. The first parent node from the current matched element is always at depth limit 1,
	 * the parent's parent is at depth limit 2 and so on. For an example, given this HTML document:
	 *
	 * ```html
	 * <div>
	 *   <article>
	 *     <header>
	 *       <span>first</span>
	 *     </header>
	 *   </article>
	 * </div>
	 * <div>
	 *   <article>
	 *     <span>second</span>
	 *   </article>
	 * </div>
	 * ```
	 *
	 * A technique to get the text of the second `span` element would be:
	 *
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("writing")
	 *     .match("span")
	 *         .containedBy("div",2)
	 *     .getText();
	 * ```
	 *
	 * This will only return the text "second" from the last `span`. Its parent is `article` (depth = 1) which in turn is
	 * a child of a `div` (depth = 2).
	 *
	 * The first `span` element is ignored as it is contained by a `div` located 3 levels up in the hierarchy.
	 *
	 * @param elementName the name of the element that should contain the current matched HTML element in its hierarchy.
	 *                    **Note:**This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 * @param depthLimit  the limit of how far the parser will search from the currently matched element into its parent nodes
	 *                    until the given element is found.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.PARENTS)
	T containedBy(String elementName, int depthLimit);

	/**
	 * Establishes that the matched HTML element should be under a given element of a table, at the same column, and
	 * where the given element:
	 *
	 *  * is a `td` or `th` of the **first** table row, or
	 *  * is *contained by* a `td` or `th` of the **first** table row.
	 *
	 * For example, given the HTML document:
	 *
	 * ```html
	 * <table>
	 *   <tr>
	 *       <th>
	 *         <span>heading1</span>
	 *       </th>
	 *       <th>heading2</th>
	 *   </tr>
	 *   <tr>
	 *     <td>one</td>
	 *     <td>two</td>
	 *   </tr>
	 *   <tr>
	 *     <td>lorem</td>
	 *     <td>ispum</td>
	 *   </tr>
	 * </table>
	 * ```
	 *
	 *  One technique to only return the text under 'heading1' would be:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("underTableHeader")
	 *     .match("td")
	 *         .underHeader("span")
	 *     .getText();
	 * ```
	 *
	 * When the parser runs, it will return "one" and "lorem", which are the text values found in first column of 
	 * each row of the table.
	 *
	 * To get all data under all table headers, one could write:
	 *
	 * ```java
	 * entity.addField("underTableHeader")
	 *     .match("td")
	 *         .underHeader("th")
	 *     .getText();
	 * ```
	 *
	 * Which will result in all the text apart from the table headers being returned.
	 *
	 * @param headerElementName the header element of a table that should appear above the current matched HTML element.
	 *                          **Note:**This finalizes the filtering rules applied over the current matched element.
	 *                          Additional filtering rules will take effect over this new element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T underHeader(String headerElementName);

	/**
	 * Establishes that the matched HTML element should be **directly** under a given element of a table,
	 * at the same column, and where the given element:
	 *
	 *  * is a `td` or `th`, or
	 *  * is *contained by* a `td` or `th`
	 *
	 * For example, given this HTML document:
	 *
	 * ```html
	 * <table>
	 *   <tr> 
	 *     <th>Animal</th>
	 *     <th>quantity</th> 
	 *   </tr>
	 *   <tr>
	 *     <td><span>Alpacas</span></td>
	 *     <td>12</td> 
	 *   </tr>
	 *   <tr>
	 *     <td>Lions</td>
	 *     <td>5</td> 
	 *   </tr>
	 * </table>
	 * ```
	 *
	 * A technique to get the text of the last row in the table is:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("firstSpan")
	 *     .match("td")
	 *         .under("span")
	 *     .getText();
	 * ```
	 *
	 * The parser will return "Lions". This is because the matching rules state "get the text from a `td`
	 * that is under a `span` element".
	 *
	 * @param elementName the element of a table that should appear above the current matched HTML element.
	 *                    **Note:**This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T under(String elementName);

	/**
	 * Establishes that the matched HTML element should be the parent of a given element. A parent is defined as the element
	 * that **directly** contains an element.
	 *
	 * A technique for using parentOf can be showcased using this simple HTML document:
	 *
	 * ```html
	 * <div title="one">
	 *   <h1>good</h1>
	 * </div>
	 * <div title="two">
	 *   <h2>bad</h2>
	 * </div>
	 * ```
	 *
	 * A technique to get the title of the first `div` is to write:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("parent")
	 *     .match("div")
	 *         .parentOf("h1")
	 *     .getAttribute("title");
	 * ```
	 *
	 * The parser will return "one", as the parent of the `h1` element is the first `div`.
	 *
	 * @param elementName name of the element that should be a child of the current matched HTML element.
	 *                    **Note:**This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T parentOf(String elementName);

	/**
	 * Establishes that the matched HTML element should contain of one or more given elements in its hierarchy.
	 * For instance, given this simple HTML document:
	 *
	 * ```html
	 * <div>
	 *   <article>
	 *     <p>Some Text</p>
	 *   </article>
	 *   <article>
	 *     <h1>Review: Tea</h1>
	 *     <p>It's good</p>
	 *   </article>
	 *   <article>
	 *     <h1>Discussion: Computers</h1>
	 *   </article>
	 * </div>
	 * ```
	 *
	 * A technique to get the text of the `<p>` element in the middle article is:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("reviewText")
	 *     .match("article")
	 *         .containing("h1","p")
	 *     .match("p")
	 *     .getText();
	 * ```
	 *
	 * The matching rule states: match an `article` element that contains a `h1` **and**
	 * a `p` element. Then, get the text of a `p` element. As neither the first `article` nor the
	 * third `article` have both a `h1` and a `p` element, they are ignored by the parser.
	 *
	 * @param elementNames names of the elements that should be in the hierarchy of the current matched HTML element.
	 *                     **Note:** This does NOT finalize the filtering rules applied over the current matched element.
	 *                     Additional filtering rules will NOT take effect over the given element names.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T containing(String... elementNames);

	/**
	 * Establishes that the matched HTML element should contain a given element in its hierarchy.
	 * For instance, given this simple HTML document:
	 *
	 * ```html
	 * <article>
	 *   <h1>Review: Tea</h1>
	 *   <p>It's good</p>
	 * </article>
	 * <article>
	 *   <h1>Discussion: Computers</h1>
	 *   <p>It's the future!</p>
	 * </article>
	 * ```
	 *
	 * A technique to get the `p` text of all articles is to write:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("reviewText")
	 *     .match("article")
	 *         .containing("h1")
	 *     .match("p")
	 *     .getText();
	 * ```
	 *
	 * The matching rules, in plain English, can be described as "match all `article` elements that contain a `h1` element;
	 * From that `article` element, match the `p` element and return its text". The parser will return "It's good"
	 * and "It's the future!" when it runs.
	 *
	 * @param elementName name of the element that should be in the hierarchy of the current matched HTML element.
	 *                    **Note:**This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T containing(String elementName);

	/**
	 * Establishes that the matched HTML element should contain a given element in its hierarchy, provided it occurs
	 * within a given search depth. This depth limit determines how far down in the hierarchy the parser
	 * will search for a given node from the currently matched element. For example, given this HTML document:
	 *
	 *
	 * ```html
	 * <div title="pen">
	 *   <article>
	 *     <header>
	 *       <span></span>
	 *     </header>
	 *   </article>
	 * </div>
	 * <div title="crayon">
	 *   <article>
	 *     <span></span>
	 *   </article>
	 * </div>
	 * ```
	 *
	 * A technique of getting the title of the second `div` element would be:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("writing")
	 *     .match("div")
	 *         .containing("span",2)
	 *     .getAttribute("title");
	 * ```
	 *
	 * Running the parser will result in only "crayon" being returned. This is because in the first `div`, the `span` is
	 * at the depth of 3. As the depth limit has been set to 2, the parser will ignore the first `div`.
	 *
	 * @param elementName name of the element that should be in the hierarchy of the current matched HTML element.
	 *                    **Note:**This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 * @param depthLimit  the limit of how far the parser will go down the hierarchy to find the given element
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T containing(String elementName, int depthLimit);

	/**
	 * Establishes that the matched HTML element should contain *exactly* a given text. Case insensitive.
	 * Also supports the wildcards `*` and `?` explained in {@link #withText(String)}. An example of using
	 * withExactTest can be shown by looking at this simple HTML document:
	 *
	 * ```html
	 * <p title="cool">a</p>
	 * <p title="not-cool">ab</p>
	 * ```
	 *
	 * A technique to get the title of the first p is to write
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("text")
	 *     .match("p")
	 *         .withExactText("a")
	 *     .getAttribute("title");
	 * ```
	 *
	 * The parser will return "cool" as the first `p` element has the same exact text as the text specified.
	 *
	 * @param textContent the string that the current matched HTML element must contain, accepting variations in the character
	 *                    case and accounting for wildcard elements
	 * @param alternativeTextContents additional `String`s to match
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withExactText(String textContent, String ... alternativeTextContents);

	/**
	 * Establishes that the matched HTML element should **start** with a given text. The text
	 * search is case insensitive, and also supports the `*` and `?` wildcards.
	 *
	 * ### The `*` wildcard
	 *
	 * Use `*` to match any sequence of characters. For instance:
	 *
	 * ```
	 * <div>
	 *   <span>abcdef</span>
	 *   <span>kettle</span>
	 * </div>
	 * ```
	 *
	 * One technique to match the first span would be to write:
	 *
	 * ```java
	 * path.match("span")
	 *     .withText("*f")
	 * .getText();
	 * ```
	 *
	 * The search for `"*f"` means "match the element with text ending with 'f' with any
	 * characters before it". Alternatives to match the text in the example could also be:
	 *  * .withText("a*") - meaning to search for any text starting with 'a' and followed by any number of characters.
	 *  * .withText("a*f") - meaning to search for any text starting with 'a' and ending with 'f', with any number of characters in between.
	 *
	 * Note that specifying `.withText("a*")` or `.withText("a")` will match the same content as this rule goes after nodes
	 * with text **starting** with the given search string.
	 *
	 * ### The `?` wildcard
	 * Use `?` to match any **one** character. For example:
	 *
	 * ```html
	 * <div>
	 *     <span>abcdef</span>
	 *     <span>abc</span>
	 * </div>
	 *
	 * We can set the matching rules as:
	 *
	 * ```java
	 * path.match("span")
	 *     .withText("a?????")
	 * .getText();
	 * ```
	 *
	 * Which describes "match the `span` element that has text starting with 'a' followed by any 5 characters".
	 * This will return the "abcdef" value from the first `span`.
	 *
	 * Alternatives to match the same content could be '?????f' (any 5 characters then a f) and 'ab??ef' ('a', followed by 'b',
	 * followed by any two characters, followed by 'e' and 'f')
	 *
	 * @param textContent the case insensitive `String` that the current matched HTML element must start with, accounting for wildcard elements
	 * @param alternativeTextContents additional `String`s to match
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withText(String textContent, String ... alternativeTextContents);

	/**
	 * Like {@link #withText(String)} but case sensitive. Matches elements whose text *start with* a given text.
	 * Also supports the `*` and `?` wildcards.
	 *
	 * @param textContent the case sensitive string that the current matched HTML element must start with,
	 *                    accounting for wildcard elements
	 * @param alternativeTextContents additional `String`s to match
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withTextMatchCase(String textContent, String ... alternativeTextContents);

	/**
	 * Case sensitive version of {@link #withExactText(String)}. Establishes that the matched HTML element should contain
	 * *exactly* a given text. Also supports the `*` and `?` wildcards.'.
	 *
	 * @param textContent the string that the current matched HTML element must contain exactly, including character case
	 *                    and accounting for wildcard elements
	 * @param alternativeTextContents additional `String`s to match
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withExactTextMatchCase(String textContent, String ... alternativeTextContents);

	/**
	 * Establishes that the matched HTML element should contain the given CSS class names. Multiple class names can be
	 * used as parameters. For example:
	 *
	 * ```html
	 * <span class = "a b"></span>
	 * ```
	 *
	 * A technique to create to match this `span` would be:
	 *
	 * ```java
	 * path.match("span").classes("a","b");
	 * ```
	 *
	 * @param firstCssClass   class of a HTML element that the matched HTML element must contain
	 * @param otherCssClasses any other classes that the matched HTML element must contain (optional).
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	T classes(String firstCssClass, String... otherCssClasses);

	/**
	 * Establishes that the matched HTML element should contain the given attribute name and value. For example, if there
	 * is HTML that looks like this:
	 *
	 * ```html
	 * <table title="The Tables Have Turned">
	 * </table>
	 * ```
	 *
	 * A way to create a path to the `table` element would be to write:
	 *
	 * ```java
	 * path.match("table").attribute("title","The Tables Have Turned"
	 * ```
	 *
	 * @param attributeName  the name of the attribute
	 * @param attributeValue the value of the attribute
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	T attribute(String attributeName, String attributeValue);

	/**
	 * Establishes that the matched HTML element should contain an id attribute with a given value. For example, if
	 * there is HTML that looks like:
	 *
	 * ```html
	 * <div id="coffee"></div>
	 * ```
	 *
	 * The `div` can matched using:
	 *
	 * ```java
	 * path.match("div").id("coffee")
	 * ```
	 *
	 * @param idValue value of the id attribute of the current matched HTML element
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	T id(String idValue);

	/**
	 * Establishes that the matched HTML element should pass the supplied filter method using a
	 * {@link HtmlElementMatcher#match(HtmlElement, HtmlElement)}.
	 *
	 * @param htmlElementMatcher the filter that will be applied to the matched {@link HtmlElement}
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	T filter(HtmlElementMatcher htmlElementMatcher);

	/**
	 * Negates the very next filter. For example:
	 *
	 * ```html
	 * <table>
	 *   <thead>
	 *      <tr>
	 *        <th>Product</th>
	 *        <th>Product Price</th>
	 *      </tr>
	 *   </thead>
	 *   <tbody>
	 *     <tr>
	 *       <td>Something</td>
	 *       <td>$8,154.90</td>
	 *     </tr>
	 *     <tr>
	 *       <td>Something else</td>
	 *       <td>$9,945.00</td>
	 *     </tr>
	 *   </tbody>
	 * </table>
	 * ```
	 *
	 * To match only product names but not product prices, we can have this
	 *
	 * ```java
	 * settings.addField("productName")
	 *     .match("td")
	 *     .not().withText("$")
	 *     .underHeader("th").withText("Product")
	 *     .getText();
	 * ```
	 *
	 * @return this `BasicElementFilter` instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	BasicElementFilter<T> not();

}
