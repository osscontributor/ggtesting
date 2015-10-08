package demo

import grails.test.mixin.TestFor
import spock.lang.Specification

// tag::class_begin[]
@TestFor(DemoController)
class DemoControllerSpec extends Specification {
    // end::class_begin[]

    // tag::command_object_spec[]
    void "test command object population"() {
        when:
        params.width = 4
        params.height = 7
        params.name = 'fidget'
        def model = controller.createWidget()
        def widget = model.widget

        then:
        widget.width == 4
        widget.height == 7
        widget.name == 'fidget'
    }
    // end::command_object_spec[]

    // tag::string_conversion_spec[]
    void "test that empty strings are not converted to null"() {
        when: 'conversion of empty strings to null is disabled in application.yml'
        params.width = 4
        params.height = 7
        params.name = ''
        def model = controller.createWidget()
        def widget = model?.widget

        then: 'unit tests respect that config value'
        !widget.hasErrors()
        widget.width == 4
        widget.height == 7
        widget.name == ''
    }
    // end::string_conversion_spec[]

    // tag::class_end[]
}
// end::class_end[]
