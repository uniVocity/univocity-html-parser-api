/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.common.*;
import com.univocity.parsers.common.processor.core.*;
import com.univocity.parsers.common.record.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

/**
 * A Parser that parses HTML.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 * @see InternalHtmlParserSettings
 * @see ReaderProvider
 * @see Record
 * @see HtmlEntity
 */
public  class HtmlParser {

	private final HtmlParserInterface parser;

	/**
	 * Creates a new HtmlParser with configuration provided by {@link InternalHtmlParserSettings}. The HtmlParser gets all
	 * configuration from this settings class.
	 *
	 * @param settings The HtmlParser configuration
	 */
	public HtmlParser(HtmlParserSettings settings) {
		if (settings == null) {
			parser = null;
		} else {
			parser = Builder.build(HtmlParserInterface.class, settings);
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

	public  Map<String, List<String[]>> parseAll(FileProvider fileProvider) {
		return parser.parseAll(fileProvider);
	}

	public   Map<String, List<String[]>> parseAll(File file) {
		return parseAll(new FileProvider(file));
	}

	public   Map<String, List<String[]>> parseAll(File file, Charset encoding) {
		return parseAll(new FileProvider(file, encoding));
	}

	public   Map<String, List<String[]>> parseAll(File file, String encoding) {
		return parseAll(new FileProvider(file, encoding));
	}

	public   Map<String, List<String[]>> parseAll(Reader reader) {
		return parser.parseAll(reader);
	}

	public   Map<String, List<String[]>> parseAll(InputStream inputStream) {
		return parser.parseAll(inputStream);
	}

	public   Map<String, List<String[]>> parseAll(InputStream inputStream, Charset encoding) {
		return parser.parseAll(inputStream, encoding);
	}

	public   Map<String, List<String[]>> parseAll(InputStream inputStream, String encoding) {
		return parser.parseAll(inputStream, encoding);
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
	public  Map<String, List<Record>> parseAllRecords(FileProvider fileProvider) {
		return parser.parseAllRecords(fileProvider);
	}

	public   Map<String, List<Record>> parseAllRecords(File file) {
		return parseAllRecords(new FileProvider(file));
	}

	public   Map<String, List<Record>> parseAllRecords(File file, Charset encoding) {
		return parseAllRecords(new FileProvider(file, encoding));
	}

	public   Map<String, List<Record>> parseAllRecords(File file, String encoding) {
		return parseAllRecords(new FileProvider(file, encoding));
	}

	public   Map<String, List<Record>> parseAllRecords(Reader reader) {
		return parser.parseAllRecords(reader);
	}

	public   Map<String, List<Record>> parseAllRecords(InputStream inputStream) {
		return parser.parseAllRecords(inputStream);
	}

	public   Map<String, List<Record>> parseAllRecords(InputStream inputStream, Charset encoding) {
		return parser.parseAllRecords(inputStream, encoding);
	}

	public   Map<String, List<Record>> parseAllRecords(InputStream inputStream, String encoding) {
		return parser.parseAllRecords(inputStream, encoding);
	}

	/**
	 * Parses a HTML file provided by {@link ReaderProvider} and delegates each row parsed to a {@link Processor}
	 * defined in the {@link InternalHtmlParserSettings}
	 * @param readerProvider The file or URL where the HTML document is located.
	 */
	public  void parse(ReaderProvider readerProvider) {
		parser.parse(readerProvider);
	}

	public void parse(FileProvider fileProvider) {
		parser.parse(fileProvider);
	}

	public  void parse(File file) {
		parse(new FileProvider(file));
	}

	public  void parse(File file, Charset encoding) {
		parse(new FileProvider(file, encoding));
	}

	public  void parse(File file, String encoding) {
		parse(new FileProvider(file, encoding));
	}

	public  void parse(Reader reader) {
		parser.parse(reader);
	}

	public  void parse(InputStream inputStream) {
		parser.parse(inputStream);
	}

	public  void parse(InputStream inputStream, Charset encoding) {
		parser.parse(inputStream, encoding);
	}

	public  void parse(InputStream inputStream, String encoding) {
		parser.parse(inputStream, encoding);
	}
}
