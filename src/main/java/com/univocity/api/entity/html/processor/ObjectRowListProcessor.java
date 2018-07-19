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
 * A convenience {@link RowProcessor} implementation for storing all rows parsed and converted to Object arrays into a list.
 * A typical use case of this class will be:
 *
 * <hr>{@code
 *
 * ObjectRowListProcessor processor = new ObjectRowListProcessor();
 * processor.convertIndexes(Conversions.toBigDecimal()).set(4, 6);
 * parserSettings.setRowProcessor(new ObjectRowListProcessor());
 * parser.parse(reader); // will invoke the {@link ObjectRowListProcessor#rowProcessed(Object[], Context)} method for each parsed record.
 *
 * String[] headers = rowProcessor.getHeaders();
 * List&lt;Object[]&gt; rows = rowProcessor.getRows();
 * BigDecimal value1 = (BigDecimal) row.get(4);
 * BigDecimal value2 = (BigDecimal) row.get(6);
 * }<hr>
 *
 * @see RowProcessor
 * @see ObjectRowProcessor
 * @see AbstractParser
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
public class ObjectRowListProcessor extends AbstractObjectListProcessor<HtmlParsingContext> implements RowProcessor {

}
