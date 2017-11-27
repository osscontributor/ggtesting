
package demo

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

// tag::person_spec[]
@TestFor(Person)
class PersonSpec extends Specification {

    @Unroll('validate on a person with age #personAge should have returned #shouldBeValid')
    void "test age validation"() {
        expect:
        new Person(age: personAge).validate(['age']) == shouldBeValid

        where:
        personAge | shouldBeValid
        -2        | false
        -1        | false
        0         | true
        1         | true
        119       | true
        120       | true
        121       | false
        122       | false
    }
}
// end::person_spec[]
