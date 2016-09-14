package com.univocity.api.entity.html.builders.annotations;

import java.lang.annotation.*;

/**
 * Created by anthony on 14/09/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Matcher {
	enum Type {WITH_TEXT, //methods that deal with text inside of element
		ATTRIBUTE, //methods that deal with an element's attributes
		TABLE, //methods that are only applicable in a table
		GETTERS,  //methods that return data
		NEIGHBOUR, //methods that deal with elements/text in the element preceding/following the current element
		INSIDE, //methods that concern of elements inside of the specified element (e.g containing/parentof)
		PARENTS //methods that deal with element(s) that contain the specified element (e.g containedBy, childOf)
	}
	Type type();
}
