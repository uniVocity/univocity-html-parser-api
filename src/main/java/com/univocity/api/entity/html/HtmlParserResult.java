/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.parsers.common.*;


/**
 * Stores the results of parsing some input with the {@link HtmlParser} parser.
 * The fields that are captured and the order that they appear in each row depend on how they were configured using
 * {@link HtmlEntitySettings} before parsing.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParsingContext
 * @see HtmlEntitySettings
 * @see HtmlParser
 * @see HtmlRecord
 */
public interface HtmlParserResult extends Result<HtmlRecord, HtmlParsingContext> {

	@Override
	HtmlParserResult getLinkedFieldData(int rowIndex);

	@Override
	Results<HtmlParserResult> getLinkedEntityData(int rowIndex);
}
