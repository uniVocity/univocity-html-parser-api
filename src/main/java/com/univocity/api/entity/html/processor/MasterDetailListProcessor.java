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

/**
 *
 * A convenience {@link MasterDetailProcessor} implementation for storing all {@link MasterDetailRecord} generated form the parsed input into a list.
 * A typical use case of this class will be:
 *
 * <hr>{@code
 *
 * ObjectRowListProcessor detailProcessor = new ObjectRowListProcessor();
 * MasterDetailListProcessor masterRowProcessor = new MasterDetailListProcessor(detailProcessor) {
 *      protected boolean isMasterRecord(String[] row, ParsingContext context) {
 *          return "Total".equals(row[0]);
 *      }
 * };
 *
 * parserSettings.setRowProcessor(masterRowProcessor);
 *
 * List&lt;MasterDetailRecord&gt; rows = masterRowProcessor.getRecords();
 * }<hr>
 *
 * @see MasterDetailProcessor
 * @see RowProcessor
 * @see AbstractParser
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
public abstract class MasterDetailListProcessor extends AbstractMasterDetailListProcessor<HtmlParsingContext> implements RowProcessor {


	public MasterDetailListProcessor(RowPlacement rowPlacement, AbstractObjectListProcessor detailProcessor) {
		super(rowPlacement, detailProcessor);
	}

	public MasterDetailListProcessor(AbstractObjectListProcessor detailProcessor) {
		super(detailProcessor);
	}
}
