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
 * @see HtmlParserSettings
 * @see ReaderProvider
 * @see Record
 * @see HtmlEntitySettings
 */
public final class HtmlParser implements EntityParserInterface {

	private final EntityParserInterface parser;

	/**
	 * Creates a new HtmlParser with configuration provided by {@link HtmlParserSettings}. The {code HtmlParser} gets all
	 * configuration from this settings class.
	 *
	 * @param settings The {@code HtmlParser} configuration
	 */
	public HtmlParser(HtmlParserSettings settings) {
		if (settings == null) {
			parser = null;
		} else {
			parser = Builder.build(EntityParserInterface.class, settings);
		}
	}

	/**
	 * Given an input, made available from a {@link ReaderProvider}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names and values are lists of rows produced for
	 * that entity.
	 *
	 * @param readerProvider an input provider with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<String[]>> parseAll(ReaderProvider readerProvider) {
		return parser.parseAll(readerProvider);
	}

	/**
	 * Given an input file, made available from a {@link FileProvider}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names and values are lists of rows produced for
	 * that entity.
	 *
	 * @param fileProvider the input file with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<String[]>> parseAll(FileProvider fileProvider) {
		return parser.parseAll(fileProvider);
	}

	/**
	 * Given a {@link java.io.Reader}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names and values are lists of rows produced for
	 * that entity.
	 *
	 * @param reader the input with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<String[]>> parseAll(Reader reader) {
		return parser.parseAll(reader);
	}

	/**
	 * Given an {@link java.io.InputStream}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names and values are lists of rows produced for
	 * that entity.
	 *
	 * <i>The default system encoding will be used to read text from the given input.</i>
	 *
	 * @param inputStream the input with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<String[]>> parseAll(InputStream inputStream) {
		return parser.parseAll(inputStream);
	}

	/**
	 * Given an {@link java.io.InputStream}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names and values are lists of rows produced for
	 * that entity.
	 *
	 * @param inputStream the input with content to be parsed
	 * @param encoding    the encoding to be used when reading text from the given input.
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<String[]>> parseAll(InputStream inputStream, Charset encoding) {
		return parser.parseAll(inputStream, encoding);
	}

	/**
	 * Given an {@link java.io.InputStream}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names and values are lists of rows produced for
	 * that entity.
	 *
	 * @param inputStream the input with content to be parsed
	 * @param encoding    the encoding to be used when reading text from the given input.
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<String[]>> parseAll(InputStream inputStream, String encoding) {
		return parser.parseAll(inputStream, encoding);
	}

	/**
	 * Given a {@link java.io.File}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names and values are lists of rows produced for
	 * that entity.
	 *
	 * <i>The default system encoding will be used to read text from the given input.</i>
	 *
	 * @param file the input with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<String[]>> parseAll(File file) {
		return parseAll(new FileProvider(file));
	}

	/**
	 * Given a {@link java.io.File}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names and values are lists of rows produced for
	 * that entity.
	 *
	 * @param file     the input with content to be parsed
	 * @param encoding the encoding to be used when reading text from the given input.
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<String[]>> parseAll(File file, Charset encoding) {
		return parseAll(new FileProvider(file, encoding));
	}

	/**
	 * Given a {@link java.io.File}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names and values are lists of rows produced for
	 * that entity.
	 *
	 * @param file     the input with content to be parsed
	 * @param encoding the encoding to be used when reading text from the given input.
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<String[]>> parseAll(File file, String encoding) {
		return parseAll(new FileProvider(file, encoding));
	}


	/**
	 * Given an input, made available from a {@link ReaderProvider}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * @param readerProvider an input provider with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<Record>> parseAllRecords(ReaderProvider readerProvider) {
		return parser.parseAllRecords(readerProvider);
	}

	/**
	 * Given an input file, made available from a {@link FileProvider}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * @param fileProvider an input file with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<Record>> parseAllRecords(FileProvider fileProvider) {
		return parser.parseAllRecords(fileProvider);
	}

	/**
	 * Given a {@link java.io.Reader}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * @param reader the input with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<Record>> parseAllRecords(Reader reader) {
		return parser.parseAllRecords(reader);
	}

	/**
	 * Given a {@link java.io.File}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * <i>The default system encoding will be used to read text from the given input.</i>
	 *
	 * @param file the input with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<Record>> parseAllRecords(File file) {
		return parseAllRecords(new FileProvider(file));
	}

	/**
	 * Given a {@link java.io.File}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * @param file     the input with content to be parsed
	 * @param encoding the encoding to be used when reading text from the given input.
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<Record>> parseAllRecords(File file, Charset encoding) {
		return parseAllRecords(new FileProvider(file, encoding));
	}

	/**
	 * Given a {@link java.io.File}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * @param file     the input with content to be parsed
	 * @param encoding the encoding to be used when reading text from the given input.
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<Record>> parseAllRecords(File file, String encoding) {
		return parseAllRecords(new FileProvider(file, encoding));
	}

	/**
	 * Given an {@link java.io.InputStream}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * <i>The default system encoding will be used to read text from the given input.</i>
	 *
	 * @param inputStream the input with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<Record>> parseAllRecords(InputStream inputStream) {
		return parser.parseAllRecords(inputStream);
	}

	/**
	 * Given an {@link java.io.InputStream}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * @param inputStream the input with content to be parsed
	 * @param encoding    the encoding to be used when reading text from the given input.
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<Record>> parseAllRecords(InputStream inputStream, Charset encoding) {
		return parser.parseAllRecords(inputStream, encoding);
	}

	/**
	 * Given an {@link java.io.InputStream}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, and returns them in a map. Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * @param inputStream the input with content to be parsed
	 * @param encoding    the encoding to be used when reading text from the given input.
	 *
	 * @return a map of entity names and the corresponding records extracted from the given input.
	 */
	public final Map<String, List<Record>> parseAllRecords(InputStream inputStream, String encoding) {
		return parser.parseAllRecords(inputStream, encoding);
	}

