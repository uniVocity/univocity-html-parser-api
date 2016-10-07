package com.univocity.api.entity.html;

import com.univocity.api.*;
import com.univocity.api.common.*;
import com.univocity.parsers.common.*;
import com.univocity.parsers.common.fields.*;
import com.univocity.parsers.common.processor.core.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

/**
 * The configuration class for {@link HtmlParser}.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 * @see HtmlParser
 * @see HtmlEntityList
 */
public class HtmlParserSettings implements HtmlParserSettingsInterface {

	final HtmlParserSettingsInterface settings;

	/**
	 * Creates a new HtmlParserSettings and associates it with {@link HtmlEntityList}
	 *
	 * @param entityList the {@link HtmlEntityList} that will be associated
	 */
	public HtmlParserSettings(HtmlEntityList entityList) {
		settings = Builder.build(HtmlParserSettingsInterface.class, entityList);
	}

	/**
	 * Returns the {@link HtmlEntityList} associated with the settings.
	 *
	 * @return the associated {@link HtmlEntityList}
	 */
	@Override
	public HtmlEntityList getEntityList() {
		return settings.getEntityList();
	}

	/**
	 * Returns the entity names contained in the associated {@link HtmlEntityList} as a set of Strings.
	 * @return the entity names
	 */
	@Override
	public Set<String> getEntityNames() {
		return settings.getEntityNames();
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
		settings.setDownloadContentDirectory(fileName,encoding);
	}

