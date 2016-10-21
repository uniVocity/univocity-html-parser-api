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
 * A {@link RowProcessor} implementation for converting rows extracted from the {@link HtmlParser} into java objects, storing
 * them into lists. This processor stores beans in separate lists, one for each type of bean processed.
 * All lists of all types will have the same number of entries as the number of records in the input.
 * When an object of a particular type can't be generated from a row, {@code null} will be added to the list. This ensures all lists are the same size,
 * and each element of each list contains the exact information parsed from each row.
 *
 * <p>The class types passed to the constructor of this class must contain the annotations provided in {@link com.univocity.parsers.annotations}.
 *
 * @see HtmlParser
 * @see RowProcessor
 * @see BeanProcessor
 * @see MultiBeanProcessor
 * @see HtmlEntitySettings
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
public class MultiBeanListProcessor extends AbstractMultiBeanListProcessor<HtmlParsingContext> implements RowProcessor {

	/**
	 * Creates a processor for java beans of multiple types
	 * @param beanTypes the classes with their attributes mapped to fields of records parsed by an {@link AbstractParser} or written by an {@link AbstractWriter}.
	 */
	public MultiBeanListProcessor(Class... beanTypes) {
		super(beanTypes);
	}
}
