/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.entity.html.builders.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.remote.*;

import java.util.*;

/**
 * This class defines entities that will be parsed. A entity has a name and one or more fields. These fields specify
 * what specific HTML elements will be parsed, as each field has a path to the HTML data that will be parsed.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class HtmlEntitySettings extends RemoteEntitySettings<HtmlParsingContext, CommonParserSettings, HtmlParserSettings> implements FieldDefinition {

	final Map<String, Object> fields = new LinkedHashMap<String, Object>();
	final List<RecordTrigger> triggers = new ArrayList<RecordTrigger>();
	HtmlParserListener listener = null;

	/**
	 * Creates a new HTML entity configuration and associates it with the supplied name.
	 *
	 * @param entityName a string that identifies the HTMLEntity
	 */
	HtmlEntitySettings(String entityName) {
		super(entityName, createEmptyParserSettings());
	}

	@Override
	public PathStart addSilentField(String fieldName) {
		return newField(fieldName, false, true);
	}

	@Override
	public PathStart addSilentPersistentField(String fieldName) {
		return newField(fieldName, true, true);
	}


	public PathStart addField(String fieldName) {
		return newField(fieldName, false, false);
	}

	public PathStart addPersistentField(String fieldName) {
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
		FieldPath pathBuilder = Builder.build(FieldPath.class, this, persistent, inhibitNewRows);
		addPathToField(fieldName, pathBuilder);
		return pathBuilder;
	}

	/**
	 * Used by {@link HtmlEntity#newField(String, boolean, boolean)} to add a path to the field. It tries to get the
	 * field from the fields map. If it doesn't exist, it puts a new field into the map and associated it with the
	 * given path.
	 *
	 * @param fieldName the name identifies the field
	 * @param path      the path that will be associated with the field
	 */
	void addPathToField(String fieldName, FieldPath path) {
		List<FieldPath> paths = (List<FieldPath>) fields.get(fieldName);
		if (paths == null) {
			paths = new ArrayList<FieldPath>();
			fields.put(fieldName, paths);
		}
		paths.add(path);
	}


	/**
	 * Used by {@link HtmlEntity#addRecordTrigger()}. When a record trigger is defined, it gets added to the
	 * RecordTrigger list
	 *
	 * @param trigger the RecordTrigger that will be added to the list
	 */
	void addTrigger(RecordTrigger trigger) {
		this.triggers.add(trigger);
	}

	/**
	 * Returns a {@link PartialPathStart} that is used to define a path. Fields then can added to this path using
	 * {@link PartialPath#addField(String)}. Creating ParitalPaths are useful for when there are multiple fields
	 * that exist in the same area of the HTML Document, as the path can be reused. For example:
	 *
	 * <p><hr><blockquote><pre>
	 * 	HtmlEntityList entityList = new HtmlEntityList();
	 * 	HtmlEntitySettings items = entityList.configureEntity("items");
	 * 	PartialPath path = items.newPath().match("table").id("productsTable").match("td").match("div").classes("productContainer");
	 * 	path.addField("name").match("span").classes("prodName", "prodNameTro").getText();
	 * 	path.addField("URL").match("a").childOf("div").classes("productPadding").getAttribute("href")
	 * 	</p></pre></blockquote><hr>
	 *
	 * @return a {@link PartialPathStart} to specify a path
	 */
	public PartialPathStart newPath() {
		return Builder.build(PartialPathStart.class, this);
	}

	/**
	 * Returns a {@link GroupStart} that allows for a group to be defined. A group is a section of the HTML input
	 * that is allowed to be parsed. Paths created from a group will only parse inside this defined area, ignoring
	 * any HTML that exists outside of it. For example, say you wanted to parse the "hello" and "howdy" in the following
	 * HTML:
	 *
	 * <p><hr><blockquote><pre>
	 * <div class="parseMe">
	 * 	<p>hello</p>
	 * </div>
	 * <p>howdy</p>
	 * <h1>No Parsing Area</h1>
	 * <p>don't parse me!</p>
	 * </p></pre></blockquote><hr>
	 *
	 * <p> </p>The parsing rules, using groups can be defined as: </p>
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * HtmlParserSettings settings = new HtmlParserSettings(entityList);
	 *
	 * Group group = entityList.configureEntity("test").newGroup().startAt("div").classes("parseMe").endAt("h1");
	 * group.addField("greeting").match("p").getText();
	 * </p></pre></blockquote><hr>
	 *
	 * <p>The parser will then ignore the 'don't parse me' as the group restricts the parsing to the area defined by from
	 * the class opening tag to the h1 tag.</p>
	 *
	 * @return a {@link GroupStart} used to specify the group
	 */
	public GroupStart newGroup() {
		return Builder.build(GroupStart.class, this);
	}


	/**
	 * Creates a new group for the {@link HtmlPaginator}. A group is a section of the HTML input
	 * that is allowed to be parsed. Paths created from a group will only parse inside this defined area, ignoring
	 * any HTML that exists outside of it.
	 *
	 * @return a {@link PaginationGroupStart} used to specify the group
	 */
	PaginationGroupStart newPaginationGroup() {
		return Builder.build(PaginationGroupStart.class, this);
	}

	/**
	 * Returns a {@link RecordTriggerStart} that is used to specify a path that defines when rows should be created.
	 *
	 * For example, assume you have set up a parser that parses email address and home address fields from a HTML document
	 * of customer details. In the document, any of the customers fields may not exist (be null when parsed). An example
	 * of this HTML is shown below:
	 *
	 * <p><hr><blockquote><pre>
	 * <table>
	 * 	<tr>
	 * 		<td>Email Address</td>
	 * 		<td>bla@email.com</td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td>Home Address</td>
	 * 	</tr>
	 * </table>
	 * <table>
	 * 	<tr>
	 * 		<td>Email Address</td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td>Home Address</td>
	 * 		<td>123 real street</td>
	 * 	</tr>
	 * </table>
	 * </p></pre></blockquote><hr>
	 *
	 * The parsing rules are set below:
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * HtmlParserSettings settings = new HtmlParserSettings(entityList);
	 *
	 * PartialPath path = entityList.configureEntity("record").newPath().match("table");
	 * path.addField("emailAddress").match("td").precededBy("td").withText("Email Address").getText();
	 * path.addField("homeAddress").match("td").precededBy("td").withText("Home Address").getText();
	 * </p></pre></blockquote><hr>
	 *
	 * <p>After running it through the HtmlParser, we get this output: </p>
	 *
	 * <p>
	 * Expected output:
	 * <br>[bla.@email.com, null]
	 * <br>[null, 123 real street]
	 * <br>
	 * Actual output:
	 * <br>[bla.@email.com, 123 real street]
	 * </p>
	 *
	 * <p>
	 * This is due to the parser finding that the first Home Address is null, assuming that the second home address is
	 * the one that was specified for the first row. To fix this error, the RecordTrigger will be set. this is done by
	 * adding the line below to the code snippet we had before:
	 * </p>
	 *
	 * <p><hr><blockquote><pre>
	 * path.addRecordTrigger().match("td").withText("Email Address");
	 * </p></pre></blockquote><hr>
	 *
	 * <p>
	 * Which, when running it throught the HtmlParser we get the expected output of:
	 * </p>
	 * <p>
	 * [bla.@email.com, null]<br>
	 * [null, 123 real street]
	 * </p>
	 *
	 * <p>
	 * This is because when the parser hits the second email address entry, the RecordTrigger is activated and a new
	 * row is created. Therefore, when the parser hits the second Home Address, it adds the value to the second row
	 * instead of the first row.
	 * </p>
	 *
	 * @return a {@link RecordTriggerStart} that defines the path for the trigger
	 */
	public RecordTriggerStart addRecordTrigger() {
		RecordTrigger out = Builder.build(RecordTrigger.class, this);
		addTrigger(out);
		return out;
	}

	public Set<String> getFieldNames() {
		return Collections.unmodifiableSet(fields.keySet());
	}

	public void removeField(String fieldName) {
		fields.remove(fieldName);
	}

	@Override
	public void addConstantField(String constantFieldName, String constantValue) {
		fields.put(constantFieldName, constantValue);
	}

	/**
	 * Associates a {@link HtmlParserListener} with this {@code HtmlEntity}. The listener methods will be triggered
	 * by the {@link HtmlParser} while it traverses the HTML structure to collects values for the fields of this entity
	 * In essence, a {@link HtmlParserListener} provides information about events that occur during the parsing process.
	 *
	 * <p><b>Important:</b>The listener methods are used in a concurrent environment. If you are using the same
	 * instance on multiple entities make sure your listener implementation is thread-safe, or limit the number
	 * of threads to be used when parsing to <b>1</b> with {@link HtmlParserSettings#setThreadCount(int)}</p>
	 *
	 * @param listener the HtmlParserListener that this entity will be associated with
	 */
	public void setListener(HtmlParserListener listener) {
		this.listener = listener;
	}

	/**
	 * Returns the {@link HtmlParserListener} associated with this {@code HtmlEntity}. The listener methods will be triggered
	 * by the {@link HtmlParser} while it traverses the HTML structure to collects values for the fields of this entity
	 * In essence, a {@link HtmlParserListener} provides information about events that occur during the parsing process.
	 *
	 * <p><b>Important:</b>The listener methods are used in a concurrent environment. If you are using the same
	 * instance on multiple entities make sure your listener implementation is thread-safe, or limit the number
	 * of threads to be used when parsing to <b>1</b> with {@link HtmlParserSettings#setThreadCount(int)}</p>
	 *
	 * @return the HtmlParserListener associated with this entity.
	 */
	public HtmlParserListener getListener() {
		return listener;
	}

	public CommonParserSettings getInternalSettings() {
		return super.getInternalSettings();
	}
}
