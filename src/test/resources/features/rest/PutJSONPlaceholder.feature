Feature: Update a Post in JSONPlaceholder API
  As a user of JSONPlaceholder API
  I want to update a post by its Id
  So that I can modify the details of a specific post

  @Update
  Scenario Outline: Update an existing post
    Given the JSONPlaceholder API is available to use
    When I make a PUT request to update the post with <id>, <title>, <body>, <UserId>
    Then the response status code that should be received is <code>
    And the response body should contain the updated post details
    Examples:
      | id   | title            | body                                                                               | UserId | code |
      | "4"  | "Rest"           | "REST (Representational State Transfer"                                            | 22     | 200  |
      | "11" | "Peticion PUT"   | "solicitud HTTP utilizado para actualizar un recurso existente en un servidor web" | 19     | 200  |
      | "13" | "API"            | "Application Programming Interface"                                                | 11     | 200  |
      | "22" | "Servicios Rest" | "Representational State Transfer"                                                  | 4      | 200  |