/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.common.*;
import com.univocity.parsers.common.record.*;

import java.util.*;

/**
 * Created by anthony on 7/07/16.
 */
public  class HtmlParser {

	private  HtmlParser parser;

	public HtmlParser(HtmlParserSettings settings) {
		if (settings == null) {
			parser = null;
		} else {
			parser = Builder.build(HtmlParser.class, settings);
		}
	}

	public  Map<String, List<String[]>> parseAll(ReaderProvider readerProvider){
		return parser.parseAll(readerProvider);
	}

	public   Map<String, List<Record>> parseAllRecords(ReaderProvider readerProvider) {
		return parser.parseAllRecords(readerProvider);
	}

	public  void parse(ReaderProvider readerProvider) {
		parser.parse(readerProvider);
	}

}
