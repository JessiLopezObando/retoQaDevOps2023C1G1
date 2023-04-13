Feature: Authentication to Restful Booker API
  I AS Restful Booke administrator
  I WANT make an authentication in the system
  SO THAT I can enter the system

  @Register
  Scenario Outline: Authentication Successful
    Given the admin is on the authentication page
    When the administrator sends an authentication request with username <username> and password <password>
    Then  the administrator should see a status response code <code> and a token
    Examples:
      | username   | password      | code |
      | "admin"    | "password123" | 200  |
      | "user123"  | "pass123"     | 200  |
      | "testuser" | "123test"     | 200  |





