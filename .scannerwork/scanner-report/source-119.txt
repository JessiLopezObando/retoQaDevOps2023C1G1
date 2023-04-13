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
import static com.sofkau.questions.soap.ResponseSoap.responseSoap;
import static com.sofkau.tasks.soap.DoPostSoap.doPostSoap;
import static com.sofkau.utils.Path.*;
import static com.sofkau.utils.ManageFile.readFile;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class LookUpCityStepDefinition extends ApiSetUp {

    String body;
    private static final Logger LOGGER = Logger.getLogger(LookUpCityStepDefinition.class);
    @Given("a user that wants to know the City Information by ZIP Code")
    public void aUserThatWantsToKnowTheCityInformationByZIPCode() {
        try {
            setUp(SOAP_LOOK_UP_CITY_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e){
            LOGGER.info("fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

    @When("the user sends a request to get the city's information with the provided {string}")
    public void theUserSendsARequestToGetTheCitySInformationWithTheProvided(String zip) {
        try {
            loadBody(zip);
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_LOOK_UP_CITY.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
            System.out.println(body);
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }


    }

    @Then("the user should receive the city's information and the status code {int}")
    public void theUserShouldReceiveTheCitySInformationAndTheStatusCode(Integer code) {

        String responseBody = new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);

        try {
            LOGGER.info(responseBody);
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + code,
                            response -> response.statusCode(code))
            );
            /**
             * Método parse() de la instancia de DocumentBuilder analiza el cuerpo de respuesta del mensaje SOAP,
             * que se proporciona como un ByteArrayInputStream
             */
            if(code == 200) {
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(responseBody.getBytes()));

                //la consulta XPath es "//", que significa "seleccionar todos los elementos con el nombre especificado en el documento"
                String city = XPathFactory.newInstance().newXPath().compile("//City").evaluate(document);
                String state = XPathFactory.newInstance().newXPath().compile("//State").evaluate(document);

                LOGGER.info("La respuesta obtenia: ");
                LOGGER.info("City: " + city);
                LOGGER.info("State: " + state);

                actor.should(
                        seeThat(" El nombre de la ciudad es:",
                                responseSoap(), containsString(city))
                );

                LOGGER.info("CUMPLE");
            }

        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }


    }

    private void loadBody(String zipCode) {
        body = readFile(BODY_LOOK_UP_CITY_PATH.getValue());
        body = String.format(body, zipCode);
    }
}
