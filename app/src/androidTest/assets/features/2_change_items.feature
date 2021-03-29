#Feature: Change settings
#
#  @ui @backend
#  Scenario Outline: Cancel
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click cancel button when was clicked cancel to change login or password button
#    Examples: Valid login and password
#      | Login     | Password  |
#      | nikita123 | Nikita123 |
#
#  @ui @backend
#  Scenario Outline: Change password and cancel
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change password button
#    And I click cancel button when was clicked change password button
#    And I click cancel button when was clicked cancel to change login or password button
#    Examples: Valid Login and Password
#      | Login     | Password  |
#      | nikita123 | Nikita123 |
#
#  @ui @backend
#  Scenario Outline: Change login and cancel
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change login button
#    And I click cancel button when was clicked change login button
#    And I click cancel button when was clicked cancel to change login or password button
#    Examples: Valid login and password
#      | Login     | Password  |
#      | nikita123 | Nikita123 |
#
#  @ui @backend
#  Scenario Outline: Change login and save, new login is empty
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change login button
#    And I click change login field
#    And I enter new login "<NewLogin>"
#    And I save login changes
#    Then I will see the error in the field of login that is empty
#    Examples: Invalid login
#      | Login     | Password  | NewLogin |
#      | nikita123 | Nikita123 |  |
#
#  @ui @backend
#  Scenario Outline: Change login and save, new login is too short
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change login button
#    And I click change login field
#    And I enter new login "<NewLogin>"
#    And I save login changes
#    Then I will see the error in the field of login that is too short
#    Examples: Invalid login
#      | Login     | Password  | NewLogin |
#      | nikita123 | Nikita123 | abcd |
#
#  @ui @backend
#  Scenario Outline: Change login and save, new login has wrong symbols
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change login button
#    And I click change login field
#    And I enter new login "<NewLogin>"
#    And I save login changes
#    Then I will see the error in the field of login that has wrong symbols
#    Examples: Invalid login
#      | Login     | Password  | NewLogin |
#      | nikita123 | Nikita123 | A.1fgtr |
#
#  @ui @backend
#  Scenario Outline: Change login and save, the same login
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change login button
#    And I click change login field
#    And I enter new login "<NewLogin>"
#    And I save login changes
#    Then I will see the warning about login
#    Examples: Valid new login and the same
#      | Login     | Password  | NewLogin |
#      | nikita123 | Nikita123 | nikita123 |
#
#  @ui @backend
#  Scenario Outline: Change login and save
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change login button
#    And I click change login field
#    And I enter new login "<NewLogin>"
#    And I save login changes
#    Then I will see the notification about login
#    Examples: Valid new login
#      | Login     | Password  | NewLogin |
#      | nikita123 | Nikita123 | nikita12 |
#
#  @ui @backend
#  Scenario Outline: User doesn't exist, sign in after login change
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to the same screen and notification with error message
#    Examples: Old login
#      | Login  | Password  |
#      | nikita123 | Nikita123 |
#
#  @ui @backend
#  Scenario Outline: User exists, login change again
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change login button
#    And I click change login field
#    And I enter new login "<NewLogin>"
#    And I save login changes
#    Then I will see the notification about login
#    Examples: New login sign in
#      | Login     | Password  | NewLogin |
#      | nikita12 | Nikita123 | nikita123 |
#
#  @ui @backend
#  Scenario Outline: User exists, sign in
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to see successful changing the screen
#    Examples: Sign in with first login
#      | Login     | Password  |
#      | nikita123 | Nikita123 |
#
#  @ui @backend
#  Scenario Outline: Change password and save and get error "Неверный пароль"
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change password button
#    And I click change old password field
#    And I enter old password "<OldPassword>"
#    And I click change new password field
#    And I enter new password "<NewPassword>"
#    And I save password changes
#    Then I will see the error in the field of old password
#    Examples: Invalid old and valid new password
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 |   | Nikita12 |
#      | nikita123 | Nikita123 | Nik | Nikita12 |
#      | nikita123 | Nikita123 | A.1fgtr | Nikita12 |
#      | nikita123 | Nikita123 | nikita123 | Nikita12 |
#
#    Examples: Invalid old password is empty and invalid new password
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 |  |  |
#      | nikita123 | Nikita123 |  | Niki |
#      | nikita123 | Nikita123 |  | A.2fgtr |
#      | nikita123 | Nikita123 |  | nikita12 |
#
#    Examples: Invalid old password is too short and invalid new password
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nik |  |
#      | nikita123 | Nikita123 | Nik | Niki |
#      | nikita123 | Nikita123 | Nik | A.2fgtr |
#      | nikita123 | Nikita123 | Nik | nikita12 |
#
#    Examples: Invalid old password has wrong symbols and invalid new password
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | A.1fgtr |  |
#      | nikita123 | Nikita123 | A.1fgtr | Niki |
#      | nikita123 | Nikita123 | A.1fgtr | A.2fgtr |
#      | nikita123 | Nikita123 | A.1fgtr | nikita12 |
#
#    Examples: Invalid old password doesn't have capital letter and invalid new password
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | nikita123 |  |
#      | nikita123 | Nikita123 | nikita123 | Niki |
#      | nikita123 | Nikita123 | nikita123 | A.2fgtr |
#      | nikita123 | Nikita123 | nikita123 | nikita12 |
#
#    Examples: Valid old password but isn't right and invalid new password is empty
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita12 | |
#
#    Examples: Valid old but isn't right and invalid new password is too short
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita12 | Niki |
#
#    Examples: Valid old but isn't right and invalid new password has wrong symbols
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita12 | A.2fgtr |
#
#    Examples: Valid old but isn't right and invalid new password doesn't have capital letter
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita12 | nikita12 |
#
#    Examples: Valid old but isn't right and valid new password, they are equal
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita12 | Nikita12 |
#
#    Examples: Valid old but isn't right and valid new password, they are different
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita12 | Nikita124 |
#
#  @ui @backend
#  Scenario Outline: Change password and save, old password is right and new is empty
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change password button
#    And I click change old password field
#    And I enter old password "<OldPassword>"
#    And I click change new password field
#    And I enter new password "<NewPassword>"
#    And I save password changes
#    Then I will see the error in the field of new password that is empty
#    Examples: Right old password and empty new password
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita123 | |
#
#  @ui @backend
#  Scenario Outline: Change password and save, old password is right and new is too short
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change password button
#    And I click change old password field
#    And I enter old password "<OldPassword>"
#    And I click change new password field
#    And I enter new password "<NewPassword>"
#    And I save password changes
#    Then I will see the error in the field of new password that is too short
#    Examples: Right old password and too short new password
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita123 | Nik |
#
#  @ui @backend
#  Scenario Outline: Change password and save, old password is right and new has wrong symbols
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change password button
#    And I click change old password field
#    And I enter old password "<OldPassword>"
#    And I click change new password field
#    And I enter new password "<NewPassword>"
#    And I save password changes
#    Then I will see the error in the field of new password that has wrong symbols
#    Examples: Right old password and new password has wrong symbols
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita123 | A.2fgtr |
#
#  @ui @backend
#  Scenario Outline: Change password and save, old password is right and new doesn't have capital letter
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change password button
#    And I click change old password field
#    And I enter old password "<OldPassword>"
#    And I click change new password field
#    And I enter new password "<NewPassword>"
#    And I save password changes
#    Then I will see the error in the field of new password that doesn't have capital letter
#    Examples: Right old password and new password doesn't have capital letter
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita123 | nikita12 |
#
#  @ui @backend
#  Scenario Outline: Change password and save, old password is right and equals new
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change password button
#    And I click change old password field
#    And I enter old password "<OldPassword>"
#    And I click change new password field
#    And I enter new password "<NewPassword>"
#    And I save password changes
#    Then I will see the error in the field of new password that was entered the same password
#    Examples: Old password is right and equals new password
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita123 | Nikita123 |
#
#  @ui @backend
#  Scenario Outline: Change password and save, old password is right and doesn't equal new
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change password button
#    And I click change old password field
#    And I enter old password "<OldPassword>"
#    And I click change new password field
#    And I enter new password "<NewPassword>"
#    And I save password changes
#    Then I will see the notification about password
#    Examples: Old password is right and doesn't equal new password
#      | Login     | Password  | OldPassword | NewPassword |
#      | nikita123 | Nikita123 | Nikita123 | Nikita12 |
#
#  @ui @backend
#  Scenario Outline: User doesn't exist, sign in after password change
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to the same screen and notification with error message
#    Examples: Sign in with old password
#      | Login  | Password  |
#      | nikita123 | Nikita123 |
#
#  @ui @backend
#  Scenario Outline: User exists, password change again
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    And I click setting button
#    And I click change login or password button
#    And I click change password button
#    And I click change old password field
#    And I enter old password "<OldPassword>"
#    And I click change new password field
#    And I enter new password "<NewPassword>"
#    And I save password changes
#    Then I will see the notification about password
#    Examples: Password change again
#      | Login     | Password  | OldPassword |NewPassword |
#      | nikita123 | Nikita12 | Nikita12 | Nikita123 |
#
#  @ui @backend
#  Scenario Outline: User exists, sign in
#    Given I start the application
#    When I click button SIGN IN
#    And I click login field
#    And I enter login "<Login>"
#    And I click password field
#    And I enter password "<Password>"
#    And I click button SIGN IN in authorize form
#    Then I expect to see successful changing the screen
#    Examples:Sign in with first password
#      | Login     | Password  |
#      | nikita123 | Nikita123 |