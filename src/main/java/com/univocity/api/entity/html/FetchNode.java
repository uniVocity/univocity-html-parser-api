/*
 * Copyright (c) 2019 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;


import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class FetchNode {

	private final URL sourceUrl;
	private final File htmlFile;
	private final int level;

	private Set<FetchNode> pagesToHere = Collections.emptySet();
	private Set<FetchNode> pagesFromHere = Collections.emptySet();

	public FetchNode(URL sourceUrl, File htmlFile, int level) {
		this.sourceUrl = sourceUrl;
		this.htmlFile = htmlFile;
		this.level = level;
	}

	public void addPageFromHere(FetchNode node) {
		if (pagesFromHere.isEmpty()) {
			pagesFromHere = new HashSet<FetchNode>();
		}
		pagesFromHere.add(node);
	}

	public void addPageToHere(FetchNode node) {
		if (pagesToHere.isEmpty()) {
			pagesToHere = new HashSet<FetchNode>();
		}
		pagesToHere.add(node);
	}

	public URL getSourceUrl() {
		return sourceUrl;
	}

	public File getHtmlFile() {
		return htmlFile;
	}

	public int getLevel() {
		return level;
	}

	public Set<FetchNode> getPagesToHere() {
		return pagesToHere;
	}

	public Set<FetchNode> getPagesFromHere() {
		return pagesFromHere;
	}

	public boolean isRoot() {
		return level == 0;
	}

	public boolean isLeaf() {
		return pagesFromHere.isEmpty();
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		FetchNode fetchNode = (FetchNode) o;

		if (level != fetchNode.level) return false;
		return sourceUrl != null ? sourceUrl.equals(fetchNode.sourceUrl) : fetchNode.sourceUrl == null;
	}

	@Override
	public int hashCode() {
		int result = sourceUrl != null ? sourceUrl.hashCode() : 0;
		result = 31 * result + level;
		return result;
	}

	public String toString() {
		StringBuilder out = new StringBuilder();
		toString(out);
		return out.toString();
	}


	public void toString(StringBuilder tmp) {
		for (int i = 0; i < level; i++) {
			tmp.append('\t');
		}
		tmp.append(sourceUrl);
		tmp.append(" - ");
		tmp.append(htmlFile);

		for (FetchNode child : pagesFromHere) {
			tmp.append('\n');
			child.toString(tmp);
		}
	}
}
