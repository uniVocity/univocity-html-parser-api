/*
 * Copyright (c) 2013 uniVocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.io.*;

import java.io.*;
import java.net.*;

public interface DownloadContext {

	RateLimiter rateLimiter();

	HtmlElement sourceElement();

	File parentHtmlFile();

	File parentCssFile();

	File parentDir();

	File targetFile();

	void targetFile(File newFile);

	String targetFileExtension();

	String targetRelativePath();

	String originalDownloadLink();

	URL downloadUrl();

	void skipDownload();

	boolean downloadSkipped();

	void stopAllDownloads();

	boolean downloadsStopped();
}
