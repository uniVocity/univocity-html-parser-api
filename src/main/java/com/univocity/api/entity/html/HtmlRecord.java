/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.parsers.common.*;

/**
 * A {@link HtmlRecord} is a record that can be linked with one more more {@link HtmlRecord}s.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see ResultRecordMetaData
 */
public interface HtmlRecord extends ResultRecord<HtmlParsingContext> {

	HtmlParserResult getLinkedFieldData();

	Results<HtmlParserResult> getLinkedEntityData();

}
