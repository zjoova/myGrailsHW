package com.ms.memsource.api

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(RestController)
@Mock([RestService])
class RestControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test error handling"() {
        setup:
        controller.restService = Mock(RestService)

        when:
        controller.projectList()

        then:
        // Exception is catched, by handleException, status code 500 returned
        response.status == 500

    }

    //TODO: add more tests with HTTPBuilder mock
}
