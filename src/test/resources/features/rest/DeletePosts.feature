Feature: Delete Successful
  AS  JSON place holder user
  I WANT TO do a request to the system
  SO THAT I can delete the page posts

  @Delete
  Scenario Outline: Delete Successful
    Given the user is in the JSON place holder post page
    When the user sends the <post> that he wants to delete
    Then the user sees a status <code>
    Examples:
      | post | code |
      | 1    | 200  |
      |      | 404  |