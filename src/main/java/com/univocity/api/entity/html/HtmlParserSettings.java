package com.univocity.api.entity.html;

import com.univocity.api.common.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.remote.*;

/**
 * Configuration class for the {@link HtmlParser}. Properties that also exist in {@link HtmlEntitySettings} are global
 * and will be used by each entity configuration by default. Individual {@link HtmlEntitySettings} can have their own
 * specific configuration modified to override its defaults.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 * @see HtmlEntityList
 */
public final class HtmlParserSettings extends RemoteParserSettings<CommonParserSettings, HtmlEntityList> {

	private int downloadThreads = Runtime.getRuntime().availableProcessors();
	private int threadCount = Runtime.getRuntime().availableProcessors();
	private HtmlParserListener listener;

	/**
	 * Creates a new {@code HtmlParserSettings} and associates it with a {@link HtmlEntityList}.
	 *
	 * @param entityList the {@link HtmlEntityList} that will be associated with the parser
	 */
	public HtmlParserSettings(HtmlEntityList entityList) {
		super(entityList);
	}

	/**
	 * Returns the maximum number of threads used by the parser when processing data of multiple entities from
	 * the same HTML input.
	 *
	 * <p>Defaults to the number of available processors available to the JVM</p>
	 *
	 * @return the number of threads used by the parser.
	 */
	public final int getThreadCount() {
		return threadCount <= 0 ? 1 : threadCount;
	}

	/**
	 * Explicitly defines a maximum number of threads that should be used by the parser when processing data of
	 * multiple entities from the same HTML input.
	 *
	 * <p>By default, to the number of available processors available to the JVM will be used</p>
	 *
	 * @param threadCount the maximum number of threads to use
	 */
	public final void setThreadCount(int threadCount) {
		if (threadCount <= 0) {
			threadCount = 1;
		}
		this.threadCount = threadCount;
	}

	/**
	 * Configures the parser to limit the length of displayed contents being processed in the exception message when an error occurs
	 *
	 * <p>If set to {@code 0}, then no exceptions will include the content being manipulated in their attributes,
	 * and the {@code "<omitted>"} string will appear in error messages as the parsed content.</p>
	 *
	 * <p>defaults to {@code -1} (no limit)</p>.
	 *
	 * @return the maximum length of contents displayed in exception messages in case of errors while parsing.
	 */
	public final int getErrorContentLength() {
		return globalSettings.getErrorContentLength();
	}

	/**
	 * Configures the parser to limit the length of displayed contents being processed in the exception message when an error occurs
	 *
	 * <p>If set to {@code 0}, then no exceptions will include the content being manipulated in their attributes,
	 * and the {@code "<omitted>"} string will appear in error messages as the parsed content.</p>
	 *
	 * <p>defaults to {@code -1} (no limit)</p>.
	 *
	 * @param errorContentLength the maximum length of contents displayed in exception messages in case of errors while parsing.
	 */
	public final void setErrorContentLength(int errorContentLength) {
		globalSettings.setErrorContentLength(errorContentLength);
	}

	/**
	 * Returns the {@code String} representation of a null value (defaults to {@code null})
	 * <p>When reading, if the parser does not read any character from the input for a particular value, the nullValue
	 * is used instead of an empty {@code String}</p>
	 *
	 * @return the String representation of a null value
	 */
	public final String getNullValue() {
		return globalSettings.getNullValue();
	}

	/**
	 * Defines the {@code String} representation of a null value (defaults to {@code null})
	 * <p>When reading, if the parser does not read any character from the input for a particular value, the nullValue
	 * is used instead of an empty {@code String}</p>
	 *
	 * @param nullValue the String representation of a null value
	 */
	public final void setNullValue(String nullValue) {
		globalSettings.setNullValue(nullValue);
	}

	@Override
	protected CommonParserSettings createGlobalSettings() {
		return createEmptyGlobalSettings();
	}


	/**
	 * Creates a new {@link HtmlPaginator} and returns it. Used by {@link #getPaginator()}.
	 *
	 * @return the {@link HtmlPaginator} that was created
	 */
	@Override
	protected HtmlPaginator newPaginator() {
		return new HtmlPaginator();
	}

	/**
	 * Returns the {@link HtmlPaginator} associated with this {@code HtmlParserSettings}
	 *
	 * @return the {@link HtmlPaginator} stored within this {@code HtmlParserSettings}
	 */
	public final HtmlPaginator getPaginator() {
		return (HtmlPaginator) super.getPaginator();
	}

