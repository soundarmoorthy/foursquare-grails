package search.directory.implementations.foursquare

import groovy.json.JsonSlurper
import search.directory.ISearchResultsNormalizer
import search.directory.SearchResult

class FourSquareResultsNormalizer implements ISearchResultsNormalizer<FourSquareResults> {

    @Override
    Iterable<SearchResult> Normalize(FourSquareResults results) {
        def jsonString = results.json;

        def parser = new JsonSlurper();
        Map json = parser.parseText(jsonString)

        List<SearchResult> list = new ArrayList<>();
        ArrayList venues = json.response.venues;
        for(int i=0;i<venues.size();i++)
         {
             def venue = venues.get(i);
            SearchResult result = new SearchResult();
            result.setEntityName(venue["name"]);
            result.setAddressLine1(venue["location"]["formattedAddress"][0]);
            result.setAddressLine2(venue["location"]["formattedAddress"][1]);
            result.setAddressLine3(venue["location"]["formattedAddress"][2]);
            list.add(result);
        }
        return list;
    }
}
