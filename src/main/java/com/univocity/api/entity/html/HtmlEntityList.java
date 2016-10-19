/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;


import com.univocity.parsers.remote.*;

/**
 * This class creates and stores {@link HtmlEntity}s.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlEntity
 * @see HtmlParser
 * @see HtmlParserListener
 */
public class HtmlEntityList extends RemoteEntityList<HtmlEntitySettings> {

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
	protected HtmlEntitySettings newEntity(String entityName) {
		return new HtmlEntitySettings(entityName);
	}

}
