Feature: randomly query a cat curiosity
  AS Admin Cats Data API
  I WANT
  randomly consult a cat curiosity
  SO THAT
  to validate the information and read it to my family

  @cat
  Scenario:successful random query of cat trivia
    Given I'm on the cat data API as an administrator
    When I send a GET request for random trivia
    Then I see a "200" status response and a JSON structure

