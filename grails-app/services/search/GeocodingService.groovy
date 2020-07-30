package search

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

@Transactional
class GeocodingService {
    static geocode_api = System.getenv("geocode_api");
    static geocode_key = System.getenv("geocode_key");

    //AddressLine is the full address in format "addr1, addr2, city, zip"
    //This is a format expected by google geocoding apart from many other formats
    def getGeoLocationAsString(String addressLine) {
        def address = URLEncoder.encode(addressLine, "UTF-8")
        return format(
                getLocation(address)
        )
    }

    def  getLocation(address) {
        def response = "$geocode_api?address=$address&key=$geocode_key".toURL().text;

        def parser = new JsonSlurper();
        Map json = parser.parseText(response)

        def location = json.results.geometry.location;
        return location;
    }

    def format(def loc)
    {

        return "${loc.lat[0]},${loc.lng[0]}"
    }
}
