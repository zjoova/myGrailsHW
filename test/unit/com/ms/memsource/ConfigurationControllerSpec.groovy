package com.ms.memsource

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ConfigurationController)
@Mock([Configuration,ConfigurationService])
class ConfigurationControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test index"() {
        when:
        controller.index()

        then:
        response.redirectedUrl == '/configuration/edit'

    }

    void "test edit view"() {
        setup:
        controller.configurationService = Mock(ConfigurationService)

        when:
        controller.edit()

        then:
        view == '/configuration/edit'
    }

     void "test update"() {
        setup:
        controller.configurationService = Mock(ConfigurationService)

        when:
        params.userName = 'dummy'
        params.password = 'dummy'
        controller.update()

        then:
        response.redirectedUrl == '/configuration/edit'
    }
}
