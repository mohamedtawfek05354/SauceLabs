Feature: Product details page functionality

  Scenario Outline: View product details
    Given I am logged in with valid credentials
    When I click on a product "<productName>" on the inventory page
    Then I should see the product details page
    And I should see the product name, description, price, and image
    And Click on view all items from slide menu
    Examples:
      | productName         |
      | Sauce Labs Backpack |
      |Sauce Labs Bolt T-Shirt|
      |Sauce Labs Onesie      |

  Scenario Outline: Add product to cart from product details page
    Given I am on the product "<productName>" details page
    When I click on the Add to Cart button
    Then the product should be added to the cart
    Examples:
      | productName             |
      | Sauce Labs Backpack     |
      | Sauce Labs Bolt T-Shirt |
      | Sauce Labs Onesie12     |