	/**
	 * Sets the file directory where downloaded content will be stored
	 *
	 * @param fileName the directory that stores downloaded content as a String
	 * @param encoding the encoding of the directory as a String
	 */
	@Override
	public void setDownloadContentDirectory(String fileName, String encoding) {
		settings.setDownloadContentDirectory(fileName,encoding);
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
	 * @param file the directory that stores downloaded content
	 * @param encoding the encoding the directory as a Charset
	 */
	@Override
	public void setDownloadContentDirectory(File file, Charset encoding) {
		settings.setDownloadContentDirectory(file,encoding);
	}

	/**
	 * Sets the file directory where downloaded content will be stored
	 *
	 * @param file The directory that stores downloaded content
	 * @param encoding the encoding of the directory as a String
	 */
	@Override
	public void setDownloadContentDirectory(File file, String encoding) {
		settings.setDownloadContentDirectory(file,encoding);
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
	 * @return the pattern of file names
	 */
	@Override
	public String getFileNamePattern() {
		return settings.getFileNamePattern();
	}


	/**
	 * Associates the given {@link Processor} with the supplied entities.
	 *
	 * @param entityProcessor  the {@link Processor}
	 * @param entities names of entities that will be handled by the {@link Processor}
	 */
	@Override
	public final void setEntityProcessor(Processor<HtmlParsingContext> entityProcessor, Collection<String> entities) {
		settings.setEntityProcessor(entityProcessor, entities);
	}

	/**
	 * Associates the given {@link Processor} with the supplied entities.
	 *
	 * @param entityProcessor  the {@link Processor}
	 * @param entities names of entities that will be handled by the {@link Processor}
	 */
	@Override
	public final void setEntityProcessor(Processor<HtmlParsingContext> entityProcessor, String... entities) {
		settings.setEntityProcessor(entityProcessor,entities);
	}


	/**
	 * Selects a sequence of fields for reading by their names
	 *
	 * @param fieldNames The field names to be read
	 *
	 * @return the (modifiable) set of selected fields
	 */
	@Override
	public final EntityFieldSet<Enum> selectFields(Enum... fieldNames) {
		return settings.selectFields(fieldNames);
	}


	/**
	 * Selects a sequence of fields for reading by their names
	 *
	 * @param fieldNames The field names to be read
	 *
	 * @return the (modifiable) set of selected fields
	 */
	@Override
	public final EntityFieldSet<String> selectFields(String... fieldNames) {
		return settings.selectFields(fieldNames);
	}

	/**
	 * Selects fields which will not be read by their names
	 *
	 * @param fieldNames The field names to exclude from the parsing process
	 *
	 * @return the (modifiable) set of ignored fields
	 */
	@Override
	public final EntityFieldSet<String> excludeFields(String... fieldNames) {
		return settings.excludeFields(fieldNames);
	}

	/**
	 * Selects a sequence of fields for reading by their indexes
	 *
	 * @param fieldIndexes The field indexes to read
	 *
	 * @return the (modifiable) set of selected fields
	 */
	@Override
	public final EntityFieldSet<Integer> selectIndexes(Integer... fieldIndexes) {
		return settings.selectIndexes(fieldIndexes);
	}

	/**
	 * Selects fields which will not be read by their indexes
	 *
	 * @param fieldIndexes The field indexes to exclude from the parsing process
	 *
	 * @return the (modifiable) set of ignored fields
	 */
	@Override
	public final EntityFieldSet<Integer> excludeIndexes(Integer... fieldIndexes) {
		return settings.excludeIndexes(fieldIndexes);
	}

	/**
	 * Selects fields which will not be read by their names
	 *
	 * @param fieldNames The field names to exclude from the parsing process
	 *
	 * @return the (modifiable) set of ignored fields
	 */
	@Override
	public final EntityFieldSet<Enum> excludeFields(Enum... fieldNames) {
		return settings.excludeFields(fieldNames);
	}
	/**
	 * Returns the String representation of a null value (defaults to null)
	 * <p>When reading, if the parser cannot find the specified element, it will return the nullValue instead of null. </p>
	 *
	 * @return the String representation of a null value
	 */
	@Override
	public String getNullValue() {
		return settings.getNullValue();
	}

	/**
	 * Sets the String representation of a null value (defaults to null)
	 * <p>When reading, if the parser cannot find the specified element, it will return the nullValue instead of null. </p>
	 *
	 * @param emptyValue the String representation of a null value
	 */
	@Override
	public void setNullValue(String emptyValue) {
		settings.setNullValue(emptyValue);
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
	 * Configures the parser/writer to limit the length of displayed contents being parsed/written in the exception message when an error occurs
	 *
	 * <p>If set to {@code 0}, then no exceptions will include the content being manipulated in their attributes,
	 * and the {@code "<omitted>"} string will appear in error messages as the parsed/written content.</p>
	 *
	 * <p>defaults to {@code -1} (no limit)</p>.
	 *
	 * @return the maximum length of contents displayed in exception messages in case of errors while parsing/writing.
	 */
	@Override
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
	@Override
	public void setErrorContentLength(int errorContentLength) {
		settings.setErrorContentLength(errorContentLength);
	}

	/**
	 * Add entities to read by their names. This entities will be parsed in addition to any entities that were
	 * previously added.
	 *
	 * @param entitiesToRead names of entities that will be parsed
	 */
	@Override
	public final void addEntitiesToRead(Collection<String> entitiesToRead) {
		settings.addEntitiesToRead(entitiesToRead);
	}

	/**
	 * Add entities to read by their names. This entities will be parsed in addition to any entities that were
	 * previously added.
	 *
	 * @param entitiesToRead names of entities that will be parsed
	 */
	@Override
	public final void addEntitiesToRead(String... entitiesToRead) {
		settings.addEntitiesToRead(entitiesToRead);
	}

	/**
	 * Add entities to skip by their names. This entities will be skipped during the parsing process, as well as any
	 * entities that were previously added to be skipped.
	 *
	 * @param entitiesToSkip names of entities that will be skipped
	 */
	@Override
	public final void addEntitiesToSkip(Collection<String> entitiesToSkip) {
		settings.addEntitiesToRead(entitiesToSkip);
	}

	/**
	 * Set entities to not be parsed by their names. Will override and previous adding/setting entities to skip.  For example,
	 * if there is three entities (A,B,C). It was previously set that it would skip A. By setting B to skip,
	 * the parser will run on A and C, ONLY skipping B.
	 *
	 * @param entitiesToSkip the names of entities that will be skipped
	 */
	@Override
	public final void setEntitiesToSkip(Collection<String> entitiesToSkip) {
		settings.addEntitiesToRead(entitiesToSkip);
	}

	/**
	 * Returns the names of the entities that will be read from.
	 *
	 * @return set of entity names as strings that will be read
	 */
	@Override
	public final Set<String> getEntitiesToRead() {
		return settings.getEntitiesToRead();
	}

	/**
	 * Set entities to read by their names. Will override any previous adding/setting of entities to read. For example,
	 * if there is three entities (A,B,C). It was previously set that it would read from A. By setting B and C to read,
	 * the parser will ONLY run on B and C.
	 *
	 * @param entitiesToRead the entities that will be parsed
	 */
	@Override
	public final void setEntitiesToRead(Collection<String> entitiesToRead) {
		settings.setEntitiesToRead(entitiesToRead);
	}

	/**
	 * Set entities to read by their names. Will override any previous adding/setting of entities to read. For example,
	 * if there is three entities (A,B,C). It was previously set that it would read from A. By setting B and C to read,
	 * the parser will ONLY run on B and C.
	 *
	 * @param entitiesToRead the entities that will be parsed
	 */
	@Override
	public final void setEntitiesToRead(String... entitiesToRead) {
		settings.setEntitiesToRead(entitiesToRead);
	}

	/**
	 * Returns the names of entities that will be ignored when parsing
	 *
	 * @return names of entities that will be skipped during parsing
	 */
	@Override
	public final Set<String> getEntitiesToSkip() {
		return settings.getEntitiesToSkip();
	}

	/**
	 * Set entities to not be parsed by their names. Will override and previous adding/setting entities to skip.  For example,
	 * if there is three entities (A,B,C). It was previously set that it would skip A. By setting B to skip,
	 * the parser will run on A and C, ONLY skipping B.
	 *
	 * @param entitiesToSkip the names of entities that will be skipped
	 */
	@Override
	public final void setEntitiesToSkip(String... entitiesToSkip) {
		settings.setEntitiesToSkip(entitiesToSkip);
	}

	/**
	 * Add entities to skip by their names. This entities will be skipped during the parsing process, as well as any
	 * entities that were previously added to be skipped.
	 *
	 * @param entitiesToSkip names of entities that will be skipped
	 */
	@Override
	public final void addEntitiesToSkip(String... entitiesToSkip) {
		settings.addEntitiesToSkip(entitiesToSkip);
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
	public  ProcessorErrorHandler<HtmlParsingContext> getProcessorErrorHandler() {
		return  settings.getProcessorErrorHandler();
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

}
