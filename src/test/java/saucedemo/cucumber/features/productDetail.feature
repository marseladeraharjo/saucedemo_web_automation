Feature: View saucedemo product detail

  @Regression @Positive
  Scenario: Success view product detail
    Given user on product page
    When user click product name
    Then user can view product detail
