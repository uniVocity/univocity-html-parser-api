/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;

import java.util.*;

/**
 * An `ElementContentReader` allows the {@link HtmlElement}s that match rules defined in the {@link FieldPath}
 * to be acquired directly instead of capturing certain parts of them.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface ElementContentReader extends ContentReader<ElementContentHandler> {

	/**
	 * Gets all matching {@link HtmlElement}s when applying the previously defined matching rules.
	 *
	 * @return the list of all matching elements
	 */
	List<HtmlElement> getElements();

	/**
	 * Gets the first matching {@link HtmlElement} when applying the previously defined matching rules.
	 *
	 * @return the first matching element
	 */
	HtmlElement getElement();

}
