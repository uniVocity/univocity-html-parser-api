/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */
package com.univocity.api.entity.html;

import com.univocity.api.common.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

/**
 * This is the configuration class used by {@link HtmlParser}.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:parsers@univocity.com">parsers@univocity.com</a>
 * @see HtmlParser
 * @see Processor
 * @see HtmlEntityList
 */
public class HtmlParserSettings extends AbstractEntityParserSettings {


	private Processor<HtmlParsingContext> processor;
	private final HtmlEntityList htmlEntityList;
	private HtmlParserListener listener;
	private FileProvider downloadContentDirectory;
	private int downloadThreads;
	private String fileNamePattern;


	/**
	 * Creates a new HtmlParserSettings with a supplied {@link HtmlEntityList}. The {@link HtmlEntityList} is used to
	 * store {@link HtmlEntity}'s which define the specific HTML elements that will be parsed.
	 *
	 * @param htmlEntityList the
	 */
	public HtmlParserSettings(HtmlEntityList htmlEntityList) {
		this.htmlEntityList = htmlEntityList;
	}

	/**
	 * Returns the {@link HtmlEntityList} associated with the settings.
	 *
	 * @return the HtmlEntityList
	 */
	public HtmlEntityList getHtmlEntityList() {
		return htmlEntityList;
	}

	/**
	 * Sets the global {@link Processor}. All rows parsed will be delegated to the processor. Does not
	 * need to be set for the {@link HtmlParser} to function.
	 *
	 * @param processor the {@link Processor} that will be set as the processor
	 */
	public void setGlobalProcessor(Processor<HtmlParsingContext> processor) {
		this.processor = processor;
	}

	/**
	 * Returns the processor if previously set. If the processor was not set, returns a
	 * {@link NoopProcessor}, which is a {@link Processor} that does nothing.
	 * @return
	 */
	public Processor<HtmlParsingContext> getGlobalProcessor() {
		return processor == null ? NoopProcessor.instance : processor;
	}

	@Override
	protected Format createDefaultFormat() {
		return new Format() {
			@Override
			protected TreeMap<String, Object> getConfiguration() {
				return new TreeMap<String, Object>();
			}
		};
	}

	/**
	 * Returns the entity names contained in the associated {@link HtmlEntityList} as a set of Strings.
	 * @return the entity names
	 */
	public Set<String> getEntityNames() {
		return htmlEntityList.getEntityNames();
	}

	/**
	 * Sets the associated {@link HtmlParserListener} that is used when the {@link HtmlParser} parses.
	 * @param listener the HtmlParserListener that the settings will be associated with
	 */
	public void setListener(HtmlParserListener listener) {
		this.listener = listener;
	}

	/**
	 * Returns the associated {@link HtmlParserListener}
	 * @return the HtmlParserListener associated with the settings
	 */
	public HtmlParserListener getListener() {
		return listener;
	}

	public int getPaginationFollowCount() {
		if (htmlEntityList.getPaginator() != null) {
			return htmlEntityList.getPaginator().getFollowCount();
		}
		return -1;
	}

	public void setDownloadThreads(int downloadThreads) {
		this.downloadThreads = downloadThreads;
	}


	public int getDownloadThreads() {
		return downloadThreads;
	}

	public void setDownloadContentDirectory(String fileName) {
		downloadContentDirectory = new FileProvider(fileName);
	}

	public void setDownloadContentDirectory(String fileName, Charset encoding) {
		downloadContentDirectory = new FileProvider(fileName, encoding);
	}

	public void setDownloadContentDirectory(String fileName, String encoding) {
		downloadContentDirectory = new FileProvider(fileName, encoding);
	}

	public void setDownloadContentDirectory(File file) {
		downloadContentDirectory = new FileProvider(file);
	}

	public void setDownloadContentDirectory(File file, Charset encoding) {
		downloadContentDirectory = new FileProvider(file, encoding);
	}

	public void setDownloadContentDirectory(File file, String encoding) {
		downloadContentDirectory = new FileProvider(file, encoding);
	}

	public FileProvider getDownloadContentDirectory() {
		return downloadContentDirectory;
	}

	public void setFileNamePattern(String pattern) {
		fileNamePattern = pattern;
	}

	public String getFileNamePattern() {
		//search/{pageNumber}.html
		return fileNamePattern;
	}


}
