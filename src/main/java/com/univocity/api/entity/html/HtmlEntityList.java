/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html;

import com.univocity.api.common.*;

import java.util.*;



/**
 * This class creates and stores {@link HtmlEntity}s.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlEntity
 * @see HtmlParser
 * @see HtmlParserListener
 */
public class HtmlEntityList {

	private final  Map<String, HtmlEntity> entities = new TreeMap<String, HtmlEntity>();

	private HtmlParserListener listener;

	/**
	 * Creates a new, empty HtmlEntityList
	 */
	public HtmlEntityList() {
	}


	/**
	 * Returns the {@link HtmlEntity} associated with the given entityName. If there is not {@link HtmlEntity} with that
	 * name, it creates it and returns it.
	 *
	 * @param entityName the name of the {@link HtmlEntity} that will be returned.
	 * @return the {@link HtmlEntity} with the given entityName
	 */
	public HtmlEntity configureEntity(String entityName) {
		Args.notBlank(entityName, "Entity name");
		String normalizedEntityName = entityName.trim().toLowerCase();
		if (entities.get(normalizedEntityName) == null) {
			entities.put(normalizedEntityName, new HtmlEntity(entityName));
		}
			return entities.get(normalizedEntityName);
	}

	/**
	 * Returns the entity names stored in the HtmlEntityList as a set of type String
	 *
	 * @return entity names stored as a set.
	 */
	public Set<String> getEntityNames() {
		return new TreeSet<String>(entities.keySet());
	}

	public void setHtmlParserListener(HtmlParserListener listener) {
		this.listener = listener;
	}

	/**
	 * Returns the {@link HtmlParserListener} associated with the HtmlEntityList.
	 *
	 * @return the {@link HtmlParserListener}
	 */
	public HtmlParserListener getHtmlParserListener() {
		return listener;
	}


	/**
	 * Returns all the entities stored in the entityList as a unmodifiable Collection
	 *
	 * @return a Collection of {@link HtmlEntity}s.
	 */
	public Collection<HtmlEntity> getEntities() {
		return Collections.unmodifiableCollection(entities.values());
	}

}
