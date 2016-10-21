/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;

/**
 *
 * A {@link RowProcessor} implementation for converting rows extracted from any implementation of {@link HtmlParser} into java objects.
 *
 * <p>The class types passed to the constructor of this class must contain the annotations provided in {@link com.univocity.parsers.annotations}.
 *
 * <p> For each row processed, one or more java bean instances of any given class will be created with their fields populated.
 * <p> Each individual instance will then be sent to the {@link #beanProcessed(Class, Object, Context)} method, where the user can access the
 * beans parsed for each row.
 *
 * @see HtmlParser
 * @see RowProcessor
 * @see BeanProcessor
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
public abstract class MultiBeanProcessor extends AbstractMultiBeanProcessor<HtmlParsingContext> implements RowProcessor {

	/**
	 * Creates a processor for java beans of multiple types
	 * @param beanTypes the classes with their attributes mapped to fields of records parsed by the {@link HtmlParser}
	 */
	public MultiBeanProcessor(Class ... beanTypes){
		super(beanTypes);
	}
}
