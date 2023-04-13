Feature: Create Products
  As an administrator of the product management system
  I want to be able to create products
  So that I can add new products to the system.

  Scenario Outline: Create a new product
    Given the administrator is on the FakerProducts page in the create product section
    When the administrator creates a new product with <title>, <price>, <description>, <image>, <category>
    Then the administrator should see a message with information about the new product with a <code> status
    Examples:
      | title           | price | description                   | image                               | category      | code |
      | "Headphones"    | 29.99 | "Wireless headphones"         | "https://i.pravatar.cc/300?img=104" | "Electronics" | 200  |
      | "Basic T-shirt" | 12.99 | "100% cotton unisex T-shirt"  | "https://i.pravatar.cc/300?img=20"  | "Fashion"     | 200  |
      | "Cookbook"      | 24.99 | "Italian cuisine recipe book" | "https://i.pravatar.cc/300?img=332" | "Books"       | 200  |
