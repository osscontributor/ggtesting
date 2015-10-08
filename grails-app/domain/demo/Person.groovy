package demo

class Person {
    String firstName
    String lastName
    Integer age

    static constraints = {
        age range: 0..120
    }
}
