package com.sofkau.stepdefinitions.rest;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.tasks.rest.DoGet.doGet;
import static com.sofkau.utils.MarvelResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetASingleCharacterStepDefinition extends ApiSetUp {

    public static Logger LOGGER = Logger.getLogger(GetASingleCharacterStepDefinition.class);
    private Response response;

    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;

    @Given("the user is in the Marvel developer portal")
    public void theUserIsInTheMarvelDeveloperPortal() {
        try {
            setUp(MARVEL_BASE_URL.getValue());
            LOGGER.info("INICIA AUTOMATIZACION");
        } catch (Exception e){
            LOGGER.info("Fallo en la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("the user sends a request with a valid character id {string}")
    public void theUserSendsARequestWithAValidCharacterId(String id) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(String.format(
                                    MARVEL_GET_SINGLE_CHARACTER_RESOURCE.getValue(),
                                    id,
                                    MARVEL_TOKEN.getValue(),
                                    MARVEL_HASH.getValue())
                            )
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());

        } catch (Exception e){
            LOGGER.info("Fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("the user gets a status code response OK and can see the character's name {string} and {string}")
    public void theUserGetsAStatusCodeResponseOKAndCanSeeTheCharacterSNameAnd(String name, String id) throws ParseException {

        try {
            String characterName = getCharacterName();
            String characterId = getCharacterId();
            long characterComics = getCharacterComics();
            actor.should(
                    seeThatResponse("El codigo de restpuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El nombre del personaje es: ", act-> characterName,
                            equalTo(name)),
                    seeThat("El id del personaje es: ", act-> characterId,
                            equalTo(id)),
                    seeThat("El personaje esta presente en los comics de Marvel: ", act-> characterComics,
                            notNullValue())
            );
            LOGGER.info("CUMPLE!");
        } catch (Exception e) {
            LOGGER.info("Error en la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Given("a user is in the Marvel developer site")
    public void aUserIsInTheMarvelDeveloperSite() {
        try {
            setUp(MARVEL_BASE_URL.getValue());
            LOGGER.info("INICIA AUTOMATIZACION");
        } catch (Exception e){
            LOGGER.info("Fallo en la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @When("the user sends an invalid request, with an id {string}, a token {string} and a hash {string}")
    public void theUserSendsAnInvalidRequestWithAnIdATokenAndAHash(String id, String token, String hash) {
        try {
            actor.attemptsTo(
                    doGet()
                            .withTheResource(String.format(
                                    MARVEL_GET_SINGLE_CHARACTER_RESOURCE.getValue(),
                                    id,
                                    token,
                                    hash)
                            )
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());

        } catch (Exception e){
            LOGGER.info("Fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("the user gets a status code response {int}")
    public void theUserGetsAStatusCodeResponse(Integer code) {
        try {
            actor.should(
                    seeThatResponse("El codigo de restpuesta es: " + code,
                            response -> response.statusCode(code))

            );
            LOGGER.info("CUMPLE!");
        } catch (Exception e) {
            LOGGER.info("Error en la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    private String getCharacterName() throws ParseException {
        response = (Response) SerenityRest.lastResponse().body();
        responseBody = (JSONObject) parser.parse(response.getBody().asString());
        JSONObject data = (JSONObject) responseBody.get(ATTRIBUTE_DATA.getValue());
        JSONArray results = (JSONArray) data.get(ATTRIBUTE_RESULTS.getValue());
        JSONObject att = (JSONObject) results.get(0);
        String characterName = att.get(ATTRIBUTE_NAME.getValue()).toString();
        return characterName;
    }

    private String getCharacterId() throws ParseException {
        response = (Response) SerenityRest.lastResponse().body();
        responseBody = (JSONObject) parser.parse(response.getBody().asString());
        JSONObject data = (JSONObject) responseBody.get(ATTRIBUTE_DATA.getValue());
        JSONArray results = (JSONArray) data.get(ATTRIBUTE_RESULTS.getValue());
        JSONObject att = (JSONObject) results.get(0);
        String characterId = att.get(ATTRIBUTE_ID.getValue()).toString();
        return characterId;
    }

    private long getCharacterComics() throws ParseException {
        response = (Response) SerenityRest.lastResponse().body();
        responseBody = (JSONObject) parser.parse(response.getBody().asString());
        JSONObject data = (JSONObject) responseBody.get(ATTRIBUTE_DATA.getValue());
        JSONArray results = (JSONArray) data.get(ATTRIBUTE_RESULTS.getValue());
        JSONObject att = (JSONObject) results.get(0);
        JSONObject comics = (JSONObject) att.get("comics");
        long availableComics = (long) comics.get("available");
        return availableComics;
    }
}
