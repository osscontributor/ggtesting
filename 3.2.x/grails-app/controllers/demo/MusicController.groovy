package demo

import grails.rest.RestfulController

// tag::begin_class[]
class MusicController extends RestfulController<Album> {

    static responseFormats = ['json', 'xml']

    public MusicController() {
        super(Album)
    }
// end::begin_class[]

    // tag::query_filter[]
    protected List<Album> listAllResources(Map m) {
        Album.where {
            if(m.genre) {
                genre == m.genre
            }
        }.list()
    }
    // end::query_filter[]
// tag::class_end[]
}
// end::class_end[]

