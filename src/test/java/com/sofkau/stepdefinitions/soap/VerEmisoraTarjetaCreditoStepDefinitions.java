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

import static com.sofkau.models.soap.HeadersCard.headersCard;
import static com.sofkau.questions.soap.ResponseSoap.responseSoap;
import static com.sofkau.tasks.soap.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.PathCreditCard.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class VerEmisoraTarjetaCreditoStepDefinitions extends ApiSetUp {
    String body;
    String numeroTarjeta;
    private static final Logger LOGGER = Logger.getLogger(VerEmisoraTarjetaCreditoStepDefinitions.class);

    @Given("an Admin that wants to check the company with the credit card number {string}")
    public void an_Admin_that_wants_to_check_the_company_with_the_credit_card_number(String string) {
        try {
            numeroTarjeta = string;
            setUp(SOAP_CREDIT_CARD_VALIDATOR.getValue());
            loadBody();
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }



    @When("the Admin send the number to the API")
    public void the_Admin_send_the_number_to_the_API() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CREDIT_CARD.getValue())
                            .withTheHeaders(headersCard().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }



    @Then("the Admin can see the credit card company {string}")
    public void the_Admin_can_see_the_credit_card_company(String string) {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat(" la entidad emisora de la tarjeta de credito es",
                            responseSoap(), containsString(string))
            );
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    private void loadBody() {
        body = readFile(CARD_BODY_PATH.getValue());
        body = String.format(body, numeroTarjeta);

    }
}
