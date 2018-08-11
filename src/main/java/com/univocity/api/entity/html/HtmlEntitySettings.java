/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.common.*;
import com.univocity.api.entity.html.builders.*;
import com.univocity.api.net.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.remote.*;

import java.util.*;

/**
 * A `HtmlEntitySettings` object manages the configuration of a HTML entity. An entity has a name and one or more
 * fields. These fields have paths to the elements that will have their data collected. In addition, a {@link HtmlParserListener}
 * can be associated with an entity to notify the user of actions made by the {@link HtmlParser}.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlEntityList
 * @see HtmlParser
 * @see HtmlParserListener
 * @see HtmlParsingContext
 */
public class HtmlEntitySettings extends RemoteEntitySettings<HtmlParsingContext, CommonParserSettings, HtmlParserSettings, HtmlLinkFollower> implements FieldDefinition {

	Map<String, Object> fields = new LinkedHashMap<String, Object>();
	final List<RecordTrigger> triggers = new ArrayList<RecordTrigger>(1);
	private HtmlParserListener listener = null;

	/**
	 * Creates a new HTML entity configuration and associates it with the supplied name.
	 *
	 * @param entityName a string that identifies the HTMLEntity
	 */
	HtmlEntitySettings(String entityName, HtmlEntitySettings parentEntity) {
		super(entityName, createEmptyParserSettings(), parentEntity);
	}

	@Override
	public final PathStart addSilentField(String fieldName) {
		return newField(fieldName, false, true);
	}

	@Override
	public final PathStart addField(String fieldName) {
		return newField(fieldName, false, false);
	}

	@Override
	public final PathStart addPersistentField(String fieldName) {
		return newField(fieldName, true, false);
	}


	/**
	 * Used by the field adding methods. Creates a new {@link PathStart} based on the supplied options and adds it
	 * to the given field. Finally, returns the created {@link PathStart}.
	 *
	 * @param fieldName      the name that identifies the field
	 * @param persistent     if true, the field is persistent
	 * @param inhibitNewRows if true, the field is silent
	 *
	 * @return a {@link PathStart} to define a path
	 */
	private PathStart newField(String fieldName, boolean persistent, boolean inhibitNewRows) {
		FieldPath pathBuilder = Builder.build(FieldPath.class, fieldName, this, persistent, inhibitNewRows);
		addPathToField(fieldName, pathBuilder);
		return pathBuilder;
	}

	/**
	 * Used by {@link #newField(String, boolean, boolean)} to add a path to the field. It tries to get the
	 * field from the fields map. If it doesn't exist, it puts a new field into the map and associates it with the
	 * given path.
	 *
	 * @param fieldName the name identifies the field
	 * @param path      the path that will be associated with the field
	 */
	final void addPathToField(String fieldName, FieldPath path) {
		List<FieldPath> paths = (List<FieldPath>) fields.get(fieldName);
		if (paths == null) {
			paths = new ArrayList<FieldPath>();
			fields.put(fieldName, paths);
		}
		paths.add(path);
	}


	/**
	 * Used by {@link #addRecordTrigger()}. When a record trigger is defined, it gets added to the
	 * {@link #triggers} list
	 *
	 * @param trigger the {@link RecordTrigger} that will be added to the list
	 */
	final void addTrigger(RecordTrigger trigger) {
		this.triggers.add(trigger);
	}

	/**
	 * Returns a {@link PartialPathStart} that is used to define a reusable path of HTML elements. Fields then can
	 * added to this path using {@link PartialPath#addField(String)} and others, which associates the field with this entity.
	 *
	 * Example:
	 *
	 * ```java
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * HtmlEntitySettings items = entityList.configureEntity("items");
	 * PartialPath path = items.newPath()
	 *     .match("table").id("productsTable")
	 *     .match("td").match("div").classes("productContainer");
	 *
	 * //uses the path to add new fields to it and further element matching rules from the initial, common path.
	 * path.addField("name").match("span").classes("prodName", "prodNameTro").getText();
	 * path.addField("URL").match("a").childOf("div").classes("productPadding").getAttribute("href")
	 * ```
	 *
	 * @return a {@link PartialPathStart} to specify the path of HTML elements
	 */
	public final PartialPathStart newPath() {
		return Builder.build(PartialPathStart.class, this);
	}

