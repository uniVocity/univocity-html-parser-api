/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.parsers.common.*;
import com.univocity.parsers.common.record.*;
import com.univocity.parsers.remote.*;

/**
 * An extension of the {@link EntityParserInterface} to include all operations specific to the {@link HtmlParser}
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlParserInterface extends RemoteEntityParserInterface<HtmlRecord, HtmlParsingContext, HtmlParserResult> {

	/**
	 * Given a {@link HtmlElement}, parses all records of all entities
	 * defined in the {@link EntityList} of this parser, and returns them in a map.  Keys are the entity names
	 * and values are lists of {@link Record} produced for that entity.
	 *
	 * @param htmlTree the HTML tree with content to be parsed
	 *
	 * @return a map of entity names and the corresponding records extracted from the given HTML tree.
	 */
	Results<HtmlParserResult> parse(HtmlElement htmlTree);

	/**
	 * Returns the {@link HtmlPaginationContext} object with information collected for the configured {@link HtmlPaginator}, if
	 * any. The information returned comes from the last input processed, and might have been modified by a
	 * {@link NextInputHandler} if it has been associated with the {@link HtmlPaginator}
	 * using {@link Paginator#setPaginationHandler(NextInputHandler)}.
	 *
	 * @return the current {@link PaginationContext} with pagination information captured after parsing a given input.
	 */
	@Override
	HtmlPaginationContext getPaginationContext();
}
