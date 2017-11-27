package demo

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class MoneyServiceSpec extends Specification implements ServiceUnitTest<MoneyService> {

    Closure doWithSpring() {{->
        bankService(DummyBankSvc)
    }}

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
