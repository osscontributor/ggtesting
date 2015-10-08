package demo

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(MoneyService)
class MoneyServiceSpec extends Specification {
    static doWithSpring = {
        bankService(DummyBankSvc)
    }

    void "test something"() {
        expect:
        service.bankNames == ['BANK ONE', 'BANK TWO', 'BANK THREE']
    }
}

class DummyBankSvc {
    List<String> getBankNames() {
        ['Bank One', 'Bank Two', 'Bank Three']
    }
}
