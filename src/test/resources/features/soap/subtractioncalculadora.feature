Feature: Subtraction of two numbers with the calculator
  AS
  user of the service calculator
  I WANT TO
  get the result of the subtraction of two integers
  SO THAT
  see the result of the operation

  Scenario: subtract two numbers
    Given that the user has access to the subtraction service of the calculator
    When sends a subtraction request with two integers
    Then should receive a successful response with the result of the subtraction