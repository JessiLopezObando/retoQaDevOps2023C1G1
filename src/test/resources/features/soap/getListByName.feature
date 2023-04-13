Feature: Find Person by Name
  AS
  user
  I WANT TO
  obtain information about a particular person based on their unique name
  SO THAT
  I can access their relevant information.

  @findPersonByName
  Scenario Outline: Obtain Person Information by Name
    Given a user that wants to know the Person Information by Name
    When the user sends a request to obtain the person's information with the provided <name>
    Then the user should receive the person's information and the status code <code>
    Examples:
      | name              | code |
      | "Johnson,Brian T" | 200  |
      | "Newton,Dave R"   | 200  |
      | ""                | 200  |
      | "!!!!"            | 200  |
      | "<"               | 400  |