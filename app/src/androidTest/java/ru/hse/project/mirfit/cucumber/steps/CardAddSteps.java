package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;

import java.time.temporal.TemporalAccessor;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
        SystemClock.sleep(5000);
    }


    @Then("^I check success sign_in for actions with cards$")
    public void iCheckSuccessSign_inForActionsWithCards() {
        onView(withId(R.id.prof_lay_add_card)).perform(click());
        Utils.closeCurrentCheck(2);
    }

    @Then("^I check error_message_ps_number$")
    public void iCheckError_message_ps_field() {
        onView(withId(R.id.edit_text_card_number_frag_add_card)).check(matches
                (hasErrorText("Карта должна быть с платежной системой MIR")));
        SystemClock.sleep(1000);
        Utils.closeCurrentCheck(3);
    }

    @Then("^I check error_message_length_number$")
    public void iCheckError_message_length_field() {
        onView(withId(R.id.edit_text_card_number_frag_add_card)).check(matches
                (hasErrorText("Номер карты не может быть короче 13 цифр")));
        SystemClock.sleep(1000);
        Utils.closeCurrentCheck(3);
    }

    @Then("^I check error_message_empty_name$")
    public void iCheckError_message_empty_name() {
        onView(withId(R.id.edit_text_name_card_frag_add_card)).check(matches
                (hasErrorText("Название карты не может быть пустым!")));
        SystemClock.sleep(1000);
        Utils.closeCurrentCheck(3);
    }

    @Then("^I check error_message_length_name$")
    public void iCheckError_message_length_name() {
        onView(withId(R.id.edit_text_name_card_frag_add_card)).check(matches
                (hasErrorText("Название карты не может содержать менее 2 символов")));
        SystemClock.sleep(1000);
        Utils.closeCurrentCheck(3);
    }

    @Then("^I check error_message_symbols_name$")
    public void iCheckError_message_symbols_name() {
        onView(withId(R.id.edit_text_name_card_frag_add_card)).check(matches
                (hasErrorText("Название карты может состоять из латиницы, кириллицы и цифр")));
        SystemClock.sleep(1000);
        Utils.closeCurrentCheck(3);
    }

    @Then("^I check success add_card$")
    public void iCheckSuccessAdd_card() {
        onView(withId(R.id.card_recycler)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, new GeneralSwipeAction(
                        Swipe.SLOW, GeneralLocation.BOTTOM_RIGHT, GeneralLocation.BOTTOM_LEFT,
                        Press.FINGER)));
        SystemClock.sleep(3000);
    }


}
