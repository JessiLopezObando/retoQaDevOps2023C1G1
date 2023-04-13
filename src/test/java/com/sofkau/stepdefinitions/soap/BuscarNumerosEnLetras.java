package com.sofkau.stepdefinitions.soap;

import com.sofkau.setup.ApiSetUp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Consequence;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.nio.charset.StandardCharsets;

import static com.sofkau.models.soap.Headers.headers;
import static com.sofkau.questions.soap.ResponseSoap.responseSoap;
import static com.sofkau.tasks.soap.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.PathNumeroLetra.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;


public class BuscarNumerosEnLetras extends ApiSetUp {
    String body;

    private static final Logger LOGGER = Logger.getLogger(BuscarNumerosEnLetras.class);

    @Given("The user wants to know the textual value of a number")
    public void the_user_wants_to_know_the_textual_value_of_a_number() {

            try {
                setUp(SOAP_NUMERO_BASE_URL.getValue());
                LOGGER.info("INICIA LA AUTOMATIZACION");
                loadBody();
            } catch (Exception e) {
                LOGGER.info(" fallo la configuracion inicial");
                LOGGER.warn(e.getMessage());
                Assertions.fail();
            }
    }

    @When("The user sends a request to the API to obtain the textual value of a number")
    public void the_user_sends_a_request_to_the_API_to_obtain_the_textual_value_of_a_number() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_NUMERO.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("The user should receive as a response the value of the number in words")
    public void the_user_should_receive_as_a_response_the_value_of_the_number_in_words() {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    (Consequence) seeThat(" El idioma de la capital es",
                            responseSoap(), containsString("six"))
            );
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private void loadBody() {
        body = readFile(BODY_PATH_NUMERO.getValue());
        body = String.format(body,"6");
    }

    }
