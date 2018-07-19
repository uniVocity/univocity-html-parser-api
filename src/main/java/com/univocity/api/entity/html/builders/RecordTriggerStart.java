/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;

/**
 * Used in {@link HtmlEntitySettings#addRecordTrigger()} to create a path that defines when a new record should be created.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 *
 * @see Trigger
 */
public interface RecordTriggerStart extends ElementFilterStart<RecordTrigger> {
}
