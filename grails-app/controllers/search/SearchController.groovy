package search

import grails.converters.JSON
import grails.rest.*

class SearchController extends RestfulController {
    static responseFormats = ['json', 'xml']
    def searchService;
    SearchController() {
        super(SearchQuery)
    }

    def doSearch() {
        SearchQuery query = new SearchQuery();
        //Filter parameters to avoid parameter substitution security issues
        bindData(query, params, [include: ['name', 'street_name', 'number', 'city', 'zip']]);
        def results = searchService.Search(query);
        render results;
    }
}
