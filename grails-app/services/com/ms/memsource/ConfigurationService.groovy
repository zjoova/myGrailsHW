package com.ms.memsource

import grails.transaction.Transactional

@Transactional
class ConfigurationService {

    def getOne() {
        return Configuration.first()
    }

    def saveOrUpdate(Configuration configuration) {
        configuration.save(flush: true)
        return configuration
    }
}
