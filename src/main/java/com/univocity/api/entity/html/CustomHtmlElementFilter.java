package com.univocity.api.entity.html;

/**
 * Created by anthony on 12/01/17.
 */
public interface CustomHtmlElementFilter {

	boolean match(HtmlElement lastMatchedElement, HtmlElement currentElement);
}
