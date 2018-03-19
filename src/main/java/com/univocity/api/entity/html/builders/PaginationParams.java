/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.parsers.remote.*;

/**
 * Methods to enable the specification of internal fields of the {@link com.univocity.api.entity.html.HtmlPaginator}.
 */
public interface PaginationParams {

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the next page element. The next page is a HTML element that changes the current page to the next page in series.
	 * When the parser runs and completes the parsing of the page, it will 'click' on the next page element
	 * and process the result. The parser will continue to access the next page until a next page element does not
	 * exist or the follow count set by {@link Paginator#setFollowCount(int)}
	 * is reached.
	 *
	 * An example of setting the next page can be demonstrated using this HTML:
	 *
	 * ```html
	 * <html>
	 *   <body>
	 *     <article>
	 *       <h1>Water: The Truth</h1>
	 *       <p>It's good for you!</p>
	 *       <a href="paginationTarget.html">Next Page</a>
	 *     </article>
	 *   </body>
	 * </html>
	 * ```
	 *
	 * Assume that the `paginationTarget.html` linked above contains the following HTML:
	 *
	 * ```html
	 * <html>
	 *   <body>
	 *     <article>
	 *       <h1>Bananas</h1>
	 *       <p>An excellent source of potassium</p>
	 *     </article>
	 *   </body>
	 * </html>
	 * ```
	 *
	 * You can get the text in the `h1` and `p` elements from both pages with:
	 *
	 * ```
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("entity");
	 *
	 * entity.addField("header").match("h1").containedBy("article").getText();
	 * entity.addField("text").match("p").containedBy("article").getText();
	 *
	 * entities.configurePaginator()
	 *     .setNextPage()
	 *         .match("a")
	 *             .containedBy("article")
	 *         .getAttribute("href");
	 * ```
	 *
	 * When the parser runs, it will parse the first page, collecting the first row of data:
	 *
	 * ```
	 * [Water: The Truth, It's good for you!]
	 * ```
	 *
	 * The paginator will then run, accessing the link's URL provided by the `href` attribute and opening the next page.
	 * This next page will then be parsed to collect the next row:
	 *
	 * ```
	 * [Bananas, An excellent source of potassium]
	 * ```
	 *
	 * As there is no `a` element on the second page with a link to the next, the paginator will be unable to run and
	 * the parsing will finish, returning the two records parsed from each page.
	 *
	 * @return a {@link PathStart} used to define the path to the element
	 */
	PathStart setNextPage();

	/**
	 * Creates a new field for the next page number and returns a {@link PathStart} which can be used to define the path
	 * to the next page number element. The next page number indicates that there are more pages after the current page.
	 * When the parser runs and completes the parsing of the page, it will read the next page number to decide whether
	 * to proceed a try to obtain the next page to parse. The parser will continue to access the next page until:
	 *
	 *  * the next page number does not exist, or
	 *  * the follow count set by {@link Paginator#setFollowCount(int)} is reached.
	 *
	 * This page number can also be used for naming files to be saved after fetching the content from the remote host,
	 * if a filename pattern is defined using {@link RemoteParserSettings#setFileNamePattern(String)}
	 *
	 * @return a {@link PathStart} used to define the path to the element that provides the next page number
	 *
	 * @see RemoteParserSettings#setFileNamePattern(String)
	 */
	PathStart setNextPageNumber();

	/**
	 * Creates a new field for the current page and returns a {@link PathStart} which can be used to define the path
	 * to the 'current page' element. The current page is a HTML element that indicates which page among a series of
	 * pages is being currently processed.
	 *
	 * @return a {@link PathStart} used to define the path to the element that provides the current page URL
	 */
	PathStart setCurrentPage();

	/**
	 * Creates a new field for the current page and returns a {@link PathStart} which can be used to define the path
	 * to the 'current page' element as a number. The current page is a HTML element that indicates which page among
	 * a series of pages is being currently parsed.
	 *
	 * @return a {@link PathStart} used to define the path to the element that provides the current page number.
	 */
	PathStart setCurrentPageNumber();

	/**
	 * Creates a request parameter with the given name and returns a {@link PathStart} that is used to collect the
	 * value for this parameter from the current page. Parameter values are submitted in POST requests to load
	 * the next page.
	 *
	 * @param parameterName the name that will be associated with the parameter, whose value will be sent in the request
	 *                      for the next page.
	 *
	 * @return a {@link PathStart} that is used to define the path to the element that contains a request parameter
	 * value, which will be sent in the request for the next page.
	 */
	PathStart addRequestParameter(String parameterName);
}
