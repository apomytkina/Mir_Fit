package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.contrib.RecyclerViewActions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class CardAddSteps {


    @And("^I click the button plus_add_card$")
    public void iClickTheButtonPlus_add_card() {
        SystemClock.sleep(4000);
        onView(withId(R.id.prof_lay_add_card)).perform(click());
    }

    @And("^I click number_card field$")
    public void iClickNumber_cardField() {
        onView(withId(R.id.edit_text_card_number_frag_add_card)).perform(click());
    }

    @And("^I enter number_card \"([^\"]*)\"$")
    public void iEnterNumber_card(String numberCard) {
        onView(withId(R.id.edit_text_card_number_frag_add_card)).perform(typeText(numberCard));
    }

    @And("^I click name_card field$")
    public void iClickName_cardField() {
        onView(withId(R.id.edit_text_name_card_frag_add_card)).perform(click());
    }

    @And("^I enter name_card \"([^\"]*)\"$")
    public void iEnterName_card(String nameCard) throws Throwable {
        onView(withId(R.id.edit_text_name_card_frag_add_card)).perform(typeText(nameCard));
    }


    @And("^I click button card_add$")
    public void iClickButtonCard_add() {
        SystemClock.sleep(2000);
        onView(withId(R.id.btn_frag_add_card)).perform(click());
        SystemClock.sleep(1000);
    }


    @Then("^I check success sign_in for actions with cards$")
    public void iCheckSuccessSign_inForActionsWithCards() {
        onView(withId(R.id.prof_lay_add_card)).perform(click());
        Utils.closeCurrentCheck(1);
    }

    @Then("^I check error_message_ps_number$")
    public void iCheckError_message_ps_field() {
        onView(withId(R.id.edit_text_card_number_frag_add_card)).check(matches
                (hasErrorText("Карта должна быть с платежной системой MIR")));
        Utils.closeCurrentCheck(2);
    }

    @Then("^I check error_message_length_number$")
    public void iCheckError_message_length_field() {
        onView(withId(R.id.edit_text_card_number_frag_add_card)).check(matches
                (hasErrorText("Номер карты не может быть короче 13 цифр")));
        Utils.closeCurrentCheck(2);
    }

    @Then("^I check error_message_empty_name$")
    public void iCheckError_message_empty_name() {
        onView(withId(R.id.edit_text_name_card_frag_add_card)).check(matches
                (hasErrorText("Название карты не может быть пустым!")));
        Utils.closeCurrentCheck(2);
    }

    @Then("^I check error_message_length_name$")
    public void iCheckError_message_length_name() {
        onView(withId(R.id.edit_text_name_card_frag_add_card)).check(matches
                (hasErrorText("Название карты не может содержать менее 2 символов")));
        Utils.closeCurrentCheck(2);
    }

    @Then("^I check error_message_symbols_name$")
    public void iCheckError_message_symbols_name() {
        onView(withId(R.id.edit_text_name_card_frag_add_card)).check(matches
                (hasErrorText("Название карты может состоять из латиницы, кириллицы и цифр")));
        Utils.closeCurrentCheck(2);
    }


    @Then("^I check toast add_no_exist_card$")
    public void iCheckToastAdd_no_exist_card() {
        onView(withText("Card not found")).
                inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Then("^I check success add_exist_card$")
    public void iCheckSuccessAdd_exist_card() {
        SystemClock.sleep(1000);
        onView(withId(R.id.card_recycler)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, new GeneralSwipeAction(
                        Swipe.SLOW, GeneralLocation.BOTTOM_RIGHT, GeneralLocation.BOTTOM_LEFT,
                        Press.FINGER)));
    }

    @Then("^I check toast add_card_duplicate$")
    public void iCheckToastAdd_card_duplicate() {
        onView(withText("Card with number '220006118769339' is already added.")).
                inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }
}
