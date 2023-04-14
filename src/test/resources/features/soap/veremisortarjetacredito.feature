Feature: Validate credit cards
  AS an Admin
  I WANT TO check the credit cards
  SO THAT I can confirm they're valid

@CheckCreditCard
  Scenario Outline: validate credit card company
    Given an Admin that wants to check the company with the credit card number <credit card number>
    When the Admin send the number to the API
    Then the Admin can see the credit card company <company>
    Examples:
      | credit card number | company      |
      | "5290028003292942" | "MASTERCARD" |
      | "4716548619823318" | "VISA"       |
