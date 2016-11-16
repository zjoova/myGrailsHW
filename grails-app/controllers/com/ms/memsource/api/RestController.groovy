package com.ms.memsource.api

import com.ms.memsource.Configuration
import com.ms.memsource.api.RestService

import grails.converters.JSON
import net.sf.json.JSONNull

class RestController {

    RestService restService


    /**
     * List all projects bound to credentials specified in Configuration
     * @return JSON list of all available projects or error
     */
    def projectList() {

        // FIXME: workaround - render JSONNull as an empty string (to avoid JSONException in net.sf.json.JSONNull
        grails.converters.JSON.registerObjectMarshaller(JSONNull, { return '' })

        Configuration config = restService.getCredentials()
        String token = restService.getMemsourceToken(config.userName, config.password)
        def projects = restService.getPojectList(token)

        render projects as JSON
    }

    /**
     * Helper method, catches all exceptions and renders message as JSON
     * TODO: implement additional exception handlers, respond with correct status codes
     * @param e Exception
     * @return http status 500 + exception message
     */
    def handleException(Exception e) {
        response.status = 500
        def responseData = ['message': e.message]
        render responseData as JSON
    }
}
