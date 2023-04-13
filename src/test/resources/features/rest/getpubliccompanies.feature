Feature: List public companies
  AS coingecko user
  I WANT TO make a request to list public companies
  SO I can get information about public companies investing in Bitcoin and Ethereum

  Scenario: List public companies successfully
    Given user is in the coingecko web page
    When the user sends a request to the Get public companies service
    Then the user gets a status code response Ok and sees the information about public companies investing in Bitcoin and Ethereum
