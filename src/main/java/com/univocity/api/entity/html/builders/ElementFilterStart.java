/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;

/**
 * Provides the first step of an {@link ElementFilter}. Essentially, the `ElementFilterStart` defines which HTML element
 * should be matched when the {@link com.univocity.api.entity.html.HtmlParser} is run. Elements matched can be subsequently
 * filtered using the rules available from {@link ElementFilter}, or have their data retrieved using the options provided
 * by {@link ContentReader}.
 *
 * This is the first step in creating a {@link FieldPath} for a field of an entity configured via {@link HtmlEntitySettings}.
 * When the parser processes an input HTML, it will run all filtering rules applied over the elements whose tag names
 * match with the rules defined using the methods provided by this class.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see FieldPath
 * @see PartialPath
 * @see ElementFilter
 */
public interface ElementFilterStart<T extends ElementFilter<T>> {
	/**
	 * Matches a given tag name.
	 *
	 * For example, to get the text of all `span` elements on a HTML document, one would
	 * have to simply write:
	 *
	 * ```java
	 * //Set up
	 * HtmlEntityList htmlEntityList = new HtmlEntityList();
	 *
	 * //Matching Rule
	 * htmlEntityList.configureEntity("test")
	 *     .addField("allSpanElements")
	 *     .match("span")
	 *     .getText();
	 * ```
	 *
	 * When the parser runs, it will match every `span` element found on the HTML document and return their text content.
	 *
	 * Multiple matching rules can be chained together which creates a more specific path. Given a sequence of elements
	 * to be matched, the parser will traverse the HTML structure looking for the first element in the sequence to match.
	 *
	 * Once the first element of the sequence is found, the parser will then look for the next element in the sequence.
	 * This next element **must** be in the hierarchy of the previous element found **or** be one of the next siblings
	 * of the previous element.
	 *
	 * For example, consider the HTML below:
	 *
	 * ```html
	 * <div>
	 *   <h1>Bad Title</h1>
	 * </div>
	 * <article>
	 *   <h1>Good Title</h1>
	 * </article>
	 * <h1>Also Good Title</h1>
	 * ```
	 *
	 * The code to only get the text from `h1` elements inside or next to `article` elements is:
	 *
	 * ```java
	 * htmlEntityList.configureEntity("test")
	 *     .addField("headers")
	 *     .match("article")
	 *     .match("h1")
	 *     .getText();
	 * ```
	 *
	 * The parser will capture "Good Title" and "Also Good Title". This is because the matching rules will look for
	 * `article` elements, then `h1` elements inside the corresponding `article`, and finally `h1` elements following the
	 * `article`.
	 *
	 * As this method returns an {@link ElementFilter}, the user can provide further matching rules to more precisely match
	 * the given element, or other elements associated to it. Very specific paths can be created to capture data from
	 * virtually anywhere in a HTML document.
	 *
	 * @param tagName tag name of the element that will be matched.
	 *
	 * @return a {@link ElementFilter} so that filtering rules over HTML elements with the given tag name can be defined
	 */
	T match(String tagName);

	/**
	 * Matches a given tag name and its occurrence index among neighboring nodes within the same parent.
	 *
	 * For example, consider the following HTML table:
	 *
	 * ```html
	 * <table>
	 *   <tr>
	 *     <td>Email Address</td>
	 *     <td>bla@email.com</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Home Address</td>
	 *     <td>123 Some St</td>
	 *   </tr>
	 * </table>
	 * <table>
	 *   <tr>
	 *     <td>Email Address</td>
	 *     <td>some@one.com</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Home Address</td>
	 *     <td>456 Another St</td>
	 *   </tr>
	 * </table>
	 * ```
	 *
	 * To capture the contents under "Home address", one could write:
	 *
	 * ```java
	 * HtmlEntitySettings address = entityList.configureEntity("address");
	 * address.addField("line1")
	 *     .match("tr", 2) //matches the second tr element of each table
	 *     .match("td", 2) //inside the previously matched tr, match the second occurrence of the td element
	 *     .getText();     //gets the text of the matched td element.
	 * ```
	 *
	 * The example above should collect the values:
	 *
	 * ```
	 * 123 Some St
	 * 456 Another St
	 * ```
	 *
	 * @param tagName    tag name of the element that will be matched.
	 * @param occurrence occurrence index of elements whose tag name matches within a given parent node.
	 *
	 * @return a {@link ElementFilter} so that filtering rules over HTML elements with the given tag name can be defined
	 */
	T match(String tagName, int occurrence);

