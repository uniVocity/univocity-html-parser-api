/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;

/**
 * A `PartialPath` can be created from an entity using {@link HtmlEntitySettings#newPath()} or from an entity's group,
 * using {@link Group#copyPath()}. It allows the specification of a reusable path, with common matching rules.
 *
 * Fields can be added to the `PartialPath`, and the user needs to provide only the additional rules required to
 * match elements from the starting point defined by the `PartialPath`, and how to collect the data from the matched
 * elements, using the methods available from a {@link ContentReader}.
 *
 * @see HtmlEntitySettings
 * @see PartialPathStart
 * @see FieldDefinition
 * @see PathCopy
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface PartialPath extends ElementFilter<PartialPath>, PartialPathStart, FieldDefinition, PathCopy<PartialPath>, Trigger {


}
