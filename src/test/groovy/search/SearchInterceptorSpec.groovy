package search

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class SearchInterceptorSpec extends Specification implements InterceptorUnitTest<SearchInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test search interceptor matching"() {
        when:"A request matches the interceptor"
        withRequest(controller:"search")

        then:"The interceptor does match"
        interceptor.doesMatch()
    }
}
