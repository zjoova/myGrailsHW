package com.ms.memsource

import grails.test.spock.IntegrationSpec

class ConfigurationServiceSpec extends IntegrationSpec  {

    def configurationService

    def setup() {
    }

    def cleanup() {
    }


    void "test getOne and saveOrUpdate methods"() {
        setup:
        def confValid = new Configuration([userName:'dummy',password:'dummy'])
        def confInvalid = new Configuration([password:'dummy'])

        when:
        def conf = configurationService.getOne()

        then:
        conf == null

        when:
        configurationService.saveOrUpdate(confInvalid)
        conf = configurationService.getOne()

        then:
        conf == null

        when:
        configurationService.saveOrUpdate(confValid)
        conf = configurationService.getOne()

        then:
        conf.id
        conf.userName == confValid.userName

    }
}
