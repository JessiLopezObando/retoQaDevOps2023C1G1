Feature: Find City by ZIP Code
  AS
  user
  I WANT TO
  obtain information about a particular city based on its ZIP Code
  SO THAT
  I can access its relevant information.

  @findCityByZIP
  Scenario Outline: Obtain City Information by ZIP Code
    Given a user that wants to know the City Information by ZIP Code
    When the user sends a request to get the city's information with the provided <zipCode>
    Then the user should receive the city's information and the status code <code>
    Examples:
      | zipCode | code |
      | "10001" | 200  |
      | "90210" | 200  |
      | ""      | 400  |
      | "!"     | 400  |
      | "<"     | 400  |
