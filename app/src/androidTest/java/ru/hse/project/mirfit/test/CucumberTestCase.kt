package ru.hse.project.mirfit.test

import cucumber.api.CucumberOptions

@CucumberOptions(
    features = ["features"],
    glue = ["ru.hse.project.mirfit.cucumber.steps"],
    tags = ["@ui", "@backend"],
    format = ["pretty", "html:target/Destination"]
)
@SuppressWarnings("unused")
class CucumberTestCase