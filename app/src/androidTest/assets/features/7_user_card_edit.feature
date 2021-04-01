Feature: Edit card for exist user

  @ui @backend
  Scenario Outline: Edit card error_empty_name
    Given I start the application
    When I swipe left on the card
    And I click button card_edit
    And I click new_name_card field
    And I enter new_name_card "<Name>"
    And I click button save_card
    Then I check error_edit_message_empty_name
    Examples: name_card
      | Name |
      |      |


  @ui @backend
  Scenario Outline: Edit card error_length_name
    Given I swipe left on the card
    When I click button card_edit
    And I click new_name_card field
    And I enter new_name_card "<Name>"
    And I click button save_card
    Then I check error_edit_message_length_name
    Examples: name_card
      | Name |
      | F    |
      | 1    |

  @ui @backend
  Scenario Outline: Edit card error_symbols_name
    Given I swipe left on the card
    When I click button card_edit
    And I click new_name_card field
    And I enter new_name_card "<Name>"
    And I click button save_card
    Then I check error_edit_message_symbols_name
    Examples: name_card
      | Name  |
      | F.... |
      | 1)3   |
      | ##    |


  @ui @backend
  Scenario Outline: Edit card name without error
    Given I swipe left on the card
    When I click button card_edit
    And I click new_name_card field
    And I enter new_name_card "<Name>"
    And I click button save_card
    Then I check change name_card "<Name>"
    Examples: name_card
      | Name    |
      | Card1   |
      | Nikita4 |
      | Card4   |

  @ui @backend
  Scenario Outline: Edit_cancel card name
    Given I swipe left on the card
    When I click button card_edit
    And I click new_name_card field
    And I enter new_name_card "<Name>"
    And I click button cancel_card
    Then I check no_change name_card
    Examples: name_card
      | Name      |
      | FominCard |