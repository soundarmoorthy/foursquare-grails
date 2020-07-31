package search


class SearchQuery {

    String name;
    String street_name;
    String number;
    String city;
    int zip;

    float latitude;
    float longitude;

    def getAddress()
    {
        return "$name, $street_name, $number, $city, $zip";
    }
}
