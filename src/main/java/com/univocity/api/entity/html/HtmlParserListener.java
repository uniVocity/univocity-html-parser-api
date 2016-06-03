/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public abstract class HtmlParserListener {

	public void parsingStarted(HtmlParsingContext context) {
	}

	public void elementVisited(HtmlElement element, HtmlParsingContext context) {
	}

	public void elementMatched(HtmlElement element, HtmlMatchingContext context) {
	}

	public void parsingEnded(HtmlParsingContext context) {
	}
}
