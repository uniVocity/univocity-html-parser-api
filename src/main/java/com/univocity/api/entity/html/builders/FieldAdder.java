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
	/**
	 * The name of the column. This method returns a {@link HtmlPathStart}, which allows the specification of a HTML
	 * path that defines what HTML data will be returned when the parser runs. For example, you could define a field
	 * called "headings", match "H1" elements and get the text. When the parser runs, the headings in the HTML document will be returned
	 * and be available in the field "headings".
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * entityList.configureEntity("heading).addField("headings").match("H1").getText();
	 *</p></pre></blockquote><hr>
	 *
	 * @param fieldName The name the field will be called
	 *
	 * @return a {@link HtmlPathStart}, so that a path to the HTML element can be defined
	 */
	HtmlPathStart addField(String fieldName);

	/**
	 * A persistent field is a field that when a value is found, will insert the found value in subsequent rows, even if
	 * the element defined does not exist for these rows. An example of using persistent fields can be explained by viewing
	 * this HTML:
	 *
	 *<p><hr><blockquote><pre>
	 *{@code
	 *<div>
	 *	<article class="feature">
	 *		<h1>first</h1>
	 *		<p>lorem</p>
	 *	</article>
	 *	<article>
	 *		<h1>second</h1>
	 *		<p>ispum</p>
	 *	</article>
	 *</div>
	 * }
	 *</p></blockquote></pre><hr>
	 *
	 *<p>In this example, we want get two rows. In one row, we want [first, lorem] and in the second row we want
	 * [first, ispum]. The "first" value from both rows comes from the h1 element in the first article, while the second
	 * value comes from each article's 'p' element. A technique to do this is: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *
	 *entity.addPersistentField("persistent").match("h1").containedBy("article").classes("feature").getText();
	 *entity.addField("text").match("article").match("p").getText();
	 *</p></blockquote></pre><hr>
	 *
	 * <p>The value from that is returned from the matching rules for the persistent field will "persist" into the
	 * subsequent rows and get the output that we want. If a regular 'addField' is used instead of a persistent field,
	 * the second row will return [null, ispum] as the element is only matched once for the first row.</p>
	 *
	 * <p>NOTE: If a persistent field's path finds another match, the persistent field value will replaced by this new
	 * value. </p>
	 *
	 * @param fieldName a string that identify's the field
	 * @return a {@link HtmlPathStart}, so that a path to the HTML element can be defined
	 */
	HtmlPathStart addPersistentField(String fieldName);

	/**
	 * A silent field is a field that when a new value is found, does not trigger a new row to be generated.
	 * @param fieldName
	 * @return
	 */
	HtmlPathStart addSilentField(String fieldName);

	HtmlPathStart addSilentPersistentField(String fieldName);
}
