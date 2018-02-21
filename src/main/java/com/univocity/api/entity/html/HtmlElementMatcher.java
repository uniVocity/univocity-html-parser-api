package com.univocity.api.entity.html;

/**
 * A custom matcher to be triggered each time the parser visits a {@link HtmlElement}. Used
 * to determine if a visited {@link HtmlElement} matches a user-provided criteria.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlElement
 */
public interface HtmlElementMatcher {

	/**
	 * Used to determine if the currentElement should be matched by the parser. The lastMatchedElement can be used
	 * to provide information to determine if the currentElement should be matched.
	 *
	 * **Note:**lastMatchedElement may be `null`.
	 *
	 * @param lastMatchedElement the element that was matched last by the parser (may be `null`)
	 * @param currentElement     the element that the parser is current visiting
	 *
	 * @return `true` if the currentElement should be matched by the parser, `false` otherwise
	 */
	boolean match(HtmlElement lastMatchedElement, HtmlElement currentElement);
}
