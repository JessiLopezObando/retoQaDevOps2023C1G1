Feature: Get the sum
  AS user
  I WANT TO add two numbers
  SO THAT I can count things

  @sumas
  Scenario Outline: Sum
    Given a user that wants to add two numbers <Number1> to <Number2>
    When the user sends the request to the sum api
    Then the user gets the result <Resultado>

    Examples:
      | Number1 | Number2 | Resultado |
      | 10      | 5       | "15"      |
      | 20      | 10      | "30"      |
      | 11      | 11      | "22"      |