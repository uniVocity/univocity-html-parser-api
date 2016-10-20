/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import java.util.*;

/**
 * A {@code HtmlElement} contains information about HTML elements collected by the parser
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlElement {

	/**
	 * Returns {@code true} if the {@code HtmlElement} consists solely of text and {@code false} otherwise. For example,
	 * {@code <p class="highlight">cool text</p>} if the {@code HtmlElement} is the 'p' element, the isText() method
	 * will return {@code false}. On the {@code HtmlElement} with "cool text", the isText() method will return {@code true}.
	 *
	 * @return {@code true} if {@code HtmlElement} is just text, otherwise {@code false}
	 */
	boolean isText();

	/**
	 * Returns the HTML tag name associated with the element. For instance the tag name of the element
	 * {@code <span title="fan" id="electric"></span>} would be "span".
	 *
	 * @return the associated HTML tag as a string
	 */
	String tagName();

	/**
	 * Returns the parent of the HTML Element. A parent is defined as the element which directly contains the
	 * current element. For instance, given this HTML {@code <div> <h1>header</h1> <p>text</p> </div>}, the parent of the
	 * 'p' would be 'div'. If there is no parent, {@code null} is returned.
	 *
	 * @return the {@code HtmlElement}'s parent or {@code null} if no parent
	 */
	HtmlElement parent();

	/**
	 * Returns all the children of the {@code HtmlElement} in an array. If there are no children, it will return an
	 * empty array. For example, {@code <article> <h1>header</h1> <p>text</p> </article>}, running children() on
	 * article will return an array of size 2 with the contents being the 'h1' element and the 'p' element.
	 *
	 * @return an array with the children of the {@code HtmlElement} or a size 0 array if no children.
	 */
	HtmlElement[] children();

	/**
	 * Gets an attribute's value by its name as a {@code String}. If the element has no attributes or if the supplied
	 * attribute name does not exist within the element, then an empty {@code String} will be returned. For example,
	 * {@code <div title="hello"></div><footer>feet</footer>}, running attribute("title") on the div will return "hello".
	 * Running the same method on footer and the text "feet" will return a empty {@code String}.
	 *
	 * <p>To get an absolute URL from a link that could be relative, prefix the attribute name with "abs:". For example
	 * running attribute("abs:href") on {@code <a href="contact.html"} will return the absolute URL of contact.html,
	 * rather than simply returning the {@code String} "contact.html".</p>
	 *
	 * @param attributeName the name of the attribute
	 *
	 * @return the value of the supplied attribute, an empty {@code String} if supplied attribute doesn't exist.
	 */
	String attribute(String attributeName);

	/**
	 * Returns all the attribute names contained within the {@code HtmlElement} as a set of strings. Returns an empty set if
	 * there are no attributes.
	 *
	 * @return set of strings representing the attributes in the {@code HtmlElement}, or an empty set if no attributes.
	 */
	Set<String> attributeNames();

	/**
	 * Gets the combined text of this element and all its children. Whitespace is normalized.
	 *
	 * <p>
	 * For example, given HTML {@code <p>Hello  <b>there</b> now!</p>}, {@code p.text()} returns {@code "Hello there now!"}
	 * </p>
	 *
	 * @return unencoded text, or empty string if no text.
	 */
	String getText();

	/**
	 * Returns the {@code HtmlElement} that is located just after this {@code HtmlElement}. Returns {@code null} if there
	 * is no next sibling. For instance, given this HTML document:
	 * {@code <div> <h1>hello</h1> <p>text <span>saucepan<span> </p> </div>}, the next sibling of 'h1' is 'p'.
	 * The next sibling of 'p' is {@code null}.
	 *
	 * @return the {@code HtmlElement} located just after this {@code HtmlElement} or {@code null} if no such element.
	 */
	HtmlElement nextSibling();

	/**
	 * Returns the {@code HtmlElement} that is located just before this {@code HtmlElement} and is not a parent. Returns {@code null} if there
	 * is no previous sibling. For instance, given this HTML document: {@code <div> <h1>hello</h1> <p>text</p> </div>}, the previous
	 * sibling of 'p' is 'h1'. The previous sibling of 'h1' is {@code null} because div is a parent, not a sibling.
	 *
	 * @return the {@code HtmlElement} located just before this {@code HtmlElement} or {@code null} if no such element.
	 */
	HtmlElement previousSibling();

	/**
	 * Returns the id of the HTML element or an empty {@code String} if the element does not have a id. For example: The HTML
	 * of {@code <span id="test"></span>} will return "test" when running id() on it.
	 *
	 * @return the element's id as a String, or empty {@code String} if id non-existent
	 */
	String id();

	/**
	 * Returns a set of classes of the HTML element or an empty set if the element has no classes.
	 *
	 * @return HTML element's classes as a set of Strings or empty set if no classes
	 */
	Set<String> classes();

	/**
	 * Returns {@code true} if the specified element is a descendant of the current element. Returns {@code false} if otherwise. For
	 * example, in this HTML document:
	 *
	 * <p><hr><blockquote><pre>
	 * <table>
	 * 	<tr>
	 * 		<td> <span>First Row</span></td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td>Second Row</td>
	 * 	</tr>
	 * </table>
	 * </p></blockquote></pre><hr>
	 *
	 * <p>Writing tableElement.containsElementInHierarchy(spanElement) would return {@code true} as the span element is a descendant
	 * of the table element. Inverting the code to spanElement.containsElementInHierarchy(tableElement) would return {@code false}. </p>
	 *
	 * @param element the element that will be checked to see if it is a descendant of the current element.
	 *
	 * @return {@code true} if the specified element is a descendant of the current element.
	 */
	boolean containsElementInHierarchy(HtmlElement element);
}
