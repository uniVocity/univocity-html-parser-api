/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.entity.html.builders.*;

import java.util.*;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class HtmlEntityConfiguration implements FieldAdder {

	private final String entityName;

	final Map<String, List<HtmlPath>> fields = new LinkedHashMap<String, List<HtmlPath>>();
	final List<RecordTrigger> triggers = new ArrayList<RecordTrigger>();

	HtmlEntityConfiguration() {
		this.entityName = null;
	}

	HtmlEntityConfiguration(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityName() {
		return entityName;
	}

	@Override
	public HtmlPathStart addSilentField(String fieldName) {
		return newField(fieldName, false, true);
	}

	@Override
	public HtmlPathStart addSilentPersistentField(String fieldName) {
		return newField(fieldName, true, true);
	}

	public HtmlPathStart addField(String fieldName) {
		return newField(fieldName, false, false);
	}

	public HtmlPathStart addPersistentField(String fieldName) {
		return newField(fieldName, true, false);
	}

	private HtmlPathStart newField(String fieldName, boolean persistent, boolean inhibitNewRows) {
		HtmlPath pathBuilder = Builder.build(HtmlPath.class, this, persistent, inhibitNewRows);
		addPathToField(fieldName, pathBuilder);
		return pathBuilder;
	}

	void addPathToField(String fieldName, HtmlPath path) {
		List<HtmlPath> paths = fields.get(fieldName);
		if (paths == null) {
			paths = new ArrayList<HtmlPath>();
			fields.put(fieldName, paths);
		}
		paths.add(path);
	}

	void addTrigger(RecordTrigger trigger) {
		this.triggers.add(trigger);
	}

	public PartialHtmlPathStart newPath() {
		return Builder.build(PartialHtmlPathStart.class, this);
	}

	public HtmlGroupStart newGroup() {
		return Builder.build(HtmlGroupStart.class, this);
	}

	public RecordTriggerStart addRecordTrigger() {
		RecordTrigger out = Builder.build(RecordTrigger.class, this);
		addTrigger(out);
		return out;
	}

}
