/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
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
 * A {@link RowProcessor} implementation for associating rows extracted from any implementation of {@link HtmlParser} into {@link MasterDetailRecord} instances.
 *
 *  For each row processed, a call to {@link #isMasterRecord(String[], Context)} will be made to identify whether or not it is a master row.
 *  The detail rows are automatically associated with the master record in an instance of {@link MasterDetailRecord}.
 *  When the master record is fully processed (i.e. {@link MasterDetailRecord} contains a master row and  all associated detail rows),
 * it is sent to the user for processing in {@link #masterDetailRecordProcessed(MasterDetailRecord, Context)}.
 *
 *  <b>Note</b> this class extends {@link ObjectRowProcessor} and value conversions provided by {@link Conversion} instances are fully supported.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see MasterDetailRecord
 * @see RowPlacement
 * @see HtmlParser
 * @see ObjectRowListProcessor
 * @see RowProcessor
 */
public abstract class MasterDetailProcessor extends AbstractMasterDetailProcessor<HtmlParsingContext> {

	/**
	 * Creates a MasterDetailProcessor
	 *
	 * @param rowPlacement    the location of the master row relative to the other rows
	 * @param detailProcessor the processor that processes detail rows.
	 */
	public MasterDetailProcessor(RowPlacement rowPlacement, ObjectRowListProcessor detailProcessor) {
		super(rowPlacement, detailProcessor);
	}

	/**
	 * Creates a MasterDetailProcessor.
	 *
	 * Default {@code rowPlacement} set to {@link RowPlacement#TOP}
	 *
	 * @param detailProcessor the processor that processes detail rows.
	 */
	public MasterDetailProcessor(ObjectRowListProcessor detailProcessor) {
		super(RowPlacement.TOP, detailProcessor);
	}

}
