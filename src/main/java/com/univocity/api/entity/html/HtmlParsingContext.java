/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.common.*;
import com.univocity.parsers.common.*;

import java.io.*;
import java.util.*;

/**
 * A class that returns information about {@link HtmlParser}'s parsing process.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 * @see Context
 * @see HtmlElement
 */
public interface HtmlParsingContext extends Context {

	/**
	 * If the Parser is reading from a web page, returns the {@link HttpResponse} that it is using. Otherwise it will
	 * return null.
	 *
	 * @return the {@link HttpResponse} that the parser is using, or null if parsing local file.
	 */
	HttpResponse getResponse();

	/**
	 * Returns the file that was last downloaded by the the specified field.
	 *
	 * @param fieldName the name that identifies the field
	 * @return the last downloaded file from a field
	 */
	File getFile(String fieldName);

	/**
	 * Returns the fields that are set to download content.
	 *
	 * @return downloading content fields
	 */
	Set<String> getBinaryFileFields();
}
