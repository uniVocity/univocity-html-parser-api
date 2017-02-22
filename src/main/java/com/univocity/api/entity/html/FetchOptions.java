package com.univocity.api.entity.html;

import java.io.*;
import java.nio.charset.*;

/**
 * Configuration class for use in the {@link HtmlElement#fetchResources(File, Charset, FetchOptions)} methods
 * Setters return {@code this} instance to enable method chaining during initialization.
 */
public class FetchOptions {

	private boolean flattenDirectoryStructure;

	public FetchOptions(){

	}

	public FetchOptions flattenDirectory(boolean flatten){
		this.flattenDirectoryStructure = flatten;
		return this;
	}

	public boolean isFlattenDirectoryStructure() {
		return flattenDirectoryStructure;
	}
}
