package search.directory;

import search.SearchQuery;

public abstract class SearchProvider<T,U> implements ISearchProvider  {
    //TODO : City is ambigious and can be substituded by a much sophisticated entity or a set of entities.
    public abstract boolean serves(String city);

    //TODO : As of now this method does Query translation, search, and translate to normal form.
    // Per design this work should be done from 3 different interfaces
    public Iterable<SearchResult> search(SearchQuery query)
    {
        T canonicalForm = translator().getQuery(query);
        if(canonicalForm == null)
            return null;

        //do the search
        U results = this.search(canonicalForm);

        if(results == null)
            return null;

        return getResultsNormalizer().Normalize(results);
    }

    protected abstract IQueryTranslator<T> translator();

    protected abstract ISearchResultsNormalizer<U> getResultsNormalizer();

    protected  abstract U search(T input);

    public abstract String getName();
}
