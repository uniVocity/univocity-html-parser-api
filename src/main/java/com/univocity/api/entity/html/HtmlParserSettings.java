package com.univocity.api.entity.html;

import com.univocity.api.common.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.remote.*;

/**
 * The configuration class for {@link HtmlParser}.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 * @see HtmlEntityList
 */
public class HtmlParserSettings extends RemoteParserSettings<CommonParserSettings, HtmlEntityList> {

	private int downloadThreads = Runtime.getRuntime().availableProcessors();
	private int threadCount = Runtime.getRuntime().availableProcessors();
	private HtmlParserListener listener;

	/**
	 * Creates a new HtmlParserSettings and associates it with {@link HtmlEntityList}
	 *
	 * @param entityList the {@link HtmlEntityList} that will be associated
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
	public int getThreadCount() {
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
	public void setThreadCount(int threadCount) {
		if (threadCount <= 0) {
			threadCount = 1;
		}
		this.threadCount = threadCount;
	}

	public int getErrorContentLength() {
		return globalSettings.getErrorContentLength();
	}

	public void setErrorContentLength(int errorContentLength) {
		globalSettings.setErrorContentLength(errorContentLength);
	}

	public String getNullValue() {
		return globalSettings.getNullValue();
	}

	public void setNullValue(String nullValue) {
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
	 * Returns the {@link HtmlPaginator} associated with the HtmlEntityList
	 *
	 * @return the {@link HtmlPaginator} stored within the HtmlEntityList
	 */
	public HtmlPaginator getPaginator() {
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
	public void setDownloadThreads(int downloadThreads) {
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
	public int getDownloadThreads() {
		return downloadThreads;
	}

	/**
	 * Sets the associated {@link HtmlParserListener} that is used when the {@link HtmlParser} parses.
	 *
	 * @param listener the HtmlParserListener that the settings will be associated with
	 */
	public void setListener(HtmlParserListener listener) {
		this.listener = listener;
	}

	/**
	 * Returns the associated {@link HtmlParserListener}
	 *
	 * @return the HtmlParserListener associated with the settings
	 */
	public HtmlParserListener getListener() {
		return listener;
	}

	public ProcessorErrorHandler getProcessorErrorHandler() {
		return globalSettings.getProcessorErrorHandler();
	}

	public void setProcessorErrorHandler(ProcessorErrorHandler processorErrorHandler) {
		globalSettings.setProcessorErrorHandler(processorErrorHandler);
	}

	public boolean isTrimTrailingWhitespaces() {
		return globalSettings.getIgnoreTrailingWhitespaces();
	}

	public void setTrimTrailingWhitespaces(boolean ignoreTrailingWhitespaces) {
		globalSettings.setIgnoreTrailingWhitespaces(ignoreTrailingWhitespaces);
	}

	public boolean isTrimLeadingWhitespaces() {
		return globalSettings.getIgnoreLeadingWhitespaces();
	}

	public void setTrimLeadingWhitespaces(boolean ignoreLeadingWhitespaces) {
		globalSettings.setIgnoreLeadingWhitespaces(ignoreLeadingWhitespaces);
	}

	public void trimValues(boolean trim) {
		globalSettings.trimValues(trim);
	}

}
