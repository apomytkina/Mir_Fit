package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class SignInDetailsSteps {
    //Нажатие на первую кнопку входа
    @When("^I click button SIGN IN$")
    public void clickSignIn() {
        onView(withId(R.id.btn_signIn)).perform(click());
    }

    //Нажатие на поле логина при входе
    @And("^I click login field$")
    public void clickLoginField() {
        onView(withId(R.id.frag_sign_login)).perform(click());
    }

    //Ввод в поле логина
    @And("^I enter login \"([^\"]*)\"$")
    public void enterValidLogin(String login) {
        onView(withId(R.id.frag_sign_login)).perform(typeText(login));
    }

    //Нажатие на поле пароля
    @And("^I click password field$")
    public void clickPasswordField() {
        onView(withId(R.id.frag_sign_password)).perform(click());
    }

    //Ввод в поле пароля
    @And("^I enter password \"([^\"]*)\"$")
    public void enterValidPassword(String password) {
        onView(withId(R.id.frag_sign_password)).perform(typeText(password));
    }

    //Нажатие на кнопку вход после заполнения полей
    @And("^I click button SIGN IN in authorize form$")
    public void clickSignInAuthorizeForm() {
        onView(withId(R.id.frag_sign_btn)).perform(click());
        SystemClock.sleep(1000);
    }

    //Проверка, что будет переход нас следущий экран
    @Then("^I expect to see successful changing the screen$")
    public void checkExistButtonAddCard() {
        Utils.closeApp();
        Espresso.pressBackUnconditionally();
    }

    public void stepBack() {
        SystemClock.sleep(3000);
        for (int i = 0; i < 3; i++) {
            Espresso.pressBackUnconditionally();
        }
    }

    //Проверка, что будет уведомление, когда нет такого пользователя
    @Then("^I expect to the same screen and notification with error message$")
    public void i_expect_to_the_same_screen_and_notification_with_error_message() {
        onView(withText("Wrong login or password")).inRoot(new ToastMatcher()).
                check(matches(isDisplayed()));
        stepBack();
    }

    //Проверка, что будет ошибка в логине с сообщением - Логин не может быть пустым!
    @Then("^I expect to the same screen and error in the login field that is empty$")
    public void i_expect_to_the_same_screen_and_error_in_the_login_field_empty() {
        onView(withId(R.id.frag_sign_login)).
                check(matches(hasErrorText("Логин не может быть пустым!")));
        stepBack();
    }

    //Проверка, что будет ошибка в логине с сообщением - Логин не может содержать менее 5 символов
    @Then("^I expect to the same screen and error in the login field that is too short$")
    public void i_expect_to_the_same_screen_and_error_in_the_login_field_short() {
        onView(withId(R.id.frag_sign_login)).
                check(matches(hasErrorText("Логин не может содержать менее 5 символов")));
        stepBack();
    }

    //Проверка, что будет ошибка в логине с сообщением - Логин может содержать только символы латиницы и цифры
    @Then("^I expect to the same screen and error in the login field that has wrong symbols$")
    public void i_expect_to_the_same_screen_and_error_in_the_login_field_wrong() {
        onView(withId(R.id.frag_sign_login)).
                check(matches(hasErrorText("Логин может содержать только символы латиницы и цифры")));
        stepBack();
    }

    //Проверка, что будет ошибка в пароле с сообщением - Пароль не может быть пустым!
    @Then("^I expect to the same screen and error in the password field that is empty$")
    public void i_expect_to_the_same_screen_and_error_in_the_password_field_empty() {
        onView(withId(R.id.frag_sign_password)).
                check(matches(hasErrorText("Пароль не может быть пустым!")));
        stepBack();
    }

    //Проверка, что будет ошибка в пароле с сообщением - Пароль не может содержать менее 5 символов
    @Then("^I expect to the same screen and error in the password field that is too short$")
    public void i_expect_to_the_same_screen_and_error_in_the_password_field_short() {
        onView(withId(R.id.frag_sign_password)).
                check(matches(hasErrorText("Пароль не может содержать менее 5 символов")));
        stepBack();
    }

    //Проверка, что будет ошибка в пароле с сообщением - Пароль может содержать только символы латиницы и цифры
    @Then("^I expect to the same screen and error in the password field that has wrong symbols$")
    public void i_expect_to_the_same_screen_and_error_in_the_password_field_wrong() {
        onView(withId(R.id.frag_sign_password)).
                check(matches(hasErrorText("Пароль может содержать только символы латиницы и цифры")));
        stepBack();
    }

    //Проверка, что будет ошибка в пароле с сообщением - Пароль Должен содержать хотя бы одину заглавную букву
    @Then("^I expect to the same screen and error in the password field that doesn't have capital letter$")
    public void i_expect_to_the_same_screen_and_error_in_the_password_field_capital() {
        onView(withId(R.id.frag_sign_password)).
                check(matches(hasErrorText("Пароль Должен содержать хотя бы одину заглавную букву")));
        stepBack();
    }
}
