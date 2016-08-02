package com.univocity.api.entity.html;

import com.univocity.api.common.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.fields.*;
import com.univocity.parsers.common.processor.core.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

/**
 * Created by anthony on 26/07/16.
 */
public class HtmlParserSettings {

	private final InternalHtmlParserSettings settings;

	public HtmlParserSettings(HtmlEntityList entityList) {
		settings = new InternalHtmlParserSettings(entityList);
	}

	public InternalHtmlParserSettings getInternalSettings() {
		return settings;
	}

	/**
	 * Sets the associated {@link HtmlParserListener} that is used when the {@link HtmlParser} parses.
	 * @param listener the HtmlParserListener that the settings will be associated with
	 */
	public void setListener(HtmlParserListener listener) {
		settings.setListener(listener);
	}

	/**
	 * Returns the associated {@link HtmlParserListener}
	 * @return the HtmlParserListener associated with the settings
	 */
	public HtmlParserListener getListener() {
		return  settings.getListener();
	}


	final void setCurrentEntity(String entityName){
		settings.setCurrentEntity(entityName);
	}


	/**
	 * Returns the {@link HtmlEntityList} associated with the settings.
	 *
	 * @return the HtmlEntityList
	 */
	public HtmlEntityList getEntityList() {
		return settings.getEntityList();
	}

	/**
	 * Sets the global {@link Processor}. All rows parsed will be delegated to the processor. Does not
	 * need to be set for the {@link HtmlParser} to function.
	 *
	 * @param processor the {@link Processor} that will be set as the processor
	 */
	public void setGlobalProcessor(Processor<HtmlParsingContext> processor) {
		settings.setGlobalProcessor(processor);
	}

	/**
	 * Returns the processor if previously set. If the processor was not set, returns a
	 * {@link NoopProcessor}, which is a {@link Processor} that does nothing.
	 *
	 * @return the {@link Processor} previously set, or a {@link NoopProcessor} if never set.
	 */
	public Processor<HtmlParsingContext> getGlobalProcessor() {
		return settings.getGlobalProcessor();
	}


	/**
	 * Returns the entity names contained in the associated {@link HtmlEntityList} as a set of Strings.
	 * @return the entity names
	 */
	public Set<String> getEntityNames() {
		return settings.getEntityNames();
	}

	/**
	 * Sets the number of threads that will be created to download content (e.g. images) after a page finishes parsing.
	 * If not set or set to a number <= 0, a thread will be created for each logical core on the running PC.
	 *
	 * @param downloadThreads the number of download threads that will be used to download content
	 */
	public void setDownloadThreads(int downloadThreads) {
		settings.setDownloadThreads(downloadThreads);
	}

	/**
	 * Returns the amount of threads that the parser will use to download content
	 *
	 * @return number of download threads set
	 */
	public int getDownloadThreads() {
		return settings.getDownloadThreads();
	}

	/**
	 * Sets the file directory where downloaded content will be stored using the system default encoding
	 *
	 * @param fileName the directory that stores downloaded content as a String
	 */
	public void setDownloadContentDirectory(String fileName) {
		settings.setDownloadContentDirectory(fileName);
	}

	/**
	 * Sets the file directory where downloaded content will be stored
	 *
	 * @param fileName the directory that stores downloaded content as a String
	 * @param encoding the encoding of the directory as a Charset
	 */
	public void setDownloadContentDirectory(String fileName, Charset encoding) {
		settings.setDownloadContentDirectory(fileName,encoding);
	}

	/**
	 * Sets the file directory where downloaded content will be stored
	 *
	 * @param fileName the directory that stores downloaded content as a String
	 * @param encoding the encoding of the directory as a String
	 */
	public void setDownloadContentDirectory(String fileName, String encoding) {
		settings.setDownloadContentDirectory(fileName,encoding);
	}

	/**
	 * Sets the file directory where downloaded content will be stored and use the default system encoding.
	 *
	 * @param file the directory that stores downloaded content
	 */
	public void setDownloadContentDirectory(File file) {
		settings.setDownloadContentDirectory(file);
	}

	/**
	 * Sets the file directory where downloaded content will be stored
	 *
	 * @param file the directory that stores downloaded content
	 * @param encoding the encoding the directory as a Charset
	 */
	public void setDownloadContentDirectory(File file, Charset encoding) {
		settings.setDownloadContentDirectory(file,encoding);
	}

	/**
	 * Sets the file directory where downloaded content will be stored
	 *
	 * @param file The directory that stores downloaded content
	 * @param encoding the encoding of the directory as a String
	 */
	public void setDownloadContentDirectory(File file, String encoding) {
		settings.setDownloadContentDirectory(file,encoding);
	}

