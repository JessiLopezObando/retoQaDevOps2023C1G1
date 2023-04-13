package com.sofkau.runners.rest;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"com.sofkau.stepdefinitions"},
        features = {"src/test/resources/features/rest/getasinglecharacter.feature"}
)
public class GetSingleCharacterTest {
}
