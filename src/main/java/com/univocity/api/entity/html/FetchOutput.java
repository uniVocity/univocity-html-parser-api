package com.univocity.api.entity.html;


import java.io.*;
import java.net.*;
import java.util.*;

/**
 * The output produced by the {@link HtmlElement#fetchResources} methods.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class FetchOutput {

	/**
	 * {@link HtmlElement} containing the new HTML.
	 * For each remote resource downloaded; the remote url has been replaced with the relative local file location.
	 */
	public HtmlElement treeRoot;

	/**
	 * {@link File} pointing to where the new HTML has been saved.
	 */
	public File treeHtmlFile;

	/**
	 * {@link Map}, mapping each local File that has been downloaded to its original remote URL
	 */
	public Map<File, URL> resourceMap;

}
