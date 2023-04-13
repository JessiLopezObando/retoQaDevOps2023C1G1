package com.sofkau.runners.soap;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/soap/cityname.feature"},
        glue = {"com.sofkau.stepdefinitions"},
        tags = {}
)
public class CityNameTest {
}