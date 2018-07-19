/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * An `ElementPath` establishes rules to select only those HTML elements that fit a certain criteria, while
 * also allowing the matching of elements under or above the current element matched by the parser. The filtering
 * options provided by this `ElementPath` are made available to the user once a method of  {@link ElementPathStart} is
 * called (such as {@link ElementPathStart#match(String)} or {@link ElementPathStart#select(String)}) to target a
 * specific HTML tag.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 *
 * @see ElementFilter
 * @see ElementPathStart
 * @see com.univocity.api.entity.html.HtmlElement
 */
public interface ElementPath extends ElementFilter<ElementPath>, ElementContentReader, ElementPathStart {


}
