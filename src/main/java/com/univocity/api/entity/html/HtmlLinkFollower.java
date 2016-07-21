package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;

/**
 * Created by anthony on 20/07/16.
 */
public class HtmlLinkFollower extends RemoteResourceLinkFollower<HtmlEntity> {

	public HtmlLinkFollower() {
		super();
	}

	@Override
	protected HtmlEntity newEntity() {
		return new HtmlEntity(entityName);
	}

	public HtmlGroupStart newGroup() {
		return entity.newGroup();
	}

	public HtmlPathStart  addLink() {
		return entity.addField("linkNo"+linkNum++);
	}

	public HtmlPathStart addField(String fieldName) {
		return entity.addField(fieldName);
	}


}
