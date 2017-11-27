package demo.pages

import geb.Page

class CreatePersonPage extends Page {

    static url = '/person/create'

    static at = {
        title == 'Create Person'
    }

    static content = {
        submitButton { $('#create', 0) }
        firstNameInputField { $('#firstName', 0) }
        lastNameInputField { $('#lastName', 0) }
        ageInputField { $('#age', 0) }
    }

    void populateCreatePersonForm(String firstName, String lastName, String age) {
        firstNameInputField << firstName
        lastNameInputField << lastName
        ageInputField << age
        submitButton.click()
    }
}
