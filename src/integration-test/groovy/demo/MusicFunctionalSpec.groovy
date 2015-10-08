package demo

import grails.test.mixin.integration.Integration
import grails.web.mime.MimeType
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.springframework.http.HttpStatus
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

// tag::begin_class[]
@Integration
@Stepwise
class MusicFunctionalSpec extends Specification {

    @Shared
    def rest = new RESTClient('http://localhost:8080/')

// end::begin_class[]

    // tag::no_albums[]
    void "test that no albums exist"() {
        when:
        def resp = rest.get(path: 'albums')

        then:
        resp.status == HttpStatus.OK.value()
        resp.contentType == MimeType.JSON.name
        resp.data.size() == 0
    }
    // end::no_albums[]

    // tag::create_albums[]
    void "test creating albums"() {
        when:
        def resp = rest.post(path: 'albums',
                body: [artistName: 'King Crimson',
                       title: 'Red',
                       genre: 'PROGRESSIVE_ROCK'],
                requestContentType: ContentType.JSON)

        then:
        resp.status == HttpStatus.CREATED.value()
        resp.contentType == 'application/json'

        and:
        resp.data.artistName == 'King Crimson'
        resp.data.title == 'Red'
        resp.data.genre.name == 'PROGRESSIVE_ROCK'

        when:
        resp = rest.post(path: 'albums',
                body: [artistName: 'Riverside',
                       title: 'Love, Fear and the Time Machine',
                       genre: 'PROGRESSIVE_ROCK'],
                requestContentType: ContentType.JSON)

        then:
        resp.status == HttpStatus.CREATED.value()
        resp.contentType == 'application/json'

        and:
        resp.data.artistName == 'Riverside'
        resp.data.title == 'Love, Fear and the Time Machine'
        resp.data.genre.name == 'PROGRESSIVE_ROCK'

        when:
        resp = rest.post(path: 'albums',
                body: [artistName: 'Johnny Winter',
                       title: 'Progressive Blues Experiment',
                       genre: 'BLUES'],
                requestContentType: ContentType.JSON)

        then:
        resp.status == HttpStatus.CREATED.value()
        resp.contentType == 'application/json'

        and:
        resp.data.artistName == 'Johnny Winter'
        resp.data.title == 'Progressive Blues Experiment'
        resp.data.genre.name == 'BLUES'

        when:
        resp = rest.post(path: 'albums',
                body: [artistName: 'Motörhead',
                       title: "No Sleep 'til Hammersmith",
                       genre: 'HEAVY_METAL'],
                requestContentType: ContentType.JSON)

        then:
        resp.status == HttpStatus.CREATED.value()
        resp.contentType == 'application/json'

        and:
        resp.data.artistName == 'Motörhead'
        resp.data.title == "No Sleep 'til Hammersmith"
        resp.data.genre.name == 'HEAVY_METAL'
    }
    // end::create_albums[]

    // tag::get_albums[]
    void 'test retrieving list of albums defaults to JSON'() {
        when:
        def resp = rest.get(path: 'albums')

        then:
        resp.status == HttpStatus.OK.value()
        resp.contentType == MimeType.JSON.name
        resp.data.size() == 4

        and:
        resp.data[0].artistName == 'King Crimson'
        resp.data[0].title == 'Red'
        resp.data[0].genre.name == 'PROGRESSIVE_ROCK'

        and:
        resp.data[1].artistName == 'Riverside'
        resp.data[1].title == 'Love, Fear and the Time Machine'
        resp.data[1].genre.name == 'PROGRESSIVE_ROCK'

        and:
        resp.data[3].artistName == 'Motörhead'
        resp.data[3].title == "No Sleep 'til Hammersmith"
        resp.data[3].genre.name == 'HEAVY_METAL'

        and:
        resp.data[2].artistName == 'Johnny Winter'
        resp.data[2].title == 'Progressive Blues Experiment'
        resp.data[2].genre.name == 'BLUES'
    }
    // end::get_albums[]

    // tag::by_genre[]
    void "test retrieving albums by genre"() {
        when:
        def resp = rest.get(path: 'albums',
                query: [genre: 'PROGRESSIVE_ROCK']) // <1>

        then:
        resp.status == HttpStatus.OK.value()
        resp.contentType == MimeType.JSON.name
        resp.data.size() == 2

        when:
        resp = rest.get(path: 'albums',
                query: [genre: 'HEAVY_METAL'])      // <2>

        then:
        resp.status == HttpStatus.OK.value()
        resp.contentType == MimeType.JSON.name
        resp.data.size() == 1

        when:
        resp = rest.get(path: 'albums',
                query: [genre: 'BLUES'])            // <3>

        then:
        resp.status == HttpStatus.OK.value()
        resp.contentType == MimeType.JSON.name
        resp.data.size() == 1
    }
    // end::by_genre[]

// tag::end_class[]
}
// end::end_class[]