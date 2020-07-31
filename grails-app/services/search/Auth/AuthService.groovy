package search.Auth

import grails.gorm.transactions.Transactional
import org.apache.catalina.connector.Request

@Transactional
class AuthService {
    static auth_token_param  = "auth_token"

    def handle(Request req) {
        def auth = new AuthHandlerResult();

        if (!authHeaderPresent(req)) {
            return auth.failed ("The auth_token header is missing")
        }

        String auth_token = req.getParameter(auth_token_param)
        if (!authTokenHasHeaderValue(auth_token)) {
            return auth.failed ("The auth_token header has an invalid value")
        }

        if (!validToken(auth_token)) {
            return auth.failed ("The given auth_token is not registered. Please use a valid auth token. Contact api@uberall.com");
        }
        //Finally if none of these happen, the authentication succeeded
        return auth.succeeded()
    }

    private boolean validToken(auth_token) {
        Token.OnlyAvailableToken().getToken().equals(auth_token);
    }

    private boolean authTokenHasHeaderValue(auth_token){
        return auth_token != null && auth_token.length() > 0
    }

    private boolean authHeaderPresent(req){
        return req.parameterNames.any {
            p -> p.equalsIgnoreCase(auth_token_param)
        }
    }
}
