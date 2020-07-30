package search

import org.apache.catalina.connector.Request

class SearchInterceptor {
    def authService;

    boolean before() {
        def req = request.request; //Hopefully this isn't null

        def auth = authService.handle req
        return auth.succeeded()
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
