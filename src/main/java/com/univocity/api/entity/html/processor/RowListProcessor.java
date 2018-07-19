/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;

/**
 *
 * A convenience {@link RowProcessor} implementation for storing all rows parsed into a list.
 * A typical use case of this class will be:
 *
 * <hr><blockquote><pre>
 *
 * parserSettings.setRowProcessor(new RowListProcessor());
 * parser.parse(reader); // will invoke the {@link RowListProcessor#rowProcessed(String[], Context)} method for each parsed record.
 *
 * String[] headers = rowProcessor.getHeaders();
 * List&lt;String[]&gt; rows = rowProcessor.getRows();
 *
 * </pre></blockquote><hr>
 *
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
public class RowListProcessor extends AbstractListProcessor<HtmlParsingContext> implements RowProcessor {

}
