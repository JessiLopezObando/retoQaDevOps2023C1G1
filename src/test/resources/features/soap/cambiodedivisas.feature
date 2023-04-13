Feature: Get the actual exchange
  AS user
  I WANT TO exchange my money
  SO THAT I can travel to any country

  @divisas
  Scenario Outline: Change currency
    Given a user that wants to know the exchange of their money in the <Country> to <Ocountry> the amount of <Amount>
    When the user sends the request to the currency api
    Then the user gets the money exchange at <Resultado>

    Examples:
      | Country | Ocountry | Amount | Resultado            |
      | "COP"   | "USD"    | 100000 | "22.499601661110628" |
      | "USD"   | "CRC"    | 10     | "5346.8906037221977"  |
      | "CRC"   | "CNY"    | 500    | "6.431506384134746" |
