/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import java.util.*;

/**
 * A class that contains information about HTML elements when the parser runs.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlElement {

	/**
	 * Returns true if the HtmlElement consists solely of text and false otherwise. For example, {@code <p class="highlight">cool text</p>}
	 * if the HtmlElement is the 'p' element, the isText() method will return false. If the HtmlElement is "cool text",
	 * the isText() method will return true.
	 *
	 * @return true if HtmlElement is just text, otherwise false
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
	 * 'p' would be 'div'. If there is no parent, null is returned.
	 *
	 * @return the HtmlElement's parent or null if no parent
	 */
	HtmlElement parent();

	/**
	 * Returns all the children of the HtmlElement in an array. If there are no children, it will return an empty, size 0
	 * array. For example, {@code <article> <h1>header</h1> <p>text</p> </article>}, running children() on
	 * article will return an array of size 2 with the contents being the 'h1' element and the 'p' element.
	 *
	 * @return an array of the children of the HtmlElement or a size 0 array if no children.
	 */
	HtmlElement[] children();

	/**
	 * Gets an attribute's value by its name as a String. If the HtmlElement is purely text, it will return null. If the
	 * element has no attributes or if the supplied attribute name does not exist within the element, then an empty String
	 * will be returned. For example, {@code <div title="hello"></div><footer>feet</footer>}, running attribute("title")
	 * on the div will return "hello". Running the same method on footer and the text "feet" will return a empty string
	 * and null respectively.
	 *
	 * <p>To get an absolute URL from a link that could be relative, prefix the attribute name with "abs:". For example
	 * running attribute("abs:href") on {@code <a href="contact.html"} will return the absolute URL of contact.html,
	 * rather than simply returning the string "contact.html".</p>
	 *
 	 * @param attributeName the name of the attribute
	 *
	 * @return the value of the supplied attribute, an empty string if supplied attribute doesn't exist or null if element
	 * is just text
	 */
	String attribute(String attributeName);

	/**
	 * Returns all the attribute names contained within the HtmlElement as a set of strings. Returns an empty set if
	 * there are no attributes.
	 *
	 * @return set of strings representing the attributes in the HTMLElement, or an empty set if no attributes.
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
	String text();

	/**
	 * Returns the HTMLElement that is located just after this HTMLElement. Returns null if there is no next sibling. For
	 * instance, given this HTML document: {@code <div> <h1>hello</h1> <p>text <span>saucepan<span> </p> </div>}, the next
	 * sibling of 'h1' is 'p'. The next sibling of 'p' is null.
	 *
	 * @return the HTMLElement located just after this HTMLElement or null if no such element.
	 */
	HtmlElement nextSibling();

	/**
	 * Returns the HTMLElement that is located just before this HTMLElement and is not a parent. Returns null if there
	 * is no previous sibling. For instance, given this HTML document: {@code <div> <h1>hello</h1> <p>text</p> </div>}, the previous
	 * sibling of 'p' is 'h1'. The previous sibling of 'h1' is null because div is a parent, not a sibling.
	 *
	 * @return the HTMLElement located just before this HTMLElement or null if no such element.
	 */
	HtmlElement previousSibling();

	/**
	 * Returns a new instance of the HTML element.
	 *
	 * @return the newly created HTML element.
	 */
	HtmlElement grab();

	/**
	 * Returns the id of the HTML element or an empty string if the element does not have a id. For example: The HTML
	 * of {@code <span id="test"></span>} will return "test" when running id() on it.
	 *
	 * @return the element's id as a String, or empty string if id non-existent
	 */
	String id();

	/**
	 * Returns a set of classes of the HTML element or an empty set if the element has no classes.
	 *
	 * @return HTML element's classes as a set of Strings or empty set if no classes
	 */
	Set<String> classes();

	/**
	 * Returns true if the specified element is a descendant of the current element. Returns false if otherwise. For
	 * example, in this HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<table>
	 *	<tr>
	 *		<td> <span>First Row</span></td>
	 *	</tr>
	 *	<tr>
	 *		<td>Second Row</td>
	 *	</tr>
	 *</table>
	 *</p></blockquote></pre><hr>
	 *
	 *<p>Writing tableElement.containsElementInHierarchy(spanElement) would return true as the span element is a descendant
	 * of the table element. Inverting the code to spanElement.containsElementInHierarchy(tableElement) would return false. </p>
	 *
	 * @param element the element that will be checked to see if it is a descendant of the current element.
	 *
	 * @return true if the specified element is a descendant of the current element.
	 */
	boolean containsElementInHierarchy(HtmlElement element);
}
