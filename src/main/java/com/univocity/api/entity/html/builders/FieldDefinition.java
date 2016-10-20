/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * Provides the options available for adding fields into a HTML entity, which are defined with the help of
 * {@link com.univocity.api.entity.html.HtmlEntitySettings}, a {@link Group} or a {@link PartialPath} associated with
 * the given entity.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see Group
 * @see PartialPath
 * @see PathStart
 * @see com.univocity.api.entity.html.HtmlEntitySettings
 */
public interface FieldDefinition {
	/**
	 * The name of the field. This method returns a {@link PathStart}, which allows the specification of a HTML
	 * path that defines paths of HTML elements pointing to data that should be be returned when the parser runs.
	 * For example, you could define a field called "headings", match "H1" elements to get their text. When the parser
	 * runs, the headings in the HTML document will be returned and be available in the field "headings".
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * entityList.configureEntity("heading).addField("headings").match("H1").getText();
	 * </p></pre></blockquote><hr>
	 *
	 * @param fieldName name of the field to be created. If called more than once, a new {@link PathStart} will be
	 *                  returned, allowing multiple paths to be used to collect data into the same field.
	 *
	 * @return a {@link PathStart}, so that a path to the target HTML content to be captured can be defined
	 */
	PathStart addField(String fieldName);

	/**
	 * A persistent field is a field that retains its value until it is overwritten by the parser. When all values for
	 * a row are collected, the parser submits the row to the output, and clears the values collected for all fields, except
	 * the persistent ones, so they will be reused in subsequent records.
	 *
	 * An example of using persistent fields can be explained by viewing this HTML:
	 *
	 * <p><hr><blockquote><pre>
	 * {@code
	 * <div id="55">
	 * 	<article>
	 * 		<h1>first</h1>
	 * 		<p>lorem</p>
	 * 	</article>
	 * 	<article>
	 * 		<h1>second</h1>
	 * 		<p>ipsum</p>
	 * 	</article>
	 * </div>
	 * }
	 * </p></blockquote></pre><hr>
	 *
	 * <p>In this example, we want get two records: [55, first, lorem] and [55, second, ipsum]. The "55" value in both
	 * records should come from the id value of the div. The following rules can be defined to produce this output:</p>
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * entity.addPersistentField("persistentID").match("div").getAttribute("id");
	 * entity.addField("title").match("h1").getText();
	 * entity.addField("text").match("p").getText();
	 * </p></blockquote></pre><hr>
	 *
	 * <p>As the "persistentID" field was created as a the persistent field, it will retain its value and the parser
	 * will reapply it into subsequent rows. If a regular {@link #addField(String)} were used instead,
	 * the output would be [55, first, lorem] and [null, second, ipsum] as the div and its ID is only matched once.</p>
	 *
	 * <p>NOTE: If a persistent field's path finds another match, the persistent field value will replaced by this new
	 * value. </p>
	 *
	 * @param fieldName name of the persistent field to be created. If called more than once, a new {@link PathStart}
	 *                  will be returned, allowing multiple paths to be used to collect data into the same field.
	 *
	 * @return a {@link PathStart}, so that a path to the target HTML content to be captured can be defined
	 */
	PathStart addPersistentField(String fieldName);

	/**
	 * A silent field is a field that when a new value is found, does not trigger a new row to be generated. If a value
	 * has been previously parsed for the field, the parser will replace it with the newly parsed value. An example
	 * of this can be shown with this HTML document:
	 *
	 * <p><hr><blockquote><pre>
	 * {@code <div>
	 * 	<article class="feature">
	 * 		<h1>first</h1>
	 * 		<p>lorem</p>
	 * 		<h1>second</h1>
	 * 	</article>
	 * </div> }
	 * </p></blockquote></pre><hr>
	 *
	 * <p>A technique to get the text of the <strong>second</strong> header and the text of the p element is: </p>
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * entity.addSilentField("silent").match("h1").containedBy("article").getText();
	 * entity.addField("text").match("article").match("p").getText();
	 * </p></blockquote></pre><hr>
	 *
	 * <p>The parser will return [second, lorem]. When the parser finishes parsing the p element, the row is actually
	 * [first, lorem]. As soon as the parser parses the second h1 element, instead of creating a new row with this value,
	 * it will replace the 'first' to generate the row [second, lorem]. If 'addField' was used in this example instead of
	 * 'addSilentField', two rows would be produced [first, lorem] and [second, null]</p>
	 *
	 * @param fieldName a string that identifies the field
	 *
	 * @return a {@link PathStart}, so that a path to the HTML element can be defined
	 */
	PathStart addSilentField(String fieldName);

