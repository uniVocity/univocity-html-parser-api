package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.common.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.core.*;
import com.univocity.parsers.remote.*;

import java.io.*;
import java.nio.charset.*;

/**
 * The configuration class for {@link HtmlParser}.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 * @see HtmlEntityList
 */
public class HtmlParserSettings extends RemoteParserSettings<CommonParserSettings, HtmlEntityList> implements HtmlParserSettingsInterface {

	final HtmlParserSettingsInterface settings;

	/**
	 * Creates a new HtmlParserSettings and associates it with {@link HtmlEntityList}
	 *
	 * @param entityList the {@link HtmlEntityList} that will be associated
	 */
	public HtmlParserSettings(HtmlEntityList entityList) {
		super(entityList);
		settings = Builder.build(HtmlParserSettingsInterface.class, entityList);
	}

	/**
	 * Sets the number of threads that will be created to download content (e.g. images) after a page finishes parsing.
	 * If not set or set to a number <= 0, a thread will be created for each logical core on the running PC.
	 *
	 * @param downloadThreads the number of download threads that will be used to download content
	 */
	@Override
	public void setDownloadThreads(int downloadThreads) {
		settings.setDownloadThreads(downloadThreads);
	}

	/**
	 * Returns the amount of threads that the parser will use to download content
	 *
	 * @return number of download threads set
	 */
	@Override
	public int getDownloadThreads() {
		return settings.getDownloadThreads();
	}

	/**
	 * Sets the file directory where downloaded content will be stored using the system default encoding
	 *
	 * @param fileName the directory that stores downloaded content as a String
	 */
	@Override
	public void setDownloadContentDirectory(String fileName) {
		settings.setDownloadContentDirectory(fileName);
	}

	/**
	 * Sets the file directory where downloaded content will be stored
	 *
	 * @param fileName the directory that stores downloaded content as a String
	 * @param encoding the encoding of the directory as a Charset
	 */
	@Override
	public void setDownloadContentDirectory(String fileName, Charset encoding) {
		settings.setDownloadContentDirectory(fileName, encoding);
	}

	/**
	 * Sets the file directory where downloaded content will be stored
	 *
	 * @param fileName the directory that stores downloaded content as a String
	 * @param encoding the encoding of the directory as a String
	 */
	@Override
	public void setDownloadContentDirectory(String fileName, String encoding) {
		settings.setDownloadContentDirectory(fileName, encoding);
	}

	/**
	 * Sets the file directory where downloaded content will be stored and use the default system encoding.
	 *
	 * @param file the directory that stores downloaded content
	 */
	@Override
	public void setDownloadContentDirectory(File file) {
		settings.setDownloadContentDirectory(file);
	}

	/**
	 * Sets the file directory where downloaded content will be stored
	 *
	 * @param file     the directory that stores downloaded content
	 * @param encoding the encoding the directory as a Charset
	 */
	@Override
	public void setDownloadContentDirectory(File file, Charset encoding) {
		settings.setDownloadContentDirectory(file, encoding);
	}

	/**
	 * Sets the file directory where downloaded content will be stored
	 *
	 * @param file     The directory that stores downloaded content
	 * @param encoding the encoding of the directory as a String
	 */
	@Override
	public void setDownloadContentDirectory(File file, String encoding) {
		settings.setDownloadContentDirectory(file, encoding);
	}

	/**
	 * Returns the file directory where downloaded content is stored in
	 *
	 * @return a FileProvider which contains the download content directory
	 */
	@Override
	public FileProvider getDownloadContentDirectory() {
		return settings.getDownloadContentDirectory();
	}

	/**
	 * The pattern that the names of pages downloaded will follow. For example, setting the pattern as
	 * "/search/page{pageNumber}" will make pages stored in the search folder with the name "page1.html", "page2.html"
	 * etc. Note: The html extension is automatically added and does not need to be specified.
	 *
	 * @param pattern the pattern of file names
	 */
	@Override
	public void setFileNamePattern(String pattern) {
		settings.setFileNamePattern(pattern);
	}

	/**
	 * Returns the file name pattern used for names when saving pages
	 *
	 * @return the pattern of file names
	 */
	@Override
	public String getFileNamePattern() {
		return settings.getFileNamePattern();
	}

