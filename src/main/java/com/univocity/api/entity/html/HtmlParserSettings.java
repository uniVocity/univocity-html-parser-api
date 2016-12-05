package com.univocity.api.entity.html;

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
public final class HtmlParserSettings extends RemoteParserSettings<CommonParserSettings, HtmlEntityList, HtmlParsingContext> {

	private int parserThreadCount = Runtime.getRuntime().availableProcessors();

	/**
	 * Creates a new {@code HtmlParserSettings} and associates it with a {@link HtmlEntityList}.
	 *
	 * @param entityList the {@link HtmlEntityList} that will be associated with the parser
	 */
	public HtmlParserSettings(HtmlEntityList entityList) {
		super(entityList);
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
	 * Returns the maximum number of threads used by the parser when processing data of multiple entities from
	 * the same HTML input.
	 *
	 * <p>Defaults to the number of available processors available to the JVM</p>
	 *
	 * @return the number of threads used by the parser.
	 */
	public final int getParserThreadCount() {
		return parserThreadCount <= 0 ? 1 : parserThreadCount;
	}

	/**
	 * Explicitly defines a maximum number of threads that should be used by the parser when processing data of
	 * multiple entities from the same HTML input.
	 *
	 * <p>By default, to the number of available processors available to the JVM will be used</p>
	 *
	 * @param parserThreadCount the maximum number of threads to use
	 */
	@Range(minSize = 1, maxSize = 16)
	@UIConfig()
	public final void setParserThreadCount(int parserThreadCount) {
		if (parserThreadCount <= 0) {
			parserThreadCount = 1;
		}
		this.parserThreadCount = parserThreadCount;
	}

	@Override
	public String getDefaultFileExtension() {
		return "html";
	}

	@Override
	protected CommonParserSettings createGlobalSettings() {
		return createEmptyGlobalSettings();
	}

}
