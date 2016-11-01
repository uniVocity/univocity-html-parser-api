/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.common.*;
import com.univocity.api.entity.html.builders.*;
import com.univocity.api.net.*;
import com.univocity.parsers.remote.*;

import java.util.*;

/**
 * Used by the {@link HtmlParser} to collect multiple pages of results in a website and to handle
 * the files that have been downloaded for each page.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 * @see PaginationContext
 * @see PaginationHandler
 */
public final class HtmlPaginator extends Paginator<HtmlEntitySettings, HtmlPaginationContext> {

	private int idealPageSize;
	private Set<String> requestParameters;

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
		return new HtmlEntitySettings(ENTITY_NAME) {
			{
				HtmlPaginator.this.requestParameters = this.requestParameters;
			}
		};

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
	 * Creates a new field for the current page and returns a {@link PathStart} which can be used to define the path
	 * to the 'current page' element. The current page is a HTML element that indicates which page among a series of pages
	 * is being currently parsed.
	 *
	 * @return a {@link PathStart} used to define the path to the current page element
	 */
	public final PathStart setCurrentPage() {
		return entitySettings.addField(CURRENT_PAGE);
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
	 * Creates a new field on this {@code HtmlPaginator} and returns a {@link PathStart} that allows the user to define
	 * a path to the field.
	 *
	 * Any value collected for the given field can be read from a {@link PaginationHandler}.
	 *
	 * @param fieldName name of the new field
	 *
	 * @return a {@link PathStart} is used to define the path of the value for the given field
	 */
	public final PathStart addField(String fieldName) {
		return entitySettings.addField(fieldName);
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
		requestParameters.add(paramName);
		return entitySettings.addField(paramName);
	}


	/**
	 * Assigns a value to a request parameter name that the paginator must always set when requesting the next page.
	 *
	 * @param paramName name of the request parameter
	 * @param value     value of the request parameter
	 */
	public final void setRequestParameter(String paramName, String value) {
		requestParameters.add(paramName);
		entitySettings.addConstantField(paramName, value);
	}

	/**
	 * Creates a new {@link PaginationGroup} group for this paginator.
	 *
	 * @return a {@link PaginationGroupStart} which is the first step in determining which element demarcates the start
	 * of a {@link PaginationGroup}.
	 */
	public final PaginationGroupStart newGroup() {
		return entitySettings.newPaginationGroup(this);
	}

}
