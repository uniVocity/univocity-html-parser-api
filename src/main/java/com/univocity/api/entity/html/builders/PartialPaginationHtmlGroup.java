package com.univocity.api.entity.html.builders;

/**
 * @author uniVocity Software Pty Ltd - <a href="mailto:dev@univocity.com">dev@univocity.com</a>
 */
public interface PartialPaginationHtmlGroup extends BaseHtmlPath<PartialPaginationHtmlGroup>, BaseHtmlPathStart<PartialPaginationHtmlGroup> {

	PaginationHtmlGroup endAt(String elementName);

	PaginationHtmlGroup endAtClosing(String elementName);
}
