package search.directory;

import search.SearchQuery;

public interface IQueryTranslator<T> {
    //For now return proper query or null
    T getQuery(SearchQuery query);
}
