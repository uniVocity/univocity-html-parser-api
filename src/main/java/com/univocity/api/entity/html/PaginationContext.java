package com.univocity.api.entity.html;

import com.univocity.api.common.*;

import java.util.*;

/**
 * Created by anthony on 18/07/16.
 */
public class PaginationContext {

	HtmlPaginator paginator;
	UrlReaderProvider urlReaderProvider;
	List<String[]> rows; //Maybe have HTMLDataCollector here instead?


	public PaginationContext(HtmlPaginator paginator, UrlReaderProvider urlReaderProvider) {
		this.paginator = paginator;
		this.urlReaderProvider = urlReaderProvider;
	}

	public String getFirstPage() {
		return  getField("firstPage");
	}

	public String getNextPage() {
		return getField("nextPage");
	}

	public String getLastPage() {
		return getField("lastPage");
	}

	public String getPreviousPage() {
		return getField("previousPage");
	}

	public String getPageSize() {
		return getField("pageSize");
	}

	public String getItemCount() {
		return getField("itemCount");
	}

	private String getField(String fieldName) {
//		if (paginator.isRequestParametersSet()) {
			return paginator.getRequestParameters().get(fieldName);
//		} else {
//			throw new IllegalArgumentException("No request parameters set in paginator");
//		}
	}

	public int getIdealPageSize() {
		return paginator.getIdealPageSize();
	}

	public void setIdealPageSize(int idealPageSize) {
		paginator.setIdealPageSize(idealPageSize);
	}

	public Map<String,String> getRequestParameters() {
		return  paginator.getRequestParameters();
	}

	public UrlReaderProvider getUrlReaderProvider() {
		return urlReaderProvider;
	}

	public List<String[]> getParsedRows() {
			return rows;
	}

	public void setRows(List<String[]> rows) {
		this.rows = rows;
	}

	public String[] getFieldNames() {
		return paginator.getFieldNames();
	}

	public int getCurrentPageNumber() {
		return paginator.getCurrentPageNumber();
	}

}
