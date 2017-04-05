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
	 * {@link HtmlElement} containing the new html with local resource locations
	 */
	public HtmlElement treeRoot;

	/**
	 * {@link File} pointing to where the new html has been saved
	 */
	public File treeHtmlFile;

	/**
	 * {@link Map} mapping: "downloaded resource files locations" => "original remote urls"
	 */
	public Map<File, URL> resourceMap;

}
