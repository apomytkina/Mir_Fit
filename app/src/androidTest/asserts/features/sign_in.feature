Feature: Enter login details

  @ui @backend
  Scenario Outline: Successful sign_in
    Given I start the application
    When I click button SIGN IN
    And I click login field
    And I enter valid login <login>
    And I click password field
    And I enter valid password <password>
    And I click button SIGN IN
    Then I expect to see successful changing the screen
    Examples:
      | login        | password |
      | abcd | 123456   |