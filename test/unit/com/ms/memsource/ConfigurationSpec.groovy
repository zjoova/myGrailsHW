package com.ms.memsource

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Configuration)
class ConfigurationSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test Configuration constraints"() {
        when: 'userName is not set'
        def conf = new Configuration(password:'dummy')

        then: 'validation should fail'
        !conf.validate()

        when: 'password is not set'
        conf = new Configuration(userName:'dummy')

        then: 'validation should fail'
        !conf.validate()

        when: 'all is set properly'
        conf = new Configuration(userName: 'dummy', password:'dummy')

        then: 'validation should pass'
        conf.validate()

    }
}
