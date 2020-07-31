package search.directory.implementations.foursquare

import search.directory.IQueryTranslator
import search.directory.ISearchResultsNormalizer
import search.directory.SearchProvider

class FourSquareSearchProvider extends SearchProvider<FourSquareQuery, FourSquareResults>{

    static fourSquareApi = System.getenv("fourSquareApi");
    static client_id = System.getenv("client_id")
    static client_secret = System.getenv("client_secret");

    @Override
    boolean serves(String city) {
        if(city.equalsIgnoreCase("berlin"))
            return true;
        else
            return false;
    }

    @Override
    protected IQueryTranslator translator() {
        return new FourSquareQueryTranslator();
    }

    @Override
    protected ISearchResultsNormalizer getResultsNormalizer() {
        return new FourSquareResultsNormalizer();
    }

    @Override
    protected FourSquareResults search(FourSquareQuery query) {
        def results = "$fourSquareApi?client_id=$client_id&client_secret=$client_secret&v=20180323&ll=$query.ll&query=$query.query"
                .toURL().text
        return new FourSquareResults(results);
    }

        @Override
        String getName() {
         "FourSquare"
    }
}
