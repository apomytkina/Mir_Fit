package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.contrib.RecyclerViewActions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class CardEditSteps {
    @When("^I swipe left on the card$")
    public void iSwipeLeftOnTheCard() {
        onView(withId(R.id.card_recycler)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, new GeneralSwipeAction(
                        Swipe.SLOW, GeneralLocation.BOTTOM_RIGHT, GeneralLocation.BOTTOM_LEFT,
                        Press.FINGER)));
    }

    @And("^I click button card_edit$")
    public void iClickButtonCard_edit() {
        SystemClock.sleep(2000);
        onView(withId(R.id.btn_bottom_wrapper_edit)).perform(click());
    }

    @And("^I click new_name_card field$")
    public void iClickNew_name_cardField() {
        onView(withId(R.id.edit_card_field)).perform(click());
    }

    @And("^I enter new_name_card \"([^\"]*)\"$")
    public void iEnterNew_name_card(String newName){
        onView(withId(R.id.edit_card_field)).perform(clearText()).perform(typeText(newName));

    }

    @And("^I click button save_card$")
    public void iClickButtonSave_card() {
        onView(withId(R.id.edit_card_save_btn)).perform(click());
    }

    @Then("^I check change name_card$")
    public void iCheckChangeName_card() {
    }

    @And("^I click button cancel_card$")
    public void iClickButtonCancel_card() {
        onView(withId(R.id.edit_card_cancel_btn)).perform(click());
    }

    @Then("^I check no_change name_card$")
    public void iCheckNo_changeName_card() {

    }
}
