package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;

import java.util.*;

/**
 * Used by the {@link HtmlParser} to follow pages on a website.
 *
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 *
 * @see HtmlParser
 * @see PaginationContext
 * @see PaginationHandler
 */
public class HtmlPaginator {
	final HtmlEntity entity;
	private int followCount;
	private int idealPageSize;
	private int currentPageNumber;
	private PaginationHandler paginationHandler;

	/**
	 * Creates a new HtmlPaginator and sets the currentPageNumber to 0
	 */
	public HtmlPaginator() {
		currentPageNumber = 0;
		this.entity = new HtmlEntity("*paginator*");
	}

	/**
	 * Returns the name of the paginator
	 *
	 * @return the name of the paginator
	 */
	public String getEntityName() {
		return entity.getEntityName();
	}

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the next page element. The next page is a HTML element that changes the current page to the next page in series.
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the element
	 */
	public HtmlPathStart setNextPage() {
		return entity.addField("nextPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the 'previous page' element. The previous page is a HTML element that changes the current page to the previous
	 * page in series.
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the  element
	 */
	public HtmlPathStart setPreviousPage() {
		return entity.addField("previousPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the 'first page' element. The first page is a HTML element that changes the current page to the first page
	 * in series.
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the  element
	 */
	public HtmlPathStart setFirstPage() {
		return entity.addField("firstPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the 'last page' element. The last page is a HTML element that changes the current page to the last page
	 * in series.
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the  element
	 */
	public HtmlPathStart setLastPage() {
		return entity.addField("lastPage");
	}

	/**
	 * Creates a new field for the next page and returns a {@link HtmlPathStart} which can be used to define the path
	 * to the 'last page' element. The last page is a HTML element that changes the current page to the last page
	 * in series.
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the element
	 */
	public HtmlPathStart setPageSize() {
		return entity.addField("pageSize");
	}

	/**
	 * Sets the ideal page size. The ideal page size is a number that the paginator will try to set the page size to.
	 *
	 * @param pageSize a number that is used to define the ideal page size
	 */
	public void setIdealPageSize(int pageSize) {
		this.idealPageSize = pageSize;
	}

	/**
	 * Returns the ideal page size. The ideal page size is a number that the paginator will try to set the page size to.
	 *
	 * @return the ideal page size
	 */
	public int getIdealPageSize() {
		return idealPageSize;
	}

	//is this pagesize?
	public HtmlPathStart setItemCount() {
		return entity.addField("itemCount");
	}

	/**
	 * Sets the amount of times that the {@link HtmlParser} will go to the next page.
	 *
	 * @param followCount the number of pages that will be visited
	 */
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

	/**
	 * Returns the amount of times the {@link HtmlParser} will go to the next page.
	 *
	 * @return the number of pages that will be visited
	 */
	public int getFollowCount() {
		return followCount;
	}

	/**
	 * Creates a new request parameter and returns a {@link HtmlPathStart} that allows the user to define path to the
	 * parameter. Request parameters are values set on a page that control what content is displayed.
	 *
	 * @param fieldName the name of the request parameter
	 *
	 * @return a {@link HtmlPathStart} is used to define the path to the parameter
	 */
	public HtmlPathStart addRequestParameter(String fieldName) {
		return entity.addRequestParameter(fieldName);
	}

	/**
	 *
	 * Sets a request parameter to a specfic value. This differs from {@link HtmlPaginator#addRequestParameter(String)}
	 * as it does not create a field in the entity, therefore not returning a path. It is generally used during
	 * the parsing process as
	 *
	 * @param fieldName the name of the request parameter
	 * @param value the string that will be associated with the request parameter
	 */
	public void setRequestParameter(String fieldName, String value) {
		entity.setRequestParameter(fieldName, value);
	}

	/**
	 * Returns the request parameters as a Map<String, String>, where the key is the request parameter name and the value
	 * is the parsed String from the HTML page.
	 *
	 * @return the request parameters as a Map
	 */
	public Map<String, String> getRequestParameters() {
		return entity.getRequestParameters();
	}

	/**
	 * Creates a new group for the paginator.
	 *
	 * @return a {@link PaginationHtmlGroupStart}
	 */
	public PaginationHtmlGroupStart newGroup() {
		return entity.newPaginationGroup();
	}

	/**
	 * Returns the field names used by the Paginator
	 *
	 * @return a String array of field names
	 */
	public String[] getFieldNames() {
		return entity.getFieldNames();
	}

	/**
	 * Returns the page number that the Paginator is currently up to
	 *
	 * @return the current page number of the paginator
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/**
	 * Sets what page number the Paginator is up to.
	 *
	 * @param currentPageNumber the page number that the paginator will be set to.
	 */
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	/**
	 * Sets the {@link PaginationHandler} which is used to prepare the call to the next page when the {@link HtmlParser}
	 * runs. If not set, will use {@link DefaultPaginationHandler}
	 *
	 * @param paginationHandler the {@link PaginationHandler} that will be associated with the paginator
	 */
	public void setPaginationHandler(PaginationHandler paginationHandler) {
		this.paginationHandler = paginationHandler;
	}

	public PaginationHandler getPaginationHandler() {
		return paginationHandler;
	}
}
