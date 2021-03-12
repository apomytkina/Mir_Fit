Feature: Enter login details

  @ui @backend
  Scenario: Successful sign_in
    Given I start the application
    When I click button SIGN IN
    And I click login field
    And I enter 'valid' login 'abcd'
    And I click password field
    And I enter 'valid' password '123456'
    And I click button SIGN IN in authorize form
    Then I expect to see successful changing the screen
#    Examples:
#      | login | password |
#      | abcd  | 123456   |