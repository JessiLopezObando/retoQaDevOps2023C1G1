Feature: Validate bank id numbers
  AS an Admin
  I WANT TO check the bank id numbers
  SO THAT I can confirm the bank is registered

@CheckBank
  Scenario Outline: validate bank id
    Given an Admin that wants to check a bank with its id <blz code>
    When the Admin send the blz code to the API
    Then the Admin can see the bank name <bank name>
    Examples:
      | blz code   | bank name                            |
      | "10020500" | "Sozialbank"                         |
      | "79063060" | "Raiffeisenbank Estenfeld-Bergtheim" |