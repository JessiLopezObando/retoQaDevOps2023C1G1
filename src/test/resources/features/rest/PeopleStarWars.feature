Feature:Star Wars Character Information
  I AS SWAPI user
  I WANT to access the information of the characters of Star Wars
  SO THAT I can learn about the movies


  Scenario Outline: Obtain information of a character by his ID
    Given that the user has the ID of a Star Wars character
    When the user makes a request with the character id <id>
    Then the user should see a response containing the character's name <name> and gender <gender>
    Examples:
      | id | name             | gender |
      | 1  | "Luke Skywalker" | "male" |
      | 2  | "C-3PO"          | "n/a"  |
      | 4  | "Darth Vader"    | "male" |


