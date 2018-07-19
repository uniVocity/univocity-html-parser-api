/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.processor.core.*;

/**
 * A concrete implementation of {@link RowProcessorSwitch} that allows switching among different implementations of
 * {@link RowProcessor} based on values found on the rows parsed from the input.
 */
public class InputValueSwitch extends AbstractInputValueSwitch<HtmlParsingContext> implements RowProcessor{
	/**
	 * Creates a switch that will analyze the first column of rows found in the input to determine which
	 * {@link RowProcessor} to use for each parsed row
	 */
	public InputValueSwitch() {
		this(0);
	}

	/**
	 * Creates a switch that will analyze a column of rows parsed from the input to determine which
	 * {@link RowProcessor} to use.
	 *
	 * @param columnIndex the column index whose value will be used to determine which {@link RowProcessor} to use for each parsed row.
	 */
	public InputValueSwitch(int columnIndex) {
		super(columnIndex);
	}

	/**
	 * Creates a switch that will analyze a column in rows parsed from the input to determine which
	 * {@link RowProcessor} to use.
	 *
	 * @param columnName name of the column whose values will be used to determine which {@link RowProcessor} to use for each parsed row.
	 */
	public InputValueSwitch(String columnName) {
		super(columnName);
	}

	@Override
	protected HtmlParsingContext wrapContext(HtmlParsingContext context) {
		return new HtmlContextWrapper(context) {

			private final String[] fieldNames = getHeaders();
			private final int[] indexes = getIndexes();

			@Override
			public String[] headers() {
				return fieldNames == null || fieldNames.length == 0 ? context.headers() : fieldNames;
			}

			@Override
			public int[] extractedFieldIndexes() {
				return indexes == null || indexes.length == 0 ? context.extractedFieldIndexes() : indexes;
			}
		};
	}
}
