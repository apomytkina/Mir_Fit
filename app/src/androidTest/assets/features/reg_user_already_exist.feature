Feature: Enter valid fields and this user exist

  @ui @backend
  Scenario Outline: Unsuccessful registration
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
    Then I expect to see toast message
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Nikita | Tkachenko | Sergeevich | nikita123 | Nikita123 |

