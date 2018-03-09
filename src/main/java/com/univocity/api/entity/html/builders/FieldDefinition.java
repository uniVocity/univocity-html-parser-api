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
	 * Associates a regular field with an entity. Regular fields are used by the parser to retain values for a row. When
	 * all values of a row are collected, the parser submits the row to the output, and clears all values collected
	 * for all fields. If the parser collects a value for a field that already contains data, the record will be submitted
	 * to the output and the incoming value will be associated with the given field in a new row.
	 *
	 * For example, you could define a field called "headings" then match `h1` elements to get their text. When the parser
	 * runs, the `h1` elements found the HTML document will be returned and be available in the field "headings", e.g.:
	 *
	 * ```java
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * entityList.configureEntity("heading)
	 *     .addField("headings")
	 *         .match("h1")
	 *         .getText();
	 * ```
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
	 * and clears the values collected for all fields, except the persistent ones, so they will be reused in subsequent
	 * records.
	 *
	 * An example of using persistent fields can be explained by viewing this HTML:
	 *
	 * ```html
	 * <div id="55">
	 *   <article>
	 *     <h1>first</h1>
	 *     <p>lorem</p>
	 *   </article>
	 *   <article>
	 *     <h1>second</h1>
	 *     <p>ipsum</p>
	 *   </article>
	 * </div>
	 * ```
	 *
	 * In this example, we want get two rows with three columns: `[55, first, lorem]` and `[55, second, ipsum]`. The value
	 * "55" in both records should come from the `id` of the `div`. The following rules can be defined to produce this output:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * entity.addPersistentField("persistentID").match("div").getAttribute("id");
	 * entity.addField("title").match("h1").getText();
	 * entity.addField("text").match("p").getText();
	 * ```
	 *
	 * As the "persistentID" field was created as a persistent field, it will retain its value and the parser
	 * will reapply it into subsequent rows. If a regular {@link #addField(String)} were used instead,
	 * the output would be `[55, first, lorem]` and `[null, second, ipsum]` as the `div` and its `id` would be matched
	 * once only.
	 *
	 * **NOTE:** A persistent field is also "silent" and does not trigger new rows (see {@link #addSilentField(String)}.
	 * If a persistent field's path finds another match while processing the same record, the first value will be
	 * replaced by the new one, and no new records will be generated.
	 *
	 * A {@link RecordTrigger} can be used to force new rows to be generated.
	 *
	 * @param fieldName name of the persistent field to be created. If called more than once, a new {@link PathStart}
	 *                  will be returned, allowing multiple paths to be used to collect data into the same field.
	 *
	 * @return a {@link PathStart}, so that a path to the target HTML content to be captured can be defined
	 */
	PathStart addPersistentField(String fieldName);

	/**
	 * Associates a "silent" field with an entity. A silent field does not trigger new records when values of a field
	 * are overwritten, i.e. if the parser collects a value for a field that already contains data,
	 * and the field is silent, it won't submit a new record. The parser will simply replace the previously collected value
	 * with the newly parsed value.
	 *
	 * A {@link RecordTrigger} can be used to force new rows to be generated.
	 *
	 * A usage example of silent fields can be shown with this HTML document:
	 *
	 * ```html
	 * <div>
	 *   <article class="feature">
	 *     <h1>first</h1>
	 *     <p>lorem</p>
	 *     <h1>second</h1>
	 *   </article>
	 * </div>
	 * ```
	 *
	 * To get the text of the `p` element along with the **second** header:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * entity.addSilentField("silent")
	 *     .match("h1")
	 *         .containedBy("article")
	 *     .getText();
	 *
	 * entity.addField("text").match("article").match("p").getText();
	 * ```
	 *
	 * The parser will return `[second, lorem]`. When the parser finishes parsing the `p` element, the row will actually
	 * be `[first, lorem]`. As soon as the parser finds the second `h1` element, instead of creating a new row with this value,
	 * it will replace the "first" `String` with "second" generating the row `[second, lorem]`.
	 *
	 * If `addField` was used in this example instead of `addSilentField`, two rows would be produced:
	 * `[first, lorem]` and `[second, null]`
	 *
	 * @param fieldName name of the silent field to be created. If called more than once, a new {@link PathStart}
	 *                  will be returned, allowing multiple paths to be used to collect data into the same field.
	 *
	 * @return a {@link PathStart}, so that a path to the target HTML content to be captured can be defined
	 */
	PathStart addSilentField(String fieldName);


	/**
	 * Creates a field that with a specified value. An example to use this method can
	 * be shown with this HTML document:
	 *
	 * ```html
	 * <div>
	 *   <article>
	 *     <h1>first</h1>
	 *     <p>lorem</p>
	 *   </article>
	 *   <article>
	 *     <h1>second</h1>
	 *     <p>ipsum</p>
	 *   </article>
	 *   <article>
	 *     <h1>third</h1>
	 *     <p>lol</p>
	 *   </article>
	 * </div>
	 * ```
	 *
	 * And the following code:
	 *
	 * ```java
	 * HtmlEntityList entities = new HtmlEntityList();
	 * HtmlEntitySettings entity = entities.configureEntity("test");
	 *
	 * // creates a constant field
	 * entity.addField("constant","cool article");
	 *
	 * // regular fields
	 * entity.addField("title").match("h1").getText();
	 * entity.addField("content").match("p").getText();
	 * ```
	 *
	 * When the parser runs, it will get the text from each article heading and `p` element. It will also attach the
	 * constant "cool article" to the first column of each row, producing:
	 *
	 * ```
	 * [cool article, first, lorem]
	 * [cool article, second, ipsum]
	 * [cool article, third, lol]
	 * ```
	 *
	 * @param fieldName         name of the field to be created
	 * @param constantValue     a constant value associated with the given field
	 */
	void addField(String fieldName, String constantValue);
}
