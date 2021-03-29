Feature: Registration with full fields

  ########################################### FIRST_NAME
  @ui @backend
  Scenario Outline: Registration with incorrect_empty firstName
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
    Then I expect to see error_empty_first_name
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      |  | Valentkin | Petrovich | nikita11 | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_length firstName
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
    Then I expect to see error_length_first_name
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | V | Valentkin | Petrovich | nikita11 | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_alphabet firstName
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
    Then I expect to see error_alphabet_first_name
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | niifk2323 | Valentkin | Petrovich | nikita11 | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_case firstName
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
    Then I expect to see error_case_first_name
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | ValentiN | Valentkin | Petrovich | nikita11 | Nikita11 |


########################################### SECOND_NAME


    @ui @backend
  Scenario Outline: Registration with incorrect_empty secondName
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
    Then I expect to see error_empty_second_name
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin |  | Petrovich | nikita11 | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_length secondName
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
    Then I expect to see error_length_second_name
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | V | Petrovich | nikita11 | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_alphabet secondName
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
    Then I expect to see error_alphabet_second_name
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valentkin434 | Petrovich | nikita11 | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_case secondName
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
    Then I expect to see error_case_second_name
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | ValentkiN | Petrovich | nikita11 | Nikita11 |


########################################### PATRONYMIC

  @ui @backend
  Scenario Outline: Registration with incorrect_empty patronymic
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
    Then I expect to see error_empty_patronymic
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valenkin |  | nikita11 | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_length patronymic
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
    Then I expect to see error_length_patronymic
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valenkin | P | nikita11 | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_alphabet patronymic
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
    Then I expect to see error_alphabet_patronymic
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valentkin | Petrovich32 | nikita11 | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_case patronymic
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
    Then I expect to see error_case_patronymic
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valentkin | PetrovicH | nikita11 | Nikita11 |




########################################### LOGIN
  @ui @backend
  Scenario Outline: Registration with incorrect_empty login
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
    Then I expect to see error_empty_login
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valenkin | Valenteev |  | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_length login
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
    Then I expect to see error_length_login
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valenkin | Parfeev | nik | Nikita11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_alphabet login
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
    Then I expect to see error_alphabet_login
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valentkin | Petrovich | nikita.11 | Nikita11 |


########################################### PASSWORD
  @ui @backend
  Scenario Outline: Registration with incorrect_empty password
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
    Then I expect to see error_empty_password
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valenkin | Sergeevich | nikita11 |  |

  @ui @backend
  Scenario Outline: Registration with incorrect_length password
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
    Then I expect to see error_length_password
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valenkin | Sergeevich | nikita11 | Nik |

  @ui @backend
  Scenario Outline: Registration with incorrect_alphabet password
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
    Then I expect to see error_alphabet_password
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valentkin | Petrovich | nikita11 | Nik.ta11 |

  @ui @backend
  Scenario Outline: Registration with incorrect_case password
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
    Then I expect to see error_case_password
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Valentin | Valentkin | Petrovich | nikita11 | nikita11 |











  @ui @backend
  Scenario Outline: Enter valid fields and this user exist
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
    Then I expect to see successful registration
    Examples:
      | firstName | secondName | patronymic | loginReg | passwordReg |
      | Sergey | Tkachenko | Sergeevich | nikita123456789 | Nikita123 |
