package search.Auth

class Token {

    //TODO : Move it to database
    static OnlyAvailableToken(){
        Token t = new Token();
        t.setToken("1441307151_4492f25946a2e8e1414a8bb53dab8a6ba1cf4615");
        t.setLocation("Berlin");
        t.setClientName("uberall");
        return t;
    }

    String token;
    String clientName;
    String location;

}
