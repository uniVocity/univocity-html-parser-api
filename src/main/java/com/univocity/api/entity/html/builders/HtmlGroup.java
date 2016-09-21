/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface HtmlGroup extends BaseHtmlPath<HtmlGroup>, BaseHtmlPathStart<HtmlGroup>, FieldAdder, CopyAndAddTrigger {

	/**
	 * Creates a field that <strong>always</strong> returns the specified value. An example to use this method can
	 * be shown with this HTML document:
	 *
	 * <p><hr><blockquote><pre>
	 *<div>
	 *	<article class="feature">
	 *		<h1>first</h1>
	 *		<p>lorem</p>
	 *	</article>
	 *	<article>
	 *		<h1>second</h1>
	 *		<p>ispum</p>
	 *	</article>
	 *	<article class="last">
	 *		<h1>third</h1>
	 *		<p>lol</p>
	 *	</article>
	 *</div>
	 *</p></pre></blockquote><hr>
	 *
	 *<p>A technique to get the header of the first two articles, as well as a constant "header" text for each row: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *
	 *	// creates group that covers first two article elements
	 *HtmlGroup group = entity.newGroup().startAt("article").classes("feature").endAt("article").classes("last");
	 *
	 *	// creates field to get header text
	 *group.addField("header").match("h1").getText();
	 *
	 *	// creates constant field
	 *group.addConstantField("constant","cool article");
	 *</p></pre></blockquote><hr>
	 *
	 *<p>This will return [first, cool article] in the first row, and [second, cool article] in the second row.</p>
	 *
	 * @param constantFieldName the name of that will be associated with the field
	 * @param constantValue the value that will always be returned in the field
	 */
	void addConstantField(String constantFieldName, String constantValue);

}