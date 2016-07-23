/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.common.*;
import com.univocity.api.common.remote.*;
import com.univocity.api.entity.html.builders.*;

/**
 * Used by the {@link HtmlParser} to follow pages on a website.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 *
 * @see HtmlParser
 * @see PaginationContext
 * @see PaginationHandler
 */
public class HtmlPaginator extends RemoteResourcePaginator<HtmlEntity> {
	/**
	 * Creates a new HtmlPaginator and sets the currentPageNumber to 0
	 */
	public HtmlPaginator() {
		super();
	}

	@Override
	public HtmlEntity newEntity() {
		return new HtmlEntity(entityName);
	}

	public HtmlEntity getEntity() {
		return entity;
	}

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the next page element. The next page is a HTML element that changes the current page to the next page in series.
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the element
	 */
	public HtmlPathStart setNextPage() {
		return entity.addField("nextPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the 'previous page' element. The previous page is a HTML element that changes the current page to the previous
	 * page in series.
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the  element
	 */
	public HtmlPathStart setPreviousPage() {
		return entity.addField("previousPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the 'first page' element. The first page is a HTML element that changes the current page to the first page
	 * in series.
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the  element
	 */
	public HtmlPathStart setFirstPage() {
		return entity.addField("firstPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the 'last page' element. The last page is a HTML element that changes the current page to the last page
	 * in series.
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the  element
	 */
	public HtmlPathStart setLastPage() {
		return entity.addField("lastPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the 'last page' element. The last page is a HTML element that changes the current page to the last page
	 * in series.
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the element
	 */
	public HtmlPathStart setPageSize() {
		return entity.addField("pageSize");
	}

	//is this pagesize?
	public HtmlPathStart setItemCount() {
		return entity.addField("itemCount");
	}


	/**
	 * Creates a new request parameter and returns a {@link HtmlPathStart} that allows the user to define path to the
	 * parameter. Request parameters are values set on a page that control what content is displayed.
	 *
	 * @param fieldName the name of the request parameter
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the parameter
	 */
	public HtmlPathStart addRequestParameter(String fieldName) {
		return entity.addRequestParameter(fieldName);
	}


	/**
	 * Creates a new group for the paginator.
	 *
	 * @return a {@link PaginationHtmlGroupStart}
	 */
	public PaginationHtmlGroupStart newGroup() {
		return entity.newPaginationGroup();
	}

	/**
	 * Returns the field names used by the Paginator
	 *
	 * @return a String array of field names
	 */
	public String[] getFieldNames() {
		return entity.getFieldNames();
	}
}
