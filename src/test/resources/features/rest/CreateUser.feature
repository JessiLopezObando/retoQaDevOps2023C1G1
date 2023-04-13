Feature: Create a user in the API
  AS reqres administrator
  I WANT
  create user
  SO THAT
  more people interact with the API

  @CreateUser
  Scenario Outline: Successful User Creation
    Given the manager is in the API
    When the administrator sends a POST request with a <name> and a <job>
    Then The admin then sees a status response <code> and the JSON structure
    Examples:
      | name     | job       | code |
      | "morphy" | "leader"  | 201  |
      | "maxi"   | "captain" | 201  |
      | "jesi"   | "teacher" | 201  |
