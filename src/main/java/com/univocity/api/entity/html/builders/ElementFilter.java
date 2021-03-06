/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * An `ElementFilter` establishes rules to select only those HTML elements that fit a certain criteria, while
 * also allowing the matching of elements under or above the current element matched by the parser. The filtering
 * options provided by this `ElementFilter` are made available to the user once {@link ElementFilterStart#match(String)}
 * is called to target a specific HTML tag.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface ElementFilter<T extends ElementFilter<T>> extends BasicElementFilter<T>, UpDown<T> {

}
