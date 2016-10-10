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
	 * Returns a Map of matched data where the value is the field name and the value is the data that was matched. Values
	 * are matched when the {@link HtmlParser} encounters a value defined by a path set by an {@link HtmlEntity}'s added
	 * field.
	 *
	 * @return a Map of data that was matched during parsing
	 */
	Map<String, String> getMatchedData();

	/**
	 * Returns the name of the {@link HtmlEntity} that the {@link HtmlParser} is using to parse the HTML document.
	 *
	 * @return the name that identifies the {@link HtmlEntity} being used by the {@link HtmlParser}
	 */
	String getEntityName();


	/**
	 * Returns the current node depth of the parser. Node depth is how many layers deep the currently visited HTML
	 * element is. For example given a simple HTML document like: {@code <div><span><span><div>}. When the parser visits
	 * the span element, the current node depth would be 1 (the node depth of div would be 0).
	 *
	 * @return the depth of the currently visited HTML element
	 */
	int currentNodeDepth();

	/**
	 * Returns the element that the Parser is currently visiting.
	 *
	 * @return the element that is currently being visited by the parser
	 */
	HtmlElement getCurrentElement();

	/**
	 * Returns the file that was last downloaded for a given binary field.
	 *
	 * @param binaryFieldName name that identifies a field configured to download binary content.
	 *
	 * @return the last downloaded file for the given field
	 */
	File getFile(String binaryFieldName);

	/**
	 * Returns the names of the fields set to download content.
	 *
	 * @return names of fields associated with downloaded content
	 */
	Set<String> getBinaryFields();

}
