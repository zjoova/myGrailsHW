package com.ms.memsource

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(MyTagLib)
class MyTagLibSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test footer rendering"() {
        String footer = grailsApplication.metadata['app.name'].toString() + ' version ' + grailsApplication.metadata['app.version'].toString()
        expect:
        applyTemplate('<my:footer />') == footer
    }
}
