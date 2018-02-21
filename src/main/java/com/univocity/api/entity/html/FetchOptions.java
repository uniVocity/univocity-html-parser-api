package com.univocity.api.entity.html;

import com.univocity.api.common.*;

/**
 * Configuration class for use in the {@link HtmlElement#fetchResources} methods
 * Setters return `this` instance to enable method chaining during initialization.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public class FetchOptions {

	private boolean flattenDirectoryStructure;

	private StringFilter fileFilter;

	/**
	 * Default constructor for FetchOptions
	 * Defaults to not flattening directory and accepting any String
	 */
	public FetchOptions() {
		flattenDirectoryStructure = false;
		fileFilter = TrueStringFilter.TRUE;
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
	 * @return current instance of {@link FetchOptions} to enable method chaining during initialization.
	 */
	public FetchOptions flattenDirectory(boolean flatten) {
		this.flattenDirectoryStructure = flatten;
		return this;
	}

	/**
	 * Only download resources that match a {@link StringFilter}. The default filter accepts any input.
	 *
	 * @param fileFilter used to filter which resources to download
	 *
	 * @return current instance of {@link FetchOptions} to enable method chaining during initialization.
	 */
	public FetchOptions filterFiles(StringFilter fileFilter) {
		this.fileFilter = fileFilter;
		return this;
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
	public boolean isFlattenDirectoryStructure() {
		return flattenDirectoryStructure;
	}

	/**
	 * Returns the currently set {@link StringFilter} which is used to determine which resource files to download and save.
	 * The default {@link StringFilter} accepts any `String` so all resources will be downloaded.
	 *
	 * @return the {@link StringFilter} used to filter resource URLs.
	 */
	public StringFilter getFileFilter() {
		return fileFilter;
	}
}
