#Feature: Sign in application

#  @ui @backend
#  Scenario Outline: Data is valid and user exists
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
 #   And I click button SIGN IN in authorize form
#    Then I expect to see successful changing the screen
#    Examples: Valid Login and Password
#      | Login | Password |
#      | nikita123 | Nikita123 |