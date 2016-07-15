package com.univocity.api.entity.html.builders;

/**
 * Created by anthony on 14/07/16.
 */
public interface PaginationHtmlGroup extends BaseHtmlPath<PaginationHtmlGroup>, BaseHtmlPathStart<PaginationHtmlGroup> {

	public HtmlPathStart setNextPage();

	public HtmlPathStart setPageSize();

	public HtmlPathStart setFirstPage();

	public HtmlPathStart addRequestParameter(String parameterName);
}
