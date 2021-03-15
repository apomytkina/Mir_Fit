Feature: Sign in application

  @ui @backend
  Scenario Outline: Data is valid but user doesn't exist
    Given I start the application
    When I click button SIGN IN
    And I click login field
    And I enter login "<Login>"
    And I click password field
    And I enter password "<Password>"
    And I click button SIGN IN in authorize form
    Then I expect to the same screen and notification with error message
    Examples: Valid Login and Password
      | Login  | Password  |
      | nikita | Nikita123 |