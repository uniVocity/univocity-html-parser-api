/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;
import com.univocity.api.io.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

/**
 * A `HtmlElement` contains information about HTML elements collected by the parser
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlElement {

	/**
	 * Returns `true` if this `HtmlElement` consists solely of text and `false` otherwise. For example,
	 * consider `<p class="highlight">cool text</p>`; If this `HtmlElement` is the 'p' element, `isText()`
	 * will return `false`. If this `HtmlElement` is just a node with "cool text", `isText()` will return `true`.
	 *
	 * **Note:** this method will return `false` if the `HtmlElement` contains text that is not meant to be rendered,
	 * such as comments and `<script>` tags. Use {@link #isData()} to identify such elements.
	 *
	 * @return `true` if this element is just text, otherwise `false`
	 */
	boolean isText();

	/**
	 * Returns `true` if this `HtmlElement` consists of data, i.e. content in comments or tags such as `<style>` or
	 * `<script>`, which should not render as text.
	 *
	 * @return `true` if this element is just invisible text, otherwise `false`
	 */
	boolean isData();

	/**
	 * Returns `true` if this `HtmlElement` consists of comments, i.e. free text between `<!--` and `-->`, which should
	 * not render as text.
	 *
	 * @return `true` if element is just comments, otherwise `false`
	 */
	boolean isComment();

	/**
	 * Returns the HTML tag name associated with the element. For instance the tag name of the element
	 * {@code <span title="fan" id="electric"></span>} would be "span".
	 *
	 * If the current HTML element is not a tag, i.e. it is a text, comment or data node, then
	 * `"#text"`, `"#comment"` or `"#data"` will be returned, respectively.
	 *
	 * @return the associated HTML tag as a string
	 */
	String tagName();

	/**
	 * Returns the parent of this Element. A parent is defined as the element which directly contains the
	 * current element. For instance, given `<div> <h1>header</h1> <p>text</p> </div>`, the parent of
	 * `<p>` would be `<div>`. If there is no parent, `null` is returned.
	 *
	 * @return the `HtmlElement`'s parent or `null` if no parent is available
	 */
	HtmlElement parent();

	/**
	 * Returns a copy of all children of this element in an array. If there are no children, it will return an
	 * empty array. For example,`<article> <h1>header</h1> <p>text</p> </article>`, running `children()` on
	 * `article` will return an array of size 2 with the contents being the `<h1>` element and the `<p>`.
	 *
	 * @return an array with the children of this element or an empty array if no children.
	 */
	List<HtmlElement> children();

	/**
	 * Returns an attribute value by its name as a `String`. If the element has no attributes or if the supplied
	 * attribute name does not exist within the element, then an empty `String` will be returned. For example,
	 * `<div title="hello"></div><footer>feet</footer>`, running `attribute("title")` on the `<div>` will return "hello".
	 * Running the same method on `<footer>` will return `""`.
	 *
	 * To get an absolute URL from a link that could be relative, prefix the attribute name with "abs:". For example
	 * running `attribute("abs:href")` on `<a href="contact.html` will return the absolute URL of `contact.html`,
	 * rather than simply returning the `String` "contact.html".
	 *
	 * @param attributeName the name of the attribute
	 *
	 * @return the value of the supplied attribute, an empty `String` if supplied attribute doesn't exist.
	 */
	String attribute(String attributeName);

	/**
	 * Modifies an attribute value (i.e. key=value). If the attribute already exists, it will be replaced.
	 *
	 * @param attributeName  the name of the attribute to modify.
	 * @param attributeValue the value to set for the given attribute name
	 */
	void attribute(String attributeName, String attributeValue);

	/**
	 * Returns all the attribute names contained within this element as a set of `String`. Returns an empty set if
	 * there are no attributes.
	 *
	 * @return set of strings representing the attributes in this element, or an empty set if no attributes.
	 */
	Set<String> attributeNames();

	/**
	 * Gets the combined text of this element and all its children. Whitespace is normalized so the only separation
	 * between words is a single `' '` character.
	 *
	 * For example, given HTML `<p>Hello  <b>there</b> now!</p>`, the call to `p.text()` returns `"Hello there now!"`
	 *
	 * @return the combined text of this element and its children, or empty string if no text.
	 */
	String text();

	/**
	 * Get the data content of this element and all its children. Data consists of textual content inside comments, or
	 * tags such as `style` or `script`, for example, where the contents should not render as text.
	 *
	 * @return the data content of this element, or empty if no data.
	 */
	String data();


	/**
	 * Returns the `HtmlElement` that is located just after this element. Returns `null` if there
	 * is no next sibling. For instance, given `<div> <h1>hello</h1> <p>text <span>saucepan<span> </p> </div>`, the next
	 * sibling of `<h1>` is `<p>`. The next sibling of `<p>` is `null`.
	 *
	 * @return the element located just after this `HtmlElement` or `null` if no such element.
	 */
	HtmlElement nextSibling();

	/**
	 * Returns the `HtmlElement` that is located just before this element. Returns `null` if there
	 * is no previous sibling. For instance, given `<div> <h1>hello</h1> <p>text</p> </div>`, the previous
	 * sibling of `<p>` is `<h1>` and previous sibling of `<h1>` is `null`.
	 *
	 * @return the element located just before this `HtmlElement` or `null` if no such element.
	 */
	HtmlElement previousSibling();

	/**
	 * Returns the id of this element or an empty `String` if the element does not have an `id` attribute. For example,
	 * in `<span id="test"></span>` calling `id()` from the `<span>` element will return `"test"`.
	 *
	 * @return the element id as a `String` if available
	 */
	String id();

	/**
	 * Returns the set of CSS classes of this element, or an empty set if has element has no `class` attribute defined.
	 *
	 * @return HTML element's classes as a set of Strings or empty set if no classes
	 */
	Set<String> classes();

	/**
	 * Returns `true` if the specified element is a descendant of the current element. Returns `false` if otherwise. For
	 * example, in this HTML document:
	 *
	 * ```html
	 * <table>
	 * <tr>
	 * <td>
	 * <span>First Row</span>
	 * </td>
	 * </tr>
	 * <tr>
	 * <td>Second Row</td>
	 * </tr>
	 * </table>
	 * ```
	 *
	 * Writing `table.containsElementInHierarchy(span)` would return `true` as the `<span>` is a descendant
	 * of the `<table>`. Inverting the code to `span.containsElementInHierarchy(table)` would return `false`.
	 *
	 * @param element the element to find in the hierarchy of the current element.
	 *
	 * @return `true` if the specified element is a descendant of the current element.
	 */
	boolean containsElementInHierarchy(HtmlElement element);


	/**
	 * Searches for elements that match a CSS query, with the current `HtmlElement` as the starting context. Matched elements
	 * may include this `HtmlElement`, or any of its children.
	 *
	 * * `el.query("a[href]")` - finds links (`a` tags with `href` attributes)
	 * * `el.query("a[href*=example.com]")` - finds links pointing to example.com (loosely)
	 *
	 * <h2>Selector syntax</h2>
	 *
	 * A selector is a chain of simple selectors, separated by combinators. Selectors are case insensitive (including against
	 * elements, attributes, and attribute values).
	 *
	 * The universal selector (*) is implicit when no element selector is supplied (i.e. {@code *.header} and {@code .header}
	 * is equivalent).
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
	 * @param cssQuery a CSS-like query
	 *
	 * @return all elements that match the query from the current element (empty if none match)
	 *
	 * @throws IllegalArgumentException if the CSS query is invalid.
	 */
	List<HtmlElement> query(String cssQuery);

	/**
	 * Generates a W3C DOM document from the current HTML element.
	 *
	 * The resulting document is guaranteed to have a `<head>` and a `<body>` element. If the current HTML element
	 * is the root of the HTML tree, it will be directly mapped to the output Document object. Otherwise, it will be added into
	 * automatically generated `<head>` or `<body>` elements, if it is not a `<head>` or `<body>`.
	 *
	 * @return the {@link org.w3c.dom.Document} representing the current HTML tree if this is the root node, or a
	 * {@link org.w3c.dom.Document} containing this HTML element.
	 */
	org.w3c.dom.Document toW3CDocument();

	/**
	 * Saves the element to a local file using the {@link FileProvider}, searching all child nodes for external
	 * resources (e.g. `href`, `src`) and saving them to local files. All references to those resources in the resulting
	 * HTML will point to the locally saved references.
	 *
	 * @param saveFile     provides the path of where to save the files
	 * @param fetchOptions options used to control the fetching of resources
	 *
	 * @return a {@link FetchOutput} instance with different options to access the downloaded contents.
	 *
	 * @see FetchOptions
	 * @see FetchOutput
	 */
	FetchOutput fetchResources(FileProvider saveFile, FetchOptions fetchOptions);


	/**
	 * Save the element to a local file using the {@link File}, searching all child nodes for external
	 * resources (e.g. `href`, `src`) and saving them to local files. All references to those resources in the resulting
	 * HTML will point to the locally saved references.
	 *
	 * @param saveFile     provides the path of where to save the files
	 * @param fetchOptions options used to control the fetching of resources
	 *
	 * @return a FetchOutput instance with different options to access the downloaded files
	 *
	 * @see FetchOptions
	 * @see FetchOutput
	 */
	FetchOutput fetchResources(File saveFile, FetchOptions fetchOptions);

	/**
	 * Save the element to a local file using the {@link File}, searching all child nodes for external
	 * resources (e.g. `href`, `src`) and saving them to local files. All references to those resources in the resulting
	 * HTML will point to the locally saved references.
	 *
	 * @param saveFile     provides the path of where to save the files
	 * @param encoding     the desired character encoding for the destination file
	 * @param fetchOptions options used to control the fetching of resources
	 *
	 * @return a FetchOutput instance with different options to access the downloaded files
	 *
	 * @see FetchOptions
	 * @see FetchOutput
	 */
	FetchOutput fetchResources(File saveFile, String encoding, FetchOptions fetchOptions);

	/**
	 * Save the element to a local file using the {@link File}, searching all child nodes for external
	 * resources (e.g. `href`, `src`) and saving them to local files. All references to those resources in the resulting
	 * HTML will point to the locally saved references.
	 *
	 * @param saveFile     provides the path of where to save the files
	 * @param encoding     the desired character encoding for the destination file
	 * @param fetchOptions options used to control the fetching of resources
	 *
	 * @return a FetchOutput instance with different options to access the downloaded files
	 *
	 * @see FetchOptions
	 * @see FetchOutput
	 */
	FetchOutput fetchResources(File saveFile, Charset encoding, FetchOptions fetchOptions);


	/**
	 * Save the element to a local file at the path `pathToFile`, searching all child nodes for external
	 * resources (e.g. `href`, `src`) and saving them to local files. All references to those resources in the resulting
	 * HTML will point to the locally saved references.
	 *
	 * @param pathToFile   the string path to the output file
	 * @param fetchOptions options used to control the fetching of resources
	 *
	 * @return a FetchOutput instance with different options to access the downloaded files
	 *
	 * @see FetchOptions
	 * @see FetchOutput
	 */
	FetchOutput fetchResources(String pathToFile, FetchOptions fetchOptions);

	/**
	 * Save the element a local file at the path `pathToFile`, searching all child nodes for external
	 * resources (e.g. `href`, `src`) and saving them to local files. All references to those resources in the resulting
	 * HTML will point to the locally saved references.
	 *
	 * @param pathToFile   the string path to the output file
	 * @param encoding     the desired character encoding for the destination file
	 * @param fetchOptions options used to control the fetching of resources
	 *
	 * @return a FetchOutput instance with different options to access the downloaded files
	 *
	 * @see FetchOptions
	 * @see FetchOutput
	 */
	FetchOutput fetchResources(String pathToFile, String encoding, FetchOptions fetchOptions);

	/**
	 * Save the element to a local file at the path `pathToFile`, searching all child nodes for external
	 * resources (e.g. `href`, `src`) and saving them to local files. All references to those resources in the resulting
	 * HTML will point to the locally saved references.
	 *
	 * @param pathToFile   the string path to the output file
	 * @param encoding     the desired character encoding for the destination file
	 * @param fetchOptions options used to control the fetching of resources
	 *
	 * @return a FetchOutput instance with different options to access the downloaded files
	 *
	 * @see FetchOptions
	 * @see FetchOutput
	 */
	FetchOutput fetchResources(String pathToFile, Charset encoding, FetchOptions fetchOptions);

	/**
	 * Starts a matching sequence so chaining selector methods can be used to traverse the HtmlElement
	 *
	 * Example:
	 *
	 * ```java
	 * HtmlElement root = HtmlParser.parse(new UrlReaderProvider("https://www.univocity.com/pages/about-parsers"));
	 * String pageTitle = root.query().match("title").getText();
	 * ```
	 * The `String` variable `pageTitle` would have the text inside the `<title>` element of the HTML page available at
	 * "https://www.univocity.com/pages/about-parsers"
	 *
	 * @return the path start ready for more matching rules to be added or values returned.
	 *
	 * @see ElementPathStart
	 */
	ElementPathStart query();

	/**
	 * Runs through the hierarchy of this element and collects the values of any input elements, including select lists,
	 * radio buttons and checkboxes. Inputs without a `name` attribute are ignored.
	 *
	 * @return a map of input names and their values
	 */
	Map<String, String[]> inputValues();

	/**
	 * Runs through the hierarchy of this element and collects the values of any input elements, including select lists,
	 * radio buttons and checkboxes. Inputs without an `id` attribute are ignored. If more than one element exists with
	 * the same ID, the value of the first element found will be considered.
	 *
	 * @return a map of input ids and their values
	 */
	Map<String, String> inputValuesById();

	/**
	 * Saves the HTML representation of this element to a file
	 *
	 * @param fileProvider the output file details into which the contents will be written.
	 */
	void saveToFile(FileProvider fileProvider);
}
