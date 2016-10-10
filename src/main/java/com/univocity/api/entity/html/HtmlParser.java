/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.common.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;
import com.univocity.parsers.common.record.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

/**
 * A very fast HTML parser.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 *
 * @see HtmlParserSettings
 * @see ReaderProvider
 * @see Record
 * @see HtmlEntitySettings
 */
public  class HtmlParser {

	private final EntityParserInterface parser;

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
			parser = Builder.build(EntityParserInterface.class, settings);
		}
	}

	/**
	 * Parses a HTML input provided by {@link ReaderProvider}. Returns a map of the rows parsed.
	 *
	 * @param readerProvider The file or URL where the HTML document is located.
	 * @return A map where the key is the entity name and the
	 * value is the list of rows(Strings) parsed from that entity.
	 */
	public  Map<String, List<String[]>> parseAll(ReaderProvider readerProvider){
		return parser.parseAll(readerProvider);
	}

	/**
	 * Parses a HTML file provided by {@link FileProvider}. Returns a map of the rows parsed.
	 *
	 * @param fileProvider the file where the HTML document is located
	 * @return A map where the key is the entity name and the value is a list of rows (as Strings) parsed from that
	 * entity
	 */
	public  Map<String, List<String[]>> parseAll(FileProvider fileProvider) {
		return parser.parseAll(fileProvider);
	}

	/**
	 * Parses a HTML file provided by a File using the default system encoding. Returns a map of the rows parsed
	 *
	 * @param file the HTML file that will be parsed
	 * @return A map where the key is the entity name and the value is a list of rows (as Strings) parsed from that
	 * entity
	 */
	public   Map<String, List<String[]>> parseAll(File file) {
		return parseAll(new FileProvider(file));
	}

	/**
	 * Parses a HTML file provided by File using the encoding given. Returns a map of the rows parsed.
	 *
	 * @param file the HTML file that will be parsed
	 * @param encoding the encoding, as a Charset, that must be used to read from the given file.
	 * @return A map where the key is the entity name and the value is a list of rows (as Strings) parsed from that
	 * entity
	 */
	public   Map<String, List<String[]>> parseAll(File file, Charset encoding) {
		return parseAll(new FileProvider(file, encoding));
	}

	/**
	 * Parses a HTML file provided by File using the encoding given. Returns a map of the rows parsed.
	 *
	 * @param file the HTML file that will be parsed.
	 * @param encoding the encoding, as a String, that must be used to read from the given file.
	 * @return A map where the key is the entity name and the value is a list of rows (as Strings) parsed from that
	 * entity
	 */
	public   Map<String, List<String[]>> parseAll(File file, String encoding) {
		return parseAll(new FileProvider(file, encoding));
	}

	/**
	 * Parses a HTML input provided by a {@link Reader}. Returns a map of the rows parsed.
	 *
	 * @param reader the Reader that provides the HTML to parse
	 * @return A map where the key is the entity name and the value is a list of rows (as Strings) parsed from that
	 * entity
	 */
	public   Map<String, List<String[]>> parseAll(Reader reader) {
		return parser.parseAll(reader);
	}

	/**
	 * Parses a HTML input provided by a {@link InputStream} using the system default encoding. Returns a map of the
	 * rows parsed.
	 *
	 * @param inputStream the HTML input that will be parsed
	 * @return A map where the key is the entity name and the value is a list of rows (as Strings) parsed from that
	 * entity
	 */
	public   Map<String, List<String[]>> parseAll(InputStream inputStream) {
		return parser.parseAll(inputStream);
	}

	/**
	 * Parses a HTML input provided by a {@link InputStream} using the supplied encoding as a {@link Charset}. Returns
	 * a map of the rows parsed.
	 *
	 * @param inputStream the HTML input that will be parsed
	 * @param encoding the encoding, as a Charset, that must be used to read from the given input
	 * @return A map where the key is the entity name and the value is a list of rows (as Strings) parsed from that
	 * entity
	 */
	public   Map<String, List<String[]>> parseAll(InputStream inputStream, Charset encoding) {
		return parser.parseAll(inputStream, encoding);
	}

	/**
	 * Parses a HTML input provided by a {@link InputStream} using the supplied encoding as a String. Returns a map of
	 * the rows parsed.
	 *
	 * @param inputStream the HTML input that will be parsed
	 * @param encoding the encoding, as a Charset, that must be used to read from the given input
	 * @return A map where the key is the entity name and the value is a list of rows (as Strings) parsed from that
	 * entity
	 */
	public   Map<String, List<String[]>> parseAll(InputStream inputStream, String encoding) {
		return parser.parseAll(inputStream, encoding);
	}

	/**
	 * Parses a HTML input provided by {@link ReaderProvider}  and returns rows as a list of {@link Record}s.
	 *
	 * @param readerProvider The file or URL where the HTML document is located.
	 * @return A map where the key is the entity name and the value is a list of rows stored as {@link Record} parsed
	 * from that entity.
	 */
	public   Map<String, List<Record>> parseAllRecords(ReaderProvider readerProvider) {
		return parser.parseAllRecords(readerProvider);
	}

	/**
	 * Parses a HTML file provided by a {@link FileProvider} and returns rows as a list of {@link Record}s
	 *
	 * @param fileProvider the file where the HTML document is located
	 * @return a map where the key is the entity name and the value is a list of rows stored as {@link Record} parsed
	 * from that entity
	 */
	public  Map<String, List<Record>> parseAllRecords(FileProvider fileProvider) {
		return parser.parseAllRecords(fileProvider);
	}

	/**
	 * Parses a HTML file provided by a File using the default system encoding and returns rows as a list of
	 * {@link Record}s
	 *
	 * @param file the file where the HTML document is located
	 * @return a map where the key is the entity name and the value is a list of rows stored as {@link Record} parsed
	 * from that entity
	 */
	public   Map<String, List<Record>> parseAllRecords(File file) {
		return parseAllRecords(new FileProvider(file));
	}

	/**
	 * Parses a HTML file provided by a File using supplied encoding as a {@link Charset} and returns rows as a list of
	 * {@link Record}s
	 *
	 * @param file the file where the HTML document is located
	 * @param encoding the encoding, as a Charset, that must be used to read from the given input
	 * @return a map where the key is the entity name and the value is a list of rows stored as {@link Record} parsed
	 * from that entity
	 */
	public   Map<String, List<Record>> parseAllRecords(File file, Charset encoding) {
		return parseAllRecords(new FileProvider(file, encoding));
	}

	/**
	 * Parses a HTML file provided by a File using supplied encoding as a String and returns rows as a list of
	 * {@link Record}s
	 *
	 * @param file the file where the HTML document is located
	 * @return a map where the key is the entity name and the value is a list of rows stored as {@link Record} parsed
	 * from that entity
	 */
	public   Map<String, List<Record>> parseAllRecords(File file, String encoding) {
		return parseAllRecords(new FileProvider(file, encoding));
	}

	/**
	 * Parses a HTML input provided by a {@link Reader} and returns rows as a list of {@link Record}s
	 *
	 * @param reader the reader where the HTML document is located
	 * @return a map where the key is the entity name and the value is a list of rows stored as {@link Record} parsed
	 * from that entity
	 */
	public   Map<String, List<Record>> parseAllRecords(Reader reader) {
		return parser.parseAllRecords(reader);
	}

	/**
	 * Parses a HTML input provided by a {@link InputStream} using the system default encoding and returns rows as a list of
	 * {@link Record}s
	 *
	 * @param inputStream the HTML input that will be parsed
	 * @return a map where the key is the entity name and the value is a list of rows stored as {@link Record} parsed
	 * from that entity
	 */
	public   Map<String, List<Record>> parseAllRecords(InputStream inputStream) {
		return parser.parseAllRecords(inputStream);
	}

	/**
	 * Parses a HTML input provided by a {@link InputStream} using the supplied encoding as a {@link Charset} and returns rows as
	 * a list of {@link Record}s
	 *
	 * @param inputStream the HTML input that will be parsed
	 * @param encoding the encoding, as a Charset, that must be used to read from the given input
	 * @return a map where the key is the entity name and the value is a list of rows stored as {@link Record} parsed
	 * from that entity
	 */
	public   Map<String, List<Record>> parseAllRecords(InputStream inputStream, Charset encoding) {
		return parser.parseAllRecords(inputStream, encoding);
	}

	/**
	 * Parses a HTML input provided by a {@link InputStream} using supplied encoding as a String and returns rows as a list of
	 * {@link Record}s
	 *
	 * @param inputStream the HTML input that will be parsed
	 * @param encoding the encoding, as a String, that must be used to read from the given input
	 * @return a map where the key is the entity name and the value is a list of rows stored as {@link Record} parsed
	 * from that entity
	 */
	public   Map<String, List<Record>> parseAllRecords(InputStream inputStream, String encoding) {
		return parser.parseAllRecords(inputStream, encoding);
	}

	/**
	 * Parses a HTML input provided by {@link ReaderProvider} and delegates each row parsed to the associated {@link Processor}s
	 * defined in the {@link HtmlParserSettings}.
	 *
	 * @param readerProvider The file or URL where the HTML document is located.
	 */
	public  void parse(ReaderProvider readerProvider) {
		parser.parse(readerProvider);
	}

	/**
	 * Parses a HTML file provided by {@link FileProvider} and delegates each row parsed to the associated {@link Processor}s
	 * defined in the {@link HtmlParserSettings}.
	 *
	 * @param fileProvider the file where the HTML document is located
	 */
	public void parse(FileProvider fileProvider) {
		parser.parse(fileProvider);
	}

	/**
	 * Parses a HTML file using the default system encoding and delegates each row parsed to the associated {@link Processor}s
	 * defined in the {@link HtmlParserSettings}.
	 *
	 * @param file the file where the HTML document is located
	 */
	public  void parse(File file) {
		parse(new FileProvider(file));
	}

	/**
	 * Parses a HTML file using the supplied encoding as a {@link Charset} and delegates each row parsed to the associated
	 * {@link Processor}s defined in the {@link HtmlParserSettings}
	 *
	 * @param file the file where the HTML document is located
	 * @param encoding the encoding, as a Charset, that must be used to read from the given file.
	 */
	public  void parse(File file, Charset encoding) {
		parse(new FileProvider(file, encoding));
	}

	/**
	 * Parses a HTML file using the supplied encoding as a String and delegates each row parsed to the associated
	 * {@link Processor}s defined in the {@link HtmlParserSettings}
	 *
	 * @param file the file where the HTML document is located
	 * @param encoding the encoding, as a String, that must be used to read from the given file.
	 */
	public  void parse(File file, String encoding) {
		parse(new FileProvider(file, encoding));
	}

	/**
	 * Parses a HTML input provided by a {@link Reader} and delegates each row parsed to the associated {@link Processor}s
	 * defined in {@link HtmlParserSettings}
	 *
	 * @param reader the reader where the HTML input is located
	 */
	public  void parse(Reader reader) {
		parser.parse(reader);
	}

	/**
	 * Parses a HTML input provided by a {@link InputStream} using the default system encoding and delegates each row parsed
	 * to the associated {@link Processor}s defined in {@link HtmlParserSettings}
	 *
	 * @param inputStream the HTML input that will be parsed
	 */
	public  void parse(InputStream inputStream) {
		parser.parse(inputStream);
	}

	/**
	 * Parses a HTML input provided by a {@link InputStream} using the encoding supplied as a {@link Charset} and delegates
	 * each row parsed to the associated {@link Processor}s defined in {@link HtmlParserSettings}
	 *
	 * @param inputStream the HTML input that will be parsed
	 * @param encoding the encoding, as a Charset, that must be used to read from the given input
	 */
	public  void parse(InputStream inputStream, Charset encoding) {
		parser.parse(inputStream, encoding);
	}

	/**
	 * Parses a HTML input provided by a {@link InputStream} using the encoding supplied as a String and delegates
	 * each row parsed to the associated {@link Processor}s defined in {@link HtmlParserSettings}
	 *
	 * @param inputStream the HTML input that will be parsed
	 * @param encoding the encoding, as a String, that must be used to read from the given input
	 */
	public  void parse(InputStream inputStream, String encoding) {
		parser.parse(inputStream, encoding);
	}
}
