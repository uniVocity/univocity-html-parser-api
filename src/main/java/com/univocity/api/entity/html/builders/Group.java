/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * A group defines the boundaries where a given set of fields should be processed. For example, given the following HTML:
 *
 * <p><hr><blockquote><pre>
 * <div id="55">
 * 	<h1>random text</h1>
 * 	<p>random paragraph</p>
 * 	<article>
 * 		<h1>first</h1>
 * 		<p>lorem</p>
 * 	</article>
 * 	<h1>random text 2</h1>
 * 	<p>random paragraph 2</p>
 * 	<article>
 * 		<h1>second</h1>
 * 		<p>ipsum</p>
 * 	</article>
 * </div>
 * </p></pre></blockquote><hr>
 *
 * To get all values of every h1 and p element inside an article, the following rules can be created:
 * <p><hr><blockquote><pre>
 *
 * HtmlEntityList entities = new HtmlEntityList();
 * HtmlEntitySettings entity = entities.configureEntity("test");
 *
 * Group group = entity.newGroup().startAt("article").endAtClosing("article");
 * group.addField("title").match("h1").getText();
 * group.addField("text").match("p").getText();
 * </p></pre></blockquote><hr>
 *
 * The group starts at every article element, and ends when the parser finds a closing article element. Fields are added
 * to the group so their paths include it. When the parser runs only the following two records will be produced:
 * [first, lorem] and [second, ipsum]. The elements h1 and p elements that are outside of article elements are simply
 * skipped by the parser.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface Group extends ElementFilter<Group>, ElementFilterStart<Group>, FieldDefinition, PathCopy, Trigger {

}