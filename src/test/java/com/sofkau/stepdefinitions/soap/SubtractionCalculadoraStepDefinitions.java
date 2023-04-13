package com.sofkau.stepdefinitions.soap;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.nio.charset.StandardCharsets;

import static com.sofkau.models.soap.HeadersSubtraction.headersSubtraction;
import static com.sofkau.questions.soap.ResponseSoap.responseSoap;
import static com.sofkau.tasks.soap.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.SubtractionCalculadora.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class SubtractionCalculadoraStepDefinitions extends ApiSetUp {

    String body;
    private static final Logger LOGGER = Logger.getLogger(SubtractionCalculadoraStepDefinitions.class);

    @Given("that the user has access to the subtraction service of the calculator")
    public void thatTheUserHasAccessToTheSubtractionServiceOfTheCalculator() {
        try {
            setUp(SOAP_RESTA_CALCULADORA_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("sends a subtraction request with two integers")
    public void sendsASubtractionRequestWithTwoIntegers() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESORUCE_RESTA_CALCULADORA.getValue())
                            .withTheHeaders(headersSubtraction().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info("fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("should receive a successful response with the result of the subtraction")
    public void shouldReceiveASuccessfulResponseWithTheResultOfTheSubtraction() {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat(" El resultado de la resta es: ",
                            responseSoap(), containsString("22"))
            );
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private void loadBody() {
        body = readFile(BODY_PATH_RESTA_CALCULADORA.getValue());
        body = String.format(body, "30", "8");
    }
}