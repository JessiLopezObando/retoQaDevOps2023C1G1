Feature:Querying publications by ID on JSONPlaceholder
  I as the administrator of the service
  I want to make a request to query publications on Placeholder
  in order to see the content


  @ConsultarPublicaciones
  Scenario Outline:Successful query
    Given That the user is on the registration page
    When The user sends a GET request with the <id>
    Then The user sees a status response code of <statusCode> and the information of the publication
    Examples:
      | id   | statusCode |
      | "4"  | 200          |
      | "12" | 200          |
      | "7"  | 200          |