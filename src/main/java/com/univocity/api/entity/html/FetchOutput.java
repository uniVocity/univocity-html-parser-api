package com.univocity.api.entity.html;


import java.io.*;
import java.net.*;
import java.util.*;

/**
 * The output produced by the {@link HtmlElement#fetchResources} methods containing:
 * <ul>
 *     <li>The {@link HtmlElement} containing the new html with local resource locations</li>
 *     <li>The {@link File} where the new html has been saved</li>
 *     <li>A {@link Map} from the downloaded resource files locations to there original remote urls</li>
 * </ul>
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class FetchOutput {

	public HtmlElement treeRoot;
	public File treeHtmlFile;
	public Map<File, URL> resourceMap;

}
