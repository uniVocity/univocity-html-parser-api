/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * An interface used to facilitate the adding of fields for {@link com.univocity.api.entity.html.HtmlEntity}'s and
 * {@link Group}s
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 *
 * @see Group
 * @see com.univocity.api.entity.html.HtmlEntity
 */
public interface FieldDefinition {
	/**
	 * The name of the column. This method returns a {@link PathStart}, which allows the specification of a HTML
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
	 * @return a {@link PathStart}, so that a path to the HTML element can be defined
	 */
	PathStart addField(String fieldName);

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
	 *HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 *entity.addPersistentField("persistent").match("h1").containedBy("article").classes("feature").getText();
	 *entity.addField("text").match("article").match("p").getText();
	 *</p></blockquote></pre><hr>
	 *
	 * <p>The value from that is returned from the matching rules for the persistent field will "persist" into the
	 * subsequent rows and get the output that we want. If a regular 'addField' is used instead of a persistent field,
	 * the second row will return [null, ispum] as the element is only matched once for the first row.</p>
	 *
	 *<p><hr><blockquote><pre>
	 *Output:
	 * [first, lorem]
	 * [first, ispum]
	 *</p></blockquote></pre><hr>
	 *
	 * <p>NOTE: If a persistent field's path finds another match, the persistent field value will replaced by this new
	 * value. </p>
	 *
	 * @param fieldName a string that identify's the field
	 * @return a {@link PathStart}, so that a path to the HTML element can be defined
	 */
	PathStart addPersistentField(String fieldName);

	/**
	 * A silent field is a field that when a new value is found, does not trigger a new row to be generated. If a value
	 * has been previously parsed for the field, the parser will replace it with the newly parsed value. An example
	 * of this can be shown with this HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *{@code <div>
	 *	<article class="feature">
	 *		<h1>first</h1>
	 *		<p>lorem</p>
	 *		<h1>second</h1>
	 *	</article>
	 *</div> }
	 *</p></blockquote></pre><hr>
	 *
	 *<p>A technique to get the text of the <strong>second</strong> header and the text of the p element is: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 *entity.addSilentField("silent").match("h1").containedBy("article").getText();
	 entity.addField("text").match("article").match("p").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>The parser will return [second, lorem]. When the parser finishes parsing the p element, the row is actually
	 * [first, lorem]. As soon as the parser parses the second h1 element, instead of creating a new row with this value,
	 * it will replace the 'first' to generate the row [second, lorem]. If 'addField' was used in this example instead of
	 * 'addSilentField', two rows would be produced [first, lorem] and [second, null]</p>
	 *
	 * @param fieldName a string that identifies the field
	 * @return a {@link PathStart}, so that a path to the HTML element can be defined
	 */
	PathStart addSilentField(String fieldName);

	/**
	 * A silent persistent field is a field that  will not cause new rows to generated when a new value is found and the
	 * value will be inserted in subsequent rows. In short, a field that combines the function of both a silent and
	 * persistent field. An example of using this can be shown with this HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<article>
	 *	<p>lorem</p>
	 *	<pre>first</pre>
	 *	<p>ispum</p>
	 *
	 *	<p>dolor sit</p>
	 *	<pre>second</pre>
	 *
	 *	<p>amet</p>
	 *	<pre>third</pre>
	 *</article>
	 *</p></blockquote></pre><hr>
	 *
	 * <p>In this document, each {@code <pre>} element is associated with the {@code <p>} element immediately before it.
	 * The first {@code <pre>} element is also associated with the {@code <p>} element directly after it. To parse the
	 * document so each row returned has each {@code pre} element and it's associated {@code <p>} elements:</p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 *entity.addSilentPersistentField("silent").match("pre").containedBy("article").getText();
	 *entity.addField("text").match("article").match("p").getText();
	 *</p></blockquote></pre><hr>
	 *
	 *<p>When the parser runs it will match first p and pre values and put them in a row. It will then match the
	 * p element containing ispum and start a new row. When it reaches the p element with "dolor sit" in it, it will
	 * trigger the start of a new row, causing the first pre value (as it is persistent) to be put in the same row as
	 * the "ispum" p value row (["first","ispum"]. etc.</p>
	 *
	 *<p><hr><blockquote><pre>
	 * Output:
	 * 	[first, lorem]
	 *	[first, ispum]
	 *	[second, dolor sit]
	 *	[third, amet]
	 *</p></blockquote></pre><hr>
	 *
	 * @param fieldName a string that identifies the field
	 * @return a {@link PathStart}, so that a path to the HTML element can be defined
	 */
	PathStart addSilentPersistentField(String fieldName);

	/**
	 * Creates a field that always returns the specified value. An example to use this method can
	 * be shown with this HTML document:
	 *
	 *<p><hr><blockquote><pre>
	 *<div>
	 *	<article>
	 *		<h1>first</h1>
	 *		<p>lorem</p>
	 *	</article>
	 *	<article>
	 *		<h1>second</h1>
	 *		<p>ispum</p>
	 *	</article>
	 *	<article>
	 *		<h1>third</h1>
	 *		<p>lol</p>
	 *	</article>
	 *</div>
	 *</p></pre></blockquote><hr>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 *	// creates constant field
	 *entity.addConstantField("constant","cool article");
	 *
	 *entity.addField("title").match("h1").getText();
	 *entity.addField("content").match("p").getText();
	 *</p></pre></blockquote><hr>
	 *
	 *<p>When the parser runs, it will get the text from each article heading and 'p' element. It will also attach
	 * "cool article" to the first column of each row. For instance, the first article row will look like: ["cool article",
	 * "first", "lorem"].</p>
	 *
	 * @param constantFieldName the name of that will be associated with the field
	 * @param constantValue the value that will always be returned in the field
	 */
	void addConstantField(String constantFieldName, String constantValue);
}
