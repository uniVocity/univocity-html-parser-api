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
 * The essential callback interface to handle records parsed by any parser that extends {@link AbstractParser}.
 *
 * <p>When parsing an input, uniVocity-parsers will obtain the RowProcessor from {@link CommonParserSettings#getRowProcessor()}, and
 * delegate each parsed row to {@link RowProcessor#rowProcessed(String[], HtmlParsingContext)}.
 *
 * <p>Before parsing the first row, the parser will invoke the {@link RowProcessor#processStarted(HtmlParsingContext)} method.
 *    By this time the input buffer will be already loaded and ready to be consumed.
 *
 * <p>After parsing the last row, all resources are closed and the processing stops. Only after the {@link RowProcessor#processEnded(HtmlParsingContext)} is called so you
 *    can perform any additional housekeeping you might need.
 *
 * <p>More control and information over the parsing process are provided by the {@link HtmlParsingContext} object.
 *
 * <p>uniVocity-parsers provides many useful default implementations of this interface in the package {@link com.univocity.parsers.common.processor}, namely:
 *
 * <ul>
 * <li>{@link RowListProcessor}: convenience class for storing the processed rows into a list.</li>
 * <li>{@link ObjectRowProcessor}: used for processing rows and executing conversions of parsed values to objects using instances of {@link Conversion}</li>
 * <li>{@link ObjectRowListProcessor}: convenience class for rows of converted objects using {@link ObjectRowProcessor} into a list.</li>
 * <li>{@link MasterDetailProcessor}: used for reading inputs where records are organized in a master-detail fashion (with a master element that contains a list of associated elements) </li>
 * <li>{@link MasterDetailListProcessor}: convenience class for storing {@link MasterDetailRecord} created by instances created by {@link MasterDetailProcessor} into a list </li>
 * <li>{@link BeanProcessor}: used for automatically create and populate javabeans annotated with the annotations provided in package {@link com.univocity.parsers.annotations}</li>
 * <li>{@link BeanListProcessor}: convenience class for storing all javabeans created by {@link BeanProcessor} into a list</li>
 * </ul>
 *
 * @see HtmlParser
 * @see HtmlEntitySettings
 * @see HtmlParsingContext
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
public interface RowProcessor extends Processor<HtmlParsingContext> {

	/**
	 * This method will by invoked by the parser once, when it is ready to start processing the input.
	 *
	 * @param context A contextual object with information and controls over the current state of the parsing process
	 */
	void processStarted(HtmlParsingContext context);

	/**
	 * Invoked by the parser after all values of a valid record have been processed.
	 *
	 * @param row the data extracted by the parser for an individual record of a given entity. It will never by null.

	 * @param context A contextual object with information and controls over the current state of the parsing process
	 */
	void rowProcessed(String[] row, HtmlParsingContext context);

	/**
	 * This method will by invoked by the parser once, after the parsing process stopped and all resources were closed.
	 * <p> It will always be called by the parser: in case of errors, if the end of the input us reached, or if the user stopped the process manually using {@link HtmlParsingContext#stop()}.
	 *
	 * @param context A contextual object with information and controls over the state of the parsing process
	 */
	void processEnded(HtmlParsingContext context);
}