	/**
	 * Given an input, made available from a {@link ReaderProvider}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, submitting them to the {@link Processor} implementation
	 * associated with each entity (through {@link EntitySettings#setProcessor(Processor)}. The {@link Processor}
	 * implementation will handle the rows as they come, in its {@link Processor#rowProcessed(String[], Context)} method
	 * which can accumulate/transform the rows on demand. The behavior and way to collect results is determined by
	 * the {@link Processor} implementation used.
	 *
	 * @param readerProvider an input provider with content to be parsed
	 */
	public final void parse(ReaderProvider readerProvider) {
		parser.parse(readerProvider);
	}

	/**
	 * Given an input, made available from a {@link FileProvider}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, submitting them to the {@link Processor} implementation
	 * associated with each entity (through {@link EntitySettings#setProcessor(Processor)}. The {@link Processor}
	 * implementation will handle the rows as they come, in its {@link Processor#rowProcessed(String[], Context)} method
	 * which can accumulate/transform the rows on demand. The behavior and way to collect results is determined by
	 * the {@link Processor} implementation used.
	 *
	 * @param fileProvider the input file with content to be parsed
	 */
	public final void parse(FileProvider fileProvider) {
		parser.parse(fileProvider);
	}

	/**
	 * Given a {@link java.io.File}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, submitting them to the {@link Processor} implementation
	 * associated with each entity (through {@link EntitySettings#setProcessor(Processor)}. The {@link Processor}
	 * implementation will handle the rows as they come, in its {@link Processor#rowProcessed(String[], Context)} method
	 * which can accumulate/transform the rows on demand. The behavior and way to collect results is determined by
	 * the {@link Processor} implementation used.
	 *
	 * <i>The default system encoding will be used to read text from the given input.</i>
	 *
	 * @param file the input with content to be parsed
	 */
	public final void parse(File file) {
		parse(new FileProvider(file));
	}

