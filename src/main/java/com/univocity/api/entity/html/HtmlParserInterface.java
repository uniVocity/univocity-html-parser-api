/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.parsers.common.*;
import com.univocity.parsers.common.record.*;
import com.univocity.parsers.remote.*;

import java.util.*;

/**
 * An extension of the {@link EntityParserInterface} to include all operations specific to the {@link HtmlParser}
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlParserInterface extends RemoteEntityParserInterface<HtmlPaginationContext, HtmlRecord, HtmlParsingContext, HtmlParserResult> {

	/**
	 * Given a {@link HtmlElement}, parses all records of all entities
	 * defined in the {@link EntityList} of this parser, and returns them in a map.  Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * @param htmlTree the HTML tree with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given HTML tree.
	 */
	Map<String, HtmlParserResult> parse(HtmlElement htmlTree);

}
