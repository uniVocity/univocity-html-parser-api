/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import java.io.*;

/**
 * A callback interface for downloads handled by a "fetch resources" operation, which downloads all resources referenced
 * by a given HTML input. This callback is used when {@link HtmlElement#fetchResources(File, FetchOptions)} is executed,
 * or when the {@link HtmlParserSettings#fetchResourcesBeforeParsing(FetchOptions)} is used, where
 * {@link FetchOptions#getDownloadHandler()} returns a non-null value.
 *
 * @see DownloadContext
 * @see FetchOptions
 * @see HtmlElement
 * @see HtmlParserSettings
 */
public interface DownloadHandler {

	/**
	 * Informs the user that a resource is ready to be downloaded. Use the {@code context} object to control the process.
	 * For example, you can use {@link DownloadContext#targetFileExtension()} to prevent the download of certain types
	 * of files - just call {@link DownloadContext#skipDownload()} to prevent the download from happening.
	 *
	 * @param context an object with all information available about the download to performed,
	 *                and options to skip or cancel the fetch operation altogether
	 */
	void nextDownload(DownloadContext context);
}
