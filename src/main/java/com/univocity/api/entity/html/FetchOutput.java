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

	private HtmlElement treeRoot;

	private File treeHtmlFile;

	private Map<File, URL> resourceMap;

	/**
	 * Creates a new `FetchOutput` with the results obtained from a call to {@link HtmlElement#fetchResources}
	 *
	 * @param treeRoot the root of the HTML structure that had its resources fetched
	 * @param treeHtmlFile a `File` with the saved HTML content, with all resources pointing to local files.
	 * @param resourceMap the mapping of each local `File` that has been downloaded to its original remote URL
	 */
	public FetchOutput(HtmlElement treeRoot, File treeHtmlFile, Map<File, URL> resourceMap) {
		this.treeRoot = treeRoot;
		this.treeHtmlFile = treeHtmlFile;
		this.resourceMap = resourceMap;
	}

	/**
	 * Returns the root {@link HtmlElement} of the new HTML structure.
	 *
	 * For each remote resource downloaded the remote url has been replaced with the relative local file location.
	 *
	 * @return the root node of the HTML tree that has been fetched
	 */
	public HtmlElement getTreeRoot() {
		return treeRoot;
	}

	/**
	 * Returns `File` pointing to where the new HTML has been saved.
	 *
	 * @return file where the new HTML has been saved.
	 */
	public File getTreeHtmlFile() {
		return treeHtmlFile;
	}

	/**
	 * Returns the mapping of each local `File` that has been downloaded to its original remote URL
	 *
	 * @return a map of each local `File` that has been downloaded to its original remote URL
	 */
	public Map<File, URL> getResourceMap() {
		return resourceMap;
	}
}
