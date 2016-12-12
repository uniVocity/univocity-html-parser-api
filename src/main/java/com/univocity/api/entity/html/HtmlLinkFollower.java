package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;
import com.univocity.parsers.remote.*;

import java.util.*;

/**
 * Created by anthony on 9/12/16.
 */
public class HtmlLinkFollower extends RemoteLinkFollower<HtmlEntitySettings> {

	static final String LINK_PATH = "**LinkPath**";

	HtmlLinkFollower() {

	}

	@Override
	protected HtmlEntitySettings newEntitySettings() {
		return new HtmlEntitySettings(ENTITY_NAME);
	}

	public PathStart addField(String fieldName) {
		return entitySettings.addField(fieldName);
	}


	public PathStart followLink() {
		return entitySettings.followLink();
	}

	Map<String, Object> getPaths() {
		return entitySettings.fields;
	}

	@Override
	public List<HtmlLinkFollower> getLinkFollowers() {
		return entitySettings.getLinkFollowers();
	}


}
