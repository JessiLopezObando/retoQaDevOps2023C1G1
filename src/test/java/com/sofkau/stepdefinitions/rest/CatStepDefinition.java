package com.sofkau.stepdefinitions.rest;

import com.sofkau.setup.ApiSetUp;
import com.sofkau.utils.Path;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public class CatStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(CatStepDefinition.class));

    private Response response;

    @Given("I'm on the cat data API as an administrator")
    public void iMOnTheCatDataAPIAsAnAdministrator() {
        try {
            RestAssured.baseURI = Path.CAT_BASE_URI.getValue();
            LOGGER.info("Inicio exitoso de la automatizacion: " + RestAssured.baseURI);
        } catch (Exception e) {
            LOGGER.warn("Error occurred while setting base URI for cat data API as an administrator: " + e.getMessage());
            throw e;
        }
    }

    @When("I send a GET request for random trivia")
    public void iSendAGETRequestForRandomTrivia() {
        try {
            response = given().when().get();
        } catch (Exception e) {
            LOGGER.info("Ocurrio un error haciendo la peticion");
            LOGGER.warn("Error occurred while sending GET request: " + e.getMessage());
            throw e;
        }
    }

    @Then("I see a {string} status response and a JSON structure")
    public void iSeeAStatusResponseAndAJSONStructure(String string) {
        try {
            int lengthValue = response.then().extract().path("length");
            actor.should(seeThat("La longitud es mayor que cero", value -> lengthValue, greaterThan(0))
                    .orComplainWith(AssertionError.class,"la longitud en menor o igual a cero"));
            LOGGER.info("La longitud es mayor que cero: " + lengthValue);

            String factValue = response.then().extract().path("fact");
            actor.should(seeThat("El valor de la propiedad 'fact' no esta vacia", actor1 -> factValue, notNullValue())
                    .orComplainWith(AssertionError.class,"El valor de la propiedad 'fact' esta vacia o nula"));
            LOGGER.info("El valor de la propiedad 'fact' no esta vacia: " + factValue);
        } catch (Exception e) {
            LOGGER.warn("Error occurred while extracting values from response: " + e.getMessage());
            throw e;
        }
    }
}


