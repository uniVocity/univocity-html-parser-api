/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * An interface used to facilitate the adding of fields for {@link com.univocity.api.entity.html.HtmlEntity}'s and
 * {@link HtmlGroup}s
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 *
 * @see HtmlGroup
 * @see com.univocity.api.entity.html.HtmlEntity
 */
public interface FieldAdder {

	HtmlPathStart addField(String fieldName);

	HtmlPathStart addPersistentField(String fieldName);

	HtmlPathStart addSilentField(String fieldName);

	HtmlPathStart addSilentPersistentField(String fieldName);
}
