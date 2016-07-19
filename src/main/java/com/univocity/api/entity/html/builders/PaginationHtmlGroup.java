package com.univocity.api.entity.html.builders;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface PaginationHtmlGroup extends BaseHtmlPath<PaginationHtmlGroup>, BaseHtmlPathStart<PaginationHtmlGroup> {

	HtmlPathStart setNextPage();

	HtmlPathStart setPageSize();

	HtmlPathStart setFirstPage();

	HtmlPathStart addRequestParameter(String parameterName);
}
