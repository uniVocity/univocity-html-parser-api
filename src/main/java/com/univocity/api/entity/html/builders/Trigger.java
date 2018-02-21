package com.univocity.api.entity.html.builders;

import com.univocity.api.entity.html.*;

/**
 * An interface that provides functionality to define a record trigger, i.e. a path to an element that when found,
 * forces the parser to generate a record with any values accumulated so far.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
interface Trigger {

	/**
	 * Starts the definition of a {@link RecordTrigger}, which is essentially a path to an element that when found 
	 * makes the parser generate a record will all values accumulated so far.
	 *
	 * For example, assume you have to capture email address and home address fields from a HTML document of customer
	 * details. In the document, any of the customers fields may or may not exist (i.e. be `null` when parsed).
	 *
	 * An example of this HTML is shown below:
	 *
	 * ```html
	 * <h1>Customer A address</h1>
	 * <table>
	 *   <tr>
	 *     <td>Email Address</td>
	 *     <td>bla@email.com</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Home Address</td>
	 *   </tr>
	 * </table>
	 *
	 * <h1>Customer B address</h1>
	 * <table>
	 *   <tr>
	 *     <td>Email Address</td>
	 *   </tr>
	 *   <tr>
	 *     <td>Home Address</td>
	 *     <td>123 real street</td>
	 *   </tr>
	 * </table>
	 * ```
	 *
	 * Consider the code without a {@link RecordTrigger}:
	 *
	 * ```java
	 * HtmlEntityList entityList = new HtmlEntityList();
	 * HtmlEntitySettings contact = entityList.configureEntity("contact");
	 *
	 * contact.addField("emailAddress")
	 *     .match("td").precededBy("td").withText("Email Address").getText();
	 *
	 * contact.addField("homeAddress")
	 *     .match("td").precededBy("td").withText("Home Address").getText();
	 * ```
	 *
	 * This would produce one row with mixed results:
	 *
	 * ```
	 *     [bla.@email.com, 123 real street]
	 * ```
	 *
	 * Instead of the expected output:
	 *
	 * ```
	 *     [bla.@email.com, null]
	 *     [null, 123 real street]
	 * ```
	 *
	 * The incorrect output is produced because there is no `<td>` after the first 'Home Address' and no
	 * `<td>` after 'E-mail Address' in the details of the second customer.
	 *
	 * We can instruct the parser to generate a record with all values collected so far when a `<td>` with text
	 * "Email Address" is found with the following:
	 *
	 * ```java
	 * path.addRecordTrigger()
	 *     .match("td").withText("Email Address");
	 * ```
	 *
	 * Now, when the parser hits the second email address entry, the record trigger will be activated and a new
	 * row will be created. Now, when the second Home Address is found, it adds the value to the second row
	 * instead of the first row.
	 *
	 * @return a {@link RecordTriggerStart} that defines the path for the trigger
	 */
	RecordTriggerStart addRecordTrigger();
}
