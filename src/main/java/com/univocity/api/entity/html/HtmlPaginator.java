package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;

import java.util.*;

/**
 * Created by anthony on 14/07/16.
 */
public class HtmlPaginator {
	HtmlEntity entity;
	private int followCount;
	private TreeMap<String, String> requestParameters = new TreeMap<String, String>();
	private TreeMap<String, List<HtmlPath>> requestParametersWithPaths = new TreeMap<String, List<HtmlPath>>();

	public HtmlPaginator() {
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


	public HtmlPathStart setPageSize() {
		return entity.addField("pageSize");
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

	public void setRequestParameter(String fieldName) {
		entity.setRequestParameter(fieldName);
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
}
