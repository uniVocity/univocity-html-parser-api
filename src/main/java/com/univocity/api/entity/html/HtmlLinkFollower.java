package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;
import com.univocity.parsers.remote.*;

import java.util.*;

/**
 * FIXME no javadoc?
 * Created by anthony on 9/12/16.
 */
public class HtmlLinkFollower extends RemoteLinkFollower<HtmlEntitySettings, HtmlEntityList, HtmlParserSettings> {


	HtmlLinkFollower(/* have entity name here and call "super(entity_name)"*/) {

	}

	@Override
	protected HtmlEntityList newEntityList() {
		//should clone parent's configuration (especially globalSettings)
		return new HtmlEntityList();
	}

	@Override
	protected HtmlParserSettings newParserSettings() {
		//should clone current parser settings.
		return new HtmlParserSettings(entityList);
	}

	//no javadoc?
	public PathStart addField(String fieldName) {
		return defaultEntitySettings.addField(fieldName);
	}

	//no javadoc?
	Map<String, Object> getPaths() {
		return defaultEntitySettings.fields;
	}

	@Override
	public Map<String, HtmlLinkFollower> getLinkFollowers() {
		return defaultEntitySettings.getLinkFollowers();
	}


}
