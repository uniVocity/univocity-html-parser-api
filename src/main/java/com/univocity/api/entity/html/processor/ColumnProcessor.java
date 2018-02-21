/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.processor.core.*;

/**
 * A simple {@link RowProcessor} implementation that stores values of columns.
 * Values parsed in each row will be split into columns of Strings. Each column has its own list of values.
 *
 *  At the end of the process, the user can access the lists with values parsed for all columns using the methods {@link #getColumnValuesAsList()},
 * {@link #getColumnValuesAsMapOfIndexes()} and {@link #getColumnValuesAsMapOfNames()}.
 *
 *
 * **Note:** Storing the values of all columns may be memory intensive. For large inputs, use a {@link BatchedColumnProcessor} instead
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see RowProcessor
 */
public class ColumnProcessor extends AbstractColumnProcessor<HtmlParsingContext> implements RowProcessor {


	/**
	 * Constructs a column processor, pre-allocating room for 1000 rows.
	 */
	public ColumnProcessor() {
		super(1000);
	}

	/**
	 * Constructs a column processor pre-allocating room for the expected number of rows to be processed
	 *
	 * @param expectedRowCount the expected number of rows to be processed
	 */
	public ColumnProcessor(int expectedRowCount) {
		super(expectedRowCount);
	}
}
