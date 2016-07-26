/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;


import com.univocity.api.common.remote.*;

/**
 * This class creates and stores {@link HtmlEntity}s.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlEntity
 * @see HtmlParser
 * @see HtmlParserListener
 */
public class HtmlEntityList extends RemoteResourceEntityList<HtmlEntity> {

	private HtmlParserListener listener; //listener exists in InternalHtmlParserSettings, candidate for deletion

	/**
	 * Creates a new, empty HtmlEntityList
	 */
	public HtmlEntityList() {
	}

	@Override
	protected HtmlEntity newEntity(String entityName) {
		return new HtmlEntity(entityName);
	}

	@Override
	protected HtmlPaginator newPaginator() {
		return new HtmlPaginator();
	}


	public HtmlPaginator getPaginator() {
		return (HtmlPaginator) paginator;
	}

	@Override
	protected  HtmlLinkFollower newLinkFollower() {
		return new HtmlLinkFollower();
	}

	public HtmlLinkFollower getLinkFollower() {
		return (HtmlLinkFollower) linkFollower;
	}


	public void setHtmlParserListener(HtmlParserListener listener) {
		this.listener = listener;
	}

	/**
	 * Returns the {@link HtmlParserListener} associated with the HtmlEntityList.
	 *
	 * @return the {@link HtmlParserListener}
	 */
	public HtmlParserListener getHtmlParserListener() {
		return listener;
	}

	public HtmlPaginator configurePaginator() {
		if (paginator == null) {
			paginator = newPaginator();
		}
		return (HtmlPaginator) paginator;
	}
	public HtmlLinkFollower configureLinkFollower() {
		if (linkFollower == null) {
			linkFollower = newLinkFollower();
		}
		return (HtmlLinkFollower) linkFollower;
	}
}
