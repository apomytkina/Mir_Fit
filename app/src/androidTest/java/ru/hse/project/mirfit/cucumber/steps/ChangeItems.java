package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ChangeItems {

    //Нажатие на кнопку с настройками
    @And("^I click setting button$")
    public void clickSettingButton() {
        SystemClock.sleep(3000);
        onView(withId(R.id.prof_lay_setting)).perform(click());
    }

    //Нажатие кнопки изменить логин или пароль
    @And("^I click change login or password button$")
    public void clickChangeLoginOrPassword() {
        SystemClock.sleep(3000);
        onView(withId(R.id.btn_change_login_password)).perform(click());
    }

    //Нажатие отмены после нажатия кнопки изменить логин или пароль
    @And("^I click cancel button when was clicked cancel to change login or password button$")
    public void clickChangeLoginOrPasswordCancel() {
        SystemClock.sleep(3000);
        onView(withId(R.id.btn_change_login_password_cancel)).perform(click());
        SystemClock.sleep(3000);
        onView(withId(R.id.btn_sign_out)).perform(click());
        Espresso.pressBackUnconditionally();
    }

    //Нажатие кнопки изменить логин
    @And("^I click change login button$")
    public void clickChangeLogin() {
        onView(withId(R.id.btn_change_login)).perform(click());
    }

    //Нажатие отмены после нажатия кнопки изменить логин
    @And("^I click cancel button when was clicked change login button$")
    public void clickChangeLoginCancel() {
        SystemClock.sleep(3000);
        onView(withId(R.id.edit_login_cancel_btn)).perform(click());
    }

    //Нажатие кнопки изменить пароль
    @And("^I click change password button$")
    public void clickChangePassword() {
        onView(withId(R.id.btn_change_password)).perform(click());
    }

    //Нажатие отмены после нажатия кнопки изменить пароль
    @And("^I click cancel button when was clicked change password button$")
    public void clickChangePasswordCancel() {
        SystemClock.sleep(3000);
        onView(withId(R.id.edit_password_cancel_btn)).perform(click());
    }

    //Нажатие на поле изменить логин
    @And("^I click change login field$")
    public void clickChangeLoginField() {
        onView(withId(R.id.edit_login_field)).perform(click());
    }

    //Ввод нового логина
    @And("^I enter new login \"([^\"]*)\"$")
    public void enterChangeLogin(String login) {
        onView(withId(R.id.edit_login_field)).perform(clearText()).perform(typeText(login));
    }

    //Нажатие на поле изменить старый пароль
    @And("^I click change old password field$")
    public void clickOldPasswordField() {

        onView(withId(R.id.edit_password_old_field)).perform(click());
    }

    //Ввод старого пароля
    @And("^I enter old password \"([^\"]*)\"$")
    public void enterOldPassword(String password) {
        onView(withId(R.id.edit_password_old_field)).perform(typeText(password));
    }

    //Нажатие на поле изменить новый пароль
    @And("^I click change new password field$")
    public void clickNewPasswordField() {
        onView(withId(R.id.edit_password_new_field)).perform(click());
    }

    //Ввод нового пароля
    @And("^I enter new password \"([^\"]*)\"$")
    public void enterNewPassword(String password) {
        onView(withId(R.id.edit_password_new_field)).perform(typeText(password));
    }

    //Нажатие кнопки сохранить логин
    @And("^I save login changes$")
    public void saveLogin() {
        onView(withId(R.id.edit_login_save_btn)).perform(click());
    }

    //Нажатие кнопки сохранить пароль
    @And("^I save password changes$")
    public void savePassword() {
        onView(withId(R.id.edit_password_save_btn)).perform(click());
    }

    public void signOutAndStepBack() {
        SystemClock.sleep(3000);
        onView(withId(R.id.btn_sign_out)).perform(click());
        Espresso.pressBackUnconditionally();
    }

    //Проверка, что появится ошибка, что введен тот же логин
    @Then("^I will see the warning about login$")
    public void seeTheWarningLogin() {
        onView(withText("Login is not available")).inRoot(new ToastMatcher()).
                check(matches(isDisplayed()));
        signOutAndStepBack();
    }

    //Проверка, что появится уведомление об успешной смене логина
    @Then("^I will see the notification about login$")
    public void seeTheNotificationLogin() {
        onView(withText("successful update login")).inRoot(new ToastMatcher()).
                check(matches(isDisplayed()));
        signOutAndStepBack();
    }

    //Проверка, что появится уведомление об успешной смене пароля
    @Then("^I will see the notification about password$")
    public void seeTheNotificationPassword() {
        onView(withText("successful update password")).inRoot(new ToastMatcher()).
                check(matches(isDisplayed()));
        signOutAndStepBack();
    }

    public void saveAndStepBack() {
        SystemClock.sleep(3000);
        Espresso.pressBackUnconditionally();
        Espresso.pressBackUnconditionally();
        signOutAndStepBack();
    }

    //Проверка, что будет ошибка в логине с сообщением - Логин не может быть пустым!
    @Then("^I will see the error in the field of login that is empty$")
    public void seeTheErrorLoginEmpty() {
        onView(withId(R.id.edit_login_field)).
                check(matches(hasErrorText("Логин не может быть пустым!")));
        saveAndStepBack();
    }

    //Проверка, что будет ошибка в логине с сообщением - Логин не может содержать менее 5 символов
    @Then("^I will see the error in the field of login that is too short$")
    public void seeTheErrorLoginShort() {
        onView(withId(R.id.edit_login_field)).
                check(matches(hasErrorText("Логин не может содержать менее 5 символов")));
        saveAndStepBack();
    }

    //Проверка, что будет ошибка в логине с сообщением - Логин может содержать только символы латиницы и цифры
    @Then("^I will see the error in the field of login that has wrong symbols$")
    public void seeTheErrorLoginWrong() {
        onView(withId(R.id.edit_login_field)).
                check(matches(hasErrorText("Логин может содержать только символы латиницы и цифры")));
        saveAndStepBack();
    }

    //Проверка, что будет ошибка в старом пароле с сообщением - Неверный пароль
    @Then("^I will see the error in the field of old password$")
    public void seeTheErrorOldPassword() {
        onView(withId(R.id.edit_password_old_field)).
                check(matches(hasErrorText("Неверный пароль")));
        saveAndStepBack();
    }

    //Проверка, что будет ошибка в новом пароле с сообщением - Пароль не может быть пустым!
    @Then("^I will see the error in the field of new password that is empty$")
    public void seeTheErrorNewPasswordEmpty() {
        onView(withId(R.id.edit_password_new_field)).
                check(matches(hasErrorText("Пароль не может быть пустым!")));
        saveAndStepBack();
    }

    //Проверка, что будет ошибка в новом пароле с сообщением - Пароль не может содержать менее 5 символов
    @Then("^I will see the error in the field of new password that is too short$")
    public void seeTheErrorNewPasswordShort() {
        onView(withId(R.id.edit_password_new_field)).
                check(matches(hasErrorText("Пароль не может содержать менее 5 символов")));
        saveAndStepBack();
    }

    //Проверка, что будет ошибка в новом пароле с сообщением - Пароль может содержать только символы латиницы и цифры
    @Then("^I will see the error in the field of new password that has wrong symbols$")
    public void seeTheErrorNewPasswordWrong() {
        onView(withId(R.id.edit_password_new_field)).
                check(matches(hasErrorText("Пароль может содержать только символы латиницы и цифры")));
        saveAndStepBack();
    }

    //Проверка, что будет ошибка в новом пароле с сообщением - Пароль Должен содержать хотя бы одину заглавную букву
    @Then("^I will see the error in the field of new password that doesn't have capital letter$")
    public void seeTheErrorNewPasswordCapital() {
        onView(withId(R.id.edit_password_new_field)).
                check(matches(hasErrorText("Пароль Должен содержать хотя бы одину заглавную букву")));
        saveAndStepBack();
    }

    //Проверка, что будет ошибка в новом пароле с сообщением - Новый пароль должен отличаться от старого
    @Then("^I will see the error in the field of new password that was entered the same password$")
    public void seeTheErrorNewPasswordSame() {
        onView(withId(R.id.edit_password_new_field)).
                check(matches(hasErrorText("Новый пароль должен отличаться от старого")));
        saveAndStepBack();
    }
}
