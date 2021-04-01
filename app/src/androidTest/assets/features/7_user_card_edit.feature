Feature: Edit card for exist user

  @ui @backend
  Scenario Outline: Edit card name
    Given I start the application
    When I swipe left on the card
    And I click button card_edit
    And I click new_name_card field
    And I enter new_name_card "<Name>"
    And I click button save_card
    Then I check change name_card
    Examples: name_card
      | Name      |
      | FominCard |

  @ui @backend
  Scenario Outline: Edit_cancel card name
    Given I start the application
    When I swipe left on the card
    And I click button card_edit
    And I click new_name_card field
    And I enter new_name_card "<Name>"
    And I click button cancel_card
    Then I check no_change name_card
    Examples: name_card
      | Name      |
      | FominCard |