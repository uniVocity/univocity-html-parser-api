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

	private Set<String> requestParameters;
	final Map<String, String> fieldParameters = new HashMap<String, String>();
	final Map<String, Object> constantParameters = new HashMap<String, Object>();

	/**
	 * Creates a new HtmlPaginator and sets the currentPageNumber to 0
	 */
	protected HtmlPaginator(HtmlParserSettings parserSettings) {
		super(parserSettings);
	}

	/**
	 * Creates a new {@link HtmlEntitySettings} which will be used to create fields specifically for this
	 * {@code HtmlPaginator}.
	 *
	 * @return the created {@link HtmlEntitySettings} to be used by this {@code HtmlPaginator}.
	 */
	@Override
	protected final HtmlEntitySettings newEntitySettings(final RemoteParserSettings htmlParserSettings) {
		return new HtmlEntitySettings(ENTITY_NAME) {
			{
				HtmlPaginator.this.requestParameters = this.requestParameters;
				this.parserSettings = (HtmlParserSettings) htmlParserSettings;
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
	 * Creates a new field for the current page and returns a {@link PathStart} which can be used to define the path
	 * to the 'current page' element as a number. The current page is a HTML element that indicates which page among
	 * a series of pages is being currently parsed.
	 *
	 * @return a {@link PathStart} used to define the path to the current page number
	 */
	public final PathStart setCurrentPageNumber() {
		return entitySettings.addField(CURRENT_PAGE_NUMBER);
	}

	/**
	 * Creates a new field for the next page number and returns a {@link PathStart} which can be used to define the path
	 * to the next page number element. The next page number indicates that there are more pages after the current page.
	 * When the parser runs and completes the parsing of the page, it will read the next page number to decide whether
	 * to proceed a try to obtain the next page to parse. The parser will continue to access the next page until the
	 * next page number does not exist or the follow count set by {@link Paginator#setFollowCount(int)} is reached.
	 *
	 * @return a {@link PathStart} used to define the path to the next page number
	 */
	public final PathStart setNextPageNumber() {
		return entitySettings.addField(NEXT_PAGE_NUMBER);
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

	/**
	 * Assigns values captured for two fields declared in this {@code HtmlPaginator} to a request parameter. For
	 * example, if fields "scriptName" and "scriptValue" have been defined in this paginator, and and their values are
	 * collected as "thescript" and "myscript.js" respectively, the {@link HttpRequest} used to invoke the next page
	 * will have its {@link HttpRequest#addDataParameter(String, Object)} invoked with "thescript" as the parameter name
	 * and "myscript.js" as the parameter value.
	 *
	 * @param fieldForParamName  name of the field available in this paginator whose value will be used as a the request
	 *                           parameter name.
	 * @param fieldForParamValue name of the field available in this paginator whose value will be used as the request
	 *                           parameter value.
	 */
	public final void toRequestParameter(String fieldForParamName, String fieldForParamValue) {
		Args.notBlank(fieldForParamName, "Field to use as parameter name");
		Args.notBlank(fieldForParamValue, "Field to use as parameter value");
		this.fieldParameters.put(fieldForParamName, fieldForParamValue);
	}

	/**
	 * Assigns the value captured for a fields declared in this {@code HtmlPaginator} to a request parameter name, and
	 * associates a constant value to it. If field "scriptValue" has been defined in this paginator, and and its value
	 * is collected as "myscript.js", the {@link HttpRequest} used to invoke the next page
	 * will have its {@link HttpRequest#addDataParameter(String, Object)} invoked with "myscript.js" as the parameter
	 * name, and a constant as the value.
	 *
	 * @param fieldForParamName name of the field available in this paginator whose value will be used as a the request
	 *                          parameter name.
	 * @param constantValue     the constant to used as the request parameter value.
	 */
	public final void toConstantRequestParameter(String fieldForParamName, Object constantValue) {
		Args.notBlank(fieldForParamName, "Field to use as parameter name");
		Args.notNull(constantValue, "Constant to be associated with " + fieldForParamName);
		this.constantParameters.put(fieldForParamName, constantValue);
	}
}
