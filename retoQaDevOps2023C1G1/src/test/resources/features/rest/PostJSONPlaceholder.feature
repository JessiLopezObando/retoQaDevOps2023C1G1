Feature: Create Posts in JSONPlaceholder API
  As a user of JSONPlaceholder API
  I want to create a new post
  So that I can add new content to the API

  @NewPost
  Scenario Outline: Create a new post
    Given I am on the JSONPlaceholder API
    When I create a new post with the <title>, <body> and the <userId>
    Then the response status code should be displayed as <code>
    And the response should contain the new post
    Examples:
      | title                               | body                                      | userId | code |
      | "This is a new post title"          | "This is the body of the first new post"  | 100    | 201  |
      | "This is the second new post title" | "This is the body of the second new post" | 101    | 201  |
      | "This is the third new post title"  | "This is the body of the third new post"  | 102    | 201  |