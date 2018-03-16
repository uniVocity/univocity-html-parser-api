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
 * be overridden in the configuration of each individual entity.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see HtmlParserSettings
 * @see HtmlParserListener
 */
public final class HtmlEntityList extends RemoteEntityList<HtmlEntitySettings> {

	/**
	 * Creates a new, empty `HtmlEntityList`, with a default global {@link HtmlParserSettings} configuration, which is
	 * used to provide defaults to all entity-specific settings in this list.
	 */
	public HtmlEntityList() {
		super(new HtmlParserSettings());
	}

	/**
	 * Creates a new, empty `HtmlEntityList`, applying the global {@link HtmlParserSettings} configuration, which is
	 * used to provide defaults to all entity-specific settings in this list.
	 *
	 * @param parserSettings the global parser settings whose configuration may provide defaults for all entities
	 *                       defined in this list.
	 */
	public HtmlEntityList(HtmlParserSettings parserSettings) {
		super(parserSettings);
	}

	/**
	 * Creates a new {@link HtmlEntitySettings} for an entity with the supplied name and returns it. Used
	 * by {@link RemoteEntityList#configureEntity(String)}.
	 *
	 * @param entityName the name that will be used to identify the entity
	 *
	 * @return the new settings object of the given entity.
	 */
	@Override
	protected final HtmlEntitySettings newEntity(String entityName, HtmlEntitySettings parentEntity) {
		return new HtmlEntitySettings(entityName, parentEntity);
	}

	public HtmlParserSettings getParserSettings() {
		return (HtmlParserSettings) super.getParserSettings();
	}

	@Override
	protected HtmlEntityList newInstance() {
		return new HtmlEntityList(getParserSettings().clone());
	}

	/**
	 * Returns the {@link HtmlPaginator} associated with the {@code HtmlParserSettings} of this {@code HtmlEntityList}
	 *
	 * @return the {@link HtmlPaginator} stored the {@code HtmlParserSettings} of this {@code HtmlEntityList}
	 */
	public final HtmlPaginator getPaginator() {
		return  getParserSettings().getPaginator();
	}
}
