/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.processor;

import com.univocity.api.entity.html.*;
import com.univocity.api.net.*;
import com.univocity.parsers.common.*;

import java.io.*;
import java.util.*;

/**
 * A simple a wrapper for a {@link HtmlParsingContext}.
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class HtmlContextWrapper extends ContextWrapper<HtmlParsingContext> implements HtmlParsingContext {

	/**
	 * Wraps a {@link HtmlParsingContext}.
	 *
	 * @param context the parsingContext object to be wrapped.
	 */
	public HtmlContextWrapper(HtmlParsingContext context) {
		super(context);
	}

	@Override
	public HttpResponse response() {
		return context.response();
	}


	@Override
	public Map<String, String> matchedData() {
		return context.matchedData();
	}

	@Override
	public String entityName() {
		return context.entityName();
	}

	@Override
	public int currentNodeDepth() {
		return context.currentNodeDepth();
	}

	@Override
	public HtmlElement currentElement() {
		return context.currentElement();
	}

	@Override
	public File getFile(String binaryFieldName) {
		return context.getFile(binaryFieldName);
	}

	@Override
	public Set<String> binaryFields() {
		return context.binaryFields();
	}

	@Override
	public HtmlRecord toRecord(String[] row) {
		return context.toRecord(row);
	}

	@Override
	public ResultRecordMetaData recordMetaData() {
		return context.recordMetaData();
	}

	@Override
	public HtmlElement pageRoot() {
		return context.pageRoot();
	}

	@Override
	public Object documentSource() {
		return context.documentSource();
	}

	@Override
	public Map<String, HtmlElement[]> getMatchedElements() {
		return context.getMatchedElements();
	}
}