	/**
	 * Given a {@link java.io.File}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, submitting them to the {@link Processor} implementation
	 * associated with each entity (through {@link EntitySettings#setProcessor(Processor)}. The {@link Processor}
	 * implementation will handle the rows as they come, in its {@link Processor#rowProcessed(String[], Context)} method
	 * which can accumulate/transform the rows on demand. The behavior and way to collect results is determined by
	 * the {@link Processor} implementation used.
	 *
	 * @param file     the input with content to be parsed
	 * @param encoding the encoding to be used when reading text from the given input.
	 */
	public final void parse(File file, Charset encoding) {
		parse(new FileProvider(file, encoding));
	}

	/**
	 * Given a {@link java.io.File}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, submitting them to the {@link Processor} implementation
	 * associated with each entity (through {@link EntitySettings#setProcessor(Processor)}. The {@link Processor}
	 * implementation will handle the rows as they come, in its {@link Processor#rowProcessed(String[], Context)} method
	 * which can accumulate/transform the rows on demand. The behavior and way to collect results is determined by
	 * the {@link Processor} implementation used.
	 *
	 * @param file     the input with content to be parsed
	 * @param encoding the encoding to be used when reading text from the given input.
	 */
	public final void parse(File file, String encoding) {
		parse(new FileProvider(file, encoding));
	}

	/**
	 * Given a {@link java.io.Reader}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, submitting them to the {@link Processor} implementation
	 * associated with each entity (through {@link EntitySettings#setProcessor(Processor)}. The {@link Processor}
	 * implementation will handle the rows as they come, in its {@link Processor#rowProcessed(String[], Context)} method
	 * which can accumulate/transform the rows on demand. The behavior and way to collect results is determined by
	 * the {@link Processor} implementation used.
	 *
	 * @param reader the input with content to be parsed
	 */
	public final void parse(Reader reader) {
		parser.parse(reader);
	}

	/**
	 * Given an {@link java.io.InputStream}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, submitting them to the {@link Processor} implementation
	 * associated with each entity (through {@link EntitySettings#setProcessor(Processor)}. The {@link Processor}
	 * implementation will handle the rows as they come, in its {@link Processor#rowProcessed(String[], Context)} method
	 * which can accumulate/transform the rows on demand. The behavior and way to collect results is determined by
	 * the {@link Processor} implementation used.
	 *
	 * <i>The default system encoding will be used to read text from the given input.</i>
	 *
	 * @param inputStream the input with content to be parsed
	 */
	public final void parse(InputStream inputStream) {
		parser.parse(inputStream);
	}

	/**
	 * Given an {@link java.io.InputStream}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, submitting them to the {@link Processor} implementation
	 * associated with each entity (through {@link EntitySettings#setProcessor(Processor)}. The {@link Processor}
	 * implementation will handle the rows as they come, in its {@link Processor#rowProcessed(String[], Context)} method
	 * which can accumulate/transform the rows on demand. The behavior and way to collect results is determined by
	 * the {@link Processor} implementation used.
	 *
	 * @param inputStream the input with content to be parsed
	 * @param encoding    the encoding to be used when reading text from the given input.
	 */
	public final void parse(InputStream inputStream, Charset encoding) {
		parser.parse(inputStream, encoding);
	}

	/**
	 * Given an {@link java.io.InputStream}, parses all records of all entities
	 * defined in the {@link HtmlEntityList} of the {@link HtmlParserSettings} object provided in the constructor
	 * of this class, submitting them to the {@link Processor} implementation
	 * associated with each entity (through {@link EntitySettings#setProcessor(Processor)}. The {@link Processor}
	 * implementation will handle the rows as they come, in its {@link Processor#rowProcessed(String[], Context)} method
	 * which can accumulate/transform the rows on demand. The behavior and way to collect results is determined by
	 * the {@link Processor} implementation used.
	 *
	 * @param inputStream the input with content to be parsed
	 * @param encoding    the encoding to be used when reading text from the given input.
	 */
	public final void parse(InputStream inputStream, String encoding) {
		parser.parse(inputStream, encoding);
	}
}
