/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.common.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class HtmlDataStoreConfiguration {

	private final Map<String, HtmlEntity> entities = new TreeMap<String, HtmlEntity>();
	private final HtmlEntity defaultEntityConfiguration = new HtmlEntity();

	private final FileProvider inputFile;
	private final ReaderProvider inputProvider;

	private HtmlParserListener listener;

	private int limitOfRowsLoadedInMemory = 1000;

	public HtmlDataStoreConfiguration(File inputFile) {
		this(new FileProvider(inputFile));
	}

	public HtmlDataStoreConfiguration(File inputFile, String encoding) {
		this(new FileProvider(inputFile, encoding));
	}

	public HtmlDataStoreConfiguration(File inputFile, Charset encoding) {
		this(new FileProvider(inputFile, encoding));
	}

	public HtmlDataStoreConfiguration(String pathToinputFile) {
		this(new FileProvider(pathToinputFile));
	}

	public HtmlDataStoreConfiguration(String pathToinputFile, String encoding) {
		this(new FileProvider(pathToinputFile, encoding));
	}

	public HtmlDataStoreConfiguration(String pathToinputFile, Charset encoding) {
		this(new FileProvider(pathToinputFile, encoding));
	}

	public HtmlDataStoreConfiguration(UrlReaderProvider inputProvider) {
		this((ReaderProvider) inputProvider);
	}

	public HtmlDataStoreConfiguration(ReaderProvider inputProvider) {
		this.inputProvider = inputProvider;
		this.inputFile = null;
	}

	public HtmlDataStoreConfiguration(FileProvider inputFile) {
		this.inputFile = inputFile;
		this.inputProvider = null;
	}

	public Set<String> getEntityNames() {
		return new TreeSet<String>(entities.keySet());
	}

	public HtmlEntity configureEntity(String entityName) {
		if (entities.containsKey(entityName)) {
			return entities.get(entityName);
		} else {
			HtmlEntity config = new HtmlEntity(entityName);
			entities.put(entityName, config);
			return config;
		}
	}

	public final FileProvider getInputFile() {
		return inputFile;
	}

	public final ReaderProvider getInputProvider() {
		return inputProvider;
	}

	public void setLimitOfRowsLoadedInMemory(int limitOfRowsLoadedInMemory) {
		limitOfRowsLoadedInMemory = limitOfRowsLoadedInMemory;
	}

	public void setHtmlParserListener(HtmlParserListener listener) {
		this.listener = listener;
	}

	public HtmlParserListener getHtmlParserListener() {
		return listener;
	}

}
