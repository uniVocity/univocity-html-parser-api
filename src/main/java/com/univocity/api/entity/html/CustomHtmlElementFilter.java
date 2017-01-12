package com.univocity.api.entity.html;

/**
 * Defines a custom filter that is triggered each time a parse visits a {@link HtmlElement} on the HTML document. Used
 * to determine if the visited {@link HtmlElement} should be matched.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlElement
 */
public interface CustomHtmlElementFilter {

	/**
	 * Used to determine if the currentElement should be matched by the parser. The lastMatchedElement can be used
	 * to provide information to determine if the currentElement should be matched.
	 *
	 * @param lastMatchedElement the element that was matched last by the parser
	 * @param currentElement the element that the parser is current visiting
	 *
	 * @return true if the currentElement should be matched by the parser, false if otherwise
	 */
	boolean match(HtmlElement lastMatchedElement, HtmlElement currentElement);
}
