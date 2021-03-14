Feature: Enter registration details

  @ui @backend
  Scenario Outline: Successful registration
    Given I start the application
    When I click button REGISTRATION
    And I click firstName field
    And I enter valid firstName "<firstName>"
    And I click secondName field
    And I enter valid secondName "<secondName>"
    And I click patronymic field
    And I enter valid patronymic "<patronymic>"
    And I click loginReg field
    And I enter valid loginReg "<loginReg>"
    And I click passwordReg field
    And I enter valid passwordReg "<passwordReg>"
    And I click button REGISTRATION in registration form
    Then I expect to see successful changing the screen
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Nikita | Tkachenko | Sergeevich | nikita123 | Nikita123 |

