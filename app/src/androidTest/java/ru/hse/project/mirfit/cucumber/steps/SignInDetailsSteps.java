package ru.hse.project.mirfit.cucumber.steps;


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
import static org.hamcrest.Matchers.startsWith;

public class SignInDetailsSteps {
    @When("^I click button SIGN IN$")
    public void clickSignIn() {
        onView(withId(R.id.btn_signIn)).perform(click());
    }

    @And("^I click login field$")
    public void clickLoginField() {
        onView(withId(R.id.frag_sign_login)).perform(click());
    }

    @And("^I enter login \"([^\"]*)\"$")
    public void enterValidLogin(String login) { onView(withId(R.id.frag_sign_login)).perform(typeText(login)); }

    @And("^I click password field$")
    public void clickPasswordField() {
        onView(withId(R.id.frag_sign_password)).perform(click());
    }

    @And("^I enter password \"([^\"]*)\"$")
    public void enterValidPassword(String password) { onView(withId(R.id.frag_sign_password)).perform(typeText(password)); }

    @And("^I click button SIGN IN in authorize form$")
    public void clickSignInAuthorizeForm() { onView(withId(R.id.frag_sign_btn)).perform(click()); }

    @Then("^I expect to see successful changing the screen$")
    public void checkExistButtonAddCard() { Utils.closeApp(); }

    @Then("^I expect to the same screen and notification with error message$")
    public void i_expect_to_the_same_screen_and_notification_with_error_message()
    {
        onView(withText("Wrong login or password")).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Then("^I expect to the same screen and error in the field$")
    public void i_expect_to_the_same_screen_and_error_in_the_field()
    {
        onView(withId(R.id.frag_sign_login)).
                check(matches(hasErrorText(startsWith("Логин"))));
    }
}
