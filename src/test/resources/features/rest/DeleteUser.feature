Feature: Delete a user from the Fake Store API

  As a user
  I want to delete an existing user
  So that I can remove unnecessary data from the system

  Scenario Outline: Delete an existing user
    Given the user has access to Fake Store API
    When the user makes a DELETE request with the "<ID>" of the user to delete
    Then the response status code should be <code>
    Examples:
      | ID       | code |
      | 1        | 200  |
      | 2        | 200  |
      | usercode | 400  |


