package com.ms.memsource


class MyTagLib {

    static defaultEncodeAs = [taglib:'html']
    static namespace = "my"

    def grailsApplication

    /**
     * Renders custom footer content
     */
    def footer = {attrs, body ->
        String appName = grailsApplication.metadata['app.name'].toString()
        String appVersion = grailsApplication.metadata['app.version'].toString()
        out << appName + ' version ' + appVersion
    }
    
}
