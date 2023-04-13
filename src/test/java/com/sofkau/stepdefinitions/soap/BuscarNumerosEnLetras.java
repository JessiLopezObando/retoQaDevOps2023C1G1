package com.sofkau.stepdefinitions.soap;

import com.sofkau.setup.ApiSetUp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;


public class BuscarNumerosEnLetras extends ApiSetUp {
    String body;

    private static final Logger LOGGER = Logger.getLogger(BuscarNumerosEnLetras.class);

    @Given("The user wants to know the textual value of a number")
    public void the_user_wants_to_know_the_textual_value_of_a_number() {

    }

    @When("The user sends a request to the API to obtain the textual value of a number")
    public void the_user_sends_a_request_to_the_API_to_obtain_the_textual_value_of_a_number() {

    }

    @Then("The user should receive as a response the value of the number in words")
    public void the_user_should_receive_as_a_response_the_value_of_the_number_in_words() {

    }



}
