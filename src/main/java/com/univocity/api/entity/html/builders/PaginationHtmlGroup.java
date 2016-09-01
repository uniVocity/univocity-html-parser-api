/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface PaginationHtmlGroup extends BaseHtmlPath<PaginationHtmlGroup>, BaseHtmlPathStart<PaginationHtmlGroup> {

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the next page element. The next page is a HTML element that changes the current page to the next page in series.
	 * When the parser runs and completes the parsing of the page, the parser will 'click' on the next page element
	 * and parse that page. The parser will continue to access the next page until the next page element does not
	 * exist or the follow count set by {@link com.univocity.api.common.remote.RemoteResourcePaginator#setFollowCount(int)}
	 * is reached.
	 *
	 * <p>An example of setting the next page can be demonstrated using this HTML: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *{@code
	 *<html>
	 *<body>
	 *	<article>
	 *		<h1>Water: The Truth</h1>
	 *		<p>It's good for you!</p>
	 *		<a href="paginationTarget.html">Next Page</a>
	 *	</article>
	 *</body>
	 *</html>
	 * }
	 *</p></blockquote></pre><hr>
	 *
	 *<p>paginationTarget.html contains the following HTML: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *{@code
	 *<html>
	 *<body>
	 *	<article>
	 *		<h1>Bananas</h1>
	 *		<p>An excellent source of potassium/</p>
	 *	</article>
	 *</body>
	 *</html>
	 * }
	 *</p></blockquote></pre><hr>
	 *
	 *<p>A technique get the text of both the header and text from both pages is: </p>
	 *
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("pagination");
	 *
	 //	first column will return header text
	 *entity.addField("header").match("h1").containedBy("article").getText();
	 //	second column will return text in p
	 *entity.addField("text").match("p").containedBy("article").getText();

	 *entities.configurePaginator().setNextPage().match("a").containedBy("article").getAttribute("href");
	 *</p></blockquote></pre><hr>
	 *
	 * <p>When the parser runs, it will parse the first page, getting [Water: The Truth, It's good for you!]. The
	 * paginator will then run, accessing the link's URL provided by the href attribute and opening the next page. The
	 * parser will then run on this new page getting [Bananas, An excellent source of potassium]. As there is no
	 * link element on this page, the paginator will be unable to run and the parsing will finish, returning all the
	 * values that were parsed.</p>
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the element
	 */
	HtmlPathStart setNextPage();

	HtmlPathStart setPageSize();

	HtmlPathStart setFirstPage();

	HtmlPathStart addRequestParameter(String parameterName);
}
