Feature: Delete card for exist user

  @ui @backend
  Scenario : Delete exist card
    Given I start the application
    When I swipe left on the card
    And I click button card_delete
    And I click button confirm_delete
    Then I check card_no_exist

