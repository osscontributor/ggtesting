package demo

class MoneyService {

    def bankService

    List<String> getBankNames() {
        bankService.bankNames*.toUpperCase()
    }
}
