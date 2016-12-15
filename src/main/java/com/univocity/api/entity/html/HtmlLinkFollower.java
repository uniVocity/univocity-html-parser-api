package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;
import com.univocity.parsers.remote.*;

import java.util.*;

/**
 * Created by anthony on 9/12/16.
 */
public class HtmlLinkFollower extends RemoteLinkFollower<HtmlEntitySettings, HtmlEntityList> {


	HtmlLinkFollower() {

	}

	@Override
	protected HtmlEntityList newEntityList() {
		return new HtmlEntityList();
	}

	public PathStart addField(String fieldName) {
		return defaultEntitySettings.addField(fieldName);
	}

	Map<String, Object> getPaths() {
		return defaultEntitySettings.fields;
	}

	@Override
	public Map<String, HtmlLinkFollower> getLinkFollowers() {
		return defaultEntitySettings.getLinkFollowers();
	}


}
