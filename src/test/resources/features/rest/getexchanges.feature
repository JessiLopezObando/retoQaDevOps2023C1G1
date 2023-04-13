Feature: List Exchanges by id
  AS coingecko user
  I WANT TO make a request to list exchanges
  SO I can see the information about the exchange volume in Bitcoin

  Scenario: List exchanges successfully
    Given the user is in the coingecko web page
    When the user sends a request to the Get Exchanges service
    Then the user gets a status code response OK