Feature: Number to Dollars
  As
  a user of the Number Conversion Service
  I WANT TO
  be able to enter a number
  SO THAT
  see its value in dollars

  Scenario Outline: Convert number to dollars
    Given a user that wants to know the value of a number in dollars
    When the user sends a request with the number "<number>"
    Then the user gets the value of the number in Dollars <text> and a status code response OK
    Examples:
      | number | text                                                  |
      | 200    | "two hundred dollars"                                 |
      | 1      | "one dollar"                                          |
      | 5000   | "five thousand dollars"                               |
      | -1     | "number too large dollars and number too large cents" |