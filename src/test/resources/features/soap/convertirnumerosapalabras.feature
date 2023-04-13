Feature: Convert a number to English words
  AS
    service user
  I WANT
    validate the conversion service
  SO THAT
    I can see the real and correct results in English

  @Conversion
  Scenario: Convert numbers to English words
    Given the user knows how to convert a number to words in English
    When  send the request to the API
    Then the correct result of the conversion should be obtained