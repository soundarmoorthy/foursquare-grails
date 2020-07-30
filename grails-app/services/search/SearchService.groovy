package search

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j

@Transactional
@Slf4j
class SearchService {
    static fourSquareApi = "https://api.foursquare.com/v2/venues/search";
    static client_id = "client_secret_foursquare";
    static client_secret = "client_secret_foursquare";
    static geocode_api = "https://maps.googleapis.com/maps/api/geocode/json";
    static geocode_key = "<geocode-key>";

    def Search(queryParams) {
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
