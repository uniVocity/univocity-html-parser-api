/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.common.*;
import com.univocity.api.entity.html.builders.annotations.*;
import com.univocity.api.net.*;

/**
 * Allows the content captured for a given field, by a {@link ContentReader}, to be transformed by a
 * {@link StringTransformation} to clean up or transform values or to obtain very specific textual content from the
 * original value.
 *
 * Also provides methods for downloading content from the URL captured by the previous path.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
interface ContentHandler<T extends ContentHandler<T>> {

	/**
	 * Specifies that the parser will download content from the URL in the HTML element defined by the
	 * path. This is useful for downloading binary files such as images and videos linked with 'src' or 'href' attributes.
	 *
	 * The content will processed by a {@link HttpResponseReader}, provided by the user.
	 *
	 * @param contentReader a user-provided callback to process the remote content.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	void download(HttpResponseReader contentReader);

	/**
	 * Specifies that the parser will download content from the URL in the HTML element defined by the
	 * path. This is useful for downloading binary files such as images and videos linked with 'src' or 'href' attributes.
	 *
	 * The content will processed by a {@link HttpResponseReader}, provided by the user.
	 *
	 * @param baseUrlProvider the base URL and associated configuration to be used for downloading the content.
	 *                        Required for downloading content wile parsing data from local files.
	 * @param contentReader   a user-provided callback to process the remote content.
	 */
	@Matcher(type = Matcher.Type.ATTRIBUTE)
	void download(UrlReaderProvider baseUrlProvider, HttpResponseReader contentReader);

	/**
	 * Assigns a {@link StringTransformation} to the current field. Once the parser collects a value for the field,
	 * it will invoke the {@link StringTransformation#transform(Object)} to modify it. The result of the transformation
	 * will be assigned to the field
	 *
	 * @param transformation the transformation to be applied over the content parsed for a given field.
	 *
	 * @return options to download content if the text represents a path to a remote resource.
	 */
	T transform(StringTransformation transformation);

}
