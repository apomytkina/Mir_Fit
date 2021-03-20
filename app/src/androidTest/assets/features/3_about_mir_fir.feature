Feature: About Mir Fit

  @ui @backend
  Scenario Outline: Check about mir fit
    Given I start the application
    When I click button SIGN IN
    And I click login field
    And I enter login "<Login>"
    And I click password field
    And I enter password "<Password>"
    And I click button SIGN IN in authorize form
    And I click setting button
    And I click about mir fit button
    And I swipe it down
    Then I go out from app
    Examples: Valid login and password
      | Login     | Password  |
      | nikita123 | Nikita123 |
