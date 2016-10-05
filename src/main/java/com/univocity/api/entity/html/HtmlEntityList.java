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

	/**
	 * Creates a new, empty HtmlEntityList
	 */
	public HtmlEntityList() {
	}

	/**
	 * Creates a new {@link HtmlEntity} with the supplied name and returns it. Used by {@link RemoteResourceEntityList#configureEntity(String)}.
	 *
	 * @param entityName the name that will be used to identify the entity
	 * @return the HtmlEntity that was created
	 */
	@Override
	protected HtmlEntity newEntity(String entityName) {
		return new HtmlEntity(entityName);
	}

	/**
	 * Creates a new {@link HtmlPaginator} and returns it. Used by {@link RemoteResourceEntityList#configurePaginator()}.
	 *
	 * @return the {@link HtmlPaginator} that was created
	 */
	@Override
	protected HtmlPaginator newPaginator() {
		return new HtmlPaginator();
	}

	/**
	 * Returns the {@link HtmlPaginator} associated with the HtmlEntityList
	 *
	 * @return the {@link HtmlPaginator} stored within the HtmlEntityList
	 */
	public HtmlPaginator getPaginator() {
		return (HtmlPaginator) paginator;
	}

	/**
	 * Creates a new {@link HtmlLinkFollower} and returns it. Used by {@link RemoteResourceEntityList#configureEntity(String)}
	 *
	 * @return the created {@link HtmlLinkFollower}
	 */
	@Override
	protected  HtmlLinkFollower newLinkFollower() {
		return new HtmlLinkFollower();
	}

	/**
	 * Returns the {@link HtmlLinkFollower} associated with the HtmlEntityList.
	 *
	 * @return the {@link HtmlLinkFollower} contained by the HtmlEntityList
	 */
	public HtmlLinkFollower getLinkFollower() {
		return (HtmlLinkFollower) linkFollower;
	}

	/**
	 * Returns the associated {@link HtmlPaginator}, creating it if it does not exist already. {@link HtmlPaginator}s are
	 * used to define how the parser loads the next page to parse.
	 *
	 *
	 * @return the {@link HtmlPaginator} associated with the HtmlEntityList
	 */
	public HtmlPaginator configurePaginator() {
		if (paginator == null) {
			paginator = newPaginator();
		}
		return (HtmlPaginator) paginator;
	}

	/**
	 * Returns the associated {@link HtmlLinkFollower}, creating it if it does not exist already. {@link HtmlLinkFollower}s
	 * are used to define links that the parser will follow and parse. A use case for this is when parsing a list of
	 * products on a store page.
	 *
	 * @return the {@link HtmlLinkFollower} associated with the HtmlEntityList
	 */
	public HtmlLinkFollower configureLinkFollower() {
		if (linkFollower == null) {
			linkFollower = newLinkFollower();
		}
		return (HtmlLinkFollower) linkFollower;
	}
}
