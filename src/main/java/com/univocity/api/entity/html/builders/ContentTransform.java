/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;
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

	HtmlLinkFollower followLink(String name);
}
