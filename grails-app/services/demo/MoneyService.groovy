package demo

import grails.transaction.Transactional

@Transactional
class MoneyService {

    def bankService

    List<String> getBankNames() {
        bankService.bankNames*.toUpperCase()
    }
}
