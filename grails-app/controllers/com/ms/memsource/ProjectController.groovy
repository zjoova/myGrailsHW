package com.ms.memsource

class ProjectController {

    def index() {
        redirect(action: "list")
    }

    def list() {
        render(view: "list")
    }

}
