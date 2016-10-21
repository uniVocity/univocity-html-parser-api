/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.*;
import com.univocity.parsers.common.processor.core.*;
import com.univocity.parsers.conversions.*;

/**
 *
 * A {@link RowProcessor} implementation for associating rows extracted from any implementation of {@link HtmlParser} into {@link MasterDetailRecord} instances.
 *
 * <p> For each row processed, a call to {@link #isMasterRecord(String[], Context)} will be made to identify whether or not it is a master row.
 * <p> The detail rows are automatically associated with the master record in an instance of {@link MasterDetailRecord}.
 * <p> When the master record is fully processed (i.e. {@link MasterDetailRecord} contains a master row and  all associated detail rows),
 * it is sent to the user for processing in {@link #masterDetailRecordProcessed(MasterDetailRecord, Context)}.
 *
 * <p> <b>Note</b> this class extends {@link ObjectRowProcessor} and value conversions provided by {@link Conversion} instances are fully supported.
 *
 * @see MasterDetailRecord
 * @see RowPlacement
 * @see HtmlParser
 * @see ObjectRowListProcessor
 * @see RowProcessor
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
public abstract class MasterDetailProcessor extends AbstractMasterDetailProcessor<HtmlParsingContext> {

	/**
	 * Creates a MasterDetailProcessor
	 *
	 * @param rowPlacement indication whether the master records are placed in relation its detail records in the input.
	 *
	 * <hr><blockquote><pre>
	 *
	 * Master record (Totals)       Master record (Totals)
	 *  above detail records         under detail records
	 *
	 *    Totals | 100                 Item   | 60
	 *    Item   | 60                  Item   | 40
	 *    Item   | 40                  Totals | 100
	 * </pre></blockquote><hr>
	 * @param detailProcessor the {@link ObjectRowListProcessor} that processes detail rows.
	 */
	public MasterDetailProcessor(RowPlacement rowPlacement, ObjectRowListProcessor detailProcessor) {
		super(rowPlacement, detailProcessor);
	}

	public MasterDetailProcessor(ObjectRowListProcessor detailProcessor) {
		super(RowPlacement.TOP, detailProcessor);
	}

}
