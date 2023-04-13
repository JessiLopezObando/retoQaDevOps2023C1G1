Feature: Register Successful
  AS  reqres user
  I WANT TO make a resgistration to the system
  SO THAT I can use the system services

  @Register
  Scenario Outline: Register Successful
    Given the user is in the register page
    When the user send a registration request with the <email> and the <password>
    Then the user see a status <code> response code and an id with a token
    Examples:
      | email                    | password     | code |
      | "eve.holt@reqres.in"     | "pistol"     | 200  |
      | "Juan@reqres.in"         | "pastor"     | 400  |
      | "Juan@reqres.in"         | "juan"       | 400  |
      | "eve@reqres.in"          | "cualquiera" | 400  |
      | "george.bluth@reqres.in" | "cualquiera" | 200  |