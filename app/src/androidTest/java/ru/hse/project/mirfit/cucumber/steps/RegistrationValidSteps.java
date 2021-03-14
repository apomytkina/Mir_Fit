package ru.hse.project.mirfit.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RegistrationValidSteps {

    @When("^I click button REGISTRATION$")
    public void iClickButtonREGISTRATION() {
        onView(withId(R.id.btn_reg)).perform(click());
    }

    @And("^I click firstName field$")
    public void iClickFirstNameField() {
        onView(withId(R.id.frag_reg_text_first_name)).perform(click());
    }

    @And("^I enter valid firstName \"([^\"]*)\"$")
    public void iEnterValidFirstName(String firstName) {
        onView(withId(R.id.frag_reg_text_first_name)).perform(typeText(firstName));
    }

    @And("^I click secondName field$")
    public void iClickSecondNameField() {
        onView(withId(R.id.frag_reg_text_second_name)).perform(click());
    }

    @And("^I enter valid secondName \"([^\"]*)\"$")
    public void iEnterValidSecondName(String secondName) {
        onView(withId(R.id.frag_reg_text_second_name)).perform(typeText(secondName));
    }

    @And("^I click patronymic field$")
    public void iClickPatronymicField() {
        onView(withId(R.id.frag_reg_text_patronymic)).perform(click());
    }

    @And("^I enter valid patronymic \"([^\"]*)\"$")
    public void iEnterValidPatronymic(String patronymic) {
        onView(withId(R.id.frag_reg_text_patronymic)).perform(typeText(patronymic));
    }

    @And("^I click loginReg field$")
    public void iClickLoginRegField() {
        onView(withId(R.id.frag_reg_text_login)).perform(click());
    }

    @And("^I enter valid loginReg \"([^\"]*)\"$")
    public void iEnterValidLoginReg(String login) {
        onView(withId(R.id.frag_reg_text_login)).perform(typeText(login));
    }

    @And("^I click passwordReg field$")
    public void iClickPasswordRegField() {
        onView(withId(R.id.frag_reg_text_password)).perform(click());
    }

    @And("^I enter valid passwordReg \"([^\"]*)\"$")
    public void iEnterValidPasswordReg(String password) {
        onView(withId(R.id.frag_reg_text_password)).perform(typeText(password));
    }

    @And("^I click button REGISTRATION in registration form$")
    public void iClickButtonREGISTRATIONInRegistrationForm() {
        onView(withId(R.id.frag_reg_btn)).perform(click());
    }
}
