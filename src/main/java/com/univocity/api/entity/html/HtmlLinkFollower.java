package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;
import com.univocity.parsers.remote.*;

/**
 * FIXME no javadoc?
 * Created by anthony on 9/12/16.
 */
public class HtmlLinkFollower extends RemoteLinkFollower<HtmlEntitySettings, HtmlEntityList, HtmlParserSettings> implements FieldDefinition {

	public HtmlLinkFollower(HtmlEntitySettings parentEntitySettings) {
		super(parentEntitySettings);
	}

	@Override
	public PathStart addPersistentField(String fieldName) {
		return parentEntitySettings.addPersistentField(fieldName);
	}

	@Override
	public PathStart addSilentField(String fieldName) {
		return parentEntitySettings.addSilentField(fieldName);
	}

	@Override
	public void addConstantField(String constantFieldName, String constantValue) {
		parentEntitySettings.addConstantField(constantFieldName, constantValue);
	}

	public PathStart addField(String fieldName) {
		return parentEntitySettings.addField(fieldName);
	}
}
