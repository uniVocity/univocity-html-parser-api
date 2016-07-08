/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.common.*;
import com.univocity.parsers.common.processor.*;
import com.univocity.parsers.common.processor.core.*;
import com.univocity.parsers.common.record.*;

import java.util.*;

/**
 * A Parser that parses HTML.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParserSettings
 * @see ReaderProvider
 * @see Record
 * @see HtmlEntity
 */
public  class HtmlParser {

	private  HtmlParser parser;

	/**
	 * Creates a new HtmlParser with configuration provided by {@link HtmlParserSettings}. The HtmlParser gets all
	 * configuration from this settings class.
	 *
	 * @param settings The HtmlParser configuration
	 */
	public HtmlParser(HtmlParserSettings settings) {
		if (settings == null) {
			parser = null;
		} else {
			parser = Builder.build(HtmlParser.class, settings);
		}
	}

	/**
	 * Parses a HTML file provided by {@link ReaderProvider}. Returns a map of the rows parsed.
	 *
	 * @param readerProvider The file or URL where the HTML document is located.
	 * @return A map where the key is the entity name and the
	 * value is the list of rows(Strings) parsed from that entity.
	 */
	public  Map<String, List<String[]>> parseAll(ReaderProvider readerProvider){
		return parser.parseAll(readerProvider);
	}

	/**
	 * Parses a HTML file provided by {@link ReaderProvider}  and returns rows as a list of {@link Record}s.
	 *
	 * @param readerProvider The file or URL where the HTML document is located.
	 * @return A map where the key is the entity name and the value is a list of rows stored as {@link Record}.
	 */
	public   Map<String, List<Record>> parseAllRecords(ReaderProvider readerProvider) {
		return parser.parseAllRecords(readerProvider);
	}

	/**
	 * Parses a HTML file provided by {@link ReaderProvider} and delegates each row parsed to a {@link RowProcessor}
	 * defined in the {@link Htmf}
	 * @param readerProvider The file or URL where the HTML document is located.
	 */
	public  void parse(ReaderProvider readerProvider) {
		parser.parse(readerProvider);
	}

}
