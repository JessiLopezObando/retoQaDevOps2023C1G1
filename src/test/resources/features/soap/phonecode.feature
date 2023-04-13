Feature: Get country phone code
  AS
  user
  I WANT TO
  get country phone code
  SO THAT
  be able to make international calls correctly

  Scenario: Get the phone code of a specific country
    Given that the user wants to know the telephone code according to his country
    When the user sends the request to the api with the abbreviation of your country
    Then the user gets the telephone code