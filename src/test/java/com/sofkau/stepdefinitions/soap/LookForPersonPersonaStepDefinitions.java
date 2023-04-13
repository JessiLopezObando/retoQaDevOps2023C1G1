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
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class LookForPersonPersonaStepDefinitions extends ApiSetUp {


    String body;
    private static final Logger LOGGER = Logger.getLogger(LookForPersonPersonaStepDefinitions.class);


    @Given("the administrator wants to search for a person")
    public void theAdministratorWantsToSearchForAPerson() {
        try {
            setUp(SOAP_PERSONA_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();
        } catch (Exception e) {
            LOGGER.info("fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


    @When("the administrator makes a request to search for the person by their code")
    public void theAdministratorMakesARequestToSearchForThePersonByTheirCode() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_PERSONA.getValue())
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


    @Then("the administrator should see the data of the person associated with the code.")
    public void theAdministratorShouldSeeTheDataOfThePersonAssociatedWithTheCode() {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat(" Los datos de la persona son",
                            responseSoap(), containsString("Diavolo,Ralph A."))
            );
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }


    private void loadBody() {
        body = readFile(BODY_PATH_PERSONA.getValue());
        body = String.format(body, "5");
    }


}
