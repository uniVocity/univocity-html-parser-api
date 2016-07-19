package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;

import java.util.*;

/**
 * Created by anthony on 14/07/16.
 */
public class HtmlPaginator {
	HtmlEntity entity;
	private int followCount;
	private int idealPageSize;
	private int currentPageNumber;
	private PaginationHandler paginationHandler;
	private TreeMap<String, String> requestParameters = new TreeMap<String, String>();
	private TreeMap<String, List<HtmlPath>> requestParametersWithPaths = new TreeMap<String, List<HtmlPath>>();

	public HtmlPaginator() {
		currentPageNumber = 0;
		this.entity = new HtmlEntity("*paginator*");
	}

	public String getEntityName() {
		return entity.getEntityName();
	}


	public HtmlPathStart setNextPage() {
		return entity.addField("nextPage");
	}

	public HtmlPathStart setPreviousPage() {
		return entity.addField("previousPage");
	}

	public HtmlPathStart setFirstPage() {
		return entity.addField("firstPage");
	}

	public HtmlPathStart setLastPage() {
		return entity.addField("lastPage");
	}

	//element on page showing number of items on page
	public HtmlPathStart setPageSize() {
		return entity.addField("pageSize");
	}

	//what the paginator will try to set the page size to.
	public void setIdealPageSize(int pageSize) {
		this.idealPageSize = pageSize;
	}

	public int getIdealPageSize() {
		return idealPageSize;
	}

	public HtmlPathStart setItemCount() {
		return entity.addField("itemCount");
	}

	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

	public int getFollowCount() {
		return followCount;
	}

	public HtmlPathStart addRequestParameter(String fieldName) {
		return entity.addRequestParameter(fieldName);
	}

	public void setRequestParameter(String fieldName, String value) {
		entity.setRequestParameter(fieldName, value);
	}

	public TreeMap<String, String> getRequestParameters() {
		return entity.getRequestParameters();
	}

	public  boolean isRequestParametersSet() {
		return !entity.getRequestParameters().isEmpty();
	}

	public PaginationHtmlGroupStart newGroup() {
		return entity.newPaginationGroup();
	}

	public String[] getFieldNames() {
		return  entity.getFieldNames();
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public void setPaginationHandler(PaginationHandler paginationHandler) {
		this.paginationHandler = paginationHandler;
	}

	public PaginationHandler getPaginationHandler() {
		return paginationHandler;
	}
}
