/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;

/**
 * The starting point of an {@link ElementPath} to match elements when {@link HtmlElement#query()} is called. It allows
 * matching a sequence of elements or selecting elements using CSS rules. Elements matched can be subsequently
 * filtered using the rules available from {@link ElementFilter}, or have their data retrieved using the options provided
 * by {@link ContentReader}.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlElement
 * @see ElementPath
 * @see ElementFilterStart
 */
public interface ElementPathStart extends ElementFilterStart<ElementPath> {
}
