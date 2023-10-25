Feature: Login to saucedemo website

  @Regression @Positive
  Scenario: Success Login
    Given user on login page
    When user input valid username
    And user input valid password
    And user click login button
    Then user move to products page

  @Regression @Negative
  Scenario: Failed Login
    Given user on login page
    When user input invalid username
    And user input invalid password
    And user click login button
    Then user get error message