	/**
	 * Returns whether or not trailing whitespaces from values being read should be trimmed  (defaults to true)
	 *
	 * @return true if trailing whitespaces from values being read should be trimmed, false otherwise
	 */
	@Override
	public boolean isTrimTrailingWhitespaces() {
		return settings.isTrimTrailingWhitespaces();
	}

	/**
	 * Defines whether or not trailing whitespaces from values being read should be trimmed  (defaults to true)
	 *
	 * @param ignoreTrailingWhitespaces true if trailing whitespaces from values being read should be trimmed, false otherwise
	 */
	@Override
	public void setTrimTrailingWhitespaces(boolean ignoreTrailingWhitespaces) {
		settings.setTrimTrailingWhitespaces(ignoreTrailingWhitespaces);
	}

	/**
	 * Returns whether or not leading whitespaces from values being read should be trimmed(defaults to true)
	 *
	 * @return true if leading whitespaces from values being read should be trimmed, false otherwise
	 */
	@Override
	public boolean isTrimLeadingWhitespaces() {
		return settings.isTrimLeadingWhitespaces();
	}

	/**
	 * Defines whether or not leading whitespaces from values being read should be trimmed(defaults to true)
	 *
	 * @param ignoreLeadingWhitespaces true if leading whitespaces from values being read should be trimmed, false otherwise
	 */
	@Override
	public void setTrimLeadingWhitespaces(boolean ignoreLeadingWhitespaces) {
		settings.setTrimLeadingWhitespaces(ignoreLeadingWhitespaces);
	}

	/**
	 * Configures the parser to trim or keep leading and trailing whitespaces around values
	 * This has the same effect as invoking both {@link #setTrimLeadingWhitespaces(boolean)} and {@link #setTrimTrailingWhitespaces(boolean)}
	 * with the same value.
	 *
	 * @param trim a flag indicating whether the whitespaces should remove whitespaces around values parsed/written.
	 */
	@Override
	public final void trimValues(boolean trim) {
		settings.trimValues(trim);
	}

	/**
	 * Defines whether fields selected using the field selection methods (defined by the parent class {@link CommonSettings}) should be reordered (defaults to true).
	 * <p>When disabled, each parsed record will contain values for all columns, in the order they occur in the input. Fields which were not selected will not be parsed but and the record will contain empty values.
	 * <p>When enabled, each parsed record will contain values only for the selected columns. The values will be ordered according to the selection.
	 *
	 * @param columnReorderingEnabled the flag indicating whether or not selected fields should be reordered and returned by the parser
	 */
	@Override
	public void setColumnReorderingEnabled(boolean columnReorderingEnabled) {
		settings.setColumnReorderingEnabled(columnReorderingEnabled);
	}

	/**
	 * Returns the custom error handler to be used to capture and handle errors that might happen while processing records with a {@link Processor}
	 * (i.e. non-fatal {@link DataProcessingException}s).
	 *
	 * <p>The parsing/writing process won't stop (unless the error handler rethrows the {@link DataProcessingException} or manually stops the process).</p>
	 *
	 * @return the callback error handler with custom code to manage occurrences of {@link DataProcessingException}.
	 */
	@Override
	public ProcessorErrorHandler<HtmlParsingContext> getProcessorErrorHandler() {
		return settings.getProcessorErrorHandler();
	}

	/**
	 * Defines a custom error handler to capture and handle errors that might happen while processing records with a {@link Processor}
	 * (i.e. non-fatal {@link DataProcessingException}s).
	 *
	 * <p>The parsing parsing/writing won't stop (unless the error handler rethrows the {@link DataProcessingException} or manually stops the process).</p>
	 *
	 * @param processorErrorHandler the callback error handler with custom code to manage occurrences of {@link DataProcessingException}.
	 */
	@Override
	public void setProcessorErrorHandler(ProcessorErrorHandler<HtmlParsingContext> processorErrorHandler) {
		settings.setProcessorErrorHandler(processorErrorHandler);
	}


