package search.directory;

import search.SearchQuery;

public interface ISearchProvider {
    String getName();
    Iterable<SearchResult> search(SearchQuery query);
    public boolean serves(String city);
}
