Feature:To obtain the value of a number in words
  I as a user of the DataAccess service
  want to obtain the number in words
  To know the textual value of the number

  @numberInWords
  Scenario: To obtain the numeric value in words
    Given The user wants to know the textual value of a number
    When The user sends a request to the API to obtain the textual value of a number
    Then The user should receive as a response the value of the number in words