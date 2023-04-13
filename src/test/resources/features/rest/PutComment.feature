Feature: Post Successful
  AS  JSON place holder user
  I WANT TO do a request to the system
  SO THAT I can post on the page

  @Put
  Scenario Outline: Put Successful
    Given the user is in the JSON place holder put page
    When the user sends the <id>, <title> and <body> of the comment he wants to edit
    Then the user sees a status <code> and the post he wants to edit <title> the comments
    Examples:
      | title      | body                 | id | code |
      | "Prueba 1" | "Hola Mundo"         | 1  | 200  |
      | "Prueba 2" | "Pasta con atun"     | 2  | 200  |
      | "Prueba 3" | "Cereales con leche" | 3  | 200  |
      | "Prueba 4" | "Arroz con pollo"    | 4  | 200  |
      | "Prueba 5" | "The best QA ever"   | 5  | 200  |