package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;

/**
 * Created by anthony on 20/07/16.
 */
public class HtmlLinkFollower {
	HtmlEntity entity;
	static private String entityName = "*itemFollower*";

	public HtmlLinkFollower() {
		entity = new HtmlEntity(entityName);
	}

	static public String getEntityName() {
		return  entityName;
	}

	public HtmlPathStart addLink() {
		return entity.addField("linkFollower");
	}

	public HtmlGroupStart newGroup() {
		return entity.newGroup();
	}

	public HtmlPathStart newField(String fieldName) {
		return entity.addField(fieldName);
	}

	public String[] getFieldNames() {
		return entity.getFieldNames();
	}


}