	/**
	 * Returns the file directory where downloaded content is stored in
	 *
	 * @return a FileProvider which contains the download content directory
	 */
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
	public void setFileNamePattern(String pattern) {
		settings.setFileNamePattern(pattern);
	}

	/**
	 * Returns the file name pattern used for names when saving pages
	 * @return the pattern of file names
	 */
	public String getFileNamePattern() {
		return settings.getFileNamePattern();
	}

	public final void setEntityProcessor(Processor<HtmlParsingContext> entityProcessor, Collection<String> entities) {
		settings.setEntityProcessor(entityProcessor, entities);
	}

	public final void setEntityProcessor(Processor<HtmlParsingContext> entityProcessor, String... entities) {
		settings.setEntityProcessor(entityProcessor,entities);
	}

	public final EntityFieldSet<Enum> selectFields(Enum... fieldNames) {
		return settings.selectFields(fieldNames);
	}


	/**
	 * Selects a sequence of fields for reading/writing by their names
	 *
	 * @param fieldNames The field names to read/write
	 *
	 * @return the (modifiable) set of selected fields
	 */
	public final EntityFieldSet<String> selectFields(String... fieldNames) {
		return settings.selectFields(fieldNames);
	}
	/**
	 * Selects fields which will not be read/written by their names
	 *
	 * @param fieldNames The field names to exclude from the parsing/writing process
	 *
	 * @return the (modifiable) set of ignored fields
	 */
	public final EntityFieldSet<String> excludeFields(String... fieldNames) {
		return settings.excludeFields(fieldNames);
	}

	/**
	 * Selects a sequence of fields for reading/writing by their indexes
	 *
	 * @param fieldIndexes The field indexes to read/write
	 *
	 * @return the (modifiable) set of selected fields
	 */
	public final EntityFieldSet<Integer> selectIndexes(Integer... fieldIndexes) {
		return settings.selectIndexes(fieldIndexes);
	}

	/**
	 * Selects fields which will not be read/written by their indexes
	 *
	 * @param fieldIndexes The field indexes to exclude from the parsing/writing process
	 *
	 * @return the (modifiable) set of ignored fields
	 */
	public final EntityFieldSet<Integer> excludeIndexes(Integer... fieldIndexes) {
		return settings.excludeIndexes(fieldIndexes);
	}

	public final EntityFieldSet<Enum> excludeFields(Enum... fieldNames) {
		return settings.excludeFields(fieldNames);
	}
	/**
	 * Returns the String representation of a null value (defaults to null)
	 * <p>When reading, if the parser does not read any character from the input, the nullValue is used instead of an empty string
	 * <p>When writing, if the writer has a null object to write to the output, the nullValue is used instead of an empty string
	 *
	 * @return the String representation of a null value
	 */
	public String getNullValue() {
		return settings.getNullValue();
	}

	/**
	 * Sets the String representation of a null value (defaults to null)
	 * <p>When reading, if the parser does not read any character from the input, the nullValue is used instead of an empty string
	 * <p>When writing, if the writer has a null object to write to the output, the nullValue is used instead of an empty string
	 *
	 * @param emptyValue the String representation of a null value
	 */
	public void setNullValue(String emptyValue) {
		settings.setNullValue(emptyValue);
	}
	/**
	 * Returns whether or not trailing whitespaces from values being read/written should be skipped  (defaults to true)
	 *
	 * @return true if trailing whitespaces from values being read/written should be skipped, false otherwise
	 */
	public boolean getIgnoreTrailingWhitespaces() {
		return settings.getIgnoreTrailingWhitespaces();
	}

	/**
	 * Defines whether or not trailing whitespaces from values being read/written should be skipped  (defaults to true)
	 *
	 * @param ignoreTrailingWhitespaces true if trailing whitespaces from values being read/written should be skipped, false otherwise
	 */
	public void setIgnoreTrailingWhitespaces(boolean ignoreTrailingWhitespaces) {
		settings.setIgnoreTrailingWhitespaces(ignoreTrailingWhitespaces);
	}

	/**
	 * Returns whether or not leading whitespaces from values being read/written should be skipped  (defaults to true)
	 *
	 * @return true if leading whitespaces from values being read/written should be skipped, false otherwise
	 */
	public boolean getIgnoreLeadingWhitespaces() {
		return settings.getIgnoreLeadingWhitespaces();
	}

	/**
	 * Defines whether or not leading whitespaces from values being read/written should be skipped  (defaults to true)
	 *
	 * @param ignoreLeadingWhitespaces true if leading whitespaces from values being read/written should be skipped, false otherwise
	 */
	public void setIgnoreLeadingWhitespaces(boolean ignoreLeadingWhitespaces) {
		settings.setIgnoreLeadingWhitespaces(ignoreLeadingWhitespaces);
	}

