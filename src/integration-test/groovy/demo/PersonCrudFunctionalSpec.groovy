package demo

// tag::class_declaration[]
import geb.spock.GebSpec
import grails.test.mixin.integration.Integration
import spock.lang.Stepwise

@Integration
@Stepwise
class PersonCrudFunctionalSpec extends GebSpec {

// end::class_declaration[]

// tag::test_create[]
    void "test creating people"() {
        when:
        go '/person/create'

        then:
        $('title').text() == 'Create Person'

        when:
        def form = $('form')
        form.firstName = 'Geddy'
        form.lastName = 'Lee'
        form.age = '63'
        form.create().click()

        then:
        $('title').text() == 'Show Person'

        when:
        go '/person/create'

        then:
        $('title').text() == 'Create Person'

        when:
        form = $('form')
        form.firstName = 'Alex'
        form.lastName = 'Lifeson'
        form.age = '63'
        form.create().click()

        then:
        $('title').text() == 'Show Person'
    }
// end::test_create[]

// tag::test_retrieve[]
    void "test retrieving people"() {
        when:
        go '/person/index'

        then:
        $("tr", 1).has('td', text: 'Geddy')
        $("tr", 1).has('td', text: 'Lee')
        $("tr", 2).has('td', text: 'Alex')
        $("tr", 2).has('td', text: 'Lifeson')
    }
// end::test_retrieve[]
// tag::class_declaration[]
}
// end::class_declaration[]
