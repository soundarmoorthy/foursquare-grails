package search

class UrlMappings {

    static mappings = {
//        delete "/$controller/$id(.$format)?"(action:"delete")
//        get "/$controller(.$format)?"(action:"index")
//        get "/$controller/$id(.$format)?"(action:"show")
//        put "/$controller/$id(.$format)?"(action:"update")
//        patch "/$controller/$id(.$format)?"(action:"patch")

        post "/search"(controller: 'Search', action:"doSearch")
        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
