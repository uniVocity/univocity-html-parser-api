package com.univocity.api.entity.html;

import com.univocity.api.common.*;

/**
 * Configuration class for use in the {@link HtmlElement#fetchResources} methods
 * Setters return {@code this} instance to enable method chaining during initialization.
 */
public class FetchOptions {

	private boolean flattenDirectoryStructure;

	private StringFilter fileFilter;

	public FetchOptions() {
		flattenDirectoryStructure = false;
		fileFilter = TrueStringFilter.TRUE;
	}

	/**
	 * Option to flatten the path section of a fetched resource into the new filename.
	 * <p>
	 * A file with the relative path
	 * {@code
	 * ./path/to/resource/image.png
	 * }
	 * would normally be saved as a file called
	 * {@code image.png}
	 * in the
	 * {@code ./path/to/resource/}
	 * directory.
	 * </p>
	 * <p>
	 * When flattened it will instead be saved as the file
	 * {@code path_to_resource_image.png}
	 * in the
	 * {@code ./}
	 * directory
	 * </p>
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
	 * <p>Only download resources that match the {@link StringFilter}.</p>
	 * <p>The default filter accepts any input.</p>
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
	 * @return {@link FetchOptions#flattenDirectory}
	 */
	public boolean isFlattenDirectoryStructure() {
		return flattenDirectoryStructure;
	}

	/**
	 * @return {@link FetchOptions#filterFiles}
	 */
	public StringFilter getFileFilter() {
		return fileFilter;
	}
}
