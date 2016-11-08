/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;
import com.univocity.api.entity.html.builders.annotations.*;
import com.univocity.api.net.*;

import java.io.*;

/**
 * Operations for downloading content from absolute or relative URLs extracted into values of a field.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface ContentDownload {

	/**
	 * Specifies that the parser will download content from the URL in the HTML element defined by the
	 * path. This is useful for downloading binary files such as images and videos linked with 'src' or 'href' attributes.
	 *
	 * <p>Content will be downloaded to the directory specified by
	 * {@link HtmlParserSettings#setDownloadContentDirectory(File)}. If the download directory is not set, the content
	 * will be stored in a temporary directory.</p>
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	void download();

	/**
	 * Specifies that the parser will download content from the URL in the HTML element defined by the
	 * path. This is useful for downloading binary files such as images and videos linked with 'src' or 'href' attributes.
	 *
	 * <p>Content will be downloaded to the directory specified by
	 * {@link HtmlParserSettings#setDownloadContentDirectory(File)}. If the download directory is not set, the content
	 * will be stored in a temporary directory.</p>
	 *
	 * @param baseUrlProvider the base URL and associated configuration to be used for downloading the content.
	 *                        Required for downloading content wile parsing data from local files.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	void download(UrlReaderProvider baseUrlProvider);

	/**
	 * Specifies that the parser will download content from the URL in the HTML element defined by the
	 * path. This is useful for downloading binary files such as images and videos linked with 'src' or 'href' attributes.
	 *
	 * <p>The content will processed by a {@link HttpResponseReader}, provided by the user.</p>
	 *
	 * @param contentReader a user-provided callback to process the remote content.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	void download(HttpResponseReader contentReader);

	/**
	 * Specifies that the parser will download content from the URL in the HTML element defined by the
	 * path. This is useful for downloading binary files such as images and videos linked with 'src' or 'href' attributes.
	 *
	 * <p>The content will processed by a {@link HttpResponseReader}, provided by the user.</p>
	 *
	 * @param baseUrlProvider the base URL and associated configuration to be used for downloading the content.
	 *                        Required for downloading content wile parsing data from local files.
	 * @param contentReader a user-provided callback to process the remote content.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	void download(UrlReaderProvider baseUrlProvider, HttpResponseReader contentReader);

}
