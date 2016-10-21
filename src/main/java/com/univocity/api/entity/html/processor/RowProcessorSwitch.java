/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;

/**
 * A special {@link RowProcessor} implementation that combines and allows switching among different
 * RowProcessors. Each RowProcessor will have its own {@link HtmlParsingContext}. Concrete implementations of this class
 * are expected to implement the {@link RowProcessorSwitch#switchRowProcessor(String[], Context)} method and analyze the input row
 * to determine whether or not the current {@link RowProcessor} implementation must be changed to handle a special
 * circumstance (determined by the concrete implementation) such as a different row format.
 *
 * When the row processor is switched, the {@link #processorSwitched(Processor, Processor)} will be called, and
 * must be overridden, to notify the change to the user.
 */
public abstract class RowProcessorSwitch extends AbstractProcessorSwitch<HtmlParsingContext> implements RowProcessor {

}