/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;
import com.univocity.api.net.*;
import com.univocity.parsers.common.*;

/**
 * Allows the content captured for a given field, by a {@link ContentReader}, to be transformed by a
 * {@link StringTransformation} to clean up or transform values or to obtain very specific textual content from the
 * original value.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface ContentTransform extends ContentDownload {

	/**
	 * Assigns a {@link StringTransformation} to the current field. Once the parser collects a value for the field,
	 * it will invoke the {@link StringTransformation#transform(Object)} to modify it. The result of the transformation
	 * will be assigned to the field
	 *
	 * @param transformation the transformation to be applied over the content parsed for a given field.
	 *
	 * @return options to download content if the text represents a path to a remote resource.
	 */
	ContentDownload transform(StringTransformation transformation);

	/**
	 * Creates a {@link HtmlLinkFollower} that will parse linked pages, each linked page URL is defined by the values
	 * retrieved by this field. Each URL returned by this field will be parsed by the associated {@link HtmlLinkFollower}.
	 * A {@link HtmlLinkFollower} is essentially a special type of {@link HtmlEntityList} that allows fields and entities
	 * to be added to it.
	 * <p>
	 * At the end of parsing, the {@link HtmlParser} will combine the rows retrieved from link followers with the rows
	 * from the original page. To change this setting, use the method {@link HtmlParserSettings#setCombineLinkFollowingRows(boolean)}.
	 * </p>
	 * <p>
	 * If a value parsed in this field is not a valid URL, the parsing process will stop unless {@link HtmlLinkFollower#ignoreLinkFollowingErrors(boolean)}
	 * has been set to true.
	 * </p>
	 *
	 * @return the created {@link HtmlLinkFollower} which allows the addition of fields and entities to define what data
	 * from the linked page will be returned.
	 */
	HtmlLinkFollower followLink();

	/**
	 * Creates a {@link HtmlLinkFollower} that will parse linked pages, each linked page URL is defined by inserting
	 * the value retrieved by this field into the supplied {@link UrlReaderProvider} as a parameter.
	 * <p>
	 * For example, if a field (called "query") retrieved the values 'cat' and 'dog', and the {@link UrlReaderProvider} provided had the
	 * URL of "https://www.google.com/search?q={query}", the two pages that would be parsed would be:
	 * "https://www.google.com/search?q=cat" and "https://www.google.com/search?q=dog"
	 * </p>
	 * <p>
	 * A {@link HtmlLinkFollower} is essentially a special type of {@link HtmlEntityList} that allows fields and entities
	 * to be added to it. At the end of parsing, the {@link HtmlParser} will combine the rows retrieved from link followers with the rows
	 * from the original page. To change this setting, use the method {@link HtmlParserSettings#setCombineLinkFollowingRows(boolean)}.
	 * </p>
	 * <p>
	 * If a value parsed in this field is not a valid URL, the parsing process will stop unless {@link HtmlLinkFollower#ignoreLinkFollowingErrors(boolean)}
	 * has been set to true.
	 * </p>
	 *
	 * @param urlReaderProvider the parameterized URL that values parsed from this field will be inserted into to get
	 *                          a linked page
	 *
	 * @return the created {@link HtmlLinkFollower} which allows the addition of fields and entities to define what data
	 * from the linked page will be returned.
	 */
	HtmlLinkFollower followLink(UrlReaderProvider urlReaderProvider);
}
