#Feature: Sign in
#
#
#  @ui @backend
#  Scenario Outline: Data is valid and user exists
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to see successful changing the screen
#    Examples: Valid login and password
#      | Login     | Password  |
#      | nikita123 | Nikita123 |
#
#
#
#  @ui @backend
#  Scenario Outline: Login is empty
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to the same screen and error in the login field that is empty
#    Examples: Login is empty and password valid
#      | Login   | Password  |
#      |         | Abcde123 |
#
#    Examples: Login is empty and password invalid
#      | Login   | Password  |
#      |         |          |
#      |         | Abcd     |
#      |         | abc.123  |
#      |         | abcde123 |
#
#
#
#
#
#  @ui @backend
#  Scenario Outline: Login is too short
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to the same screen and error in the login field that is too short
#    Examples: Login is too short and password valid
#      | Login   | Password  |
#      | 1234    | Abcde123 |
#
#    Examples:  Login is too short and password invalid
#      | Login | Password |
#      | 1234  |          |
#      | 1234  | Abcd     |
#      | 1234  | abc.123  |
#      | 1234  | abcde123 |
#
#  @ui @backend
#  Scenario Outline: Login has wrong symbols
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to the same screen and error in the login field that has wrong symbols
#    Examples: Login has wrong symbol and password valid
#      | Login | Password  |
#      | abc.123 | Abcde123 |
#
#    Examples: Login has wrong symbol and password invalid
#      | Login | Password |
#      | abc.123 |          |
#      | abc.123 | Abcd     |
#      | abc.123 | abc.123  |
#      | abc.123 | abcde123 |
#
#  @ui @backend
#  Scenario Outline: Password is empty
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to the same screen and error in the password field that is empty
#    Examples: Password is invalid and login is valid
#      | Login     | Password |
#      | nikita123 |          |
#
#  @ui @backend
#  Scenario Outline: Password is too short
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to the same screen and error in the password field that is too short
#    Examples: Password is invalid and login is valid
#      | Login     | Password |
#      | nikita123 | Abcd     |
#
#  @ui @backend
#  Scenario Outline: Password has wrong symbols
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to the same screen and error in the password field that has wrong symbols
#    Examples: Password is invalid and login is valid
#      | Login     | Password |
#      | nikita123 | abc.123  |
#
#  @ui @backend
#  Scenario Outline: Password doesn't have capital letter
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to the same screen and error in the password field that doesn't have capital letter
#    Examples: Password is invalid and login is valid
#      | Login     | Password |
#      | nikita123 | abcde123 |
#
#  @ui @backend
#  Scenario Outline: Data is valid but user doesn't exist
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to the same screen and notification with error message
#    Examples: Valid login and password
#      | Login  | Password  |
#      | nikita | Nikita123 |