	/**
	 * Returns a {@link GroupStart} that allows for a {@link Group} to be defined. A {@link Group} demarcates a section
	 * of the HTML input that is allowed to be parsed. {@link FieldPath}s created from a group will only be executed inside
	 * this defined area, ignoring any HTML that exists outside of it. For example, say you wanted to extract
	 * the "hello" and "howdy" words from the following HTML:
	 *
	 * ```html
	 * <div class="parseMe">
	 * <p>hello</p>
	 * </div>
	 * <p>howdy</p>
	 * <h1>No Parsing Area</h1>
	 * <p>don't parse me!</p>
	 * ```
	 *
	 * The parsing rules, using groups, can be defined as:
	 *
	 * ```java
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * HtmlParserSettings settings = new HtmlParserSettings(entityList);
	 *
	 * Group group = entityList.configureEntity("test")
	 *     .newGroup()
	 *     .startAt("div").classes("parseMe")
	 *     .endAt("h1");
	 *
	 * group.addField("greeting").match("p").getText();
	 * ```
	 *
	 * The parser will then ignore the `"don't parse me"` paragraph as the group restricts the parsing to the area
	 * defined from a `div` with `class` "parseMe" until an opening `h1` tag.
	 *
	 * @return a {@link GroupStart} used to specify where the {@link Group} starts.
	 */
	public final GroupStart newGroup() {
		return Builder.build(GroupStart.class, this);
	}


	/**
	 * Creates a new {@link PaginationGroup} for the {@link HtmlPaginator}.
	 *
	 * @return a {@link PaginationGroupStart} used to specify where {@link PaginationGroup} starts.
	 */
	final PaginationGroupStart newPaginationGroup(HtmlPaginator paginator) {
		return Builder.build(PaginationGroupStart.class, paginator);
	}

	/**
	 * Returns a {@link RecordTriggerStart} that is used to specify a path that defines when rows should be created.
	 *
	 * See documentation in {@link Trigger#addRecordTrigger()} for a detailed explanation.
	 *
	 * @return a {@link RecordTriggerStart} that defines the path for the trigger
	 */
	public final RecordTriggerStart addRecordTrigger() {
		RecordTrigger out = Builder.build(RecordTrigger.class, this);
		addTrigger(out);
		return out;
	}

	@Override
	public final Set<String> getFieldNames() {
		return Collections.unmodifiableSet(fields.keySet());
	}

	@Override
	public final void removeField(String fieldName) {
		fields.remove(fieldName);
	}


	@Override
	public final void addField(String fieldName, String constantValue) {
		fields.put(fieldName, constantValue);
	}

	@Override
	public final void addFieldFromParent(String field) {
		if (getParentEntitySettings() == null) {
			throw new IllegalArgumentException("Can't add parent field '" + field + "' to '" + getEntityName() + "'. No parent entity defined.");
		}
		addFieldFrom(getParentEntitySettings().getEntityName(), field);
	}

	@Override
	public final void addFieldFrom(String parentEntityName, String field) {
		Args.notBlank(parentEntityName, "Parent entity name");
		Args.notBlank(field, "Field from parent entity " + parentEntityName);

		HtmlEntitySettings parent = getParentEntitySettings();
		Set<String> allParentFields = new TreeSet<String>();
		while (parent != null) {
			if (parentEntityName.equalsIgnoreCase(parent.getEntityName())) {
				Set<String> parentFields = parent.getFieldNames();
				if (parentFields.contains(field)) {
					this.fields.put(field, parent);
					return;
				} else {
					allParentFields.addAll(parentFields);
				}
			}
			parent = parent.getParentEntitySettings();
		}
		if (parent == null) {
			throw new IllegalArgumentException("Can't find entity '" + parentEntityName + "' in hierarchy of entity '" + this.getEntityName() + "'");
		} else {
			throw new IllegalArgumentException("Can't find field '" + field + "' in parent entity '" + parentEntityName + "'. Available fields are: " + allParentFields);
		}
	}

