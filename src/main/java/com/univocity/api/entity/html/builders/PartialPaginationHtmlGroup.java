package com.univocity.api.entity.html.builders;

/**
 * Created by anthony on 14/07/16.
 */
public interface PartialPaginationHtmlGroup extends BaseHtmlPath<PartialPaginationHtmlGroup>, BaseHtmlPathStart<PartialPaginationHtmlGroup> {

	PaginationHtmlGroup endAt(String elementName);

	PaginationHtmlGroup endAtClosing(String elementName);
}
