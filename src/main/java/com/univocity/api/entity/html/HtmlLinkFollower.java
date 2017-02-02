package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;
import com.univocity.parsers.remote.*;

/**
 * A class that allows the addition of fields which are used by the {@link HtmlParser} to parse and return information
 * from a linked page.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 */
public class HtmlLinkFollower extends RemoteLinkFollower<HtmlEntitySettings, HtmlEntityList, HtmlParserSettings> implements FieldDefinition {

	protected HtmlLinkFollower(HtmlEntitySettings parentEntitySettings) {
		super(parentEntitySettings);
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
	public void addConstantField(String constantFieldName, String constantValue) {
		entitySettings.addConstantField(constantFieldName, constantValue);
	}

	public PathStart addField(String fieldName) {
		return entitySettings.addField(fieldName);
	}
}
