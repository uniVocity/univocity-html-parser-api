/*
 * Copyright (c) 2013 Univocity Software Pty Ltd. All rights reserved.
 * This file is subject to the terms and conditions defined in file
 * 'LICENSE.txt', which is part of this source code package.
 */

package com.univocity.api.entity.html;

import com.univocity.api.io.*;
import com.univocity.parsers.remote.*;

import java.io.*;
import java.net.*;

/**
 * Provides all information available during a "fetch resources" operation to a {@link DownloadHandler} callback, and
 * allows the user to skip the download of certain file types.
 */
public interface DownloadContext {

	/**
	 * The current base URI associated with the document whose resources are being fetched. Used to "build" the full
	 * URL used to download a given resource. For example, if a link such as `<a href="/Images/Icons/garage.svg"></a>`
	 * is being processed, and the base URI is set to `http://www.univocity.com`, the download URL will be
	 * `http://www.univocity.com/Images/Icons/garage.svg`
	 *
	 * @return the base URI if available, or an empty {@code String}
	 */
	String baseUri();

	/**
	 * Modifies the current base URI associated with the document whose resources are being fetched. Used to "build" the full
	 * URL used to download a given resource. For example, if a link such as `<a href="/Images/Icons/garage.svg"></a>`
	 * is being processed, and the base URI is set to `http://www.univocity.com`, the download URL will be
	 * `http://www.univocity.com/Images/Icons/garage.svg`
	 *
	 * @param baseUri the base URI to use for generating absolute download URL paths.
	 */
	void setBaseUri(String baseUri);

	/**
	 * Returns the {@link RateLimiter} being used during the fetch resources operation to slow down the rate of downloads
	 * being performed. This rate limiter is configured via {@link FetchOptions#getRemoteInterval()} and is not associated
	 * with the setting in {@link RemoteParserSettings#getRemoteInterval()}.
	 *
	 * @return the active rate limiter used during the fetch resources operation.
	 */
	RateLimiter rateLimiter();

	/**
	 * Returns the specific {@link HtmlElement} of the HTML that has a reference to the resource being downloaded. Any
	 * attribute of this element that points to a remote resource will be updated to point to the file downloaded locally.
	 * Will only return a non-null element when {@link #parentHtmlFile()} returns a valid `File`.
	 *
	 * This method will return `null` when a CSS file is being updated to point to local resources, i.e.
	 * {@link #parentHtmlFile()} returns `null` and {@link #parentCssFile()} returns a valid `File`.
	 *
	 * @return the HTML element whose relevant attributes will be updated to point to a local file
	 * instead of the remote resource, or `null` if the file to download originates from a CSS file.
	 */
	HtmlElement sourceElement();

	/**
	 * Returns the specific attribute of the {@link HtmlElement} that has a reference to the resource being downloaded.
	 * This is the attribute of the {@link #sourceElement()} that will be updated to point to the file downloaded locally.
	 *
	 * This method will return `null` when a CSS file is being updated to point to local resources, i.e.
	 * {@link #parentHtmlFile()} returns `null` and {@link #parentCssFile()} returns a valid `File`.
	 *
	 * @return the attribute of the HTML element that will be updated to point to a local file
	 * instead of the remote resource, or `null` if the file to download originates from a CSS file.
	 */
	String sourceAttribute();

	/**
	 * Returns the HTML file that is going to be updated/generated after the fetch resources operation is complete. All
	 * HTML nodes that have attributes pointing to a remote resource that could be downloaded will be modified to point
	 * to the locally downloaded files.
	 *
	 * The resulting HTML file will *not* have a `<base>` tag.
	 *
	 * @return the updated HTML file using local resources.
	 */
	File parentHtmlFile();

	/**
	 * Returns the CSS file that is going to be updated after the fetch resources operation is complete. All
	 * URLs pointing to a remote resource that could be downloaded will be modified to point to the locally downloaded
	 * files. Paths to these files will be relative to the CSS file location.
	 *
	 * @return the updated CSS file using local resources.
	 */
	File parentCssFile();

	/**
	 * The directory where the {@link #parentHtmlFile()} is located.
	 * @return the parent directory of the HTML being updated.
	 */
	File parentDir();

	/**
	 * Returns the local target `File` where the downloaded contents will be saved. The file may exist already
	 * and will be overwritten if {@link FetchOptions#isOverwriteSharedResources()} evaluates to `true`.
	 *
	 * @return the target file being downloaded.
	 */
	File targetFile();

	/**
	 * Returns the updated attribute value using the path to the downloaded content.
	 *
	 * @return the target attribute pointing to the downloaded file.
	 */
	String targetAttribute();

	/**
	 * Changes the download destination to a new location.
	 *
	 * @param newFile the download destination of the {@link #targetFile()}
	 */
	void setTargetFile(File newFile);

	/**
	 * Returns the extension of the {@link #targetFile()}. Will never be `null`.
	 *
	 * @return the target file extension, without the dot.
	 */
	String targetFileExtension();

	/**
	 * Tests whether the target file extension matches one of the given file extensions (case insensitive)
	 * @param targetFileExtensions the file extensions to match
	 * @return `true` if the {@link #targetFileExtension()} is found in the given list of extensions.
	 */
	boolean fileExtensionMatches(String ... targetFileExtensions);

	/**
	 * Returns the relative path of the file being downloaded. The path will be relative to the location of the HTML file
	 * if {@link #parentHtmlFile()} is not `null, or relative to the CSS file if {@link #parentCssFile()} is not `null`.
	 *
	 * @return the path to the current file, relative to its parent HTML or CSS file.
	 */
	String targetRelativePath();

	/**
	 * Returns the original link extracted from the HTML or CSS file. For example, when fetching the resources of
	 * `<a href="/Images/Icons/garage.svg"></a>`, this method will return `/Images/Icons/garage.svg`.
	 *
	 * @return the text of the link to a remote resource as originally found in the input HTML or CSS.
	 */
	String originalDownloadLink();

	/**
	 * Returns the fully assembled URL pointing to a remote resource, which will be used to perform the download into the
	 * {@link #targetFile()}.
	 *
	 * @return returns the absolute download URL
	 */
	URL downloadUrl();

	/**
	 * Skips this download and moves on to the next.
	 */
	void skipDownload();

	/**
	 * Returns a flag indicating that this download has been skipped .
	 * @return whether the current download has been skipped.
	 */
	boolean downloadSkipped();

	/**
	 * Skips this download and stops any active downloads, finalizing the fetch operation
	 */
	void stopAllDownloads();

	/**
	 * Returns a flag indicating that all downloads have been stopped
	 * 
	 * @return whether the fetch operation has been stopped.
	 */
	boolean downloadsStopped();

	/**
	 * Returns a flag indicating that the current URL points to an external website
	 *
	 * @return whether the the resource collection process identified the current URL as a link to an external website.
	 */
	boolean isExternalWebsite();
}
