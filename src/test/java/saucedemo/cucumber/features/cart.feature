Feature: Add product to cart

  @Regression @Positive
  Scenario: Success adding product to cart
    Given user already on product page
    When user click add to cart
    Then the shopping cart badge display the number of products added