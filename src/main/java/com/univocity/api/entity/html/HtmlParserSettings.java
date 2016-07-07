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
 * Created by anthony on 24/06/16.
 */
public class HtmlParserSettings extends AbstractEntityParserSettings {


	private RowProcessor globalRowProcessor;

	private final HtmlEntityList htmlEntityList;

	public HtmlParserSettings(HtmlEntityList htmlEntityList) {
		this.htmlEntityList = htmlEntityList;
	}

	public HtmlEntityList getHtmlEntityList() {
		return htmlEntityList;
	}

	public void setGlobalRowProcessor(RowProcessor globalRowProcessor) {
		this.globalRowProcessor = globalRowProcessor;
	}

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

	public Set<String> getEntityNames() {
		return htmlEntityList.getEntityNames();
	}
}
