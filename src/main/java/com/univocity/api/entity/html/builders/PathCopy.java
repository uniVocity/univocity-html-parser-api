/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * Allows the copying of a {@link PartialPath} or {@link PaginationPath}.
 *
 * @param <T> the type of path being copied.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
interface PathCopy<T> {

	/**
	 * Copies the current path allowing new matching to be added to a common path without changing the original
	 * one. Useful for when the path to multiple elements share a common structure. An example can be shown with
	 * this HTML:
	 *
	 *
	 * ```html
	 *
	 * <div>
	 *   <article>
	 *     <h1>first</h1>
	 *     <p>lorem</p>
	 *   </article>
	 *   <article>
	 *     <h1>second</h1>
	 *     <p>ispum</p>
	 *   </article>
	 * </div>
	 * <div>
	 *   <article>
	 *     <h1>first</h1>
	 *     <p>article</p>
	 *   </article>
	 * </div>
	 *
	 * ```
	 *
	 *
	 * A technique to get the text of the `p` element of the first and second `article`s of the first `div` is:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 *  //creates a new path with a common matching sequence.
	 * PartialPath path = entity.newPath()
	 *     .match("article").containing("h1");
	 *
	 * //the copied path ends with 'h1', we further specify it should start with text 'first'.
	 * path.copyPath().withText("first")
	 *     .addField("one").match("p").getText();
	 *
	 * //the original path is unchanged. We can copy it again and match 'h1' elements now starting with text 'second'
	 * path.copyPath().withText("second")
	 *     .addField("two").match("p").getText();
	 * ```
	 *
	 * In this example, the first path that is created is used as a basis. This path is then copied to specify
	 * the the text to be matched from a `h1` element. Fields can be added to paths and in the example above each
	 * copy matches any `p` element starting from the path. This will make the parser capture the values:
	 *
	 * ```
	 * [lorem, ispum]
	 * ```
	 *
	 * @return a {@link PartialPath} that allows the specification of a path and does not affect the path that it
	 * is built upon.
	 */
	T copyPath();
}
