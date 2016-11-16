package com.ms.memsource.api

import com.ms.memsource.Configuration
import grails.test.spock.IntegrationSpec

class RestServiceSpec extends IntegrationSpec  {

    def restService
    def configurationService

    def setup() {
    }

    def cleanup() {
    }

    void "test getCredentials"() {
        setup:
        def confValid = new Configuration([userName:'dummy',password:'dummy'])

        when:
        configurationService.saveOrUpdate(confValid)
        def conf = restService.getCredentials()

        then:
        conf.userName == confValid.userName

    }

    void "test getCredentials throws Exception"() {
        setup:
        def confInvalid = new Configuration([password:'dummy'])

        when:
        configurationService.saveOrUpdate(confInvalid)
        def conf = restService.getCredentials()

        then:
        def exception = thrown(Exception)
        exception.message == 'Configuration not set'

    }

    void "test getApiUrl"() {
        when:
        def url = restService.getApiUrl()

        then:
        //url == PUBLIC_API_URL only, there is no request context set in this phase
        url == '/api/rest/project/list'

    }

    //TODO: add more tests with HTTPBuilder mock

}
