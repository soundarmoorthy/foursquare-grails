package search

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j

@Transactional
@Slf4j
class SearchService {
    static fourSquareApi = System.getenv("fourSquareApi");
    static client_id = System.getenv("client_id")
    static client_secret = System.getenv("client_secret");
    static geocode_api = System.getenv("geocode_api");
    static geocode_key = System.getenv("geocode_key");

    def Search(queryParams) {
        /*

        var providers = ISearchDirectoryProviders.getProviders(queryParams);

        //multithreaded List<SearchResult> results;
        providers.each { p ->
        IQueryIput in = p.GetQueryTranslator(searchQuery);
        def result = p.search(in);
        def normalized = p.Normalize(result)
        results.add(normalized)
        }
         */

        def address = URLEncoder.encode(queryParams.getAddress(), "UTF-8")
        def location = getLocation(address)
        def results = getResultsFromFourSquare(queryParams.getName(), location)
        log.info results
        return results
    }

    def getResultsFromFourSquare(def address, def loc) {
        def latLong = getLatLong(loc);
        def results =  "$fourSquareApi?client_id=$client_id&client_secret=$client_secret&v=20180323&ll=$latLong&query=$address"
        .toURL().text

        return results;

//        def parser = new JsonSlurper();
//        Map json = parser.parseText(results)

//        List<Results> list = new ArrayList<Results>();
//
        //TODO : Validate the response and the model
//        ArrayList venues = json.response.venues;
//        venues.each { venue ->
//            Results result = new Results();
//            result.setName(venue.name);
//            result.setAddress1(venue.location.formattedAddress[0]);
//            result.setAddress2(venue.location.formattedAddress[1]);
//            result.setAddress3(venue.location.formattedAddress[2]);
//            result.save();
//            list.add(result);
//        }

//        return list;
    }

    def  getLocation(address) {
        def response = "$geocode_api?address=$address&key=$geocode_key".toURL().text;

        def parser = new JsonSlurper();
        Map json = parser.parseText(response)

        def location = json.results.geometry.location;
        return location;
    }

    def getLatLong(def loc)
    {
        return "${loc.lat[0]},${loc.lng[0]}"
    }
}
