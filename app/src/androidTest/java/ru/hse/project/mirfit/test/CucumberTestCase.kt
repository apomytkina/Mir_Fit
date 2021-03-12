package ru.hse.project.mirfit.test

import cucumber.api.CucumberOptions;
@CucumberOptions(features = ["features"],
    glue = ["com.sniper.bdd.cucumber.steps"],
    tags = ["@ui", "@backend"])
@SuppressWarnings("unused")
class CucumberTestCase