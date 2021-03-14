package ru.hse.project.mirfit.cucumber.steps;

import androidx.test.rule.ActivityTestRule;

import cucumber.api.java.en.Given;
import ru.hse.project.mirfit.ui.auth.AuthActivity;

public class GeneralSteps {

    private final ActivityTestRule<AuthActivity> loginActivity = new ActivityTestRule<>(AuthActivity.class);

    @Given("^I start the application$")
    public void startApplication() {
        loginActivity.launchActivity(null);
    }
}
