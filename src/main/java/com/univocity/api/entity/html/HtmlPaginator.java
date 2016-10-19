/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;
import com.univocity.api.exception.*;
import com.univocity.parsers.remote.*;

import java.util.*;

/**
 * Used by the {@link HtmlParser} to follow pages on a website.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 * @see PaginationContext
 * @see PaginationHandler
 */
public class HtmlPaginator extends Paginator<HtmlEntitySettings> {

	public static Set<String> RESERVED_NAMES = new HashSet<String>(Arrays.asList("nextPage", "previousPage", "pageSize", "firstPage", "lastPage", "itemCount"));
	private int idealPageSize;

	/**
	 * Creates a new HtmlPaginator and sets the currentPageNumber to 0
	 */
	public HtmlPaginator() {
	}

	/**
	 * Creates a new {@link HtmlEntity} and returns it. Used by {@link Paginator} to associated the
	 * correct type of entity to a specific {@link Paginator} implementation.
	 *
	 * @return the created {@link HtmlEntity}.
	 */
	@Override
	protected HtmlEntitySettings newEntitySettings() {
		return new HtmlEntitySettings(ENTITY_NAME);
	}

	/**
	 * Returns the {@link HtmlEntity} that is associated with the Paginator.
	 *
	 * @return returns the associated {@link HtmlEntity}
	 */
	HtmlEntitySettings getSettings() {
		return entitySettings;
	}

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the next page element. The next page is a HTML element that changes the current page to the next page in series.
	 * When the parser runs and completes the parsing of the page, the parser will 'click' on the next page element
	 * and parse that page. The parser will continue to access the next page until the next page element does not
	 * exist or the follow count set by {@link Paginator#setFollowCount(int)}
	 * is reached.
	 *
	 * <p>An example of setting the next page can be demonstrated using this HTML: </p>
	 *
	 * <p><hr><blockquote><pre>
	 * {@code
	 * <html>
	 * <body>
	 * 	<article>
	 * 		<h1>Water: The Truth</h1>
	 * 		<p>It's good for you!</p>
	 * 		<a href="paginationTarget.html">Next Page</a>
	 * 	</article>
	 * </body>
	 * </html>
	 * }
	 * </p></blockquote></pre><hr>
	 *
	 * <p>paginationTarget.html contains the following HTML: </p>
	 *
	 * <p><hr><blockquote><pre>
	 * {@code
	 * <html>
	 * <body>
	 * 	<article>
	 * 		<h1>Bananas</h1>
	 * 		<p>An excellent source of potassium/</p>
	 * 	</article>
	 * </body>
	 * </html>
	 * }
	 * </p></blockquote></pre><hr>
	 *
	 * <p>A technique get the text of both the header and text from both pages is: </p>
	 *
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("pagination");
	 *
	 * //	first column will return header text
	 * entity.addField("header").match("h1").containedBy("article").getText();
	 * //	second column will return text in p
	 * entity.addField("text").match("p").containedBy("article").getText();
	 *
	 * entities.configurePaginator().setNextPage().match("a").containedBy("article").getAttribute("href");
	 * </p></blockquote></pre><hr>
	 *
	 * <p>When the parser runs, it will parse the first page, getting [Water: The Truth, It's good for you!]. The
	 * paginator will then run, accessing the link's URL provided by the href attribute and opening the next page. The
	 * parser will then run on this new page getting [Bananas, An excellent source of potassium]. As there is no
	 * link element on this page, the paginator will be unable to run and the parsing will finish, returning all the
	 * values that were parsed.</p>
	 *
	 * @return a {@link PathStart} is used to define the path to the element
	 */
	public PathStart setNextPage() {
		return entitySettings.addField("nextPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the 'previous page' element. The previous page is a HTML element that changes the current page to the previous
	 * page in series.
	 *
	 * @return a {@link PathStart} is used to define the path to the  element
	 */
	public PathStart setPreviousPage() {
		return entitySettings.addField("previousPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the 'first page' element. The first page is a HTML element that changes the current page to the first page
	 * in series.
	 *
	 * @return a {@link PathStart} is used to define the path to the  element
	 */
	public PathStart setFirstPage() {
		return entitySettings.addField("firstPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the 'last page' element. The last page is a HTML element that changes the current page to the last page
	 * in series.
	 *
	 * @return a {@link PathStart} is used to define the path to the  element
	 */
	public PathStart setLastPage() {
		return entitySettings.addField("lastPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link PathStart} which can be used to define the path
	 * to the 'last page' element. The last page is a HTML element that changes the current page to the last page
	 * in series.
	 *
	 * @return a {@link PathStart} is used to define the path to the element
	 */
	public PathStart setPageSize() {
		return entitySettings.addField("pageSize");
	}

	//is this pagesize?
	public PathStart setItemCount() {
		return entitySettings.addField("itemCount");
	}


	/**
	 * Creates a new request parameter and returns a {@link PathStart} that allows the user to define path to the
	 * parameter. Request parameters are values set on a page that control what content is displayed.
	 *
	 * @param fieldName the name of the request parameter
	 *
	 * @return a {@link PathStart} is used to define the path to the parameter
	 */
	public PathStart addRequestParameter(String fieldName) {
		//return entity.addRequestParameter(fieldName); //FIXME
		return null;
	}


	/**
	 * Creates a new group for the paginator.
	 *
	 * @return a {@link PaginationGroupStart}
	 */
	public PaginationGroupStart newGroup() {
		return entitySettings.newPaginationGroup();
	}

	/**
	 * Defines the ideal page size the paginator should try to set the page size to when attempting to access paginated
	 * content. You might want to provide a page size explicitly if the resource you are trying to access it too large
	 * and your requests are failing/timing out.
	 *
	 * You must configure the concrete {@code Paginator} implementation to use a "pageSize" field before using this method.
	 *
	 * @param pageSize the ideal page size
	 */
	public final void setIdealPageSize(int pageSize) {
		if (!getFieldNames().contains("pageSize")) {
			throw new IllegalConfigurationException("Paginator does not have a 'pageSize' field defined.");
		}
		this.idealPageSize = pageSize;
	}

	/**
	 * Returns the configured ideal page size the paginator should try to set the page size to when attempting to access paginated
	 * content. You might want to provide a page size explicitly if the resource you are trying to access it too large
	 * and your requests are failing/timing out.
	 *
	 * You must configure the concrete {@code Paginator} implementation to use a "pageSize" field before using this method.
	 *
	 * @return the ideal page size
	 */
	public final int getIdealPageSize() {
		if (!getFieldNames().contains("pageSize")) {
			throw new IllegalConfigurationException("Paginator does not have a 'pageSize' field defined.");
		}
		return idealPageSize;
	}
}
