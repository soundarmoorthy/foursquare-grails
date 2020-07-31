package search.directory;

import search.directory.SearchResult;

public interface ISearchResultsNormalizer<U> {

    Iterable<SearchResult> Normalize(U results);
}
