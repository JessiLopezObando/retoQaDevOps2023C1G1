Feature:Retrieve user by ID on JSONPlaceholder
  I as the administrator of the JSONPlaceholder service
  want to make a request to retrieve a user by ID on Placeholder.
  In order to view the registered users

  @ConsultarUsuario
  Scenario Outline:Successful retrieval
    Given That the user is on the user registration page
    When The user sends a GET request with the user's <id>
    Then The user should observe a response code of <statusCode> and the user's information
    Examples:
      | id  | statusCode |
      | "5" | 200         |
      | "7" | 200         |
      | "9" | 200         |