Feature: Add cards for exist user

  @ui @backend
  Scenario Outline: Sign in user exist
    Given I start the application
    When I click button SIGN IN
    And I click login field
    And I enter login "<Login>"
    And I click password field
    And I enter password "<Password>"
    And I click button SIGN IN in authorize form
    Then I check success sign_in for actions with cards
    Examples: Valid login and password
      | Login     | Password  |
      | nikita123 | Nikita123 |

  @ui @backend
  Scenario Outline: Add card with incorrect_payment_system_number
    Given I click the button plus_add_card
    When I click number_card field
    And I enter number_card "<Number>"
    And I click name_card field
    And I enter name_card "<Name>"
    And I click button card_add
    Then I check error_message_ps_number
    Examples: number_card and name_card
      | Number        | Name        |
      | 2291000011112 | CardNikita1 |

  @ui @backend
  Scenario Outline: Add card with incorrect_length_number
    Given I click the button plus_add_card
    When I click number_card field
    And I enter number_card "<Number>"
    And I click name_card field
    And I enter name_card "<Name>"
    And I click button card_add
    Then I check error_message_length_number
    Examples: number_card and name_card
      | Number    | Name        |
      | 220100001 | CardNikita2 |

  @ui @backend
  Scenario Outline: Add card with empty_name
    Given I click the button plus_add_card
    When I click number_card field
    And I enter number_card "<Number>"
    And I click name_card field
    And I enter name_card "<Name>"
    And I click button card_add
    Then I check error_message_empty_name
    Examples: number_card and name_card
      | Number        | Name |
      | 2201000011112 |      |

  @ui @backend
  Scenario Outline: Add card with incorrect_length_name
    Given I click the button plus_add_card
    When I click number_card field
    And I enter number_card "<Number>"
    And I click name_card field
    And I enter name_card "<Name>"
    And I click button card_add
    Then I check error_message_length_name
    Examples: number_card and name_card
      | Number        | Name |
      | 2201000011112 | N    |

  @ui @backend
  Scenario Outline: Add card with incorrect_symbols_name
    Given I click the button plus_add_card
    When I click number_card field
    And I enter number_card "<Number>"
    And I click name_card field
    And I enter name_card "<Name>"
    And I click button card_add
    Then I check error_message_symbols_name
    Examples: number_card and name_card
      | Number        | Name |
      | 2201000011112 | N... |

  @ui @backend
  Scenario Outline: Add no_exist_card with full correct fields
    Given I click the button plus_add_card
    When I click number_card field
    And I enter number_card "<Number>"
    And I click name_card field
    And I enter name_card "<Name>"
    And I click button card_add
    Then I check toast add_no_exist_card
    Examples: number_card and name_card
      | Number           | Name        |
      | 2201000011111102 | CardNikita3 |


  @ui @backend
  Scenario Outline: Add exist_card with full correct fields
    Given I click the button plus_add_card
    When I click number_card field
    And I enter number_card "<Number>"
    And I click name_card field
    And I enter name_card "<Name>"
    And I click button card_add
    Then I check success add_exist_card
    Examples: number_card and name_card
      | Number          | Name        |
      | 220006118769339 | CardNikita4 |


  @ui @backend
  Scenario Outline: Add duplicate card with full correct fields
    Given I click the button plus_add_card
    When I click number_card field
    And I enter number_card "<Number>"
    And I click name_card field
    And I enter name_card "<Name>"
    And I click button card_add
    Then I check toast add_card_duplicate
    Examples: number_card and name_card
      | Number          | Name        |
      | 220006118769339 | CardNikita5 |

