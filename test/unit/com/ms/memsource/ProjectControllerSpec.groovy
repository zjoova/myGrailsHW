package com.ms.memsource

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ProjectController)
class ProjectControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test index"() {
        when:
        controller.index()

        then:
        response.redirectedUrl == '/project/list'

    }

    void "test list view"() {
        when:
        controller.list()

        then:
        view == '/project/list'
    }
}
