/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * An {@code ElementPath} establishes rules to select only those HTML elements that fit a certain criteria, while
 * also allowing the matching of elements under or above the current element matched by the parser. The filtering
 * options provided by this {@code ElementPath} are made available to the user once {@link ElementPathStart#match(String)}
 * is called to target a specific HTML tag, or {@link ElementPathStart#select(String)} is used to filter HTML tags using
 * a CSS selection rule.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 *
 * @see ElementFilter
 * @see ElementPathStart
 * @see com.univocity.api.entity.html.HtmlElement
 */
public interface ElementPath extends ElementFilter<ElementPath>, ElementContentReader, ElementPathStart {


}
