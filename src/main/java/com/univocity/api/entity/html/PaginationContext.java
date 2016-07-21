package com.univocity.api.entity.html;

import com.univocity.api.common.*;

import java.util.*;

/**
 *  Contains information about the pagination process.
 *
 *  @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 *
 *  @see HtmlPaginator
 *  @see UrlReaderProvider
 */
public class PaginationContext {

	private final RemoteResourcePaginator paginator;
	private final UrlReaderProvider urlReaderProvider;
	private List<String[]> rows;

	/**
	 * Creates a new PaginationContext with a {@link HtmlPaginator} and a {@link UrlReaderProvider}.
	 * @param paginator
	 * @param urlReaderProvider
	 */
	public PaginationContext(RemoteResourcePaginator paginator, UrlReaderProvider urlReaderProvider) {
		this.paginator = paginator;
		this.urlReaderProvider = urlReaderProvider;
	}

	/**
	 * Returns the value parsed from the first page path.
	 *
	 * @return String from html element specified by the first page path
	 */
	public String getFirstPage() {
		return getField("firstPage");
	}

	/**
	 * Returns the value parsed from the next page path.
	 *
	 * @return String from html element specified by the next page path
	 */
	public String getNextPage() {
		return getField("nextPage");
	}

	/**
	 * Returns the value parsed from the last page path.
	 *
	 * @return String from html element specified by the last page path
	 */
	public String getLastPage() {
		return getField("lastPage");
	}

	/**
	 * Returns the value parsed from the previous page path.
	 *
	 * @return String from html element specified by the previous page path
	 */
	public String getPreviousPage() {
		return getField("previousPage");
	}

	/**
	 * Returns the value parsed from the page size path.
	 *
	 * @return String from html element specified by the page size path
	 */
	public String getPageSize() {
		return getField("pageSize");
	}

	/**
	 * Returns the value parsed from the item count path.
	 *
	 * @return String from html element specified by the first page path
	 */
	public String getItemCount() {
		return getField("itemCount");
	}

	private String getField(String fieldName) {
		Map<String,String> map = paginator.getRequestParameters();
		return  map.get(fieldName);
	}

	/**
	 * Returns the ideal page size that the paginator is set at.
	 *
	 * @return a number defining the ideal page size
	 */
	public int getIdealPageSize() {
		return paginator.getIdealPageSize();
	}

	/**
	 * Sets the page size that the paginator will set the web page size to.
	 *
	 * @param idealPageSize
	 */
	public void setIdealPageSize(int idealPageSize) {
		paginator.setIdealPageSize(idealPageSize);
	}


	public Map<String,String> getRequestParameters() {
		return  paginator.getRequestParameters();
	}

	/**
	 * Returns the associated {@link UrlReaderProvider}
	 *
	 * @return the associated {@link UrlReaderProvider}
	 */
	public UrlReaderProvider getUrlReaderProvider() {
		return urlReaderProvider;
	}

	/**
	 * Returns the rows that were received from the parsing process.
	 *
	 * @return parsed rows
	 */
	public List<String[]> getParsedRows() {
			return rows;
	}

	/**
	 * Sets the rows that were parsed using the paths set in the {@link HtmlPaginator}. This method is primarily
	 * used by {@link HtmlParser}.
	 *
	 * @param rows the rows parsed
	 */
	public void setRows(List<String[]> rows) {
		this.rows = rows;
	}

	/**
	 * Returns a String array of field names used by the paginator. Field names are set via setting requestParameters or
	 * using the other setters such as {@link HtmlPaginator#setNextPage()}
	 *
	 * @return a string array of field names.
	 */
	public String[] getFieldNames() {
		return paginator.getFieldNames();
	}

	/**
	 * Returns the number of the page that the paginator is currently up to
	 * @return the current page number
	 */
	public int getCurrentPageNumber() {
		return paginator.getCurrentPageNumber();
	}

}
