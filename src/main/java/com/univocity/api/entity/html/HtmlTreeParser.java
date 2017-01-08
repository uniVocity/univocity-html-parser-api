/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.io.*;

import java.io.*;
import java.nio.charset.*;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlTreeParser {

	/**
	 * Generates a DOM tree from the input made available by a {@link ReaderProvider}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param readerProvider an input provider with content to be parsed
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	HtmlElement parseTree(ReaderProvider readerProvider);

	/**
	 * Generates a DOM tree from the input made available by a {@link FileProvider}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param fileProvider the input file with content to be parsed
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	HtmlElement parseTree(FileProvider fileProvider);

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.Reader}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param reader the input with content to be parsed
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	HtmlElement parseTree(Reader reader);

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
	HtmlElement parseTree(InputStream inputStream);

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.InputStream}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param inputStream the input with content to be parsed
	 * @param encoding    the encoding to be used when reading text from the given input.
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	HtmlElement parseTree(InputStream inputStream, Charset encoding);

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.InputStream}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param inputStream the input with content to be parsed
	 * @param encoding    the encoding to be used when reading text from the given input.
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	HtmlElement parseTree(InputStream inputStream, String encoding);

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
	HtmlElement parseTree(File file);

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.File}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param file     the input with content to be parsed
	 * @param encoding the encoding to be used when reading text from the given input.
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	HtmlElement parseTree(File file, Charset encoding);

	/**
	 * Generates a DOM tree from the input made available by a {@link java.io.File}. Users can navigate the HTML tree
	 * and use CSS selectors against the {@link HtmlElement}s returned to target any specific HTML node.
	 *
	 * @param file     the input with content to be parsed
	 * @param encoding the encoding to be used when reading text from the given input.
	 *
	 * @return the root {@link HtmlElement} of the entire HTML document.
	 */
	HtmlElement parseTree(File file, String encoding);
}
