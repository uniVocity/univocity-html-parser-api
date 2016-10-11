/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;
import com.univocity.parsers.remote.*;

/**
 * An subclass of {@link LinkFollower} used specifically for following links on a HTML document.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class HtmlLinkFollower extends LinkFollower<HtmlEntitySettings> {

	/**
	 * Creates a new HtmlLinkFollower
	 */
	HtmlLinkFollower() {
		super();
	}

	/**
	 * Returns the {@link HtmlEntity} associated with the HtmlLinkFollower
	 *
	 * @return the associated {@link HtmlEntity}
	 */
	public HtmlEntitySettings getEntity() {
		return entitySettings;
	}

	/**
	 * Creates a new HtmlEntity with the static entityName defined in {@link LinkFollower}.
	 *
	 * @return the created HTmlEntity
	 */
	@Override
	protected HtmlEntitySettings newEntitySettings() {
		return new HtmlEntitySettings(ENTITY_NAME);
	}

	/**
	 * Returns a {@link HtmlGroupStart} that allows for a group to be defined. A group is a section of the HTML input
	 * that is allowed to be parsed. Paths created from a group will only parse inside this defined area, ignoring
	 * any HTML that exists outside of it.
	 *
	 * @return a {@link HtmlGroupStart} used to specify the group
	 */
	public HtmlGroupStart newGroup() {
		return entitySettings.newGroup();
	}

	/**
	 * Returns a {@link HtmlPathStart} that is used to define the path of the HTML link. When the parsing process is
	 * running, the parser will follow the links added and parse the linked HTML documents. Multiple links can
	 * be added.
	 *
	 * @return a {@link HtmlPathStart} to specify path of link
	 */
	public HtmlPathStart addLink() {
		return entitySettings.addField("linkNo"+linkNum++);
	}

	/**
	 * Adds a new field specified by the given fieldName and returns a {@link HtmlPathStart} that specifies the
	 * path to the element that will be parsed.
	 *
	 * @param fieldName the name that will be used to identify the field
	 * @return a {@link HtmlPathStart} to specify path to element
	 */
	public HtmlPathStart addField(String fieldName) {
		return entitySettings.addField(fieldName);
	}


}