	/**
	 * Configures the parser/writer to trim or keep leading and trailing whitespaces around values
	 * This has the same effect as invoking both {@link #setIgnoreLeadingWhitespaces(boolean)} and {@link #setIgnoreTrailingWhitespaces(boolean)}
	 * with the same value.
	 *
	 * @param trim a flag indicating whether the whitespaces should remove whitespaces around values parsed/written.
	 */
	public final void trimValues(boolean trim) {
		settings.trimValues(trim);
	}

	/**
	 * Configures the parser/writer to limit the length of displayed contents being parsed/written in the exception message when an error occurs
	 *
	 * <p>If set to {@code 0}, then no exceptions will include the content being manipulated in their attributes,
	 * and the {@code "<omitted>"} string will appear in error messages as the parsed/written content.</p>
	 *
	 * <p>defaults to {@code -1} (no limit)</p>.
	 *
	 * @return the maximum length of contents displayed in exception messages in case of errors while parsing/writing.
	 */
	public int getErrorContentLength() {
		return settings.getErrorContentLength();
	}

	/**
	 * Configures the parser/writer to limit the length of displayed contents being parsed/written in the exception message when an error occurs.
	 *
	 * <p>If set to {@code 0}, then no exceptions will include the content being manipulated in their attributes,
	 * and the {@code "<omitted>"} string will appear in error messages as the parsed/written content.</p>
	 *
	 * <p>defaults to {@code -1} (no limit)</p>.
	 *
	 * @param errorContentLength maximum length of contents displayed in exception messages in case of errors while parsing/writing.
	 */
	public void setErrorContentLength(int errorContentLength) {
		settings.setErrorContentLength(errorContentLength);
	}

	public final void addEntitiesToRead(Collection<String> entitiesToRead) {
		settings.addEntitiesToRead(entitiesToRead);
	}

	public final void addEntitiesToRead(String... entitiesToRead) {
		settings.addEntitiesToRead(entitiesToRead);
	}

	public final void addEntitiesToSkip(Collection<String> entitiesToSkip) {
		settings.addEntitiesToRead(entitiesToSkip);
	}

	public final void setEntitiesToSkip(Collection<String> entitiesToSkip) {
		settings.addEntitiesToRead(entitiesToSkip);
	}

	public final Set<String> getEntitiesToRead() {
		return settings.getEntitiesToRead();
	}

	public final void setEntitiesToRead(Collection<String> entitiesToRead) {
		settings.setEntitiesToRead(entitiesToRead);
	}

	public final void setEntitiesToRead(String... entitiesToRead) {
		settings.setEntitiesToRead(entitiesToRead);
	}

	public final Set<String> getEntitiesToSkip() {
		return settings.getEntitiesToSkip();
	}

	public final void setEntitiesToSkip(String... entitiesToSkip) {
		settings.setEntitiesToSkip(entitiesToSkip);
	}

	public final void addEntitiesToSkip(String... entitiesToSkip) {
		settings.addEntitiesToSkip(entitiesToSkip);
	}

	public void setColumnReorderingEnabled(boolean columnReorderingEnabled) {
		settings.setColumnReorderingEnabled(columnReorderingEnabled);
	}

	/**
	 * Returns the custom error handler to be used to capture and handle errors that might happen while processing records with a {@link com.univocity.parsers.common.processor.core.Processor}
	 * or a {@link RowWriterProcessor} (i.e. non-fatal {@link DataProcessingException}s).
	 *
	 * <p>The parsing/writing process won't stop (unless the error handler rethrows the {@link DataProcessingException} or manually stops the process).</p>
	 *
	 * @return the callback error handler with custom code to manage occurrences of {@link DataProcessingException}.
	 */
	public  ProcessorErrorHandler<HtmlParsingContext> getProcessorErrorHandler() {
		return  settings.getProcessorErrorHandler();
	}

	/**
	 * Defines a custom error handler to capture and handle errors that might happen while processing records with a {@link com.univocity.parsers.common.processor.core.Processor}
	 * or a {@link RowWriterProcessor} (i.e. non-fatal {@link DataProcessingException}s).
	 *
	 * <p>The parsing parsing/writing won't stop (unless the error handler rethrows the {@link DataProcessingException} or manually stops the process).</p>
	 *
	 * @param processorErrorHandler the callback error handler with custom code to manage occurrences of {@link DataProcessingException}.
	 */
	public void setProcessorErrorHandler(ProcessorErrorHandler<HtmlParsingContext> processorErrorHandler) {
		settings.setProcessorErrorHandler(processorErrorHandler);
	}
}
