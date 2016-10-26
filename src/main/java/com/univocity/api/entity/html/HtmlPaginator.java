/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.common.*;
import com.univocity.api.entity.html.builders.*;
import com.univocity.api.exception.*;
import com.univocity.api.net.*;
import com.univocity.parsers.remote.*;

/**
 * Used by the {@link HtmlParser} to collect multiple pages of results in a website and to handle
 * the files that have been downloaded for each page.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 * @see PaginationContext
 * @see PaginationHandler
 */
public final class HtmlPaginator extends Paginator<HtmlEntitySettings> {

	private int idealPageSize;

	/**
	 * Creates a new HtmlPaginator and sets the currentPageNumber to 0
	 */
	public HtmlPaginator() {
	}

	/**
	 * Creates a new {@link HtmlEntitySettings} which will be used to create fields specifically for this
	 * {@code HtmlPaginator}.
	 *
	 * @return the created {@link HtmlEntitySettings} to be used by this {@code HtmlPaginator}.
	 */
	@Override
	protected final HtmlEntitySettings newEntitySettings() {
		return new HtmlEntitySettings(ENTITY_NAME);
	}

	/**
	 * Returns the {@link HtmlEntitySettings} that is associated with this {@code HtmlPaginator}.
	 *
	 * @return returns the paginator {@link HtmlEntitySettings}
	 */
	final HtmlEntitySettings getSettings() {
		return entitySettings;
	}

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the next page element. The next page is the HTML element that changes the current page to the next page in series.
	 * When the parser runs and completes the parsing of the page, it will 'click' on the next page element
	 * and parse that page. The parser will continue to access the next page until the next page element does not
	 * exist or the follow count set by {@link Paginator#setFollowCount(int)} is reached.
	 *
	 * <p>An example of setting the next page can be demonstrated using this HTML: </p>
	 *
	 * <hr>{@code
	 * {@code
	 * <html>
	 * <body>
	 * <article>
	 * <h1>Water: The Truth</h1>
	 * <p>It's good for you!</p>
	 * <a href="paginationTarget.html">Next Page</a>
	 * </article>
	 * </body>
	 * </html>
	 * }
	 * }<hr>
	 *
	 * <p>paginationTarget.html contains the following HTML: </p>
	 *
	 * <hr>{@code
	 * {@code
	 * <html>
	 * <body>
	 * <article>
	 * <h1>Bananas</h1>
	 * <p>An excellent source of potassium/</p>
	 * </article>
	 * </body>
	 * </html>
	 * }
	 * }<hr>
	 *
	 * <p>A technique get the text of both the header and text from both pages is: </p>
	 *
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("pagination");
	 *
	 * //	first column will return header text
	 * entity.addField("header").match("h1").containedBy("article").getText();
	 * //	second column will return text in p
	 * entity.addField("text").match("p").containedBy("article").getText();
	 *
	 * entities.configurePaginator().setNextPage().match("a").containedBy("article").getAttribute("href");
	 * }<hr>
	 *
	 * <p>When the parser runs, it will parse the first page, getting [Water: The Truth, It's good for you!]. The
	 * paginator will then run, accessing the link's URL provided by the href attribute and opening the next page. The
	 * parser will then run on this new page getting [Bananas, An excellent source of potassium]. As there is no
	 * link element on this page, the paginator will be unable to run and the parsing will finish, returning all the
	 * values that were parsed.</p>
	 *
	 * @return a {@link PathStart} is used to define the path to the next page element
	 */
	public final PathStart setNextPage() {
		return entitySettings.addField(NEXT_PAGE);
	}

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the 'previous page' element. The previous page is a HTML element that changes the current page to the previous
	 * page in series.
	 *
	 * @return a {@link PathStart} is used to define the path to the previous page element
	 */
	public final PathStart setPreviousPage() {
		return entitySettings.addField(PREVIOUS_PAGE);
	}

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the 'first page' element. The first page is a HTML element that changes the current page to the first page
	 * in series.
	 *
	 * @return a {@link PathStart} is used to define the path to the first page element
	 */
	public final PathStart setFirstPage() {
		return entitySettings.addField(FIRST_PAGE);
	}

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the 'last page' element. The last page is a HTML element that changes the current page to the last page
	 * in series.
	 *
	 * @return a {@link PathStart} is used to define the path to the last page element
	 */
	public final PathStart setLastPage() {
		return entitySettings.addField(LAST_PAGE);
	}

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the 'page size' element. The page size is a HTML element that indicates how many records can be listed
	 * in the current page.
	 *
	 * @return a {@link PathStart} is used to define the path to the element
	 */
	public final PathStart setPageSize() {
		return entitySettings.addField(PAGE_SIZE);
	}

	/**
	 * Creates a new field for the item count and returns a {@link PathStart} which can be used to define the path
	 * to the 'item count' element. The item count is a HTML element that indicates how many records in total have
	 * been returned and are available. It's the sum all records available in all pages.
	 *
	 * @return a {@link PathStart} is used to define the path to the item count element
	 */
	public final PathStart setItemCount() {
		return entitySettings.addField(ITEM_COUNT);
	}


	/**
	 * Creates a new request parameter and returns a {@link PathStart} that allows the user to define path to the
	 * parameter. Request parameters are values set on a page, usually internally in hidden fields of a form element,
	 * and which contain details about the page state. These are usually used to be sent back to the server in a POST
	 * {@link HttpRequest}, to validate the current session and return a next page of results.
	 *
	 * If you assign the path to the request parameters you are interested in, the paginator will automatically
	 * set them for you when requesting for the next page of results. Otherwise, you'd have to manually set the parameters
	 * using a {@link PaginationHandler}.
	 *
	 * @param paramName name of the request parameter
	 *
	 * @return a {@link PathStart} is used to define the path of the value for the given parameter
	 */
	public final PathStart addRequestParameter(String paramName) {
		Args.notBlank(paramName, "Request parameter name");
		return entitySettings.addField(paramName);
	}


	/**
	 * Assigns a value to a request parameter name that the paginator must always set when requesting the next page.
	 *
	 * @param paramName name of the request parameter
	 * @param value     value of the request parameter
	 */
	public final void setRequestParameter(String paramName, String value) {
		entitySettings.addConstantField(paramName, value);
	}

	/**
	 * Creates a new {@link PaginationGroup} group for this paginator.
	 *
	 * @return a {@link PaginationGroupStart} which is the first step in determining which element demarcates the start
	 * of a {@link PaginationGroup}.
	 */
	public final PaginationGroupStart newGroup() {
		return entitySettings.newPaginationGroup();
	}

	/**
	 * Defines the ideal page size the paginator should try to set the page size to when attempting to access paginated
	 * content. You might want to provide a page size explicitly if the resource you are trying to access it too large
	 * and your requests are failing/timing out.
	 *
	 * You must configure this {@code HtmlPaginator} use a "pageSize" field before using this method.
	 *
	 * @param pageSize the ideal page size
	 */
	public final void setIdealPageSize(int pageSize) {
		if (!getFieldNames().contains(PAGE_SIZE)) {
			throw new IllegalConfigurationException("Paginator does not have a 'pageSize' field defined.");
		}
		this.idealPageSize = pageSize;
	}

	/**
	 * Returns the configured ideal page size the paginator should try to set the page size to when attempting to access paginated
	 * content. You might want to provide a page size explicitly if the resource you are trying to access it too large
	 * and your requests are failing/timing out.
	 *
	 * You must configure this {@code HtmlPaginator} to use a "pageSize" field before using this method.
	 *
	 * @return the ideal page size
	 */
	public final int getIdealPageSize() {
		if (!getFieldNames().contains(PAGE_SIZE)) {
			throw new IllegalConfigurationException("Paginator does not have a 'pageSize' field defined.");
		}
		return idealPageSize;
	}
}
