package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;

import cucumber.api.java.en.Then;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ErrorRegistrationSteps {


    @Then("^I expect to see error_empty_first_name$")
    public void iExpectToSeeError_empty_first_name() {
        onView(withId(R.id.frag_reg_text_first_name)).check(matches
                (hasErrorText("Поле не может быть пустым!")));
        closeCurrentCheck();
    }


    @Then("^I expect to see error_length_first_name$")
    public void iExpectToSeeError_length_first_name() {
        onView(withId(R.id.frag_reg_text_first_name)).check(matches
                (hasErrorText("Поле не может содержать менее 2 символов")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_alphabet_first_name$")
    public void iExpectToSeeError_alphabet_first_name() {
        onView(withId(R.id.frag_reg_text_first_name)).check(matches
                (hasErrorText("Поле может содержать только символы латиницы")));
        closeCurrentCheck();

    }

    @Then("^I expect to see error_case_first_name$")
    public void iExpectToSeeError_case_first_name() {
        onView(withId(R.id.frag_reg_text_first_name)).check(matches
                (hasErrorText("Поле должно содержать первую заглавную букву и остальные строчные")));
        closeCurrentCheck();

    }


    @Then("^I expect to see error_empty_second_name$")
    public void iExpectToSeeError_empty_second_name() {
        onView(withId(R.id.frag_reg_text_second_name)).check(matches
                (hasErrorText("Поле не может быть пустым!")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_length_second_name$")
    public void iExpectToSeeError_length_second_name() {
        onView(withId(R.id.frag_reg_text_second_name)).check(matches
                (hasErrorText("Поле не может содержать менее 2 символов")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_alphabet_second_name$")
    public void iExpectToSeeError_alphabet_second_name() {
        onView(withId(R.id.frag_reg_text_second_name)).check(matches
                (hasErrorText("Поле может содержать только символы латиницы")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_case_second_name$")
    public void iExpectToSeeError_case_second_name() {
        onView(withId(R.id.frag_reg_text_second_name)).check(matches
                (hasErrorText("Поле должно содержать первую заглавную букву и остальные строчные")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_empty_patronymic$")
    public void iExpectToSeeError_empty_patronymic() {
        onView(withId(R.id.frag_reg_text_patronymic)).check(matches
                (hasErrorText("Поле не может быть пустым!")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_length_patronymic$")
    public void iExpectToSeeError_length_patronymic() {
        onView(withId(R.id.frag_reg_text_patronymic)).check(matches
                (hasErrorText("Поле не может содержать менее 2 символов")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_alphabet_patronymic$")
    public void iExpectToSeeError_alphabet_patronymic() {
        onView(withId(R.id.frag_reg_text_patronymic)).check(matches
                (hasErrorText("Поле может содержать только символы латиницы")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_case_patronymic$")
    public void iExpectToSeeError_case_patronymic() {
        onView(withId(R.id.frag_reg_text_patronymic)).check(matches
                (hasErrorText("Поле должно содержать первую заглавную букву и остальные строчные")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_empty_login$")
    public void iExpectToSeeError_empty_login() {
        onView(withId(R.id.frag_reg_text_login)).check(matches
                (hasErrorText("Логин не может быть пустым!")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_length_login$")
    public void iExpectToSeeError_length_login() {
        onView(withId(R.id.frag_reg_text_login)).check(matches
                (hasErrorText("Логин не может содержать менее 5 символов")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_alphabet_login$")
    public void iExpectToSeeError_alphabet_login() {
        onView(withId(R.id.frag_reg_text_login)).check(matches
                (hasErrorText("Логин может содержать только символы латиницы и цифры")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_empty_password$")
    public void iExpectToSeeError_empty_password() {
        onView(withId(R.id.frag_reg_text_password)).check(matches
                (hasErrorText("Пароль не может быть пустым!")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_length_password$")
    public void iExpectToSeeError_length_password() {
        onView(withId(R.id.frag_reg_text_password)).check(matches
                (hasErrorText("Пароль не может содержать менее 5 символов")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_alphabet_password$")
    public void iExpectToSeeError_alphabet_password() {
        onView(withId(R.id.frag_reg_text_password)).check(matches
                (hasErrorText("Пароль может содержать только символы латиницы и цифры")));
        closeCurrentCheck();
    }

    @Then("^I expect to see error_case_password$")
    public void iExpectToSeeError_case_password() {
        onView(withId(R.id.frag_reg_text_password)).check(matches
                (hasErrorText("Пароль Должен содержать хотя бы одину заглавную букву")));
        closeCurrentCheck();
    }

    @Then("^I expect to see successful registration$")
    public void iExpectToSeeSuccessfulRegistration() {
        Utils.closeApp();
        closeCurrentCheck();
    }
    public void closeCurrentCheck(){
        Espresso.pressBackUnconditionally();
        Espresso.pressBackUnconditionally();
    }
}