	/**
	 * A silent persistent field is a field that  will not cause new rows to generated when a new value is found and the
	 * value will be inserted in subsequent rows. In short, a field that combines the function of both a silent and
	 * persistent field. An example of using this can be shown with this HTML document:
	 *
	 * <p><hr><blockquote><pre>
	 * <article>
	 * 	<p>lorem</p>
	 * 	<pre>first</pre>
	 * 	<p>ispum</p>
	 *
	 * 	<p>dolor sit</p>
	 * 	<pre>second</pre>
	 *
	 * 	<p>amet</p>
	 * 	<pre>third</pre>
	 * </article>
	 * </p></blockquote></pre><hr>
	 *
	 * <p>In this document, each {@code <pre>} element is associated with the {@code <p>} element immediately before it.
	 * The first {@code <pre>} element is also associated with the {@code <p>} element directly after it. To parse the
	 * document so each row returned has each {@code pre} element and it's associated {@code <p>} elements:</p>
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * entity.addSilentPersistentField("silent").match("pre").containedBy("article").getText();
	 * entity.addField("text").match("article").match("p").getText();
	 * </p></blockquote></pre><hr>
	 *
	 * <p>When the parser runs it will match first p and pre values and put them in a row. It will then match the
	 * p element containing ispum and start a new row. When it reaches the p element with "dolor sit" in it, it will
	 * trigger the start of a new row, causing the first pre value (as it is persistent) to be put in the same row as
	 * the "ispum" p value row (["first","ispum"]. etc.</p>
	 *
	 * <p><hr><blockquote><pre>
	 * Output:
	 * 	[first, lorem]
	 * 	[first, ispum]
	 * 	[second, dolor sit]
	 * 	[third, amet]
	 * </p></blockquote></pre><hr>
	 *
	 * @param fieldName a string that identifies the field
	 *
	 * @return a {@link PathStart}, so that a path to the HTML element can be defined
	 */
	PathStart addSilentPersistentField(String fieldName);

	/**
	 * Creates a field that always returns the specified value. An example to use this method can
	 * be shown with this HTML document:
	 *
	 * <p><hr><blockquote><pre>
	 * <div>
	 * 	<article>
	 * 		<h1>first</h1>
	 * 		<p>lorem</p>
	 * 	</article>
	 * 	<article>
	 * 		<h1>second</h1>
	 * 		<p>ispum</p>
	 * 	</article>
	 * 	<article>
	 * 		<h1>third</h1>
	 * 		<p>lol</p>
	 * 	</article>
	 * </div>
	 * </p></pre></blockquote><hr>
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * 	// creates constant field
	 * entity.addConstantField("constant","cool article");
	 *
	 * entity.addField("title").match("h1").getText();
	 * entity.addField("content").match("p").getText();
	 * </p></pre></blockquote><hr>
	 *
	 * <p>When the parser runs, it will get the text from each article heading and 'p' element. It will also attach
	 * "cool article" to the first column of each row. For instance, the first article row will look like: ["cool article",
	 * "first", "lorem"].</p>
	 *
	 * @param constantFieldName the name of that will be associated with the field
	 * @param constantValue     the value that will always be returned in the field
	 */
	void addConstantField(String constantFieldName, String constantValue);
}
