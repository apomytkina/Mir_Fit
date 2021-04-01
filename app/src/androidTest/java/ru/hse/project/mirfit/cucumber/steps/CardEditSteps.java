package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class CardEditSteps {
    @When("^I swipe left on the card$")
    public void iSwipeLeftOnTheCard() {
        onView(withId(R.id.card_recycler)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, new GeneralSwipeAction(
                        Swipe.SLOW, GeneralLocation.BOTTOM_RIGHT, GeneralLocation.BOTTOM_LEFT,
                        Press.FINGER)));
        SystemClock.sleep(1000);
    }

    @And("^I click button card_edit$")
    public void iClickButtonCard_edit() {
        onView(allOf(withId(R.id.btn_bottom_wrapper_edit),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(click());
    }

    @And("^I click new_name_card field$")
    public void iClickNew_name_cardField() {
        onView(withId(R.id.edit_card_field)).perform(click());
    }

    @And("^I enter new_name_card \"([^\"]*)\"$")
    public void iEnterNew_name_card(String newName) {
        onView(withId(R.id.edit_card_field)).perform(clearText()).perform(typeText(newName));

    }

    @And("^I click button save_card$")
    public void iClickButtonSave_card() {
        onView(withId(R.id.edit_card_save_btn)).perform(click());
    }

    @Then("^I check change name_card \"([^\"]*)\"$")
    public void iCheckChangeName_card(String newName) {
        onView(allOf(withText(newName),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @And("^I click button cancel_card$")
    public void iClickButtonCancel_card() {
        onView(withId(R.id.edit_card_cancel_btn)).perform(click());
    }

    @Then("^I check no_change name_card$")
    public void iCheckNo_changeName_card() {
        onView(allOf(withText("Card4"),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Then("^I check error_edit_message_empty_name$")
    public void iCheckError_edit_message_empty_name() {
        onView(withId(R.id.edit_card_field)).check(matches(
                hasErrorText("Название карты не может быть пустым!")));
        Utils.closeCurrentCheck(2);
    }

    @Then("^I check error_edit_message_length_name$")
    public void iCheckError_edit_message_length_name() {
        onView(withId(R.id.edit_card_field)).check(matches(
                hasErrorText("Название карты не может содержать менее 2 символов")));
        Utils.closeCurrentCheck(2);
    }

    @Then("^I check error_edit_message_symbols_name$")
    public void iCheckError_edit_message_symbols_name() {
        onView(withId(R.id.edit_card_field)).check(matches(
                hasErrorText("Название карты может состоять из латиницы, кириллицы и цифр")));
        Utils.closeCurrentCheck(2);
    }

}
