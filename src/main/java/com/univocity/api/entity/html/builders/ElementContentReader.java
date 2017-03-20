/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.*;

import java.util.*;

/**
 * FIXME: javadoc
 */
public interface ElementContentReader extends ContentReader<ElementContentHandler> {

	List<HtmlElement> getElements();

	HtmlElement getElement();


}
