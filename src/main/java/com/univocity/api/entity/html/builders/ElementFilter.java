/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;
import com.univocity.api.entity.html.builders.annotations.*;

/**
 * An {@code ElementFilter} establishes rules to select only those HTML elements that fit a certain criteria. The filtering
 * options provided by this {@code ElementFilter} are made available to the user once {@link ElementFilterStart#match(String)}
 * is called to target a specific HTML tag.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface ElementFilter<T extends ElementFilter<T>> {
	/**
	 * Establishes that the matched HTML element must have a given text placed in an element before it. For example,
	 * given this simple HTML document:
	 *
	 * <hr><pre><code>{@code
	 * <span>first</span><span>second</span><span>third</span>
	 * }<pre/></code><hr>
	 *
	 * <p>One technique to get the text of the first 'span' element is: </p>
	 *
	 * <hr><pre><code>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("followed").match("span").followedByText("second").getText();
	 * }<pre/></code><hr>
	 *
	 * <p>The parser will return "first", as the element following the first 'span' element has the text "second"</p>
	 *
	 * @param text the text contained in the element that is placed after the element being matched
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedByText(String text);


	/**
	 * Establishes that the matched HTML element must have a given text placed in an element after it. For example,
	 * given this simple HTML document:
	 *
	 * <hr><pre><code>{@code
	 * <span>first</span><span>second</span><span>third</span>
	 * }<pre/></code><hr>
	 *
	 * <p>One technique to get the text of the last 'span' element is: </p>
	 *
	 * <hr><pre><code>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("preceded").match("span").precededByText("second").getText();
	 * }<pre/></code><hr>
	 *
	 * <p>The parser will return "third" from the third 'span' element, as the element preceding this element has the text "second"</p>
	 *
	 * @param text the text contained in the element that is placed before the element being matched
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededByText(String text);

	/**
	 * Establishes that the matched HTML element should have a given element placed after it, at any distance. For
	 * example, given this simple HTML document:
	 *
	 * <hr><pre><code>{@code
	 * <div>
	 * 		<strong>before</strong><strong>after</strong>
	 * </div>
	 * }<pre/></code><hr>
	 *
	 * <p>One technique to get the text of the first 'strong' element is:</p>
	 *
	 * <hr><pre><code>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("followed").match("strong").followedBy("strong").withText("after").getText();
	 * }<pre/></code><hr>
	 *
	 * <p>The matching rules in plain english are: get the text of a 'strong' element that has a 'strong' element placed
	 * after it, and that 'strong' element must have text 'after'. When the parsing process runs, it will return 'before'</p>
	 *
	 * @param elementName the name of the HTML element. <b>Note:</b>This finalizes the filtering rules applied over the
	 *                    current matched element. Additional filtering rules will take effect over this new element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedBy(String elementName);

	/**
	 * Establishes that the matched HTML element should have a given element at a given distance after it. The distance
	 * is a measure of how many siblings away the element is. For example, in this HTML:
	 *
	 * {@code <span>first</span> <span>second</span> <pre>surpriseHeader</pre> <strong>third</strong>}, the pre element
	 * is 1 distance away from the second span element and the strong element is at a distance of 2. A technique to
	 * get the text from the second span element would be:
	 *
	 * <hr><pre><code>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("fieldName").match("span").followedBy("strong",2).getText();
	 * }<pre/></code><hr>
	 *
	 * <p>The parser will return "second" as the strong element is at the following distance of 2. The first span element
	 * is ignored as the distance from it to the strong element is 3.</p>
	 *
	 * @param elementName the name of the HTML element that should follow the current matched HTML element. <b>Note:</b>
	 *                    This finalizes the filtering rules applied over the current matched element. Additional
	 *                    filtering rules will take effect over this new element.
	 * @param distance    the number of siblings where the parser should find the given element after matching the
	 *                    current HTML element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedBy(String elementName, int distance);

	/**
	 * Establishes that the matched HTML element should have a given element placed directly after it. This can be showcased
	 * by looking at this small HTML snippet: {@code <p>someText</p><p>moreText</p><footer>foot</footer>}. The second p
	 * element is immediately followed by a footer element, but the first p element is immediately followed by the other
	 * p element.
	 *
	 * <p>An example using this method can be described with the following HTML document:</p>
	 *
	 * <hr><pre><code>{@code
	 * <span>first</span><span>second</span><strong>third</strong>
	 * }<hr><pre/></code>
	 *
	 * <p>A technique to get the text of the second span element is:</p>
	 *
	 * <hr><pre><code>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("secondSpan").match("span").followedImmediatelyBy("strong").getText();
	 * }<hr><pre/></code>
	 *
	 * <p>This will only return the text in the second span element as it is followed immediately by a strong element.
	 * The first span element is followed immediately by a span element, so it will be ignored</p>
	 *
	 * @param elementName the element expected to be directly after the current matched HTML element. <b>Note:</b>This
	 *                    finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedImmediatelyBy(String elementName);

	/**
	 * Establishes that the matched HTML element should have a given element placed before it, at any distance. For
	 * example, given this simple HTML document:
	 *
	 * <hr><pre><code>{@code
	 * <div>
	 * 		<strong>before</strong><strong>after</strong>
	 * </div>
	 * }<hr><pre/></code>
	 *
	 * <p>One technique to get the text of the second 'strong' element is:</p>
	 *
	 * <hr><pre><code> {@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("preceded").match("strong").precededBy("strong").getText();
	 * }<hr><pre/>
	 *
	 * <p>The matching rules in plain english are: get the text of a 'strong' element that has a 'strong' element placed
	 * before it. When the parsing process runs, it will return 'after'</p>
	 *
	 * @param elementName the name of the element that must precede the current matched HTML element. <b>Note:</b>This
	 *                    finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededBy(String elementName);

	/**
	 * Establishes that the matched HTML element should have a given element at a given distance before it.
	 * The distance is a measure of how many siblings away the specified element is. For example, in this HTML:
	 * {@code <strong>first</strong> <h1>surprise header</h1> <span>first</span> <span>second</span>}, the h1 element
	 * is 1 distance away from the first span element and the strong element is at a distance of 2. A technique to
	 * get the text from the first span element would be:
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("firstSpan").match("span").precededBy("strong",2).getText();
	 * }<hr>
	 *
	 * <p>The parser will return "first" as the strong element is 2 positions away from the matched span element.
	 * The second span element is ignored as the distance from it to the strong element is 3.</p>
	 *
	 * @param elementName the name of the element that must precede the current matched HTML element. <b>Note:</b>This
	 *                    finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 * @param distance    the number of previous siblings before the currently matched HTML element where the parser should
	 *                    find the given element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededBy(String elementName, int distance);


	/**
	 * Establishes that the matched HTML element should have a given element place directly before it. This can be showcased
	 * by looking at this small HTML snippet: {@code <h1>feet</h1><p>someText</p><p>moreText</p>}. The first p
	 * element is immediately preceded by a h1 element, but the second p element is immediately preceded by the other
	 * p element.
	 *
	 * <p>An example using this method can be described with the following HTML document:</p>
	 *
	 * <hr>{@code
	 * <strong>first</strong><span>second</span><span>third</span>
	 * }<hr>
	 *
	 * <p>A technique to get the text of the first span element is:</p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("firstSpan").match("span").precededImmediatelyBy("strong").getText();
	 * }<hr>
	 *
	 * <p>This will only return the text in the first span element as it is followed immediately by a strong element.
	 * The second span element is preceded immediately by a span element, so it will not be returned by the parser</p>
	 *
	 * @param elementName the name of the element that must directly precede the current matched HTML element.
	 *                    <b>Note:</b>This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededImmediatelyBy(String elementName);

	/**
	 * Establishes that the matched HTML element should be a child of a given element. A child is defined as a HTML
	 * element that is <strong>directly</strong> contained by another HTML element. For example, given this simple HTML document:
	 *
	 * <hr>{@code
	 * <div>
	 * <h1>one</h1>
	 * </div>
	 * <article>
	 * <h1>two</h1>
	 * </article>;
	 * }<hr>
	 *
	 * <p>One technique to get the text of the first 'h1' element is to write:</p>
	 *
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("child").match("h1").childOf("div").getText();
	 * }<hr>
	 *
	 * <p>The matching rules, in plain english, can be described as: get the text of the 'h1' element that is the child
	 * of a div element. </p>
	 *
	 * @param elementName the name of the element that should be the parent of the current matched HTML element.
	 *                    <b>Note:</b>This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.PARENTS)
	T childOf(String elementName);

	/**
	 * Establishes that the matched HTML element should be in the hierarchy of a given element. For example, given this simple
	 * HTML document:
	 *
	 * <hr>{@code
	 * <div>
	 * <span>I'm in a div!</span>
	 * </div>
	 * <article>
	 * <span>I'm in an article!</span>
	 * </article>
	 * }<hr>
	 *
	 * <p>One technique to get the text of the span element inside of the 'article' element would be to write:</p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("followed").match("span").containedBy("article").getText();
	 * }<hr>
	 *
	 * <p>The matching rules can be described as "get the text of span elements inside of an article element". When
	 * the parser runs, it will return "I'm in an article!"</p>
	 *
	 * @param elementName the name of the element that should contain the current matched HTML element in its hierarchy.
	 *                    <b>Note:</b>This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.PARENTS)
	T containedBy(String elementName);

	/**
	 * Establishes that the matched HTML element should be in the hierarchy of a given element. Differs
	 * from {@link #containedBy(String)} as it allows the specification of a depth limit. This depth limit restricts
	 * the number of times the parser can go up in the HTML hierarchy to search for the given element. The first parent
	 * it visits is at depth limit 1, the parent's parent is at depth limit 2 and so on. For an example, given
	 * this HTML document:
	 *
	 *
	 * <hr>{@code
	 * <div>
	 * <article>
	 * <header>
	 * <span>first</span>
	 * </header>
	 * </article>
	 * </div>
	 * <div>
	 * <article>
	 * <span>second</span>
	 * </article>
	 * </div>
	 * }<hr>
	 *
	 * <p>A technique to get the text of the second span element would be:</p>
	 *
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("writing").match("span").containedBy("div",2).getText();
	 * }<hr>
	 *
	 * <p>This will only return 'second' from the last span. While the first span element is contained by a div, the div
	 * is at a depth level of 3 meaning that the parser will ignore it.</p>
	 *
	 * @param elementName the name of the element that should contain the current matched HTML element in its hierarchy.
	 *                    <b>Note:</b>This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 * @param depthLimit  the limit of how far the parser will search from the currently matched element into its parent nodes
	 *                    until the given element is found.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.PARENTS)
	T containedBy(String elementName, int depthLimit);

	/**
	 * Establishes that the matched HTML element should be under a given element of a table, at the same column, and
	 * where the given element is a {@code td} or {@code th} of the first row, or is contained by a {@code td}
	 * or {@code th} of the <strong>first</strong> table row.
	 *
	 * For example, given a simple HTML document of:
	 *
	 * <hr>{@code
	 * <table>
	 * <tr> <th><span>heading1</span></th> <th>heading2</th> </tr>
	 * <tr> <td>one</td> <td>two</td> </tr>
	 * <tr> <td>lorem</td> <td>ispum</td> </tr>
	 * </table>
	 * }<hr>
	 *
	 * <p> One technique to only return the text under 'heading1' would be: </p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("underTableHeader").match("td").underHeader("span").getText();
	 * }<hr>
	 *
	 * <p>When the parser runs, it will return 'one' and 'lorem'. If all data under the headers needed to be returned,
	 * only a simple change to the matching rules needs to be done: </p>
	 *
	 * <hr>{@code
	 * entity.addField("underTableHeader").match("td").underHeader("th").getText();
	 * }<hr>
	 *
	 * <p>Which will result in all the text apart from the table headers being returned.</p>
	 *
	 * @param headerElementName the header element of a table that should appear above the current matched HTML element.
	 *                          <b>Note:</b>This finalizes the filtering rules applied over the current matched element.
	 *                          Additional filtering rules will take effect over this new element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T underHeader(String headerElementName);

	/**
	 * /**
	 * Establishes that the matched HTML element should be <strong>directly</strong> under a given element of a table,
	 * at the same column, and where the given element is a {@code td} or {@code th}, or is contained by a {@code td}
	 * or {@code th}.
	 *
	 * For example, given this HTML document:
	 *
	 * <hr>{@code
	 * <table>
	 * <tr> <th>Animal</th>  <th>quantity</th> </tr>
	 * <tr> <td><span>Alpacas</span></td> <td>12</td> </tr>
	 * <tr> <td>Lions</td>   <td>5</td> </tr>
	 * </table>
	 * }<hr>
	 *
	 * <p>A technique to get the text of the last row in the table is:</p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("firstSpan").match("td").under("span").getText();
	 * }<hr>
	 *
	 * <p>The parser will return the 'Lions'. This is because the matching rules state 'get the text from a 'td'
	 * that is under a 'span' element'.</p>
	 *
	 * @param elementName the element of a table that should appear above the current matched HTML element.
	 *                    <b>Note:</b>This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T under(String elementName);

	/**
	 * Establishes that the matched HTML element should be the parent of a given element. A parent is defined as the element
	 * that <strong>directly</strong> contains an element. For example:
	 * {@code <div> <article> <h1> heading </h1> <p> text </p> </article></div>}. The article element is the parent of
	 * both the h1 and p elements. The div element is parent of article but <strong>not</strong> parent of h1 and p.
	 * A technique for using parentOf can be showcased using this simple HTML document:
	 *
	 * <hr>{@code
	 * <div title="one">
	 * <h1>good</h1>
	 * </div>
	 * <div title="two">
	 * <h2>bad</h2>
	 * </div>
	 * }<hr>
	 *
	 * <p>A technique to get the title of the first div element is to write: </p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("parent").match("div").parentOf("h1").getAttribute("title");
	 * }<hr>
	 *
	 * <p>The parser will return "one", as the parent of the h1 element is the first div.</p>
	 *
	 * @param elementName name of the element that should be a child of the current matched HTML element.
	 *                    <b>Note:</b>This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T parentOf(String elementName);

	/**
	 * Establishes that the matched HTML element should contain of one or more given elements in its hierarchy.
	 * For instance, given this simple HTML document:
	 *
	 * <hr>{@code
	 * <div>
	 * <article>
	 * <p>Some Text</p>
	 * </article>
	 * <article>
	 * <h1>Review: Tea</h1>
	 * <p>It's good</p>
	 * </article>
	 * <article>
	 * <h1>Discussion: Computers</h1>
	 * </article>
	 * </div>
	 * }<hr>
	 *
	 * <p>A technique to get the text of the{@code <p>} element in the middle article is: </p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("reviewText").match("article").containing("h1","p").match("p").getText();
	 * }<hr>
	 *
	 * <p>The matching rule states: match an {@code <article>} element that contains a {@code <h1>} <strong>and</strong>
	 * a {@code <p>} element. Then, get the text of a {@code <p>} element. As neither the first article nor the
	 * third article have both a {@code <h1>} and a {@code <p>} element, they are ignored by the parser.</p>
	 *
	 * @param elementNames names of the elements that should be in the hierarchy of the current matched HTML element.
	 *                     <b>Note:</b>This does NOT finalize the filtering rules applied over the current matched element.
	 *                     Additional filtering rules will NOT take effect over the given element names.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T containing(String... elementNames);

	/**
	 * Establishes that the matched HTML element should contain of a given element in its hierarchy.
	 * For instance, given this simple HTML document:
	 *
	 * <hr>{@code
	 * <article>
	 * <h1>Review: Tea</h1>
	 * <p>It's good</p>
	 * </article>
	 * <article>
	 * <h1>Discussion: Computers</h1>
	 * <p>It's the future!</p>
	 * </article>;
	 * }<hr>
	 *
	 * <p>A technique to get the 'p' text of all articles is to write: </p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("reviewText").match("article").containing("h1").match("p").getText();
	 * }<hr>
	 *
	 * <p>The matching rules, in plain English, can be described as "match all article elements that contain a h1 element.
	 * From that article element, match the p element and return its text". The parser will return 'It's good'
	 * and 'It's the future!' when it runs. </p>
	 *
	 * @param elementName name of the element that should be in the hierarchy of the current matched HTML element.
	 *                    <b>Note:</b>This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T containing(String elementName);

	/**
	 * Establishes that the matched HTML element should contain of a given element in its hierarchy, provided it occurs
	 * within a given depth. This depth limit determines how far down in the hierarchy the parser
	 * will search for given element from the currently matched element. For example, given this HTML document:
	 *
	 *
	 * <hr>{@code
	 * <div title="pen">
	 * <article>
	 * <header>
	 * <span></span>
	 * </header>
	 * </article>
	 * </div>
	 * <div title="crayon">
	 * <article>
	 * <span></span>
	 * </article>
	 * </div>;
	 * }<hr>
	 *
	 * <p>A technique of getting the title of the second div element would be:</p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("writing").match("div").containing("span",2).getAttribute("title");
	 * }<hr>
	 *
	 * <p>Running the parser will result in only 'crayon' being returned. This is because in the first div, the span is
	 * at the depth of 3. As the depth limit as been set at 2, the parser will ignore the first div.</p>
	 *
	 * @param elementName name of the element that should be in the hierarchy of the current matched HTML element.
	 *                    <b>Note:</b>This finalizes the filtering rules applied over the current matched element.
	 *                    Additional filtering rules will take effect over this new element.
	 * @param depthLimit  the limit of how far the parser will go down the hierarchy to find the given element
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T containing(String elementName, int depthLimit);

	/**
	 * Establishes that the matched HTML element should start with a given text. The text
	 * search is case insensitive, and also supports the '*' and '?' wildcards.
	 *
	 * <p>* is used by the parser to match any sequence of characters. For instance, there is a short HTML document
	 * of '{@code <div> <span>abcdef</span> <span>kettle</span> </div>}'. One technique to match the first span would
	 * be to write:
	 * </p>
	 *
	 * <hr>{@code
	 * path.match("span").withText("*f").getText();
	 * }<hr>
	 *
	 * <p>
	 * The meaning of setting the matching text to '*f' means 'match the element with text ending with 'f' with any
	 * characters before it'. Alternatives to match the text could be: 'a' (text starting with a) and 'a*f' (text starting
	 * with a and ending with f).
	 * </p>
	 *
	 * <p>Note: Specifying 'a*' or 'a' will match the same elements.</p>
	 *
	 * <p>? is used by the parser to match any one character. Using this simple HTML document
	 * ('{@code <div> <span>abcdef</span> <span>abc</span> </div>}'), we can set the matching rules as:
	 * </p>
	 *
	 * <hr>{@code
	 * path.match("span").withText("a?????").getText();
	 * }<hr>
	 *
	 * <p>Which describes 'match the span element that has text that starts with 'a' and has 5 characters after the 'a'.
	 * Alternatives to match the text could be '?????f' (any 5 characters then a f) and 'ab??ef' ('a', followed by 'b',
	 * followed by any two characters, followed by 'e' and 'f') </p>
	 *
	 * @param textContent the string that the current matched HTML element must start with, accounting for wildcard elements
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withText(String textContent);

	/**
	 * Establishes that the matched HTML element should contain <strong>exactly</strong> a given text. Is case insensitive.
	 * Also supports the special characters '*' and '?' explained in {@link #withText(String)}. An example of using
	 * withExactTest can be shown by looking at this simple HTML document:
	 *
	 * <hr>{@code
	 * {@code <p title="cool">a</p>
	 * <p title="not-cool">ab</p> }
	 * }<hr>
	 *
	 * <p>A technique to get the title of the first p is to write</p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 * entity.addField("text").match("p").withExactText("a").getAttribute("title");
	 * }<hr>
	 *
	 * <p>The parser will return "cool" as the first p element has the same exact text as the text specified. If the
	 * method {@link #withText(String)} was used instead, the parser would return "cool" <strong>and</strong> "not-cool".
	 * This is because {@link #withText(String)} will search for elements that <strong>start with</strong> "a".</p>
	 *
	 * @param textContent the string that the current matched HTML element must contain, accepting variations in the character
	 *                    case and accounting for wildcard elements
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withExactText(String textContent);

	/**
	 * Like {@link #withText(String)} but case sensitive. Creates a path to a HTML element that <strong>starts with</strong>
	 * text. Also supports the special characters of '*' and '?'.
	 *
	 * @param textContent the string that the current matched HTML element must start with, accounting for wildcard elements
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withTextMatchCase(String textContent);

	/**
	 * Case sensitive version of {@link #withExactText(String)}. Establishes that the matched HTML element should contain
	 * <strong>exactly</strong> a given text. Also supports the special characters of '*' and '?'.
	 *
	 * @param textContent the string that the current matched HTML element must contain exactly, including character case
	 *                    and accounting for wildcard elements
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withExactTextMatchCase(String textContent);

	/**
	 * Establishes that the matched HTML element should contain the given class names. One to many class names can be
	 * used as parameters. As an example, assume there is HTML that looks like this: {@code '<span class = "a b"></span>'}.
	 * A technique to create to match this would be {@code 'path.match("span").classes("a","b")'}/
	 *
	 * @param firstCssClass   class of a HTML element that the matched HTML element must contain
	 * @param otherCssClasses any other classes that the matched HTML element must contain (optional).
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	T classes(String firstCssClass, String... otherCssClasses);

	/**
	 * Establishes that the matched HTML element should contain the given attribute and value. For example, if there
	 * is HTML that looks like this: {@code '<table title="The Tables Have Turned"></table>'}, a way to create a path
	 * to the table element would be to write {@code 'path.match("table").attribute("title","The Tables Have Turned")'}.
	 *
	 * @param attributeName  the name of the attribute
	 * @param attributeValue the value of the attribute
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	T attribute(String attributeName, String attributeValue);

	/**
	 * Establishes that the matched HTML element should contain an id attribute with a given value. For example, if
	 * there is HTML that looks like this: {@code '<div id="coffee"></div>'} the path code can match it using:
	 * {@code 'path.match("div").id("coffee")'}.
	 *
	 * @param idValue value of the id attribute of the current matched HTML element
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	T id(String idValue);

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
	 * the previously matched element. Will only work if the previously matched element is a &lt;td&gt; or &lt;th&gt; element or
	 * inside of one.
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
	 * the previously matched element. Will only work if the previously matched element is a &lt;td&gt; or &lt;th&gt; element or
	 * inside of one.
	 *
	 * @param elementName the tag name of the element
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	T downToFooter(String elementName);

	/**
	 * Establishes that the matched HTML element should pass the supplied filter using
	 * {@link HtmlElementMatcher#match(HtmlElement, HtmlElement)}.
	 *
	 * @param htmlElementMatcher the filter that will be applied to the matched {@link HtmlElement}
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	T filter(HtmlElementMatcher htmlElementMatcher);

	/**
	 * Negates the very next filter
	 *
	 * @return this {@link ElementFilter} instance, allowing method chaining to add more filtering rules over the
	 * HTML element being matched.
	 */
	T not();

}
