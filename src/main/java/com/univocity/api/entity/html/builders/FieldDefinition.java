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
	 * Associates a regular field with an entity. Regulars field are used by the parser to retain values for a row. When
	 * all values of a row are collected, the parser submits the row to the output, and clears all values collected
	 * for all fields. If the parser collects a value for a field that already contains data, the record will be submitted
	 * to the output and the incoming value will be associated with the given field in a new row.
	 *
	 * For example, you could define a field called "headings", match "H1" elements to get their text. When the parser
	 * runs, the headings in the HTML document will be returned and be available in the field "headings".
	 * <hr>{@code
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * entityList.configureEntity("heading).addField("headings").match("H1").getText();
	 * }<hr>
	 *
	 * @param fieldName name of the field to be created. If called more than once, a new {@link PathStart} will be
	 *                  returned, allowing multiple paths to be used to collect data into the same field.
	 *
	 * @return a {@link PathStart}, so that a path to the target HTML content to be captured can be defined
	 */
	PathStart addField(String fieldName);

	/**
	 * Associates a persistent field with an entity. A persistent field is a field that retains its value until it is
	 * overwritten by the parser. When all values of a row are collected, the parser submits the row to the output,
	 * and clears the values collected for all fields, except the persistent ones, so they will be reused in subsequent records.
	 *
	 * An example of using persistent fields can be explained by viewing this HTML:
	 *
	 * <hr>{@code
	 * {@code
	 * <div id="55">
	 * <article>
	 * <h1>first</h1>
	 * <p>lorem</p>
	 * </article>
	 * <article>
	 * <h1>second</h1>
	 * <p>ipsum</p>
	 * </article>
	 * </div>
	 * }
	 * }<hr>
	 *
	 * <p>In this example, we want get two records: [55, first, lorem] and [55, second, ipsum]. The "55" value in both
	 * records should come from the id value of the div. The following rules can be defined to produce this output:</p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * entity.addPersistentField("persistentID").match("div").getAttribute("id");
	 * entity.addField("title").match("h1").getText();
	 * entity.addField("text").match("p").getText();
	 * }<hr>
	 *
	 * <p>As the "persistentID" field was created as a the persistent field, it will retain its value and the parser
	 * will reapply it into subsequent rows. If a regular {@link #addField(String)} were used instead,
	 * the output would be [55, first, lorem] and [null, second, ipsum] as the div and its ID is only matched once.</p>
	 *
	 * <p>NOTE: The persistent field is also silent. If a persistent field's path finds another match, the persistent
	 * field value will be replaced by this new value, and no new records will be generated. A {@link RecordTrigger} can
	 * be used to force new records to be generated.</p>
	 *
	 * @param fieldName name of the persistent field to be created. If called more than once, a new {@link PathStart}
	 *                  will be returned, allowing multiple paths to be used to collect data into the same field.
	 *
	 * @return a {@link PathStart}, so that a path to the target HTML content to be captured can be defined
	 */
	PathStart addPersistentField(String fieldName);

	/**
	 * Associates a silent field with an entity. If the parser collects a value for a field that already contains data,
	 * and the field is silent, it won't submit a new record. The parser will simply replace the previously collected value
	 * with the newly parsed value. An example of this can be shown with this HTML document:
	 *
	 * <hr>{@code
	 * {@code <div>
	 * <article class="feature">
	 * <h1>first</h1>
	 * <p>lorem</p>
	 * <h1>second</h1>
	 * </article>
	 * </div> }
	 * }<hr>
	 *
	 * <p>A technique to get the text of the <strong>second</strong> header and the text of the p element is: </p>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * entity.addSilentField("silent").match("h1").containedBy("article").getText();
	 * entity.addField("text").match("article").match("p").getText();
	 * }<hr>
	 *
	 * <p>The parser will return [second, lorem]. When the parser finishes parsing the p element, the row is actually
	 * [first, lorem]. As soon as the parser parses the second h1 element, instead of creating a new row with this value,
	 * it will replace the 'first' {@code String} with 'second' generating the row [second, lorem].
	 * If 'addField' was used in this example instead of 'addSilentField', two rows would be produced:
	 * [first, lorem] and [second, null]</p>
	 *
	 * @param fieldName name of the silent field to be created. If called more than once, a new {@link PathStart}
	 *                  will be returned, allowing multiple paths to be used to collect data into the same field.
	 *
	 * @return a {@link PathStart}, so that a path to the target HTML content to be captured can be defined
	 */
	PathStart addSilentField(String fieldName);


	/**
	 * Creates a field that always returns the specified value. An example to use this method can
	 * be shown with this HTML document:
	 *
	 * <hr>{@code
	 * <div>
	 * <article>
	 * <h1>first</h1>
	 * <p>lorem</p>
	 * </article>
	 * <article>
	 * <h1>second</h1>
	 * <p>ipsum</p>
	 * </article>
	 * <article>
	 * <h1>third</h1>
	 * <p>lol</p>
	 * </article>
	 * </div>
	 * }<hr>
	 *
	 * <hr>{@code
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * // creates constant field
	 * entity.addConstantField("constant","cool article");
	 *
	 * entity.addField("title").match("h1").getText();
	 * entity.addField("content").match("p").getText();
	 * }<hr>
	 *
	 * <p>When the parser runs, it will get the text from each article heading and 'p' element. It will also attach
	 * "cool article" to the first column of each row. For instance, the first article row will look like: ["cool article",
	 * "first", "lorem"].</p>
	 *
	 * @param constantFieldName name of the constant field to be created
	 * @param constantValue     the value that will always be returned from the given field
	 */
	void addConstantField(String constantFieldName, String constantValue);
}
