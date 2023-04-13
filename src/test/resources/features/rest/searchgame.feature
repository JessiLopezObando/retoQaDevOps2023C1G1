Feature: Game List
  As a user of the Freetogame website
  I want to know the list of all games
  So that I can choose a game.

  @GameList
  Scenario Outline: Get all games
    Given the player is on the appropriate page to make the query
    When the player makes a request to search for the game by <id>
    Then the player receives a <code> status with the game found.
    Examples:
      | id  | code |
      | 453 | 200  |
      | 453 | 200  |