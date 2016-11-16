package com.ms.memsource

import grails.transaction.Transactional

@Transactional
class ConfigurationService {

    /**
     * Get one (first) configuration instance
     * @return Configuration
     */
    def getOne() {
        return Configuration.first()
    }

    /**
     * Save or update configuration
     * @param configuration new or updated Configuration
     * @return configuration
     */
    def saveOrUpdate(Configuration configuration) {
        configuration.save(flush: true)
        return configuration
    }
}
