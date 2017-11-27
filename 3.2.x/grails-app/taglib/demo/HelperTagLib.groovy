package demo

class HelperTagLib {
    static defaultEncodeAs = [taglib:'html']

    static namespace = 'demo'

    def sayHello = { attrs ->
        out << "Hi there ${attrs.name}"
    }

    def repeat = { attrs, body ->
        int count = attrs.int('count', 3)
        count.times {
            out << body()
        }
    }
}
