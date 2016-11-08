package com.ms.memsource

import com.ms.memsource.Configuration


class ConfigurationController {

    ConfigurationService configurationService

    def index() {
        redirect(action: "edit")
    }

    def create() {

    }

    def edit() {
        def configurationInstance = configurationService.getOne()
        if (configurationInstance == null)
            configurationInstance = new Configuration()

        render(view: "edit", model:
        [
            configurationInstance: configurationInstance
        ])

    }

    def update(Long id, Long version) {
        def configurationInstance = configurationService.getOne()
        if (configurationInstance == null)
            configurationInstance = new Configuration(params)

        if (version != null) {
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
