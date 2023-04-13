package com.sofkau.stepdefinitions.rest;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.sofkau.tasks.rest.DoGet.doGet;

import static com.sofkau.utils.SWAPIResources.PEOPLE_RESOURSE;
import static com.sofkau.utils.SWAPIResources.SWAPI_BASE_URL;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeopleStarWarsStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(PeopleStarWarsStepDefinition.class);

    @Given("that the user has the ID of a Star Wars character")
    public void thatTheUserHasTheIDOfAStarWarsCharacter() {
        try {
            setUp(SWAPI_BASE_URL.getValue());
            LOGGER.info("Se inicio la automatizacion en la URL: " + SWAPI_BASE_URL.getValue());
        } catch (Exception e) {
            LOGGER.error("Error al iniciar la automatizacion : Detalles: " + e.getMessage());
            actor.should(
                    seeThatResponse("El servidor no está disponible",
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
        }
    }

    @When("the user makes a request with the character id {int}")
    public void theUserMakesARequestWithTheCharacterId(Integer id) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(PEOPLE_RESOURSE.getValue() + id)
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());
        } catch (Exception e) {
            LOGGER.error("OcurriO un error al enviar la solicitud GET: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Then("the user should see a response containing the character's name {string} and gender {string}")
    public void theUserShouldSeeAResponseContainingTheCharacterSNameAndGender(String nombre, String genero) {
        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(200))
        );
        String responseBody = SerenityRest.lastResponse().body().asString();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) parser.parse(responseBody);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String name = (String) jsonObject.get("name");
        assertEquals(nombre, name);
        String gender = (String) jsonObject.get("gender");
        assertEquals(genero, gender);
    }

}