	/**
	 * Returns the String representation of an empty value (defaults to null)
	 *
	 * <p>When reading, if the parser does not read any character from the input, for example getting the text from
	 * &lt;td>&lt;/td>, the empty value is used instead of an empty string </p>
	 *
	 * @return the String representation of an empty value
	 */
	@Override
	public String getEmptyValue() {
		return settings.getEmptyValue();
	}

	/**
	 * Sets the String representation of an empty value (defaults to null)
	 *
	 * <p>When reading, if the parser does not read any character from the input, for example getting the text from
	 * &lt;td>&lt;/td>, the empty value is used instead of an empty string. </p>
	 *
	 * @param emptyValue the String representation of an empty value
	 */
	@Override
	public void setEmptyValue(String emptyValue) {
		settings.setEmptyValue(emptyValue);
	}


	/**
	 * Returns the maximum number of threads used by the parser when processing data of multiple entities from
	 * the same HTML input.
	 *
	 * <p>Defaults to the number of available processors available to the JVM</p>
	 *
	 * @return the number of threads used by the parser.
	 */
	@Override
	public int getThreadCount() {
		return settings.getThreadCount();
	}

	/**
	 * Explicitly defines a maximum number of threads that should be used by the parser when processing data of
	 * multiple entities from the same HTML input.
	 *
	 * <p>By default, to the number of available processors available to the JVM will be used</p>
	 *
	 * @param threadCount the maximum number of threads to use
	 */
	@Override
	public void setThreadCount(int threadCount) {
		settings.setThreadCount(threadCount);
	}

	@Override
	public int getErrorContentLength() {
		return settings.getErrorContentLength();
	}

	@Override
	public void setErrorContentLength(int errorContentLength) {
		settings.setErrorContentLength(errorContentLength);
	}

	@Override
	public String getNullValue() {
		return settings.getNullValue();
	}

	@Override
	public void setNullValue(String nullValue) {
		settings.setNullValue(nullValue);
	}

	@Override
	protected CommonParserSettings createGlobalSettings() {
		return createEmptyGlobalSettings();
	}


	/**
	 * Creates a new {@link HtmlPaginator} and returns it. Used by {@link #configurePaginator()}.
	 *
	 * @return the {@link HtmlPaginator} that was created
	 */
	@Override
	protected HtmlPaginator newPaginator() {
		return new HtmlPaginator();
	}

	/**
	 * Returns the {@link HtmlPaginator} associated with the HtmlEntityList
	 *
	 * @return the {@link HtmlPaginator} stored within the HtmlEntityList
	 */
	public HtmlPaginator getPaginator() {
		return (HtmlPaginator) paginator;
	}

	/**
	 * Creates a new {@link HtmlLinkFollower} and returns it. Used by {@link RemoteEntityList#configureEntity(String)}
	 *
	 * @return the created {@link HtmlLinkFollower}
	 */
	@Override
	protected HtmlLinkFollower newLinkFollower() {
		return new HtmlLinkFollower();
	}

	/**
	 * Returns the {@link HtmlLinkFollower} associated with the HtmlEntityList.
	 *
	 * @return the {@link HtmlLinkFollower} contained by the HtmlEntityList
	 */
	public HtmlLinkFollower getLinkFollower() {
		return (HtmlLinkFollower) linkFollower;
	}

	/**
	 * Returns the associated {@link HtmlPaginator}, creating it if it does not exist already. {@link HtmlPaginator}s are
	 * used to define how the parser loads the next page to parse.
	 *
	 * @return the {@link HtmlPaginator} associated with the HtmlEntityList
	 */
	public HtmlPaginator configurePaginator() {
		if (paginator == null) {
			paginator = newPaginator();
		}
		return (HtmlPaginator) paginator;
	}

	/**
	 * Returns the associated {@link HtmlLinkFollower}, creating it if it does not exist already. {@link HtmlLinkFollower}s
	 * are used to define links that the parser will follow and parse. A use case for this is when parsing a list of
	 * products on a store page.
	 *
	 * @return the {@link HtmlLinkFollower} associated with the HtmlEntityList
	 */
	public HtmlLinkFollower configureLinkFollower() {
		if (linkFollower == null) {
			linkFollower = newLinkFollower();
		}
		return (HtmlLinkFollower) linkFollower;
	}
}
