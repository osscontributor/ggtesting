package demo

import grails.test.mixin.TestFor
import spock.lang.Specification

// tag::class_begin[]
@TestFor(HelperTagLib)
class HelperTagLibSpec extends Specification {

// end::class_begin[]

    // tag::test_simple_tag[]
    void "test sayHello"() {
        expect:
        applyTemplate('<demo:sayHello name="Jeff"/>') == 'Hi there Jeff'
    }
    // end::test_simple_tag[]

    // tag::test_repeat[]
    void "test repeat"() {
        expect:
        applyTemplate('<demo:repeat count="4">the body </demo:repeat>') == 'the body the body the body the body '
    }
    // end::test_repeat[]

    // tag::test_repeat_no_count[]
    void "test repeat with no count"() {
        expect:
        applyTemplate('<demo:repeat>the body </demo:repeat>') == 'the body the body the body '
    }
    // end::test_repeat_no_count[]

    // tag::test_method_call[]
    void "test tag method calls"() {
        expect:
        tagLib.sayHello(name: 'Jeffrey') == 'Hi there Jeffrey'
        tagLib.repeat(count: 4) { 'yes' } == 'yesyesyesyes'
        tagLib.repeat() { 'yes' } == 'yesyesyes'
    }
    // end::test_method_call[]
// tag::class_end[]
}
// end::class_end[]

