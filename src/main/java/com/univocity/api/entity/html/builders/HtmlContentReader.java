/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlContentReader {

	void getHeadingText();

	void getHeadingText(int headingRowIndex);

	void getTextAbove();

	void getTextAbove(int numberOfElementsAbove);

	void getText();

	void getText(int numberOfSiblingsToInclude);

	void getContentFrom(String attributeName);

	void getPrecedingText();

	void getPrecedingText(int numberOfSiblingsToInclude);

	void getFollowingText();

	void getFollowingText(int numberOfSiblingsToInclude);

	void getAttribute(String attributeName);
}
