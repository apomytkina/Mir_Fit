package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import cucumber.api.PendingException;
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
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class RegistrationDetailsSteps {

    @When("^I click button REGISTRATION$")
    public void iClickButtonREGISTRATION() {
        onView(withId(R.id.btn_reg)).perform(click());
    }

    @And("^I click firstName field$")
    public void iClickFirstNameField() {
        onView(withId(R.id.frag_reg_text_first_name)).perform(click());
    }

    @And("^I enter firstName \"([^\"]*)\"$")
    public void iEnterValidFirstName(String firstName) {
        onView(withId(R.id.frag_reg_text_first_name)).perform(typeText(firstName));
    }

    @And("^I click secondName field$")
    public void iClickSecondNameField() {
        onView(withId(R.id.frag_reg_text_second_name)).perform(click());
    }

    @And("^I enter secondName \"([^\"]*)\"$")
    public void iEnterValidSecondName(String secondName) {
        onView(withId(R.id.frag_reg_text_second_name)).perform(typeText(secondName));
    }

    @And("^I click patronymic field$")
    public void iClickPatronymicField() {
        onView(withId(R.id.frag_reg_text_patronymic)).perform(click());
    }

    @And("^I enter patronymic \"([^\"]*)\"$")
    public void iEnterValidPatronymic(String patronymic) {
        onView(withId(R.id.frag_reg_text_patronymic)).perform(typeText(patronymic));
    }

    @And("^I click loginReg field$")
    public void iClickLoginRegField() {
        onView(withId(R.id.frag_reg_text_login)).perform(click());
    }

    @And("^I enter loginReg \"([^\"]*)\"$")
    public void iEnterValidLoginReg(String login) {
        onView(withId(R.id.frag_reg_text_login)).perform(typeText(login));
    }

    @And("^I click passwordReg field$")
    public void iClickPasswordRegField() {
        onView(withId(R.id.frag_reg_text_password)).perform(click());
    }

    @And("^I enter passwordReg \"([^\"]*)\"$")
    public void iEnterValidPasswordReg(String password) {
        onView(withId(R.id.frag_reg_text_password)).perform(typeText(password));
    }

    @And("^I click button REGISTRATION in registration form$")
    public void iClickButtonREGISTRATIONInRegistrationForm() {
        onView(withId(R.id.frag_reg_btn)).perform(click());
    }



    @Then("^I expect to see error marks$")
    public void iExpectToSeeErrorMarks() {
        onView(withId(R.id.frag_reg_text_first_name)).check(matches
                (hasErrorText("Поле не может содержать менее 2 символов")));
    }


    
}
