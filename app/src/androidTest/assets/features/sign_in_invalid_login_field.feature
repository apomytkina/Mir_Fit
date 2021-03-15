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
    Then I expect to the same screen and error in the login field
    Examples: Login is invalid but password valid
      | Login   | Password  |
      |         | Nikita123 |
      | 1234    | Nikita123 |
      | abc.123 | Nikita123 |
    Examples: Password and login are invalid
      | Login   | Password |
      |         |          |
      |         | Abcd     |
      |         | 1234ABC  |
      |         | abc.123  |
      |         | abcde123 |
      | 1234    |          |
      | 1234    | Abcd     |
      | 1234    | 1234ABC  |
      | 1234    | abc.123  |
      | 1234    | abcde123 |
      | abc.123 |          |
      | abc.123 | Abcd     |
      | abc.123 | 1234ABC  |
      | abc.123 | abc.123  |
      | abc.123 | abcde123 |