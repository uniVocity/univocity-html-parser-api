/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.annotations.helpers.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;

/**
 * A {@link RowProcessor} implementation for converting rows extracted from any implementation of {@link HtmlParser} into java objects.
 * The class type of the object must contain the annotations provided in {@link com.univocity.parsers.annotations}.
 *
 *  For each row processed, a java bean instance of a given class will be created with its fields populated.
 *  This instance will then be sent to the {@link BeanProcessor#beanProcessed(Object, Context)} method, where the user can access it.
 *
 * @param <T> the annotated class type.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see HtmlEntitySettings
 * @see RowProcessor
 * @see AbstractBeanProcessor
 */
public abstract class BeanProcessor<T> extends AbstractBeanProcessor<T, HtmlParsingContext> implements RowProcessor {

	/**
	 * Creates a processor for java beans of a given type.
	 *
	 * @param beanType the class with its attributes mapped to fields of records parsed by an {@link HtmlParser}.
	 */
	public BeanProcessor(Class<T> beanType) {
		super(beanType, MethodFilter.ONLY_SETTERS);
	}


}
