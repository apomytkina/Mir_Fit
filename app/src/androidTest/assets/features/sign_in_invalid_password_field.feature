Feature: Sign in application

  @ui @backend
  Scenario Outline: Data isn' t valid
    Given I start the application
    When I click button SIGN IN
    And I click login field
    And I enter login "<Login>"
    And I click password field
    And I enter password "<Password>"
    And I click button SIGN IN in authorize form
    Then I expect to the same screen and error in the password field
    Examples: Password is invalid but login is valid
      | Login     | Password |
      | nikita123 |          |
      | nikita123 | Abcd     |
      | nikita123 | abc.123  |
      | nikita123 | abcde123 |