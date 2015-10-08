package demo

class DemoController {

    def index() { }

    def createWidget(Widget w) {
        [widget: w]
    }
}

class Widget {
    int width
    int height
    String name

    static constraints = {
        width min: 1
        height min: 1
    }
}