	/**
	 * Associates a {@link HtmlParserListener} with this HTML entity. The listener methods will be triggered
	 * by the {@link HtmlParser} while it traverses the HTML structure to collect values for the fields of this entity.
	 * In essence, a {@link HtmlParserListener} provides information about events that occur during the parsing process.
	 *
	 * <b>Important:</b>The listener methods are used in a concurrent environment. If you are using the same
	 * instance on multiple entities make sure your listener implementation is thread-safe, or limit the number
	 * of threads to be used when parsing to <b>1</b> with {@link HtmlParserSettings#setParserThreadCount(int)}
	 *
	 * @param listener the {@link HtmlParserListener} to be used when the parser executes to collect values for the fields
	 *                 of this entity.
	 */
	public final void setListener(HtmlParserListener listener) {
		this.listener = listener;
	}

	/**
	 * Returns the {@link HtmlParserListener} associated with this HTML entity. The listener methods will be triggered
	 * by the {@link HtmlParser} while it traverses the HTML structure to collect values for the fields of this entity
	 * In essence, a {@link HtmlParserListener} provides information about events that occur during the parsing process.
	 *
	 * <b>Important:</b>The listener methods are used in a concurrent environment. If you are using the same
	 * instance on multiple entities make sure your listener implementation is thread-safe, or limit the number
	 * of threads to be used when parsing to <b>1</b> with {@link HtmlParserSettings#setParserThreadCount(int)}
	 *
	 * @return the {@link HtmlParserListener} to be used when the parser executes to collect values for the fields
	 * of this entity.
	 */
	public final HtmlParserListener getListener() {
		return listener;
	}

	HtmlLinkFollower addHtmlLinkFollower(String fieldName) {
		HtmlLinkFollower htmlLinkFollower = new HtmlLinkFollower(this);
		//FIXME: will break if multiple paths are assigned to same field name and a different link follower is to be used for each path.
		followers.put(fieldName, htmlLinkFollower);
		return htmlLinkFollower;
	}


	/**
	 * Creates a {@link HtmlLinkFollower} a field with the name provided. The link follower will access the
	 * {@link UrlReaderProvider} all values collected from this resource will be joined with the results of the current
	 * entity using the {@link #getNesting()} strategy defined.
	 *
	 * A parametrized URL can be used here so values from each record produced by this entity can replace parameters
	 * in the URL. Use {@link HtmlLinkFollower#assigning} to replace the URL parameters.
	 *
	 * @param fieldName the name of the field associated with {@link HtmlLinkFollower}
	 * @param urlReaderProvider the url that the {@link HtmlLinkFollower} will follow
	 *
	 * @return this {@link HtmlLinkFollower} to allow for method chaining
	 */
	public HtmlLinkFollower followLink(String fieldName, UrlReaderProvider urlReaderProvider) {
		HtmlLinkFollower follower = ((FieldContentTransform) addField(fieldName)).followLink(urlReaderProvider);
		return follower;
	}

	@Override
	protected final HtmlEntitySettings getParentEntitySettings() {
		return (HtmlEntitySettings) parentEntity;
	}

	@Override
	protected final CommonParserSettings getInternalSettings() {
		return super.getInternalSettings();
	}

	@Override
	protected HtmlEntitySettings clone() {
		HtmlEntitySettings out = (HtmlEntitySettings) super.clone();
		out.fields = new LinkedHashMap<String, Object>();
		return out;
	}
}
