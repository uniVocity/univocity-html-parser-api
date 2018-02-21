/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * Provides the start of a {@link FieldPath}. Essentially, the `PathStart` defines which HTML element
 * should be matched when the {@link com.univocity.api.entity.html.HtmlParser} is run. When the parser processes an
 * input HTML, it will run all filtering rules applied over the elements whose tag names match with the one provided
 * by the {@link #match(String)} method. Values from a matched element are extracted using the rules defined by
 * a {@link ContentReader}
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see FieldPath
 * @see ElementFilterStart
 * @see ElementFilter
 */
public interface PathStart extends ElementFilterStart<FieldPath> {
}
