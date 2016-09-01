/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface PartialHtmlPath extends BaseHtmlPath<PartialHtmlPath>, PartialHtmlPathStart, FieldAdder {

	/**
	 * Copies the current path so that any matching rules created after the copy is made does not change the original
	 * path. Useful for when a path to an element is commonly used to create new paths. An example can be shown with
	 * this HTML:
	 *
	 *<p><hr><blockquote><pre>
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
	 *</p></pre></blockquote><hr>
	 *
	 *<p>A technique to get the text of the p element of the first and second articles in the first div is: </p>
	 *
	 *<p><hr><blockquote><pre>
	 *HtmlEntityList entities = new HtmlEntityList();
	 *HtmlEntity entity = entities.configureEntity("test");
	 *
	 * //path to the first div that will be used to base other paths on
	 *PartialHtmlPath path = entity.newPath().match("div").classes("cool-div").match("article").containing("h1");
	 *
	 * //get the text of the first article
	 *path.copyPath().withText("first").addField("one").match("p").getText();
	 *
	 * //get the text of the second article
	 *path.copyPath().withText("second").addField("second").match("p").getText();
	 *
	 *</p></pre></blockquote><hr>
	 *
	 *<p>In this example, the first path that is created is used as a basis. The path is then copied twice to specify
	 * the two p elements where the text will be returned from. Running the parser will return [lorem, ispum]. If copyPath
	 * was not used, the parser would return [lorem, null] as the path created for the second article would be built
	 * upon the path generated for the first article. This path would not exist and null is the return value. </p>
	 *
	 * @return a {@link PartialHtmlPath} that allows the specification of a path and does not affect the path that it
	 * is built upon.
	 */
	PartialHtmlPath copyPath();

	/**
	 * Returns a {@link RecordTriggerStart} that is used to specify a path that defines when rows should be created.
	 *
	 * For example, assume you have set up a parser that parses email address and home address fields from a HTML document
	 * of customer details. In the document, any of the customers fields may not exist (be null when parsed). An example
	 * of this HTML is shown below:
	 *
	 * <p><hr><blockquote><pre>
	 * <table>
	 * 	<tr>
	 * 		<td>Email Address</td>
	 * 		<td>bla@email.com</td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td>Home Address</td>
	 * 	</tr>
	 * </table>
	 * <table>
	 * 	<tr>
	 * 		<td>Email Address</td>
	 * 	</tr>
	 * 	<tr>
	 * 		<td>Home Address</td>
	 * 		<td>123 real street</td>
	 * 	</tr>
	 * </table>
	 *</p></pre></blockquote><hr>
	 *
	 * The parsing rules are set below:
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * HtmlParserSettings settings = new HtmlParserSettings(entityList);
	 *
	 * PartialHtmlPath path = entityList.configureEntity("record").newPath().match("table");
	 * path.addField("emailAddress").match("td").precededBy("td").withText("Email Address").getText();
	 * path.addField("homeAddress").match("td").precededBy("td").withText("Home Address").getText();
	 *</p></pre></blockquote><hr>
	 *
	 * <p>After running it through the HtmlParser, we get this output: </p>
	 *
	 * <p>
	 * Expected output:
	 * <br>[bla.@email.com, null]
	 * <br>[null, 123 real street]
	 * <br>
	 * Actual output:
	 * <br>[bla.@email.com, 123 real street]
	 * </p>
	 *
	 * <p>
	 * This is due to the parser finding that the first Home Address is null, assuming that the second home address is
	 * the one that was specified for the first row. To fix this error, the RecordTrigger will be set. this is done by
	 * adding the line below to the code snippet we had before:
	 * </p>
	 *
	 *<p><hr><blockquote><pre>
	 * path.addRecordTrigger().match("td").withText("Email Address");
	 *</p></pre></blockquote><hr>
	 *
	 * <p>
	 * Which, when running it throught the HtmlParser we get the expected output of:
	 *	</p>
	 *<p>
	 * [bla.@email.com, null]<br>
	 * [null, 123 real street]
	 *</p>
	 *
	 * <p>
	 * This is because when the parser hits the second email address entry, the RecordTrigger is activated and a new
	 * row is created. Therefore, when the parser hits the second Home Address, it adds the value to the second row
	 * instead of the first row.
	 * </p>
	 *
	 * @return a {@link RecordTriggerStart} that defines the path for the trigger
	 */
	RecordTriggerStart addRecordTrigger();

}
