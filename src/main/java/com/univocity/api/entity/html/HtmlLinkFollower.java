package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.common.*;
import com.univocity.api.entity.html.builders.*;
import com.univocity.parsers.remote.*;

/**
 * A class that allows the addition of fields which are used by the {@link HtmlParser} to parse and return information
 * from a linked page.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 */
public class HtmlLinkFollower extends RemoteFollower<HtmlEntitySettings, HtmlEntityList, HtmlParserSettings> implements FieldDefinition {

	/**
	 * Creates a HtmlLinkFollower using {@code parentEntitySettings} as a basis for the settings
	 *
	 * @param parentEntitySettings the parent entity settings to be used as a basis for this instances settings
	 */
	protected HtmlLinkFollower(HtmlEntitySettings parentEntitySettings) {
		super(parentEntitySettings);
	}

	public HtmlLinkFollower getParentFollower(){
		return (HtmlLinkFollower) parentLinkFollower;
	}


	@Override
	public PathStart addPersistentField(String fieldName) {
		return entitySettings.addPersistentField(fieldName);
	}

	@Override
	public PathStart addSilentField(String fieldName) {
		return entitySettings.addSilentField(fieldName);
	}

	@Override
	public void addField(String fieldName, String constantValue) {
		entitySettings.addField(fieldName, constantValue);
	}

	public PathStart addField(String fieldName) {
		return entitySettings.addField(fieldName);
	}

	@Override
	public void addFieldFromParent(String fieldName) {
		entitySettings.addFieldFromParent(fieldName);
	}

	@Override
	public void addFieldFrom(String parentEntity, String fieldName) {
		entitySettings.addFieldFrom(parentEntity, fieldName);
	}

	/**
	 * @return this {@link HtmlLinkFollower} to allow for method chaining
	 */
	@Override
	public HtmlLinkFollower assigning(String parameterName, Object parameterValue) {
		return ((HtmlLinkFollower) super.assigning(parameterName, parameterValue));
	}

	/**
	 * @return this {@link HtmlLinkFollower} to allow for method chaining
	 */
	@Override
	public HtmlLinkFollower assigning(String parameterName, ValueGetter<?> valueGetter) {
		return ((HtmlLinkFollower) super.assigning(parameterName, valueGetter));
	}

	/**
	 * Returns the {@link HtmlPaginator} associated with the {@code HtmlParserSettings} of this {@code HtmlEntityList}
	 *
	 * @return the {@link HtmlPaginator} stored the {@code HtmlParserSettings} of this {@code HtmlEntityList}
	 */
	public final HtmlPaginator getPaginator() {
		return  getParserSettings().getPaginator();
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
		return entitySettings.newPath();
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
		return entitySettings.newGroup();
	}
}
