/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.processor.core.*;

/**
 * A {@link RowProcessor} implementation that stores values of columns in batches. Use this implementation in favor of {@link ColumnProcessor}
 * when processing large inputs to avoid running out of memory.
 *
 * Values parsed in each row will be split into columns of Strings. Each column has its own list of values.
 *
 * <p> During the execution of the process, the {@link #batchProcessed(int)} method will be invoked after a given number of rows has been processed.</p>
 * <p> The user can access the lists with values parsed for all columns using the methods {@link #getColumnValuesAsList()},
 * {@link #getColumnValuesAsMapOfIndexes()} and {@link #getColumnValuesAsMapOfNames()}. </p>
 * <p> After {@link #batchProcessed(int)} is invoked, all values will be discarded and the next batch of column values will be accumulated.
 * This process will repeat until there's no more rows in the input.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see RowProcessor
 * @see AbstractBatchedColumnProcessor
 * @see HtmlEntitySettings
 */
public abstract class BatchedColumnProcessor extends AbstractBatchedColumnProcessor<HtmlParsingContext> implements RowProcessor {

	/**
	 * Constructs a batched column processor configured to invoke the {@link #batchesProcessed} method after a given number of rows has been processed.
	 *
	 * @param rowsPerBatch the number of rows to process in each batch.
	 */
	public BatchedColumnProcessor(int rowsPerBatch) {
		super(rowsPerBatch);
	}


}
