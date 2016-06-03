/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import java.util.*;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlElement {

	boolean isText();

	String tagName();

	HtmlElement parent();

	HtmlElement[] children();

	String attribute(String attributeName);

	Set<String> attributeNames();

	String text();

	HtmlElement nextSibling();

	HtmlElement previousSibling();

	HtmlElement grab();

	String id();

	Set<String> classes();

	boolean containsElementInHierarchy(HtmlElement element);
}