	/**
	 * Matches the first occurrence of the given tag name among neighboring nodes within the same parent.
	 *
	 * For example, consider the following HTML table:
	 *
	 * ```html
	 * <table>
	 *   <tr>
	 *     <td>Email Address</td>
	 *     <td>bla@email.com</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Home Address</td>
	 *     <td>123 Some St</td>
	 *   </tr>
	 * </table>
	 * <table>
	 *   <tr>
	 *     <td>Email Address</td>
	 *     <td>some@one.com</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Home Address</td>
	 *     <td>456 Another St</td>
	 *   </tr>
	 * </table>
	 * ```
	 *
	 * To capture the contents under "Email Address", one could write:
	 *
	 * ```java
	 * HtmlEntitySettings email = entityList.configureEntity("email");
	 * email.addField("email")
	 *     .matchFirst("tr") //matches the first tr element of each table
	 *     .matchLast("td")  //inside the previously matched tr, match the last occurrence of the td element
	 *     .getText();       //gets the text of the matched td element.
	 * ```
	 *
	 * This will capture "bla@email.com" but not "123 Some st" as this `td` is not inside the first `tr` matched in
	 * the `table`.
	 *
	 * It will also capture "some@one.com" as this `td` is inside a different parent `table`.
	 *
	 * @param tagName    tag name of the first element to be matched inside its parent.
	 *
	 * @return a {@link ElementFilter} so that filtering rules over HTML elements with the given tag name can be defined
	 */
	T matchFirst(String tagName);

	/**
	 * Matches the last occurrence of the given tag name among neighboring nodes within the same parent.
	 *
	 * For example, consider the following HTML table:
	 *
	 * ```html
	 * <table>
	 *   <tr>
	 *     <td>Email Address</td>
	 *     <td>bla@email.com</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Home Address</td>
	 *     <td>123 Some St</td>
	 *   </tr>
	 * </table>
	 * <table>
	 *   <tr>
	 *     <td>Email Address</td>
	 *     <td>some@one.com</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Home Address</td>
	 *     <td>456 Another St</td>
	 *   </tr>
	 * </table>
	 * ```
	 *
	 * To capture the contents under "Home address", one could write:
	 *
	 * ```java
	 * HtmlEntitySettings address = entityList.configureEntity("address");
	 * address.addField("line1")
	 *     .matchLast("tr") //matches the last tr element of each table
	 *     .matchLast("td")  //inside the previously matched tr, match the last occurrence of the td element
	 *     .getText();       //gets the text of the matched td element.
	 * ```
	 *
	 * This will capture "123 Some St" from the first `table`. It will also capture "456 Another St" as this `td` is
	 * inside a different parent `table`.
	 *
	 * @param tagName    tag name of the last element to be matched inside its parent.
	 *
	 * @return a {@link ElementFilter} so that filtering rules over HTML elements with the given tag name can be defined
	 */
	T matchLast(String tagName);

