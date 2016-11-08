package com.ms.memsource

class Configuration {

    String userName
    String password

    static constraints = {
        userName    blank: false
        password    blank: false
    }
}
