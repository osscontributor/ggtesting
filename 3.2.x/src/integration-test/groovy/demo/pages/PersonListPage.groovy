package demo.pages

import geb.Page

class PersonListPage extends Page {

    static url = '/person'

    static at = {
        title == 'Person List'
    }

    static content = {
        personRows { $('table tbody tr') }
        numberOfPersonRows { personRows.size() }
    }
}
