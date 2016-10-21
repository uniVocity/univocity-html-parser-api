/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;


import com.univocity.parsers.remote.*;

/**
 * Manages a list of HTML entities and their {@link HtmlEntitySettings}. Common configuration options shared with
 * {@link HtmlEntitySettings} and {@link HtmlParserSettings} are inherited from the {@link HtmlParserSettings}, but can
 * be overriden in the configuration of each individual entity.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see HtmlParserSettings
 * @see HtmlParserListener
 */
public final class HtmlEntityList extends RemoteEntityList<HtmlEntitySettings> {

	/**
	 * Creates a new, empty HtmlEntityList
	 */
	public HtmlEntityList() {
	}

	/**
	 * Creates a new {@link HtmlEntitySettings} with the supplied name and returns it. Used by {@link RemoteEntityList#configureEntity(String)}.
	 *
	 * @param entityName the name that will be used to identify the entity
	 *
	 * @return the HtmlEntitySettings that was created
	 */
	@Override
	protected final HtmlEntitySettings newEntity(String entityName) {
		return new HtmlEntitySettings(entityName);
	}

}
