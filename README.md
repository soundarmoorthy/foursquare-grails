# foursquare-grails

The applicatio basically is a wrapper for the foursquare venue search API. The application is a rest API application based on grails 3.3.11. 

#### Setup
* Install grails 3.3.11
* IntelliJ Idea community
* Java 1.8

#### Usage
The following postman query will be a useful starting point to test the application. The auth token is for educational purposes and i don't see any issues about anyone using it

`http://localhost:8080/search?name=McDonalds&street_name=Alexanderplatz &number=S-Bahnhof&city=Berlin&zip=10178&auth_token=1441307151_4492f25946a2e8e1414a8bb53dab8a6ba1cf4615`

