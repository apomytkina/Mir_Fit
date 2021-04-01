package ru.hse.project.mirfit.cucumber.steps;

import androidx.test.espresso.matcher.ViewMatchers;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class CardDeleteSteps {

    @And("^I click button card_delete$")
    public void iClickButtonCard_delete() {
        onView(allOf(withId(R.id.btn_bottom_wrapper_delete),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(click());

    }

    @And("^I click button_confirm$")
    public void iClickButton_confirm() {
        onView(withText("Confirm")).inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());
    }

    @Then("^I check success delete_card$")
    public void iCheckSuccessDelete_card() {
        onView(allOf(withText("Card4"),
                withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }
}
