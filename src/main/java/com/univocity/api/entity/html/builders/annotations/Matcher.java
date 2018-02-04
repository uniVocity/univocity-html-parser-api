package com.univocity.api.entity.html.builders.annotations;

import java.lang.annotation.*;

/**
 * Basic annotation used internally to classify methods of the public API based on their purpose.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Matcher {
	/**
	 * The general type of matching algorithm associated with the method.
	 */
	enum Type {
		/**
		 * methods that deal with text inside of an element
		 */
		WITH_TEXT,
		/**
		 * methods that deal with an element's attributes
		 */
		ATTRIBUTE,
		/**
		 * methods that are only applicable in a tables
		 */
		TABLE,
		/**
		 * methods that return data
		 */
		GETTERS,
		/**
		 * methods that deal with elements/text in the element preceding/following the current element
		 */
		NEIGHBOUR,
		/**
		 * methods that concern of elements inside of the specified element (e.g containing/parentof)
		 */
		INSIDE,
		/**
		 * methods that deal with element(s) that contain the specified element (e.g containedBy, childOf)
		 */
		PARENTS
	}

	/**
	 * Returns the general type that indicates what the annotated method does.
	 * @return the type of matching algorithm implemented by this matcher.
	 */
	Type type();
}