	/**
	 * Sets the number of threads that will be used to download remote content (e.g. images) that is associated with
	 * the parsed input
	 *
	 * <i>Defaults to the available number of processors given by {@link Runtime#availableProcessors()}</i>
	 *
	 * @param downloadThreads the maximum number of threads to be used for downloading content
	 */
	public final void setDownloadThreads(int downloadThreads) {
		Args.positive(downloadThreads, "Number of threads for content download");
		this.downloadThreads = downloadThreads;
	}

	/**
	 * Sets the number of threads that will be used to download remote content (e.g. images) that is associated with
	 * the parsed input
	 *
	 * <i>Defaults to the available number of processors given by {@link Runtime#availableProcessors()}</i>
	 *
	 * @return the maximum number of threads to be used for downloading content
	 */
	public final int getDownloadThreads() {
		return downloadThreads;
	}

	/**
	 * Returns the custom error handler to be used to capture and handle errors that might happen while processing
	 * records with a {@link com.univocity.parsers.common.processor.core.Processor}
	 * (i.e. non-fatal {@link DataProcessingException}s).
	 *
	 * <p>The parsing process won't stop (unless the error handler rethrows the {@link DataProcessingException}
	 * or manually stops the process).</p>
	 *
	 * @return the callback error handler with custom code to manage occurrences of {@link DataProcessingException}.
	 */
	public final ProcessorErrorHandler<HtmlParsingContext> getProcessorErrorHandler() {
		return globalSettings.getProcessorErrorHandler();
	}

	/**
	 * Defines a custom error handler to be used to capture and handle errors that might happen while processing
	 * records with a {@link com.univocity.parsers.common.processor.core.Processor}
	 * (i.e. non-fatal {@link DataProcessingException}s).
	 *
	 * <p>The parsing process won't stop (unless the error handler rethrows the {@link DataProcessingException}
	 * or manually stops the process).</p>
	 *
	 * @param processorErrorHandler the callback error handler with custom code to manage occurrences of {@link DataProcessingException}.
	 */
	public final void setProcessorErrorHandler(ProcessorErrorHandler<HtmlParsingContext> processorErrorHandler) {
		globalSettings.setProcessorErrorHandler(processorErrorHandler);
	}

	/**
	 * Returns whether or not trailing whitespaces from values being read should be trimmed (defaults to {@code true})
	 *
	 * @return {@code true} if trailing whitespaces from values being read should be trimmed, {@code false} otherwise
	 */
	public final boolean getTrimTrailingWhitespaces() {
		return globalSettings.getIgnoreTrailingWhitespaces();
	}

	/**
	 * Defines whether or not trailing whitespaces from values being read should be trimmed (defaults to {@code true})
	 *
	 * @param trimTrailingWhitespaces flag indicating whether to remove trailing whitespaces from values being read
	 */
	public final void setTrimTrailingWhitespaces(boolean trimTrailingWhitespaces) {
		globalSettings.setIgnoreTrailingWhitespaces(trimTrailingWhitespaces);
	}

	/**
	 * Returns whether or not leading whitespaces from values being read should be trimmed (defaults to {@code true})
	 *
	 * @return {@code true} if leading whitespaces from values being read should be trimmed, {@code false} otherwise
	 */
	public final boolean getTrimLeadingWhitespaces() {
		return globalSettings.getIgnoreLeadingWhitespaces();
	}

	/**
	 * Defines whether or not trailing whitespaces from values being read should be trimmed (defaults to {@code true})
	 *
	 * @param trimTrailingWhitespaces flag indicating whether to remove trailing whitespaces from values being read
	 */
	public final void setTrimLeadingWhitespaces(boolean trimTrailingWhitespaces) {
		globalSettings.setIgnoreLeadingWhitespaces(trimTrailingWhitespaces);
	}

	/**
	 * Configures the parser to trim/keep leading and trailing whitespaces around values
	 * This has the same effect as invoking both {@link #setTrimLeadingWhitespaces(boolean)}
	 * and {@link #setTrimTrailingWhitespaces(boolean)} with the same value.
	 *
	 * @param trim a flag indicating whether whitespaces should be removed around values parsed.
	 */
	public final void trimValues(boolean trim) {
		globalSettings.trimValues(trim);
	}
}
