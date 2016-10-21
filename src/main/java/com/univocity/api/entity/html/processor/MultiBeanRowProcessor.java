/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;

import java.util.*;


/**
 * A {@link RowProcessor} implementation for converting rows extracted by the {@link HtmlParser} into java objects.
 *
 * <p>The class types passed to the constructor of this class must contain the annotations provided in {@link com.univocity.parsers.annotations}.
 *
 * <p> For each row processed, one or more java bean instances of any given class will be created with their fields populated.
 * <p> Once all beans are populated from an individual input record, they will be sent to through the {@link #rowProcessed(Map, Context)} method,
 * where the user can access all beans parsed for that row.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see RowProcessor
 * @see BeanProcessor
 * @see MultiBeanProcessor
 */
public abstract class MultiBeanRowProcessor extends AbstractMultiBeanRowProcessor<HtmlParsingContext> implements RowProcessor {
	/**
	 * Creates a processor for java beans of multiple types
	 *
	 * @param beanTypes the classes with their attributes mapped to fields of records parsed by an {@link HtmlParser}.
	 */
	public MultiBeanRowProcessor(Class... beanTypes) {
		super(beanTypes);
	}
}