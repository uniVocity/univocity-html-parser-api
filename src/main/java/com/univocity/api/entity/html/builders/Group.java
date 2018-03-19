/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html.builders;

/**
 * A group defines the boundaries where a given set of fields should be processed. The parser will only collect values
 * from elements of a group if a matching condition is satisfied.
 *
 * For example, given the following HTML:
 *
 * ```html
 * <div id="55">
 *   <h1>random text</h1>
 *   <p>random paragraph</p>
 *   <article>
 *     <h1>first</h1>
 *     <p>lorem</p>
 *   </article>
 *   <h1>random text 2</h1>
 *   <p>random paragraph 2</p>
 *   <article>
 *     <h1>second</h1>
 *     <p>ipsum</p>
 *   </article>
 * </div>
 * ```
 *
 * To get all values of every `h1` and `p` element inside an `article`, the following rules can be created:
 *
 * ```java
 * HtmlEntityList entities = new HtmlEntityList();
 * HtmlEntitySettings entity = entities.configureEntity("test");
 *
 * Group group = entity
 *     .newGroup()
 *         .startAt("article")
 *         .endAtClosing("article");
 *
 * group.addField("title").match("h1").getText();
 * group.addField("text").match("p").getText();
 * ```
 *
 * In the example above, the group starts at every `article` element, and ends when the parser finds a closing `article`
 * element. Fields must added to the group. The `h1` and `p` elements outside of `article` are simply ignored by the
 * parser.
 *
 * The example above should produce the following two records:
 *
 * ```
 * [first, lorem]
 * [second, ipsum]
 * ```
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface Group extends ElementFilter<Group>, ElementFilterStart<Group>, FieldDefinition, PathCopy<PartialPath>, Trigger {

}