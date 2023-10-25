Feature: Logout from saucedemo website

  @Regression @Positive
  Scenario: Success logout
    Given user login to saucedemo website
    When user navigate left sidebar
    And user click logout
    Then user move to login page