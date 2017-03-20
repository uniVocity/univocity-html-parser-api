/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import java.util.*;

/**
 * FIXME: javadoc
 */
public interface ElementContentHandler extends ContentHandler<ElementContentHandler> {

	List<String> getValues();

	String getValue();

}
