package ru.hse.project.mirfit.test

import cucumber.api.CucumberOptions
@CucumberOptions(features = ["src/androidTest/asserts/features"],
    glue = ["src/androidTest/java/ru/hse/project/mirfit/cucumber/steps/SignInDetailsSteps.java"],
    tags = ["@ui", "@backend"])
@SuppressWarnings("unused")
class CucumberTestCase