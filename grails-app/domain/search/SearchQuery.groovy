package search

class SearchQuery {

    static constraints = {
    }

    String name;
    String street_name;
    String number;
    String city;
    int zip;


    def getAddress()
    {
        return "$name, $street_name, $number, $city, $zip";
    }
}
