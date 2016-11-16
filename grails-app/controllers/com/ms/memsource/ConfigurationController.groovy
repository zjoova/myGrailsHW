package com.ms.memsource

import com.ms.memsource.Configuration


class ConfigurationController {

    ConfigurationService configurationService

    /**
     * Redirect index action to "edit"
     * There is just single configuration saved, no need to list all instances
     * GET /
     */
    def index() {
        redirect(action: "edit")
    }

    /**
     * Edit configuration
     * GET /configuration/edit
     */
    def edit() {
        def configurationInstance = configurationService.getOne()
        if (configurationInstance == null)
            configurationInstance = new Configuration()

        render(view: "edit", model:
        [
            configurationInstance: configurationInstance
        ])

    }

    /**
     * Update configuration
     * @param id Configuration.id - internal id
     * @param version Configuration.version - internal version number
     * POST /configuration/update
     */
    def update(Long id, Long version) {
        def configurationInstance = configurationService.getOne()
        if (configurationInstance == null)
            configurationInstance = new Configuration(params)

        if (version != null) {
            // handle concurrent updates
            if (configurationInstance.version > version) {
                configurationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          ["Configuration"] as Object[],
                          "Another user has updated this Configuration while you were editing")
                render(view: "edit", model:
                [
                    configurationInstance: configurationInstance
                ])
                return
            }
        }

        configurationInstance.properties = params
        configurationInstance = configurationService.saveOrUpdate(configurationInstance)

        redirect(action: "edit")

    }
}