	/**
	 * Selects what HTML element the parser must match using a CSS query. The query is applied over the children of the current
	 * element in the path.
	 *
	 * Matched elements may include the current element itself, or any of its children, depending on the query.
	 *
	 * ## Selector syntax
	 *
	 * A selector is a chain of simple selectors, separated by combinators. Selectors are case insensitive (including against
	 * elements, attributes, and attribute values).
	 *
	 * The universal selector (*) is implicit when no element selector is supplied (i.e.`*.header` and `.header` are equivalent).
	 *
	 * <table summary="">
	 * <tr><th align="left">Pattern</th><th align="left">Matches</th><th align="left">Example</th></tr>
	 * <tr><td><code>*</code></td><td>any element</td><td><code>*</code></td></tr>
	 * <tr><td><code>tag</code></td><td>elements with the given tag name</td><td><code>div</code></td></tr>
	 * <tr><td><code>*|E</code></td><td>elements of type E in any namespace <i>ns</i></td><td><code>*|name</code> finds <code>&lt;fb:name&gt;</code> elements</td></tr>
	 * <tr><td><code>ns|E</code></td><td>elements of type E in the namespace <i>ns</i></td><td><code>fb|name</code> finds <code>&lt;fb:name&gt;</code> elements</td></tr>
	 * <tr><td><code>#id</code></td><td>elements with attribute ID of "id"</td><td><code>div#wrap</code>, <code>#logo</code></td></tr>
	 * <tr><td><code>.class</code></td><td>elements with a class name of "class"</td><td><code>div.left</code>, <code>.result</code></td></tr>
	 * <tr><td><code>[attr]</code></td><td>elements with an attribute named "attr" (with any value)</td><td><code>a[href]</code>, <code>[title]</code></td></tr>
	 * <tr><td><code>[^attrPrefix]</code></td><td>elements with an attribute name starting with "attrPrefix". Use to find elements with HTML5 datasets</td><td><code>[^data-]</code>, <code>div[^data-]</code></td></tr>
	 * <tr><td><code>[attr=val]</code></td><td>elements with an attribute named "attr", and value equal to "val"</td><td><code>img[width=500]</code>, <code>a[rel=nofollow]</code></td></tr>
	 * <tr><td><code>[attr=&quot;val&quot;]</code></td><td>elements with an attribute named "attr", and value equal to "val"</td><td><code>span[hello="Cleveland"][goodbye="Columbus"]</code>, <code>a[rel=&quot;nofollow&quot;]</code></td></tr>
	 * <tr><td><code>[attr^=valPrefix]</code></td><td>elements with an attribute named "attr", and value starting with "valPrefix"</td><td><code>a[href^=http:]</code></td></tr>
	 * <tr><td><code>[attr$=valSuffix]</code></td><td>elements with an attribute named "attr", and value ending with "valSuffix"</td><td><code>img[src$=.png]</code></td></tr>
	 * <tr><td><code>[attr*=valContaining]</code></td><td>elements with an attribute named "attr", and value containing "valContaining"</td><td><code>a[href*=/search/]</code></td></tr>
	 * <tr><td><code>[attr~=<em>regex</em>]</code></td><td>elements with an attribute named "attr", and value matching the regular expression</td><td><code>img[src~=(?i)\\.(png|jpe?g)]</code></td></tr>
	 * <tr><td></td><td>The above may be combined in any order</td><td><code>div.header[title]</code></td></tr>
	 * <tr><td><td colspan="3"><h3>Combinators</h3></td></tr>
	 * <tr><td><code>E F</code></td><td>an F element descended from an E element</td><td><code>div a</code>, <code>.logo h1</code></td></tr>
	 * <tr><td><code>E {@literal >} F</code></td><td>an F direct child of E</td><td><code>ol {@literal >} li</code></td></tr>
	 * <tr><td><code>E + F</code></td><td>an F element immediately preceded by sibling E</td><td><code>li + li</code>, <code>div.head + div</code></td></tr>
	 * <tr><td><code>E ~ F</code></td><td>an F element preceded by sibling E</td><td><code>h1 ~ p</code></td></tr>
	 * <tr><td><code>E, F, G</code></td><td>all matching elements E, F, or G</td><td><code>a[href], div, h3</code></td></tr>
	 * <tr><td><td colspan="3"><h3>Pseudo selectors</h3></td></tr>
	 * <tr><td><code>:lt(<em>n</em>)</code></td><td>elements whose sibling index is less than <em>n</em></td><td><code>td:lt(3)</code> finds the first 3 cells of each row</td></tr>
	 * <tr><td><code>:gt(<em>n</em>)</code></td><td>elements whose sibling index is greater than <em>n</em></td><td><code>td:gt(1)</code> finds cells after skipping the first two</td></tr>
	 * <tr><td><code>:eq(<em>n</em>)</code></td><td>elements whose sibling index is equal to <em>n</em></td><td><code>td:eq(0)</code> finds the first cell of each row</td></tr>
	 * <tr><td><code>:has(<em>selector</em>)</code></td><td>elements that contains at least one element matching the <em>selector</em></td><td><code>div:has(p)</code> finds divs that contain p elements </td></tr>
	 * <tr><td><code>:not(<em>selector</em>)</code></td><td>elements that do not match the <em>selector</em>.</td><td><code>div:not(.logo)</code> finds all divs that do not have the "logo" class.<code>div:not(:has(div))</code> finds divs that do not contain divs.</td></tr>
	 * <tr><td><code>:contains(<em>text</em>)</code></td><td>elements that contains the specified text. The search is case insensitive. The text may appear in the found element, or any of its descendants.</td><td><code>p:contains(univocity)</code> finds p elements containing the text "univocity".</td></tr>
	 * <tr><td><code>:matches(<em>regex</em>)</code></td><td>elements whose text matches the specified regular expression. The text may appear in the found element, or any of its descendants.</td><td><code>td:matches(\\d+)</code> finds table cells containing digits. <code>div:matches((?i)login)</code> finds divs containing the text, case insensitively.</td></tr>
	 * <tr><td><code>:containsOwn(<em>text</em>)</code></td><td>elements that directly contain the specified text. The search is case insensitive. The text must appear in the found element, not any of its descendants.</td><td><code>p:containsOwn(univocity)</code> finds p elements with own text "univocity".</td></tr>
	 * <tr><td><code>:matchesOwn(<em>regex</em>)</code></td><td>elements whose own text matches the specified regular expression. The text must appear in the found element, not any of its descendants.</td><td><code>td:matchesOwn(\\d+)</code> finds table cells directly containing digits. <code>div:matchesOwn((?i)login)</code> finds divs containing the text, case insensitively.</td></tr>
	 * <tr><td><code>:containsData(<em>data</em>)</code></td><td>elements that contains the specified <em>data</em>. The contents of {@code script} and {@code style} elements, and {@code comment} nodes (etc) are considered data nodes, not text nodes. The search is case insensitive. The data may appear in the found element, or any of its descendants.</td><td><code>script:contains(univocity)</code> finds script elements containing the data "univocity".</td></tr>
	 * <tr><td></td><td>The above may be combined in any order and with other selectors</td><td><code>.light:contains(name):eq(0)</code></td></tr>
	 * <tr><td><code>:matchText</code></td><td>treats text nodes as elements, and so allows you to match against and select text nodes.</td><td>{@code p:matchText:firstChild} with input {@code One<br />Two} will return one element with text "{@code One}".</td></tr>
	 * <tr><td colspan="3"><h3>Structural pseudo selectors</h3></td></tr>
	 * <tr><td><code>:root</code></td><td>The element that is the root of the document. In HTML, this is the <code>html</code> element</td><td><code>:root</code></td></tr>
	 * <tr><td><code>:nth-child(<em>a</em>n+<em>b</em>)</code></td><td>elements that have <code><em>a</em>n+<em>b</em>-1</code> siblings <b>before</b> it in the document tree, for any positive integer or zero value of <code>n</code>, and has a parent element. For values of <code>a</code> and <code>b</code> greater than zero, this effectively divides the element's children into groups of a elements (the last group taking the remainder), and selecting the <em>b</em>th element of each group. For example, this allows the selectors to address every other row in a table, and could be used to alternate the color of paragraph text in a cycle of four. The <code>a</code> and <code>b</code> values must be integers (positive, negative, or zero). The index of the first child of an element is 1.
	 * In addition to this, <code>:nth-child()</code> can take <code>odd</code> and <code>even</code> as arguments instead. <code>odd</code> has the same signification as <code>2n+1</code>, and <code>even</code> has the same signification as <code>2n</code>.</td><td><code>tr:nth-child(2n+1)</code> finds every odd row of a table. <code>:nth-child(10n-1)</code> the 9th, 19th, 29th, etc, element. <code>li:nth-child(5)</code> the 5h li</td></tr>
	 * <tr><td><code>:nth-last-child(<em>a</em>n+<em>b</em>)</code></td><td>elements that have <code><em>a</em>n+<em>b</em>-1</code> siblings <b>after</b> it in the document tree. Otherwise like <code>:nth-child()</code></td><td><code>tr:nth-last-child(-n+2)</code> the last two rows of a table</td></tr>
	 * <tr><td><code>:nth-of-type(<em>a</em>n+<em>b</em>)</code></td><td>pseudo-class notation represents an element that has <code><em>a</em>n+<em>b</em>-1</code> siblings with the same expanded element name <em>before</em> it in the document tree, for any zero or positive integer value of n, and has a parent element</td><td><code>img:nth-of-type(2n+1)</code></td></tr>
	 * <tr><td><code>:nth-last-of-type(<em>a</em>n+<em>b</em>)</code></td><td>pseudo-class notation represents an element that has <code><em>a</em>n+<em>b</em>-1</code> siblings with the same expanded element name <em>after</em> it in the document tree, for any zero or positive integer value of n, and has a parent element</td><td><code>img:nth-last-of-type(2n+1)</code></td></tr>
	 * <tr><td><code>:first-child</code></td><td>elements that are the first child of some other element.</td><td><code>div {@literal >} p:first-child</code></td></tr>
	 * <tr><td><code>:last-child</code></td><td>elements that are the last child of some other element.</td><td><code>ol {@literal >} li:last-child</code></td></tr>
	 * <tr><td><code>:first-of-type</code></td><td>elements that are the first sibling of its type in the list of children of its parent element</td><td><code>dl dt:first-of-type</code></td></tr>
	 * <tr><td><code>:last-of-type</code></td><td>elements that are the last sibling of its type in the list of children of its parent element</td><td><code>tr {@literal >} td:last-of-type</code></td></tr>
	 * <tr><td><code>:only-child</code></td><td>elements that have a parent element and whose parent element hasve no other element children</td><td></td></tr>
	 * <tr><td><code>:only-of-type</code></td><td> an element that has a parent element and whose parent element has no other element children with the same expanded element name</td><td></td></tr>
	 * <tr><td><code>:empty</code></td><td>elements that have no children at all</td><td></td></tr>
	 * </table>
	 *
	 * @param cssQuery the CSS-like query to be used for matching elements of interest.
	 *
	 * @return a {@link ElementFilter} so that filtering rules can be applied over the HTML elements matched with the CSS query
	 */
	T select(String cssQuery);

	/**
	 * Specifies what element the parser must match based on the return value supplied by the given
	 * {@link HtmlElementMatcher}. When the parser runs it will invoke the {@link HtmlElementMatcher#match(HtmlElement, HtmlElement)}
	 * method for each node visited from the current matched node in path. If the result is `true`, then the node will be
	 * matched and the parser will proceed trying to match the next element in the matching sequence, if any.
	 *
	 * **Note:** the `lastMatchedElement` parameter in {@link HtmlElementMatcher#match(HtmlElement, HtmlElement)}
	 * **will always** be `null` if you use this method at the beginning of a matching sequence. Also note that in this
	 * case **all** elements of the HTML tree will be sent to your {@link HtmlElementMatcher} so you'd likely want its
	 * implementation to be as quick as possible.
	 *
	 * @param customHtmlElementMatcher the filter that will be used to determine if a visited HTML element should be matched
	 *
	 * @return a {@link ElementFilter} so that filtering rules over HTML elements that were matched by the supplied {@link HtmlElementMatcher}
	 */
	T match(HtmlElementMatcher customHtmlElementMatcher);


	T matchCurrent();
}
