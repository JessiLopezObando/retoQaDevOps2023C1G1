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
import static com.sofkau.utils.PathCity.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class CityNameStepDefinitions extends ApiSetUp {
    String body;
    private static final Logger LOGGER = Logger.getLogger(CityNameStepDefinitions.class);

    @Given("that the user has access to the LatLonListCityNames service")
    public void thatTheUserHasAccessToTheLatLonListCityNamesService() {
        try {
            setUp(SOAP_CITY_NAME_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
            loadBody();
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("send a SOAP request with the latitude and longitude of the desired area")
    public void sendASOAPRequestWithTheLatitudeAndLongitudeOfTheDesiredArea() {
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESORUCE_CITY.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info("fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("you should receive a successful response with a list of city names and their geographic coordinates")
    public void youShouldReceiveASuccessfulResponseWithAListOfCityNamesAndTheirGeographicCoordinates() {
        try {
            LOGGER.info(new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8));
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat(" lista de ciudades con sus cordenadas geograficas: ",
                            responseSoap(), containsString("Kansas City"))
            );
            LOGGER.info("CUMPLE");
        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private void loadBody() {
        body = readFile(BODY_PATH_CITY.getValue());
        body = String.format(body, "2");
    }
}