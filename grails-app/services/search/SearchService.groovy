package search

import grails.gorm.transactions.Transactional
import search.directory.SearchResult

@Transactional
class SearchService {

    GeocodingService geocodingService;
    SearchDirectoryService searchDirectoryService;

    def Search(SearchQuery query) {
        def prepared = prepareQuery(query)

        def providers = searchDirectoryService.get(prepared.getCity());

        def list = new ArrayList<SearchResult>();
        providers.each {p ->
            def partials = p.search(query);
            partials.each({ partial -> list.add(partial) });
        }

        return list;
    }

    private SearchQuery prepareQuery(SearchQuery searchQuery) {
        def latLong = geocodingService.getLatLong(searchQuery.getAddress());
        searchQuery.setLatitude(Float.parseFloat(latLong.getaValue().toString()));
        searchQuery.setLongitude(Float.parseFloat(latLong.getbValue().toString()));
        return searchQuery;
    }
}
