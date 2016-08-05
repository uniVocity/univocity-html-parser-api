/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.common.remote.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;

import java.util.*;

/**
 * This is the configuration class used by {@link HtmlParser}.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see Processor
 * @see HtmlEntityList
 */
public class InternalHtmlParserSettings extends RemoteResourceSettings<HtmlEntityList, Format, HtmlParsingContext> {


	private HtmlParserListener listener;
	private String emptyValue = null;

	/**
	 * Creates a new InternalHtmlParserSettings with a supplied {@link HtmlEntityList}. The {@link HtmlEntityList} is used to
	 * store {@link HtmlEntity}'s which define the specific HTML elements that will be parsed.
	 *
	 * @param entityList
	 */
	public InternalHtmlParserSettings(HtmlEntityList entityList) {
		super(entityList);
	}

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


	@Override
	protected Format createDefaultFormat() {
		return new Format() {
			@Override
			protected TreeMap<String, Object> getConfiguration() {
				return new TreeMap<String, Object>();
			}
		};
	}

	final void setCurrentEntity(String entityName){
		this.currentEntity = ArgumentUtils.normalize(entityName);
	}

	/**
	 * Sets the String representation of an empty value (defaults to null)
	 *
	 * <p>When reading, if the parser does not read any character from the input, for example getting the text from
	 * &lt;td>&lt;/td>, the empty value is used instead of an empty string </p>
	 *
	 * @param emptyValue the String representation of an empty value
	 */
	public void setEmptyValue(String emptyValue) {
		this.emptyValue = emptyValue;
	}

	/**
	 * Returns the String representation of an empty value (defaults to null)
	 *
	 * <p>When reading, if the parser does not read any character from the input, for example getting the text from
	 * &lt;td>&lt;/td>, the empty value is used instead of an empty string </p>
	 *
	 * @return the String representation of an empty value
	 */
	public String getEmptyValue() {
		return emptyValue;
	}
}
