/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.processor.core.*;
import com.univocity.parsers.conversions.*;

/**
 *
 * A {@link RowProcessor} implementation for converting rows extracted by the {@link HtmlParser} into columns of objects.
 * This uses the value conversions provided by {@link Conversion} instances.
 *
 *  For each row processed, a sequence of conversions will be executed to generate the appropriate object. Each resulting object will then be stored in
 * 	a list that contains the values of the corresponding column.
 *
 *  At the end of the process, the user can access the lists with values parsed for all columns using the methods {@link #getColumnValuesAsList()},
 * {@link #getColumnValuesAsMapOfIndexes()} and {@link #getColumnValuesAsMapOfNames()}.
 *
 * **Note:** Storing the values of all columns may be memory intensive. For large inputs, use a {@link BatchedObjectColumnProcessor} instead
 *
 * @see HtmlParser
 * @see RowProcessor
 * @see ColumnProcessor
 * @see Conversion
 * @see HtmlEntitySettings
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
public class ObjectColumnProcessor extends AbstractObjectColumnProcessor<HtmlParsingContext> implements RowProcessor {

	/**
	 * Constructs a column processor, pre-allocating room for 1000 rows.
	 */
	public ObjectColumnProcessor() {
		this(1000);
	}

	/**
	 * Constructs a column processor pre-allocating room for the expected number of rows to be processed
	 * @param expectedRowCount the expected number of rows to be processed
	 */

	public ObjectColumnProcessor(int expectedRowCount) {
		super(expectedRowCount);
	}
}
