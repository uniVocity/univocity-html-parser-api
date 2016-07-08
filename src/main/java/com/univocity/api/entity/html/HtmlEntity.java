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
 * This class defines entities that will be parsed. A entity has a name and one or more fields. These fields specify
 * what specific HTML elements will be parsed, as each field has a path to the HTML data that will be parsed.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class HtmlEntity implements FieldAdder {

	private final String entityName;

	final Map<String, List<HtmlPath>> fields = new LinkedHashMap<String, List<HtmlPath>>();
	final List<RecordTrigger> triggers = new ArrayList<RecordTrigger>();

	/**
	 * Creates a new HTMLEntity without a name.
	 */
	HtmlEntity() {
		this.entityName = null;
	}

	/**
	 * Creates a new HTMLEntity and associates it with the supplied name
	 * @param entityName a string that identifies the HTMLEntity
	 */
	HtmlEntity(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * Returns the name of the HTMLEntity
	 * @return the name as a String
	 */
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

	/**
	 * The name of the column. This method returns a {@link HtmlPathStart}, which allows the specification of a HTML
	 * path that defines what HTML data will be returned when the parser runs. For example, you could define a field
	 * called "headings", match "H1" and getText. When the parser runs, the headings in the HTML document will be returned
	 * and be available in the field "headings".
	 * <code>
	 *	HtmlEntity entity = new HtmlEntity();
	 *  entity.addField("headings").match("H1").getText();
	 * </code>
	 * @param fieldName The that the field will be called
	 * @return a {@link HtmlPathStart}, so that a path to the HTML can be defined
	 */
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

	/**
	 * Returns the name of all fields associated with the HtmlEntity.
	 * @return a String array of the field names
	 */
	public String[] getFields(){
		return fields.keySet().toArray(new String[0]);
	}
}
