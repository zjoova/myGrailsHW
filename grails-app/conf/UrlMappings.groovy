class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
        }

        "/" {
            controller = "configuration"
            action = "index"
        }

        "/api/rest/project/list" {
            controller = "rest"
            action =  "projectList"
        }

        "500"(view:'/error')
    }
}
