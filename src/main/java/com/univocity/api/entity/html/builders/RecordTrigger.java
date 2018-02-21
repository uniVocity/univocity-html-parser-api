/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * Used in {@link com.univocity.api.entity.html.HtmlEntitySettings#addRecordTrigger()} to create a path that defines
 * when a new record should be created.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 *
 * @see Trigger
 */
public interface RecordTrigger extends ElementFilter<RecordTrigger>, RecordTriggerStart {
}
