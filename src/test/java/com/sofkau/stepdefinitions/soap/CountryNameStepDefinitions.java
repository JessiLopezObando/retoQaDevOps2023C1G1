package com.sofkau.stepdefinitions.soap;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static com.sofkau.models.soap.Headers.headers;
import static com.sofkau.tasks.soap.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CountryNameStepDefinitions extends ApiSetUp {


    String body;
    private static final Logger LOGGER = Logger.getLogger(CountryNameStepDefinitions.class);

    @Given("the administrator wants to search for a country by its corresponding international code")
    public void theAdministratorWantsToSearchForACountryByItsCorrespondingInternationalCode() {
        try {
            setUp(SOAP_CAPITAL_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


    @When("the administrator makes a request to search for the country with the {string} code")
    public void theAdministratorMakesARequestToSearchForTheCountryWithTheCode(String code) {
        loadBody(code);
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CAPITAL.getValue())
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

    @Then("the administrator should see the name of the country corresponding to the provided code and a {int}")
    public void theAdministratorShouldSeeTheNameOfTheCountryCorrespondingToTheProvidedCodeAndA(Integer code) {


        String responseBody = new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);


        try {
            LOGGER.info(responseBody);
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + code,
                            response -> response.statusCode(code))

            );
            Document documentXml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(responseBody.getBytes()));
            String country = XPathFactory.newInstance().newXPath().compile("//m").evaluate(documentXml);
            LOGGER.info("NombrePais-->" + country);
            LOGGER.info("CUMPLE");


        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }


    private void loadBody(String codigo) {
        body = readFile(BODY_PATH_PAIS.getValue());
        body = String.format(body, codigo);
    }

}
