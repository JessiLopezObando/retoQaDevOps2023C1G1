Feature:Get the language of a country
  I as a user of the ListOfLanguages service
  I want to obtain information about the language of a country
  To find out the language of the country


  @language
  Scenario: List of languages
    Given The user wants to know the language of a country
    When The user sends a request to the API to obtain the language
    Then The user receives the language of the country as a response