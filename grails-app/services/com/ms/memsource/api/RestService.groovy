package com.ms.memsource.api

import com.ms.memsource.Configuration
import com.ms.memsource.ConfigurationService
import grails.transaction.Transactional
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import org.springframework.web.context.request.RequestContextHolder

@Transactional
class RestService {

    // constants related to public API
    private static final String PUBLIC_API_URL = '/api/rest/project/list'

    // constants related to Memsource API
    private static final String MEMSOURCE_API_BASE = 'https://cloud.memsource.com/web/api/v3/'
    private static final String MEMSOURCE_LOGIN = 'auth/login'
    private static final String MEMSOURCE_PROJECT_LIST = 'project/list'

    //inject necessary services
    ConfigurationService configurationService

    /**
     * Get credentials from ConfigurationService
     * @return Configuration domain class (userName, password)
     */
    Configuration getCredentials() {
        Configuration config = configurationService.getOne()
        if (config == null)
            throw new Exception('Configuration not set')

        return config
    }

    /**
     * Get auth token from Memsource auth/login interface
     * @param userName
     * @param password
     * @return String token
     */
    String getMemsourceToken(String userName, String password) {
        // make POST request
        String url = MEMSOURCE_API_BASE + MEMSOURCE_LOGIN
        HTTPBuilder http = new HTTPBuilder(url)
        http.request(POST) {
            requestContentType = URLENC
            body = [userName: userName, password: password]
            response.success = {resp, json ->
                if (json.token)
                    return json.token
            }
            response.failure = { resp, json ->
                log.error "Request failed : [URI : ${url}, Status: ${resp.status}]"
                String message = resp.status.toString()
                if (json.errorCode) message += ' ' + json.errorCode
                if (json.errorDescription != 'null') message += ' ' + json.errorDescription
                throw new Exception(message)
            }
        }
    }

    /**
     *
     * @param token
     * @return
     */
    def getPojectList(String token) {
        // make GET request
        String url = MEMSOURCE_API_BASE + MEMSOURCE_PROJECT_LIST
        HTTPBuilder http = new HTTPBuilder(url)
        http.request(GET) {
            uri.query = [token: token]
            response.success = {resp, json ->
                return json
            }
            response.failure = { resp, json ->
                log.error "Request failed : [URI : ${url}, Status: ${resp.status}]"
                String message = resp.status.toString()
                if (json.errorCode) message += ' ' + json.errorCode
                if (json.errorDescription != 'null') message += ' ' + json.errorDescription
                throw new Exception(message)
            }
        }

    }

    /**
     * Helper method, used by view to obtain public API URL
     * @return String absolute path
     */
    public static String getApiUrl() {
        def request = RequestContextHolder.currentRequestAttributes().request

        return  request.getContextPath() + PUBLIC_API_URL as String
    }
}
