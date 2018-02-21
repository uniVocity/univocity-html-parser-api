/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;
import com.univocity.parsers.conversions.*;

/**
 *
 * A {@link RowProcessor} implementation for converting rows extracted by the {@link HtmlParser} into arrays of objects.
 * This uses the value conversions provided by {@link Conversion} instances.
 *
 *  For each row processed, a sequence of conversions will be executed and stored in an object array, at its original position.
 *  The row with the result of these conversions will then be sent to the {@link ObjectRowProcessor#rowProcessed(Object[], Context)} method, where the user can access it.
 *
 * @see HtmlParser
 * @see RowProcessor
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 */
public abstract class ObjectRowProcessor extends AbstractObjectProcessor<HtmlParsingContext> implements RowProcessor {

}
