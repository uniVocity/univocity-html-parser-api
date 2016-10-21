/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * Allows the copying of a {@link PartialPath}.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
interface PathCopy {

	/**
	 * Copies the current path so that any matching rules created after the copy is made does not change the original
	 * path. Useful for when a path to an element is commonly used to create new paths. An example can be shown with
	 * this HTML:
	 *
	 *<hr>{@code
	 * {@code
	 *<div class="cool-div">
	 *     <article>
	 *         <h1>first</h1>
	 *		   <p>lorem</p>
	 *		</article>
	 *		<article>
	 *			<h1>second</h1>
	 *			<p>ispum</p>
	 *		</article>
	 *</div>
	 *<div>
	 *     <article>
	 *      	<h1>boring</h1>
	 *       	<p>article</p>
	 *     </article>
	 *</div>
	 * }
	 *}<hr>
	 *
	 *<p>A technique to get the text of the p element of the first and second articles in the first div is: </p>
	 *
	 *<hr>{@code
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * //path to the first div that will be used to base other paths on
	 *PartialPath path = entity.newPath().match("div").classes("cool-div").match("article").containing("h1");
	 *
	 * //get the text of the first article
	 *path.copyPath().withText("first").addField("one").match("p").getText();
	 *
	 * //get the text of the second article
	 *path.copyPath().withText("second").addField("second").match("p").getText();
	 *
	 *}<hr>
	 *
	 *<p>In this example, the first path that is created is used as a basis. The path is then copied twice to specify
	 * the two p elements where the text will be returned from. Running the parser will return [lorem, ispum]. If copyPath
	 * was not used, the parser would return [lorem, null] as the path created for the second article would be built
	 * upon the path generated for the first article. This path would not exist and null is the return value. </p>
	 *
	 * @return a {@link PartialPath} that allows the specification of a path and does not affect the path that it
	 * is built upon.
	 */
	PartialPath copyPath();
}
