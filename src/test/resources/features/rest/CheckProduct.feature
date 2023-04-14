Feature: Check details of a product using its ID

  AS an user
  I WANT TO check the product info
  SO THAT I can make a purchase

  @CheckProduct
  Scenario Outline: Obtener detalles de un producto existente

    Given the user has access to the Fake Store API
    When the user makes a GET petition with the ID <product_id>
    Then the user see a status <code> response code and can see the "<title>" and <price>

    Examples:
      | product_id | code | title                                                 | price  |
      | 1          | 200  | Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops | 109.95 |
      | 3          | 200  | Mens Cotton Jacket                                    | 55.99  |
