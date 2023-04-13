Feature: Delete a product by id in the dummyJOSN API
  AS API dummy JOSN admin
  I WANT
  delete products with a specific id
  SO THAT
  the information for users is updated

  @ProductsDelete
  Scenario Outline:successful removal of a product
    Given I'm on the dummyJSON API as an administrator
    When I send a DELETE request with the specific <id> of a product
    Then I see a status response 200 and a JSON structure
    Examples:
      | id  |
      | "1" |
      | "3" |
      | "6" |
      | "9" |