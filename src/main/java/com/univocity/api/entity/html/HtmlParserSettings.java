/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html;

import com.univocity.parsers.common.processor.core.*;

/**
 * This is the configuration class used by {@link HtmlParser}.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see Processor
 * @see HtmlEntityList
 */
public class HtmlParserSettings extends RemoteResourceSettings<HtmlEntityList> {


	private HtmlParserListener listener;

	/**
	 * Creates a new HtmlParserSettings with a supplied {@link HtmlEntityList}. The {@link HtmlEntityList} is used to
	 * store {@link HtmlEntity}'s which define the specific HTML elements that will be parsed.
	 *
	 * @param entityList
	 */
	public HtmlParserSettings(HtmlEntityList entityList) {
		super(entityList);
	}


//	/**
//	 * Sets the global {@link Processor}. All rows parsed will be delegated to the processor. Does not
//	 * need to be set for the {@link HtmlParser} to function.
//	 *
//	 * @param processor the {@link Processor} that will be set as the processor
//	 */
//	public void setGlobalProcessor(Processor<HtmlParsingContext> processor) {
//		this.processor = processor;
//	}

//	/**
//	 * Returns the processor if previously set. If the processor was not set, returns a
//	 * {@link NoopProcessor}, which is a {@link Processor} that does nothing.
//	 *
//	 * @return the {@link Processor} previously set, or a {@link NoopProcessor} if never set.
//	 */
//	public Processor<HtmlParsingContext> getGlobalProcessor() {
//		return processor == null ? NoopProcessor.instance : processor;
//	}


	/**
	 * Sets the associated {@link HtmlParserListener} that is used when the {@link HtmlParser} parses.
	 * @param listener the HtmlParserListener that the settings will be associated with
	 */
	public void setListener(HtmlParserListener listener) {
		this.listener = listener;
	}

	/**
	 * Returns the associated {@link HtmlParserListener}
	 * @return the HtmlParserListener associated with the settings
	 */
	public HtmlParserListener getListener() {
		return listener;
	}



}
