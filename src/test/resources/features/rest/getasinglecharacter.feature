Feature: Get a single character by id
  As a Marvel API user
  I WANT TO make a request to the API
  SO THAT I can see the information of a Marvel character

  Scenario Outline: List character info successfully
    Given the user is in the Marvel developer portal
    When the user sends a request with a valid character id "<id>"
    Then the user gets a status code response OK and can see the character's name "<name>" and "<id>"
    Examples:
      | id      | name                     |
      | 1011136 | Air-Walker (Gabriel Lan) |
      | 1011334 | 3-D Man                  |
      | 1017100 | A-Bomb (HAS)             |


  Scenario Outline: Failed request
    Given a user is in the Marvel developer site
    When the user sends an invalid request, with an id "<Id>", a token "<token>" and a hash "<hash>"
    Then the user gets a status code response <code>
    Examples:
      | Id      | token                            | hash                             | code |
      | 1234    | f0a55c0526a2a7495cce01466a651fa6 | b7026387801d6606abe480e0dd879b2e | 404  |
      | 1011136 | aaa                              | b7026387801d6606abe480e0dd879b2e | 401  |
      | 1234    | aaa                              | myhash                           | 401  |