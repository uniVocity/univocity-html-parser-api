package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;

/**
 * Created by anthony on 20/07/16.
 */
public class HtmlLinkFollower {
	final HtmlEntity entity;
	static private String entityName = "*itemFollower*";
	private int itemCount;
	private boolean joinRows;
	private int linkNum;


	public HtmlLinkFollower() {
		entity = new HtmlEntity(entityName);
		itemCount = 0;
		joinRows = false;
		linkNum = 1;
	}

	static public String getEntityName() {
		return  entityName;
	}

	public HtmlPathStart addLink() {
		return entity.addField("linkNo"+linkNum++);
	}

	public HtmlGroupStart newGroup() {
		return entity.newGroup();
	}

	public HtmlPathStart addField(String fieldName) {
		return entity.addField(fieldName);
	}

	public String[] getFieldNames() {
		return entity.getFieldNames();
	}

	public void setFollowedLinkCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getFollowedLinkCount() {
		return itemCount;
	}

	public void setJoinRows(boolean joinRows) {
		this.joinRows = joinRows;
	}

	public boolean getJoinRowsOption() {
		return joinRows;
	}


}
