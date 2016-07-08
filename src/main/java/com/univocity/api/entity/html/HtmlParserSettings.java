/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html;

import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.*;

import java.util.*;

/**
 * This is the configuration class used by {@link HtmlParser}.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see RowProcessor
 * @see HtmlEntityList
 */
public class HtmlParserSettings extends AbstractEntityParserSettings {


	private RowProcessor globalRowProcessor;
	private final HtmlEntityList htmlEntityList;
	private HtmlParserListener listener;

	/**
	 * Creates a new HtmlParserSettings with a supplied {@link HtmlEntityList}. The {@link HtmlEntityList} is used to
	 * store {@link HtmlEntity}'s which define the specific HTML elements that will be parsed.
	 *
	 * @param htmlEntityList the
	 */
	public HtmlParserSettings(HtmlEntityList htmlEntityList) {
		this.htmlEntityList = htmlEntityList;
	}

	/**
	 * Returns the {@link HtmlEntityList} associated with the settings.
	 *
	 * @return the HtmlEntityList
	 */
	public HtmlEntityList getHtmlEntityList() {
		return htmlEntityList;
	}

	/**
	 * Sets the global {@link RowProcessor}. All rows parsed will be delegated to the globalRowProcessor. Does not
	 * need to be set for the {@link HtmlParser} to function.
	 *
	 * @param globalRowProcessor the {@link RowProcessor} that will be set as the globalRowProcessor
	 */
	public void setGlobalRowProcessor(RowProcessor globalRowProcessor) {
		this.globalRowProcessor = globalRowProcessor;
	}

	/**
	 * Returns the globalRowProcessor if previously set. If the globalRowProcessor was not set, returns a
	 * {@link NoopRowProcessor}, which is a {@link RowProcessor} that does nothing.
	 * @return
	 */
	public RowProcessor getGlobalRowProcessor() {
		return globalRowProcessor == null ? NoopRowProcessor.instance : globalRowProcessor;
	}

	@Override
	protected Format createDefaultFormat() {
		return new Format() {
			@Override
			protected TreeMap<String, Object> getConfiguration() {
				return new TreeMap<String, Object>();
			}
		};
	}

	/**
	 * Returns the entity names contained in the associated {@link HtmlEntityList} as a set of Strings.
	 * @return the entity names
	 */
	public Set<String> getEntityNames() {
		return htmlEntityList.getEntityNames();
	}

	public void setListener(HtmlParserListener listener) {
		this.listener = listener;
	}

	public HtmlParserListener getListener() {
		return listener;
	}
}
