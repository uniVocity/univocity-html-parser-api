package com.univocity.api.entity.html;

import com.univocity.api.entity.html.builders.*;

/**
 * Created by anthony on 14/07/16.
 */
public class HtmlPaginator {
	HtmlEntity entity;
	private int followCount;

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

	public void addRequestParameter(String fieldName) {
		//entity.
	}

	public PaginationHtmlGroupStart newGroup() {
		return entity.newPaginationGroup();
	}
}
