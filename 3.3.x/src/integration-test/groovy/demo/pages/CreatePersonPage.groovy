package demo.pages

import geb.Page

class CreatePersonPage extends Page {

    static url = '/person/create'

    static at = {
        title == 'Create Person'
    }
}
