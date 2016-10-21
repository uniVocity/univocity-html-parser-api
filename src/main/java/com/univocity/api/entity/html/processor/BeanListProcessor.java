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
 * A convenience {@link BeanProcessor} implementation for storing all java objects generated form the parsed input into a list.
 * A typical use case of this class will be:
 *
 * <hr>{@code
 *
 * parserSettings.setRowProcessor(new BeanListProcessor(MyObject.class));
 * parser.parse(reader); // will invoke the {@link BeanListProcessor#beanProcessed(Object, Context)} method for each generated object.
 *
 * List&lt;T&gt; beans = rowProcessor.getBeans();
 * }<hr>
 *
 * @param <T> the annotated class type.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see BeanProcessor
 * @see RowProcessor
 * @see HtmlParser
 * @see HtmlEntitySettings
 */
public class BeanListProcessor<T> extends AbstractBeanListProcessor<T, HtmlParsingContext> implements RowProcessor {

	/**
	 * Creates a processor that stores java beans of a given type into a list
	 *
	 * @param beanType the class with its attributes mapped to fields of records parsed by a {@link HtmlParser}.
	 */
	public BeanListProcessor(Class<T> beanType) {
		super(beanType);
	}


}
