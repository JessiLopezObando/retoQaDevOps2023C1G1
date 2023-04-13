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

import static com.sofkau.models.soap.Headers.headers;
import static com.sofkau.questions.soap.ResponseSoap.responseSoap;
import static com.sofkau.tasks.soap.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.PathNumberToDollars.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;


public class NumberToDollarsStepDefinition extends ApiSetUp {
    private static final Logger LOGGER = Logger.getLogger(NumberToDollarsStepDefinition.class);
    String body;

    @Given("a user that wants to know the value of a number in dollars")
    public void aUserThatWantsToKnowTheValueOfANumberInDollars() {
        try {
            setUp(SOAP_NUMBER_TO_WORDS_BASE_URL.getValue());
            LOGGER.info("INICIA AUTOMATIZACION");
        } catch (Exception e){
            LOGGER.info("fallo en configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("the user sends a request with the number {string}")
    public void theUserSendsARequestWithTheNumber(String number) {
        try {
            loadBody(number);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_NUMBER_TO_DOLLARS.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e){
            LOGGER.info("Fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("the user gets the value of the number in Dollars {string} and a status code response OK")
    public void theUserGetsTheValueOfTheNumberInDollarsAndAStatusCodeResponseOK(String conversion) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("Retorna una conversion a dolares: ",
                            responseSoap(), containsString(conversion))
            );
            LOGGER.info("Cumple!");
        } catch (Exception e){
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private void loadBody(String number) {
        body = readFile(BODY_PATH_NUMBER_TO_DOLLARS.getValue());
        body = String.format(body, number);
    }

}
