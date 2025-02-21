Feature: User login functionality

  Scenario Outline: Successful login with valid credentials
    Given I am on the Saucedemo login page
    When I enter the username "<username>" and the password "<password>"
    And I click on the login button
    Then I should be redirected to the inventory page

    Examples:
      | username       | password      |
      | standard_user | secret_sauce  |
