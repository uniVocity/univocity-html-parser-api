package com.univocity.api.entity.html;

import com.univocity.api.io.*;

/**
 * Configuration class for use in the {@link HtmlElement#fetchResources} methods
 * Setters return `this` instance to enable method chaining during initialization.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class FetchOptions implements Cloneable{

	private boolean overwriteSharedResources = false;
	private FileProvider sharedResourceDir;
	private boolean flattenDirectoryStructure;
	private DownloadHandler downloadHandler;
	private long remoteInterval = 5L;
	private String baseUri;

	/**
	 * Default constructor for FetchOptions
	 * Defaults to not flattening directory and accepting any String
	 */
	public FetchOptions() {
		flattenDirectoryStructure = false;
	}

	public String getBaseUri() {
		return baseUri;
	}

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
	 * @param flatten whether to flatten the path of a resource into the saved name.
	 *
	 */
	public void flattenDirectory(boolean flatten) {
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
	 * @return whether or not the directory structure in filenames will be flattened when saving resources.
	 */
	public boolean flattenDirectoryStructure() {
		return flattenDirectoryStructure;
	}

	public DownloadHandler getDownloadHandler() {
		return downloadHandler;
	}

	public void setDownloadHandler(DownloadHandler downloadHandler) {
		this.downloadHandler = downloadHandler;
	}

	/**
	 * Returns the minimum interval of time to wait between each download request. This is required to prevent
	 * submitting multiple requests to the same server at the same time.
	 *
	 * <em>Defaults to 5 ms</em>
	 *
	 * @return the minimum time (in milliseconds) to wait between download requests.
	 *         Values {@link <= 0} mean the internal {@link RateLimiter} is disabled.
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
	 *                       Any value {@link <= 0} will disable the internal {@link RateLimiter}.
	 */
	public final void setRemoteInterval(long remoteInterval) {
		if (remoteInterval < 0L) {
			remoteInterval = 0L;
		}
		this.remoteInterval = remoteInterval;
	}

	public boolean isOverwriteSharedResources() {
		return overwriteSharedResources;
	}

	public void setOverwriteSharedResources(boolean overwriteSharedResources) {
		this.overwriteSharedResources = overwriteSharedResources;
	}

	public FileProvider getSharedResourceDir() {
		return sharedResourceDir;
	}

	public void setSharedResourceDir(String sharedResourceDir) {
		this.sharedResourceDir = new FileProvider(sharedResourceDir);
	}

	@Override
	protected FetchOptions clone() {
		try {
			return (FetchOptions) super.clone();
		} catch(CloneNotSupportedException e){
			throw new IllegalStateException(e);
		}
	}
}
