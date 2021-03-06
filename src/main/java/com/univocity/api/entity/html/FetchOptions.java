package com.univocity.api.entity.html;

import com.univocity.api.io.*;

import java.io.*;

/**
 * Configuration class for use in the {@link HtmlElement#fetchResources} methods
 * Setters return `this` instance to enable method chaining during initialization.
 *
 * @author Univocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class FetchOptions implements Cloneable {

	private boolean overwriteSharedResources = false;
	private FileProvider sharedResourceDir;
	private boolean flattenDirectoryStructure;
	private DownloadHandler downloadHandler;
	private String baseUri;
	private boolean downloadBlacklistingEnabled = true;
	private long remoteInterval = 5L;
	private int depth = 0;

	/**
	 * Default constructor for FetchOptions
	 * Defaults to not flattening directory and accepting any String
	 */
	public FetchOptions() {
		flattenDirectoryStructure = false;
	}

	/**
	 * The current base URI associated with the document whose resources are being fetched. Used to "build" the full
	 * URL used to download a given resource. For example, if a link such as `<a href="/Images/Icons/garage.svg"></a>`
	 * is being processed, and the base URI is set to `http://www.univocity.com`, the download URL will be
	 * `http://www.univocity.com/Images/Icons/garage.svg`
	 *
	 * @return the base URI if available, or an empty {@code String}
	 */
	public String getBaseUri() {
		return baseUri;
	}

	/**
	 * Modifies the current base URI associated with the document whose resources are being fetched. Used to "build" the full
	 * URL used to download a given resource. For example, if a link such as `<a href="/Images/Icons/garage.svg"></a>`
	 * is being processed, and the base URI is set to `http://www.univocity.com`, the download URL will be
	 * `http://www.univocity.com/Images/Icons/garage.svg`
	 *
	 * @param baseUri base URI to use for generating absolute download URL paths.
	 */
	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	/**
	 * Option to flatten the path section of a fetched resource into the new filename.
	 *
	 * A file with the relative path such as `./path/to/resource/image.png`
	 * would normally be saved as a file named `image.png` in the `./path/to/resource/` directory.
	 *
	 * When flattened it will instead be saved as `path_to_resource_image.png` in the `.` directory.
	 *
	 * <em>Defaults to {@code false}</em>
	 *
	 * @param flatten whether to flatten the path of a resource into the saved name.
	 *
	 */
	public void flattenDirectories(boolean flatten) {
		this.flattenDirectoryStructure = flatten;
	}

	/**
	 * Whether or not the resource filenames should be 'flattened'. That is to say have the directories condensed into
	 * the filename so all resource files are in the same directory but all uniquely named.
	 * e.g.
	 *
	 * A file with the relative path such as `./path/to/resource/image.png`
	 * would normally be saved as a file named `image.png` in the `./path/to/resource/` directory.
	 *
	 * When flattened it will instead be saved as `path_to_resource_image.png` in the `.` directory.
	 *
	 * <em>Defaults to {@code false}</em>
	 *
	 * @return whether or not the directory structure in filenames will be flattened when saving resources.
	 */
	public boolean flattenDirectoryStructure() {
		return flattenDirectoryStructure;
	}

	/**
	 * Returns the {@link DownloadHandler} callback to be used by the fetch resources operation.
	 * @return the current download handler
	 */
	public DownloadHandler getDownloadHandler() {
		return downloadHandler;
	}

	/**
	 * Defines a {@link DownloadHandler} to manipulate the downloads performed by the fetch resources operation.
	 * @param downloadHandler the download handler to use
	 */
	public void setDownloadHandler(DownloadHandler downloadHandler) {
		this.downloadHandler = downloadHandler;
	}

	/**
	 * Returns a flag indicating whether resources that have been downloaded and are shared among multiple pages should
	 * be overwritten during a new fetch resources operation.
	 *
	 * <em>Defaults to {@code false}</em>
	 *
	 * @return whether local files that already exist should be overwritten
	 */
	public boolean isOverwriteSharedResources() {
		return overwriteSharedResources;
	}

	/**
	 * Defines whether resources that have been downloaded and are shared among multiple pages should
	 * be overwritten during a new fetch resources operation.
	 *
	 * <em>Defaults to {@code false}</em>
	 *
	 * @param overwriteSharedResources flag indicating that local files that already exist should be overwritten
	 */
	public void setOverwriteSharedResources(boolean overwriteSharedResources) {
		this.overwriteSharedResources = overwriteSharedResources;
	}

	/**
	 * Returns the shared resource directory used to store files referenced by one or more HTML pages and CSS files.
	 * Use it to prevent downloading the same images and CSS files over and over again for each HTML page you want to
	 * store.
	 *
	 * If unspecified (i.e. `null`) a directory named after the HTML file concatenated with the `_files` the suffix will
	 * be created, and all resources used by that HTML will be stored in this directory - which emulates what most browsers
	 * do when their "File -> Save Page As..." action is executed.
	 *
	 * @return the current resource directory, if any.
	 */
	public FileProvider getSharedResourceDir() {
		return sharedResourceDir;
	}


	/**
	 * Defines the shared resource directory used to store files referenced by one or more HTML pages and CSS files.
	 * Use it to prevent downloading the same images and CSS files over and over again for each HTML page you want to
	 * store.
	 *
	 * If unspecified (i.e. `null`) a directory named after the HTML file concatenated with the `_files` the suffix will
	 * be created, and all resources used by that HTML will be stored in this directory - which emulates what most browsers
	 * do when their "File -> Save Page As..." action is executed.
	 *
	 * @param sharedResourceDir the path to a shared resource directory to use.  It can contain system variables enclosed
	 * within { and } (e.g. {@code {user.home}/Downloads"}). Subdirectories that don't exist will be created if required.
	 */
	public void setSharedResourceDir(String sharedResourceDir) {
		if (sharedResourceDir == null) {
			this.sharedResourceDir = null;
		} else {
			this.sharedResourceDir = new FileProvider(sharedResourceDir);
		}
	}

	/**
	 * Defines the shared resource directory used to store files referenced by one or more HTML pages and CSS files.
	 * Use it to prevent downloading the same images and CSS files over and over again for each HTML page you want to
	 * store.
	 *
	 * If unspecified (i.e. `null`) a directory named after the HTML file concatenated with the `_files` the suffix will
	 * be created, and all resources used by that HTML will be stored in this directory - which emulates what most browsers
	 * do when their "File -> Save Page As..." action is executed.
	 *
	 * @param sharedResourceDir the path to a shared resource directory to use.  Subdirectories that don't exist will
	 * be created if required.
	 */
	public void setSharedResourceDir(File sharedResourceDir) {
		if (sharedResourceDir == null) {
			this.sharedResourceDir = null;
		} else {
			this.sharedResourceDir = new FileProvider(sharedResourceDir);
		}
	}

	/**
	 * Indicates whether URLs of resources that resulted in a download failure (such as a 404) should be blacklisted
	 * while the parser is running, so no further attempts to access the same URL will be made. Enabled by default to improve
	 * speed when fetching resources of multiple pages, especially when link following is used.
	 *
	 * <em>Defaults to {@code true}</em>
	 *
	 * @return flag indicating whether bad URLs should be blacklisted
	 */
	public boolean isDownloadBlacklistingEnabled() {
		return downloadBlacklistingEnabled;
	}

	/**
	 * Configures whether URLs of resources that resulted in a download failure (such as a 404) should be blacklisted
	 * while the parser is running, so no further attempts to access the same URL will be made. Enabled by default to improve
	 * speed when fetching resources of multiple pages, especially when link following is used.
	 *
	 * <em>Defaults to {@code true}</em>
	 *
	 * @param downloadBlacklistingEnabled flag indicating whether bad URLs should be blacklisted
	 */
	public void setDownloadBlacklistingEnabled(boolean downloadBlacklistingEnabled) {
		this.downloadBlacklistingEnabled = downloadBlacklistingEnabled;
	}

	/**
	 * Returns the minimum interval of time to wait between each download request. This is required to prevent
	 * submitting multiple requests to the same server at the same time.
	 *
	 * <em>Defaults to 5 ms</em>
	 *
	 * @return the minimum time (in milliseconds) to wait between download requests.
	 *         Values {@code <= 0} mean the internal {@link RateLimiter} is disabled.
	 */
	public final long getRemoteInterval() {
		return remoteInterval;
	}

	/**
	 * Defines the minimum interval of time to wait between each download request. This is required to prevent submitting
	 * multiple requests to the same server at the same time.
	 *
	 * <em>Defaults to 5 ms</em>
	 *
	 * @param remoteInterval minimum time (in milliseconds) to wait between download requests.
	 *                       Any value {@code <= 0} will disable the internal {@link RateLimiter}.
	 */
	public final void setRemoteInterval(long remoteInterval) {
		if (remoteInterval < 0L) {
			remoteInterval = 0L;
		}
		this.remoteInterval = remoteInterval;
	}

	/**
	 * Returns the maximum link following depth from the first page whose resources have been collected.
	 * The first HTML page has depth = 0. Any pages linked from that first page will have depth = 1,
	 * any pages linked from each one of these will have depth = 2 and so on. With a large enough depth
	 * setting, the fetch process can potentially save all contents from an entire website.
	 *
	 * Defaults to {@code 0}, i.e. saves only the resources available from a given URL without visiting linked
	 * pages.
	 *
	 * @return the maximum link following depth.
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Defines the maximum link following depth from the first page whose resources have been collected.
	 * The first HTML page has depth = 0. Any pages linked from that first page will have depth = 1,
	 * any pages linked from each one of these will have depth = 2 and so on. With a large enough depth
	 * setting, the fetch process can potentially save all contents from an entire website.
	 *
	 * Defaults to {@code 0}, i.e. saves only the resources available from a given URL without visiting linked
	 * pages.
	 *
	 * @param depth the maximum link following depth.
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	protected FetchOptions clone() {
		try {
			return (FetchOptions) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalStateException(e);
		}
	}
}
