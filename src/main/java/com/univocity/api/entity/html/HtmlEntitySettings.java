/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.entity.html.builders.*;
import com.univocity.api.net.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.remote.*;

import java.util.*;

/**
 * A {@code HtmlEntitySettings} object manages the configuration of a HTML entity. An entity has a name and one or more
 * fields. These fields have paths to the elements that will have their data collected. In addition, a {@link HtmlParserListener}
 * can be associated with an entity to notify the user of actions made by the {@link HtmlParser}.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlEntityList
 * @see HtmlParser
 * @see HtmlParserListener
 * @see HtmlParsingContext
 */
public class HtmlEntitySettings extends RemoteEntitySettings<HtmlParsingContext, CommonParserSettings, HtmlParserSettings, HtmlLinkFollower> implements FieldDefinition {

	Map<String, Object> fields = new LinkedHashMap<String, Object>();
	final List<RecordTrigger> triggers = new ArrayList<RecordTrigger>();
	private HtmlParserListener listener = null;

	/**
	 * Creates a new HTML entity configuration and associates it with the supplied name.
	 *
	 * @param entityName a string that identifies the HTMLEntity
	 */
	HtmlEntitySettings(String entityName, HtmlEntitySettings parentEntity) {
		super(entityName, createEmptyParserSettings(), parentEntity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final PathStart addSilentField(String fieldName) {
		return newField(fieldName, false, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final PathStart addField(String fieldName) {
		return newField(fieldName, false, false);
	}

	/**
	 * {@inheritDoc}
	 */
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
	 * <hr>{@code
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * HtmlEntitySettings items = entityList.configureEntity("items");
	 * PartialPath path = items.newPath().match("table").id("productsTable").match("td").match("div").classes("productContainer");
	 * path.addField("name").match("span").classes("prodName", "prodNameTro").getText();
	 * path.addField("URL").match("a").childOf("div").classes("productPadding").getAttribute("href")
	 * }<hr>
	 *
	 * @return a {@link PartialPathStart} to specify the path of HTML elements
	 */
	public final PartialPathStart newPath() {
		return Builder.build(PartialPathStart.class, this);
	}

	/**
	 * Returns a {@link GroupStart} that allows for a {@link Group} to be defined. A {@link Group} demarcates a section
	 * of the HTML input that is allowed to be parsed. {@link FieldPath}s created from a group will only be executed inside
	 * this defined area, ignoring any HTML that exists outside of it. For example, say you wanted to parse
	 * the "hello" and "howdy" in the following HTML:
	 *
	 * <hr>{@code
	 * <div class="parseMe">
	 * <p>hello</p>
	 * </div>
	 * <p>howdy</p>
	 * <h1>No Parsing Area</h1>
	 * <p>don't parse me!</p>
	 * }<hr>
	 *
	 * <p> The parsing rules, using groups can be defined as: </p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * HtmlParserSettings settings = new HtmlParserSettings(entityList);
	 *
	 * Group group = entityList.configureEntity("test").newGroup().startAt("div").classes("parseMe").endAt("h1");
	 * group.addField("greeting").match("p").getText();
	 * }<hr>
	 *
	 * <p>The parser will then ignore the 'don't parse me' as the group restricts the parsing to the area defined from
	 * a div with class "parseMe" until an opening h1 tag.</p>
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
	 * For example, assume you have set up a parser that parses email address and home address fields from a HTML document
	 * of customer details. In the document, any of the customers fields may not exist (be null when parsed). An example
	 * of this HTML is shown below:
	 *
	 * <hr>{@code
	 * <table>
	 * <tr>
	 * <td>Email Address</td>
	 * <td>bla@email.com</td>
	 * </tr>
	 * <tr>
	 * <td>Home Address</td>
	 * </tr>
	 * </table>
	 * <table>
	 * <tr>
	 * <td>Email Address</td>
	 * </tr>
	 * <tr>
	 * <td>Home Address</td>
	 * <td>123 real street</td>
	 * </tr>
	 * </table>
	 * }<hr>
	 *
	 * The parsing rules are set below:
	 *
	 * <hr>{@code
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * HtmlParserSettings settings = new HtmlParserSettings(entityList);
	 *
	 * PartialPath path = entityList.configureEntity("record").newPath().match("table");
	 * path.addField("emailAddress").match("td").precededBy("td").withText("Email Address").getText();
	 * path.addField("homeAddress").match("td").precededBy("td").withText("Home Address").getText();
	 * }<hr>
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
	 * This is due to the parser finding that the first Home Address is {@code null}, assuming that the second home
	 * address is the one that was specified for the first row. To fix this error, the RecordTrigger will be set. This
	 * is done by adding the line below to the code snippet we had before:
	 * </p>
	 *
	 * <hr>{@code
	 * path.addRecordTrigger().match("td").withText("Email Address");
	 * }<hr>
	 *
	 * <p>
	 * Which, when running it through the HtmlParser we get the expected output of:
	 * </p>
	 * <p>
	 * [bla.@email.com, null]<br>
	 * [null, 123 real street]
	 * </p>
	 *
	 * <p>
	 * This is because when the parser hits the second email address entry, the {@code RecordTrigger} is activated
	 * and a new row is created. Therefore, when the parser hits the second Home Address, it adds the value to the
	 * second row instead of the first row.
	 * </p>
	 *
	 * @return a {@link RecordTriggerStart} that defines the path for the trigger
	 */
	public final RecordTriggerStart addRecordTrigger() {
		RecordTrigger out = Builder.build(RecordTrigger.class, this);
		addTrigger(out);
		return out;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<String> getFieldNames() {
		return Collections.unmodifiableSet(fields.keySet());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void removeField(String fieldName) {
		fields.remove(fieldName);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addConstantField(String constantFieldName, String constantValue) {
		fields.put(constantFieldName, constantValue);
	}


	/**
	 * Associates a {@link HtmlParserListener} with this HTML entity. The listener methods will be triggered
	 * by the {@link HtmlParser} while it traverses the HTML structure to collect values for the fields of this entity.
	 * In essence, a {@link HtmlParserListener} provides information about events that occur during the parsing process.
	 *
	 * <p><b>Important:</b>The listener methods are used in a concurrent environment. If you are using the same
	 * instance on multiple entities make sure your listener implementation is thread-safe, or limit the number
	 * of threads to be used when parsing to <b>1</b> with {@link HtmlParserSettings#setParserThreadCount(int)}</p>
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
	 * <p><b>Important:</b>The listener methods are used in a concurrent environment. If you are using the same
	 * instance on multiple entities make sure your listener implementation is thread-safe, or limit the number
	 * of threads to be used when parsing to <b>1</b> with {@link HtmlParserSettings#setParserThreadCount(int)}</p>
	 *
	 * @return the {@link HtmlParserListener} to be used when the parser executes to collect values for the fields
	 * of this entity.
	 */
	public final HtmlParserListener getListener() {
		return listener;
	}

	HtmlLinkFollower addHtmlLinkFollower(String fieldName) {
		HtmlLinkFollower htmlLinkFollower = new HtmlLinkFollower(this);
		//will break if multiple paths are assigned to same field name and a different link follower is to be used for each path.
		followers.put(fieldName, htmlLinkFollower);
		return htmlLinkFollower;
	}


	/**
	 * TODO Documentation for followLink
	 *
	 * @param fieldName
	 * @param urlReaderProvider
	 *
	 * @return
	 */
	public HtmlLinkFollower followLink(String fieldName, UrlReaderProvider urlReaderProvider) {
		HtmlLinkFollower follower = ((ContentTransform) addField(fieldName)).followLink(urlReaderProvider);
		return follower;
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
