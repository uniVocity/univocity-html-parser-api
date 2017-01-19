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
	 * <p>
	 * <em>NOTE:</em>lastMatchedElement may be {@code null}.
	 * </p>
	 *
	 * @param lastMatchedElement the element that was matched last by the parser (may be {@code null})
	 * @param currentElement     the element that the parser is current visiting
	 *
	 * @return {@code true} if the currentElement should be matched by the parser, {@code false} otherwise
	 */
	boolean match(HtmlElement lastMatchedElement, HtmlElement currentElement);
}
