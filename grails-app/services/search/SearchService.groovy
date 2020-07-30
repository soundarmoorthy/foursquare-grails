package search

import grails.gorm.transactions.Transactional

@Transactional
class SearchService {
    static fourSquareApi = System.getenv("fourSquareApi");
    static client_id = System.getenv("client_id")
    static client_secret = System.getenv("client_secret");

    def geocodingService;

    def Search(SearchQuery queryParams) {
        def geoLocation = geocodingService.getGeoLocationAsString(queryParams.getAddress());
        def results = getResultsFromFourSquare(queryParams.getName(), geoLocation)
        log.info results
        return results
    }

    def getResultsFromFourSquare(def address, def latLong) {
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


}
