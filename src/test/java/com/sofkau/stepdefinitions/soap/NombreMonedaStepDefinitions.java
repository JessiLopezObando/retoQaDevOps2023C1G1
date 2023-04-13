package com.sofkau.stepdefinitions.soap;

import com.sofkau.setup.ApiSetUp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;


public class NombreMonedaStepDefinitions extends ApiSetUp {

    String body;


    private static final Logger LOGGER = Logger.getLogger(NombreMonedaStepDefinitions.class);
    @Given("The user wants to know the name of a currency")
    public void the_user_wants_to_know_the_name_of_a_currency() {

    }

    @When("The user sends a request to the API to obtain the name of the currency")
    public void the_user_sends_a_request_to_the_API_to_obtain_the_name_of_the_currency() {

    }

    @Then("The user receives the name of the currency as a response")
    public void the_user_receives_the_name_of_the_currency_as_a_response() {

    }

}
