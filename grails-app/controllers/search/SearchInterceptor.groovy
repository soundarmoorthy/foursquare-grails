package search


class SearchInterceptor {
    def authService;

    boolean before() {
        def req = request.request; //Hopefully this isn't null
        def auth = authService.handle req

        if(auth.success) {
            true
        }
        else {
            req.response.sendError(auth.errorCode, auth.message);
        }
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
