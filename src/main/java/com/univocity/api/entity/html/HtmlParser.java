/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.io.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;
import com.univocity.parsers.common.record.*;
import com.univocity.parsers.remote.*;

import java.io.*;
import java.nio.charset.*;

/**
 * A very fast HTML parser.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParserSettings
 * @see ReaderProvider
 * @see Record
 * @see HtmlEntitySettings
 */
public final class HtmlParser implements HtmlParserInterface {

	private final HtmlParserInterface parser;

	/**
	 * Creates a new HtmlParser with the entity configuration provided by a {@link HtmlEntityList}. The {@code HtmlParser}
	 * gets all configuration from this list and from {@link HtmlEntityList#getParserSettings()}.
	 *
	 * @param entityList The list of entities to be parsed by the {@code HtmlParser}, and their configuration
	 */
	public HtmlParser(HtmlEntityList entityList) {
		if (entityList == null) {
			parser = null;
		} else {
			parser = Builder.build(HtmlParserInterface.class, entityList);
		}
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
	public final Results<HtmlParserResult> parse(ReaderProvider readerProvider) {
		return parser.parse(readerProvider);
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
	public final Results<HtmlParserResult> parse(FileProvider fileProvider) {
		return parser.parse(fileProvider);
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
	public final Results<HtmlParserResult> parse(File file) {
		return parse(new FileProvider(file));
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
	public final Results<HtmlParserResult> parse(File file, Charset encoding) {
		return parse(new FileProvider(file, encoding));
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
	public final Results<HtmlParserResult> parse(File file, String encoding) {
		return parse(new FileProvider(file, encoding));
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
	public final Results<HtmlParserResult> parse(Reader reader) {
		return parser.parse(reader);
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
	public final Results<HtmlParserResult> parse(InputStream inputStream) {
		return parser.parse(inputStream);
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
	public final Results<HtmlParserResult> parse(InputStream inputStream, Charset encoding) {
		return parser.parse(inputStream, encoding);
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
	public final Results<HtmlParserResult> parse(InputStream inputStream, String encoding) {
		return parser.parse(inputStream, encoding);
	}

	/**
	 * Given a {@link HtmlElement}, parses all records of all entities
	 * defined in the {@link EntityList} of this parser, submitting them to the {@link Processor} implementation
	 * associated with each entity (through {@link EntitySettings#setProcessor(Processor)}. The {@link Processor}
	 * implementation will handle the rows as they come, in its {@link Processor#rowProcessed(String[], Context)} method
	 * which can accumulate/transform the rows on demand. The behavior and way to collect results is determined by
	 * the {@link Processor} implementation used.
	 *
	 * @param htmlTree the HTML tree with content to be parsed
	 */
	public final Results<HtmlParserResult> parse(HtmlElement htmlTree) {
		return parser.parse(htmlTree);
	}


	/**
	 * Returns the {@link PaginationContext} object with information collected for the configured {@link Paginator}, if
	 * any. The information returned comes from the last input processed, and might have been modified by a
	 * {@link PaginationHandler} if it has been associated with the {@link Paginator}
	 * using {@link Paginator#setPaginationHandler(PaginationHandler)}.
	 *
	 * @return the current {@link PaginationContext} with pagination information captured after parsing a given input.
	 */
	@Override
	public PaginationContext getPaginationContext() {
		return parser.getPaginationContext();
	}

	/**
	 * Generates a DOM tree from the input made available by a {@link ReaderProvider}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param readerProvider an input provider with content to be parsed
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	public static final HtmlElement parseTree(ReaderProvider readerProvider) {
		return Builder.build(HtmlTreeParser.class).parseTree(readerProvider);
	}

	/**
	 * Generates a DOM tree from the input made available by a {@link FileProvider}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param fileProvider the input file with content to be parsed
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	public static final HtmlElement parseTree(FileProvider fileProvider) {
		return Builder.build(HtmlTreeParser.class).parseTree(fileProvider);
	}

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.Reader}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param reader the input with content to be parsed
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	public static final HtmlElement parseTree(Reader reader) {
		return Builder.build(HtmlTreeParser.class).parseTree(reader);
	}

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.InputStream}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * <i>The default system encoding will be used to read text from the given input.</i>
	 *
	 * @param inputStream the input with content to be parsed
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	public static final HtmlElement parseTree(InputStream inputStream) {
		return Builder.build(HtmlTreeParser.class).parseTree(inputStream);
	}

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.InputStream}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param inputStream the input with content to be parsed
	 * @param encoding    the encoding to be used when reading text from the given input.
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	public static final HtmlElement parseTree(InputStream inputStream, Charset encoding) {
		return Builder.build(HtmlTreeParser.class).parseTree(inputStream, encoding);
	}

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.InputStream}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param inputStream the input with content to be parsed
	 * @param encoding    the encoding to be used when reading text from the given input.
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	public static final HtmlElement parseTree(InputStream inputStream, String encoding) {
		return Builder.build(HtmlTreeParser.class).parseTree(inputStream, encoding);
	}

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.File}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * <i>The default system encoding will be used to read text from the given input.</i>
	 *
	 * @param file the input with content to be parsed
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	public static final HtmlElement parseTree(File file) {
		return Builder.build(HtmlTreeParser.class).parseTree(file);
	}

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.File}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param file     the input with content to be parsed
	 * @param encoding the encoding to be used when reading text from the given input.
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	public static final HtmlElement parseTree(File file, Charset encoding) {
		return Builder.build(HtmlTreeParser.class).parseTree(file, encoding);
	}

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.File}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param file     the input with content to be parsed
	 * @param encoding the encoding to be used when reading text from the given input.
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	public static final HtmlElement parseTree(File file, String encoding) {
		return Builder.build(HtmlTreeParser.class).parseTree(file, encoding);
	}
}
