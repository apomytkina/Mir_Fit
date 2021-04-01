Feature: Delete exist user card

  @ui @backend
  Scenario: delete card
    Given I start the application
    When I swipe left on the card
    And I click button card_delete
    And I click button_confirm
    Then I check success delete_card
