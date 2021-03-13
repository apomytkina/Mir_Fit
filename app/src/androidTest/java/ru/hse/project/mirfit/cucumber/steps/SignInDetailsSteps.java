package ru.hse.project.mirfit.cucumber.steps;

import android.content.Context;

import ru.hse.project.mirfit.R;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.hse.project.mirfit.ui.auth.AuthActivity;


import androidx.test.core.app.ApplicationProvider;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;


public class SignInDetailsSteps {

    public SignInDetailsSteps() {
    int d =10;

    }

    private final ActivityTestRule<AuthActivity> loginActivity = new ActivityTestRule<>(AuthActivity.class);


    @Given("^I start the application$")
    public void startApplication() {
        loginActivity.launchActivity(null);
    }

    @When("^I click button SIGN IN$")
    public void clickSignIn() {
        onView(withId(R.id.btn_signIn)).perform(click());
    }

    @And("^I click login field$")
    public void clickLoginField() {
        onView(withId(R.id.frag_sign_login)).perform(click());
    }

    @And("^I enter '(.*)' login '(.*)'$")
    public void enterValidLogin(String typeLogin, String login) {
        onView(withId(R.id.frag_sign_login)).perform(typeText(login));
    }

    @And("^I click password field$")
    public void clickPasswordField() {
        onView(withId(R.id.frag_sign_password)).perform(click());
    }

    @And("^I enter '(.*)' password '(.*)'$")
    public void enterValidPassword(String typePassword, String password) {
        onView(withId(R.id.frag_sign_password)).perform(typeText(password));
    }

    @And("^I click button SIGN IN in authorize form$")
    public void clickSignInAuthorizeForm() {
        onView(withId(R.id.frag_sign_btn)).perform(click());
    }

    @Then("^I expect to see successful changing the screen$")
    public void i_expect_to_see_successful_changing_the_screen() {
        onView(withId(R.id.prof_lay_add_card)).check(matches(isClickable()));
    }


}