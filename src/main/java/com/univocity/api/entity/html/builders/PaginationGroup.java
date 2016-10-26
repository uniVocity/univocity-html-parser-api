/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.parsers.remote.*;

/**
 * A special purpose {@link Group}-like structure, used only for {@link com.univocity.api.entity.html.HtmlPaginator}s.
 * Creates an area in the HTML where the paginator will look for elements to help in pagination. Elements outside
 * the area will be ignored.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface PaginationGroup extends ElementFilter<PaginationGroup>, ElementFilterStart<PaginationGroup> {
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
	 * @return a {@link PathStart} used to define the path to the element
	 */
	PathStart setNextPage();

	/**
	 * Creates a new field and returns a {@link PathStart} that is used to define a path to the page size element.
	 * The page size element is the element on the HTML page that describes how many elements are present in a page.
	 *
	 * @return a {@link PathStart} is used to define the path to the page size
	 */
	PathStart setPageSize();

	/**
	 * Creates a new field and returns a {@link PathStart} that is used to define a path to the first page element.
	 * The first page element is the element on the HTML page that loads the first page of a series when pressed.
	 *
	 * @return a {@link PathStart} is used to define the path to the first page element
	 */
	PathStart setFirstPage();

	/**
	 * Creates a request parameter with the given name and returns a {@link PathStart} that is used to define the
	 * value of the parameter. Parameter values are submitted as a POST request to load the next page.
	 *
	 * @param parameterName the name that will be associated with the parameter, which will be sent in the request
	 *                      for the next page associated with the value collected from the path defined using the
	 *                      {@link PathStart} object returned by this method.
	 *
	 * @return a {@link PathStart} that is used to define the path to the element that contains the request parameter
	 * value, which will be sent in the request for the next page.
	 */
	PathStart addRequestParameter(String parameterName);

	/**
	 * Associates a constant value to a request parameter. Parameter values are submitted as a POST request
	 * to load the next page.
	 *
	 * @param parameterName  the name that will be associated with the parameter, which will be sent in the request
	 *                       for the next page
	 * @param parameterValue the value of the corresponding parameter
	 */
	void setRequestParameter(String parameterName, String parameterValue);
}
