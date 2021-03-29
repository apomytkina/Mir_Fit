Feature: Delete user

  @ui @backend
  Scenario Outline: Cancel delete user
    Given I start the application
    When I click button SIGN IN
    And I click login field
    And I enter login "<Login>"
    And I click password field
    And I enter password "<Password>"
    And I click button SIGN IN in authorize form
    And I click setting button
    And I click delete user button
    And I click cancel button in dialog
    Then I leave the app
    Examples: Valid login and password
      | Login     | Password  |
      | nikita123 | Nikita123 |

  @ui @backend
  Scenario Outline: Data is valid and user exists
    Given I start the application
    When I click button SIGN IN
    And I click login field
    And I enter login "<Login>"
    And I click password field
    And I enter password "<Password>"
    And I click button SIGN IN in authorize form
    Then I expect to see successful changing the screen
    Examples: Valid login and password
      | Login     | Password  |
      | nikita123 | Nikita123 |


  @ui @backend
  Scenario Outline: Delete user
    Given I start the application
    When I click button SIGN IN
    And I click login field
    And I enter login "<Login>"
    And I click password field
    And I enter password "<Password>"
    And I click button SIGN IN in authorize form
    And I click setting button
    And I click delete user button
    And I click accept button in dialog
    Examples: Valid login and password
      | Login     | Password  |
      | nikita123 | Nikita123 |

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
    Examples: Valid login and password
      | Login  | Password  |
      | nikita | Nikita123 |

  @ui @backend
  Scenario Outline: Enter valid fields and this user not exist
    Given I start the application
    When I click button REGISTRATION
    And I click firstName field
    And I enter firstName "<firstName>"
    And I click secondName field
    And I enter secondName "<secondName>"
    And I click patronymic field
    And I enter patronymic "<patronymic>"
    And I click loginReg field
    And I enter loginReg "<loginReg>"
    And I click passwordReg field
    And I enter passwordReg "<passwordReg>"
    And I click button REGISTRATION in registration form
    Then I expect to see successful changing the screen
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Nikita | Tkachenko | Sergeevich | nikita123 | Nikita123 |

