Feature:Get the name of the currency of a country
  I as a user of the CurrencyName service
  want to obtain information about the currency of a country
  In order to know the name of the currency of a country


  @Moneda
  Scenario: List of currency names
    Given The user wants to know the name of a currency
    When The user sends a request to the API to obtain the name of the currency
    Then The user receives the name of the currency as a response

