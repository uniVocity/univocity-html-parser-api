/*
 * Copyright (c) 2018 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.parsers.common.*;

/**
 * A filter of records captured by an entity of the {@link HtmlParser}. Allows discarding records,
 * before any {@link Nesting} operation is applied or any link following occurs
 */
public interface HtmlRecordFilter extends RecordFilter<HtmlRecord, HtmlParsingContext> {
}
