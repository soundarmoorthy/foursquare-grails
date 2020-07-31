package search.directory.implementations.foursquare

import search.SearchQuery
import search.directory.IQueryTranslator

class FourSquareQueryTranslator implements IQueryTranslator<FourSquareQuery>{
    @Override
    FourSquareQuery getQuery(SearchQuery query) {

        def canonical = new FourSquareQuery();
        canonical.ll  = "$query.latitude,$query.longitude";
        canonical.query = query.name;

        return canonical;
    }
}
