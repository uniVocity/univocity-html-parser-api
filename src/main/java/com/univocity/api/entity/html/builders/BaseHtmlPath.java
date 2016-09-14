/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.builders.annotations.*;

/**
 * A class that facilitates the creation of paths to HTML Elements. These paths are used by the HTML Parser to determine
 * what data will be returned when the parsing process runs.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface BaseHtmlPath<T extends BaseHtmlPath<T>> {

	/**
	 * Creates a path ot the HTML element that has the specified text placed in an element before it. For example,
	 * given this simple HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<span>first</span><span>second</span><span>third</span>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>One technique to get the text of the first 'span' element is: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("followed").match("span").followedByText("second").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>The parser will return "first", as the element following the first 'span' element has the text "second"</p>
	 *
	 * @param text the text contained in the element that is placed after the element that will be matched
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedByText(String text);


	/**
	 * Creates a path ot the HTML element that has the specified text placed in an element after it. For example,
	 * given this simple HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<span>first</span><span>second</span><span>third</span>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>One technique to get the text of the last 'span' element is: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("preceded").match("span").precededByText("second").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>The parser will return "third" from the third 'span' element, as the element preceding this element has the text "second"</p>
	 *
	 * @param text the text contained in the element that is placed before the element that will be matched
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
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
	 *<p>One technique to get the text of the first 'strong' element is:</p>
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
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedBy(String elementName);

	/**
	 * Creates a path to the HTML element that has the specified element at the given distance following it. The distance
	 * is a measure of how many siblings away the specified element is. For example, in this HTML:
	 * {@code <span>first</span> <span>second</span> <pre>surpriseHeader</pre> <strong>third</strong>}, the pre element
	 * is 1 distance away from the second span element and the strong element is at a distance of 2. A technique to
	 * get the text from the second span element would be:
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("fieldName").match("span").followedBy("strong",2).getText();
	 *</p></blockquote></pre><hr>
	 *
	 * <p>The parser will return "second" as the strong element is at the following distance of 2. The first span element
	 * is ignored as the distance from it to the strong element is 3.</p>
	 *
	 * @param elementName the name of the HTML element
	 * @param distance the number sibling that the parser will go to
	 *
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T followedBy(String elementName, int distance);

	/**
	 * Creates a path to the HTML element that has the given element place directly after it. This can be showcased
	 * by looking at this small HTML snippet: {@code <p>someText</p><p>moreText</p><footer>foot</footer>}. The second p
	 * element is immediately followed by a footer element, but the first p element is immediately followed by the other
	 * p element.
	 *
	 * <p>An example using this method can be described with the following HTML document:</p>
	 *
	 *<p><hr><blockquote><pre>
	 * <span>first</span><span>second</span><strong>third</strong>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>A technique to get the text of the second span element is:</p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("secondSpan").match("span").followedImmediatelyBy("strong").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>This will only return the text in the second span element as it is followed immediately by a strong element.
	 * The first span element element is followed immediately by a span element, so it will not be returned by the parser</p>
	 *
	 * @param elementName the element before this given element will be matched
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
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
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededBy(String elementName);

	/**
	 * Creates a path to the HTML element that has the specified element at the given distance before it. The distance
	 * is a measure of how many siblings away the specified element is. For example, in this HTML:
	 * {@code <strong>first</strong> <h1>surprise header</h1> <span>first</span> <span>second</span>}, the h1 element
	 * is 1 distance away from the first span element and the strong element is at a distance of 2. A technique to
	 * get the text from the first span element would be:
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("firstSpan").match("span").precededBy("strong",2).getText();
	 *</p></blockquote></pre><hr>
	 *
	 * <p>The parser will return "first" as the strong element is at the following distance of 2. The second span element
	 * is ignored as the distance from it to the strong element is 3.</p>
	 *
	 * @param elementName the name of the HTML element
	 * @param distance the number sibling that the parser will go to
	 *
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededBy(String elementName, int distance);


	/**
	 * Creates a path to the HTML element that has the given element place directly before it. This can be showcased
	 * by looking at this small HTML snippet: {@code <h1>feet</h1><p>someText</p><p>moreText</p>}. The first p
	 * element is immediately preceded by a h1 element, but the second p element is immediately preceded by the other
	 * p element.
	 *
	 * <p>An example using this method can be described with the following HTML document:</p>
	 *
	 *<p><hr><blockquote><pre>
	 *<strong>first</strong><span>second</span><span>third</span>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>A technique to get the text of the first span element is:</p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 entity.addField("firstSpan").match("span").precededImmediatelyBy("strong").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>This will only return the text in the first span element as it is followed immediately by a strong element.
	 * The second span element is preceded immediately by a span element, so it will not be returned by the parser</p>
	 *
	 * @param elementName the element after this given element will be matched
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.NEIGHBOUR)
	T precededImmediatelyBy(String elementName);

	/**
	 * Creates a path to the child of the specified HTML element. A child is defined as a HTML element that is directly
	 * contained by another HTML element. For example, given this simple HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<div>
	 *	<h1>one</h1>
	 *</div>
	 *<article>
	 *	<h1>two</h1>
	 *</article>;
	 *</p></blockquote></pre><hr>
	 *
	 *<p>One technique to get the text of the first 'h1' element is to write:</p>
	 *
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("child").match("h1").childOf("div").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>The matching rules, in plain english, can be described as: get the text of the 'h1' element that is the child
	 * of a div element. </p>
	 *
	 * @param elementName the name of the element
	 * @return  a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.PARENTS)
	T childOf(String elementName);

	/**
	 * Creates a path to the HTML element that is contained by the specified element. For example, given this simple
	 * HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<div>
	 *<span>I'm in a div!</span>
	 *</div>
	 *<article>
	 *<span>I'm in an article!</span>
	 *</article>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>One technique to get the text of the span element inside of the 'article' element would be to write:</p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("followed").match("span").containedBy("article").getText();
	 *</p></blockquote></pre><hr>
	 *
	 * <p>The matching rules can be described as "get the text of span elements inside of an article element". When
	 * the parser runs, it will return "I'm in an article!"</p>
	 *
	 * @param elementName the name of the element where elements contained within will be matched
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.PARENTS)
	T containedBy(String elementName);

	/**
	 * Creates a path to the HTML element that is contained by the given element. Differs from {@link #containedBy(String)}
	 * as it allows the specification of a depth limit. This depth limit restricts the number of times the parser can
	 * go up in the HTML hierarchy to search for the given element. The first parent it visits is at depth limit 1, the
	 * parent's parent is at depth limit 2 and so on. For an example, given this HTML document:
	 *
	 *
	 *<p><hr><blockquote><pre>
	 *<div>
	 *	<article>
	 *		<header>
	 *			<span>first</span>
	 *		</header>
	 *	</article>
	 *</div>
	 *<div>
	 *	<article>
	 *		<span>second</span>
	 *	</article>
	 *</div>
	 *</p></blockquote></pre><hr>
	 *
	 * <p>A technique to get the text of the second span element would be:</p>
	 *
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("writing").match("span").containedBy("div",2).getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>This will only return 'second' from the last span. While the first span element is contained by a div, the div
	 * is at a depth level of 3 meaning that the parser will ignore it.</p>
	 *
	 * @param elementName the name of the element that contains the element that apath will be created to.
	 * @param depthLimit the limit of how far the parser will search up the HTML hierarchy
	 *
	 * @return  a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.PARENTS)
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
	 *</p></blockquote></pre><hr>
	 *
	 * <p>Which will result in all the text apart from the table headers being returned.</p>
	 *
	 * @param headerElementName the header element of a table that will be matched
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T underHeader(String headerElementName);

	/**
	 * Creates a path to the HTML element that is directly under the given element in a table. For example, given this
	 * HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 * <table>
	 *<tr> <th>Animal</th>  <th>quantity</th> </tr>
	 *<tr> <td>Alpacas</td> <td>12</td> </tr>
	 *<tr> <td>Lions</td>   <td>5</td> </tr>
	 *</table>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>A technique to get the text of the last row in the table is:</p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("firstSpan").match("td").under("td").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>The parser will return the 'Lions' and '5'. This is because the matching rules state 'get the text from a 'td'
	 * that is under another 'td' element'. As the second row is under 'th' elements, it is ignored by the parser.</p>
	 *
	 * @param elementName The name of the element above the element that a path will be created to
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.TABLE)
	T under(String elementName);

	/**
	 * Creates a path to the HTML element that is the parent of the specified element. A parent is defined as the element
	 * that directly contains an element. For example: {@code <div> <article> <h1> heading </h1> <p> text </p> </article>
	 * </div>}. The article element is the parent of both the h1 and p elements. The div element is parent of article
	 * but <strong>not</strong> parent of h1 and p. A technique for using parentOf can be showcased using this simple
	 * HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<div title="one">
	 *<h1>good</h1>
	 *</div>
	 *<div title="two">
	 *<h2>bad</h2>
	 *</div>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>A technique to get the title of the first div element is to write: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("parent").match("div").parentOf("h1").getAttribute("title");
	 *</p></blockquote></pre><hr>
	 *
	 *<p>The parser will return "one", as the parent of the h1 element is the first div.</p>
	 *
	 * @param elementName the name of the element where the parent of which will be matched
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T parentOf(String elementName);

	/**
	 * Creates a path to the HTML element that contains the given elements. For instance, given this simple HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<div>
	 *<article>
	 *	<p>Some Text</p>
	 *</article>
	 *<article>
	 *	<h1>Review: Tea</h1>
	 *	<p>It's good</p>
	 *</article>
	 *<article>
	 *	<h1>Discussion: Computers</h1>
	 *</article>
	 *</div>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>A technique to get the text of the{@code <p>} element in the middle article is: /p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("reviewText").match("article").containing("h1","p").match("p").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>The matching rules state, match an {@code <article>} element that contains a {@code <h1>} and  a {@code <p>} element.
	 * Then, get the text of a {@code <p>} element. As neither the first article nor the third article has both a
	 * {@code <h1>} and  a {@code <p>} element, they are ignored by the parser.</p>
	 *
	 * @param pathOfElementNames the element that contains these elements will be matched
	 *
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T containing(String... pathOfElementNames);

	/**
	 * Creates a path to the HTML element that contains the specified element. For instance, given this simple HTML
	 * document:
	 *
	 *<p><hr><blockquote><pre>
	 *<article>
	 *	<h1>Review: Tea</h1>
	 *	<p>It's good</p>
	 *</article>
	 *<article>
	 *	<h1>Discussion: Computers</h1>
	 *	<p>It's the future!</p>
	 *</article>;
	 *</p></blockquote></pre><hr>
	 *
	 *<p>A technique to get the 'p' text of the first article is to write: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("reviewText").match("article").containing("h1").withText("Review*").match("p").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>The matching rules, in plain english, can be described as "get the text of the p element that is in the article
	 * that contains a h1 element starting with 'Review' ". The parser will return 'It's good' when it runs. </p>
	 *
	 * @param elementName the HTML element that contains this element will be matched
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T containing(String elementName);

	/**
	 * Creates a path to the HTML element that contains the given element. Differs from {@link #containing(String)} by
	 * allowing the specification of a depth limit. This depth limit determines how far down in the hierarchy the parser
	 * will search for the element. For example, given this HTML document:
	 *
	 *
	 *<p><hr><blockquote><pre>
	 *<div title=\pen\>
	 *	<article>
	 *		<header>
	 *			<span></span>
	 *		</header>
	 *	</article>
	 *</div>
	 *<div title=\crayon\>
	 *	<article>
	 *		<span></span>
	 *	</article>
	 *</div>;
	 *</p></blockquote></pre><hr>
	 *
	 *<p>A technique of getting the title of the second div element would be:</p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("writing").match("div").containing("span",2).getAttribute("title");
	 *</p></blockquote></pre><hr>
	 *
	 *<p>Running the parser will result in only 'crayon' being returned. This is because in the first div, the span is
	 * at the depth of 3. As the depth limit as been set at 2, the parser will ignore the first div.</p>
	 *
	 * @param elementName the HTML element that contains this element will be matched
	 * @param depthLimit the limit of how far the parser will go down the hierarchy to find the given element
	 *
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.INSIDE)
	T containing(String elementName, int depthLimit);

	/**
	 * Creates a path to a HTML element that <strong>starts with</strong> the specified text. Is case insensitive. It also supports the special characters of
	 * '*' and '?'.
	 *
	 * <p>* is used by the parser to match any characters. For instance, there is a short HTML document of '{@code <div> <span>abcdef</span> <span>kettle</span> </div>}'.
	 * One technique to match the first span would be to write:
	 * </p>
	 *
	 * <p><hr><blockquote><pre>
	 * path.match("span").withText("*f").getText();
	 * </p></blockquote></pre><hr>
	 *
	 * <p>
	 * The meaning of setting the matching text to '*f' means 'match the element with text ending with 'f' with any
	 * characters before it'. Alternatives to match the text could be: 'a' (text starting with a) and 'a*f' (text starting
	 * with a and ending with f).
	 * </p>
	 *
	 * <p>Note: Specifying 'a*' or 'a' will match the same elements.</p>
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
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withText(String textContent);

	/**
	 * Creates a path to the HTML element that matches <strong>exactly</strong> with the specified text. Is case insensitive.
	 * Also supports the special characters '*' and '?' explained in {@link #withText(String)}. An example of using
	 * withExactTest can be shown by looking at this simple HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *{@code <p title="cool">a</p>
	 *<p title="not-cool">ab</p> }
	 *</p></blockquote></pre><hr>
	 *
	 *<p>A technique to get the title of the first p is to write</p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *entity.addField("text").match("p").withExactText("a").getAttribute("title");
	 *</p></blockquote></pre><hr>
	 *
	 * <p>The parser will return "cool" as the first p element has the same exact text as the text specified. If the
	 * method {@link #withText(String)} was used instead, the parser would return "cool" <strong>and</strong> "not-cool".
	 * This is because {@link #withText(String)} will search for elements that <strong>start with</strong> "a".</p>
	 *
	 * @param textContent the exact string that will be matched
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withExactText(String textContent);

	/**
	 * Like {@link #withText(String)} but case sensitive. Creates a path to a HTML element that <strong>starts with</strong>
	 * text. Also supports the special characters of '*' and '?'.
	 *
 	 * @param textContent the string that will be matched, accounting for wildcard elements
	 *
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withTextMatchCase(String textContent);

	/**
	 * Case sensitive version of {@link #withExactText(String)}. Creates a path to the HTML element that
	 * matches <strong>exactly</strong> with the specified text. Also supports the special characters of '*' and '?'.
	 *
	 * @param textContent the string that will be matched, accounting for wildcard elements
	 * @return a {@link BaseHtmlPath} which allows more HTML elements to be added to the path, or the specification of
	 * what information to return.
	 */
	@Matcher(type = Matcher.Type.WITH_TEXT)
	T withExactTextMatchCase(String textContent);

	/**
	 * Creates a path to a HTML element by the specified class names. One to many class names can be inserted as
	 * parameters. As an example, assume there is HTML that looks like this: {@code '<span class = "a b"></span>'}. A
	 * technique to create a path to this would be {@code 'path.match("span").classes("a","b")'}/
	 *
	 * @param firstCssClass class of a HTML element that a path will be created to
	 * @param otherCssClasses any other classes that the HTML element contains, optional.
	 * @return a {@link BaseHtmlPath} which allows the more HTML elements to be added to the path
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
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
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	T attribute(String attributeName, String attributeValue);

	/**
	 * Creates a path to a HTML element by the specified id. For example, if there is HTML that looks like this:
	 * {@code '<div id="coffee"></div>'} and the path code looks like {@code 'path.match("div").id("coffee")'}, the path
	 * will be at the div shown in the HTML snippet.
	 *
	 * @param idValue the id of an element that a path will be created to
	 * @return a {@link BaseHtmlPath} which allows the more HTML elements to be added to the path
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	T id(String idValue);

}
