package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;

/**
 * An interface that provides functionality add a record trigger, i.e. a path to an element that when found,
 * forces the parser to generate a record with any values accumulated so far.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
interface Trigger {

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
	 * </p></pre></blockquote><hr>
	 *
	 * The parsing rules are set below:
	 *
	 * <p><hr><blockquote><pre>
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * HtmlParserSettings settings = new HtmlParserSettings(entityList);
	 *
	 * PartialPath path = entityList.configureEntity("record").newPath().match("table");
	 * path.addField("emailAddress").match("td").precededBy("td").withText("Email Address").getText();
	 * path.addField("homeAddress").match("td").precededBy("td").withText("Home Address").getText();
	 * </p></pre></blockquote><hr>
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
	 * the one that was specified for the first row. To fix this error, the {@link RecordTrigger} will be set. This is done by
	 * adding the line below to the code snippet we had before:
	 * </p>
	 *
	 * <p><hr><blockquote><pre>
	 * path.addRecordTrigger().match("td").withText("Email Address");
	 * </p></pre></blockquote><hr>
	 *
	 * <p>
	 * Which, when running it through the {@link HtmlParser} produces:
	 * </p>
	 * <p>
	 * [bla.@email.com, null]<br>
	 * [null, 123 real street]
	 * </p>
	 *
	 * <p>
	 * When the parser hits the second email address entry, the RecordTrigger is activated and a new
	 * row is created. Therefore, when the parser hits the second Home Address, it adds the value to the second row
	 * instead of the first row.
	 * </p>
	 *
	 * @return a {@link RecordTriggerStart} that defines the path for the trigger
	 */
	RecordTriggerStart addRecordTrigger();
}
