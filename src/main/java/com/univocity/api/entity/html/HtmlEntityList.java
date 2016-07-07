package com.univocity.api.entity.html;

import com.univocity.api.common.*;

import java.util.*;

/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
public class HtmlEntityList {

	private final  Map<String, HtmlEntity> entities = new TreeMap<String, HtmlEntity>();

	private HtmlParserListener listener;

	public HtmlEntityList() {

	}


	public HtmlEntity configureEntity(String entityName) {
		Args.notBlank(entityName, "Entity name");
		String normalizedEntityName = entityName.trim().toLowerCase();
		if (entities.get(normalizedEntityName) == null) {
			entities.put(normalizedEntityName, new HtmlEntity(entityName));
		}
			return entities.get(normalizedEntityName);
	}

	public Set<String> getEntityNames() {
		return new TreeSet<String>(entities.keySet());
	}

	public void setHtmlParserListener(HtmlParserListener listener) {
		this.listener = listener;
	}

	public HtmlParserListener getHtmlParserListener() {
		return listener;
	}


	public Collection<HtmlEntity> getEntities() {
		return Collections.unmodifiableCollection(entities.values());
	}

}
