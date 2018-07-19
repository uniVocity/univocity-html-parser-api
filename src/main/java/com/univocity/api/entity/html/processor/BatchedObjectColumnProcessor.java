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
 * A {@link RowProcessor} implementation for converting batches of rows extracted from any implementation of {@link HtmlParser} into columns of objects.
 * This uses the value conversions provided by {@link Conversion} instances.
 *
 *  For each row processed, a sequence of conversions will be executed to generate the appropriate object. Each resulting object will then be stored in
 * a list that contains the values of the corresponding column.
 *
 *  During the execution of the process, the {@link #batchProcessed(int)} method will be invoked after a given number of rows has been processed.
 *  The user can access the lists with values parsed for all columns using the methods {@link #getColumnValuesAsList()},
 * {@link #getColumnValuesAsMapOfIndexes()} and {@link #getColumnValuesAsMapOfNames()}.
 *  After {@link #batchProcessed(int)} is invoked, all values will be discarded and the next batch of column values will be accumulated.
 * This process will repeat until there's no more rows in the input.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see RowProcessor
 * @see HtmlEntitySettings
 * @see AbstractBatchedColumnProcessor
 * @see Conversion
 */
public abstract class BatchedObjectColumnProcessor extends AbstractBatchedObjectColumnProcessor<HtmlParsingContext> implements RowProcessor {


	/**
	 * Constructs a batched column processor configured to invoke the {@link #batchesProcessed} method after a given number of rows has been processed.
	 *
	 * @param rowsPerBatch the number of rows to process in each batch.
	 */
	public BatchedObjectColumnProcessor(int rowsPerBatch) {
		super(rowsPerBatch);
	}

}
