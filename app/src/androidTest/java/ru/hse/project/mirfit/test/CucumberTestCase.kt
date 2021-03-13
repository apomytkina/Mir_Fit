package ru.hse.project.mirfit.test

import cucumber.api.CucumberOptions
@CucumberOptions
    (features = ["assets/features"],
    glue = ["steps/SignInDetailsSteps.java"],
    tags = ["@ui", "@backend"])
@SuppressWarnings("unused")
class CucumberTestCase