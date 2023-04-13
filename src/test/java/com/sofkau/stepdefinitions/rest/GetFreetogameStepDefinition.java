package com.sofkau.stepdefinitions.rest;

import com.sofkau.models.rest.ResponseGame;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.rest.ReturnQuestionGames.returnQuestionGames;
import static com.sofkau.tasks.rest.DoGet.doGet;
import static com.sofkau.utils.FreetoGameResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsNull.notNullValue;

public class GetFreetogameStepDefinition extends ApiSetUp {

    public static Logger LOGGER = Logger.getLogger(GetFreetogameStepDefinition.class);


    @Given("the player is on the appropriate page to make the query")
    public void thePlayerIsOnTheAppropriatePageToMakeTheQuery() {
        setUp(FREETOGAME_RESOURCES_BASE_URL.getValue());
    }

    @When("the player makes a request to search for the game by {int}")
    public void thePlayerMakesARequestToSearchForTheGameBy(Integer id) {
        actor.attemptsTo(
                doGet()
                        .withTheResource(FREETOGAME_GET_RESOURCE.getValue() + id)
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }


    @Then("the player receives a {int} status with the game found.")
    public void thePlayerReceivesAStatusWithTheGameFound(Integer code) {
        ResponseGame actualResponse = returnQuestionGames().answeredBy(actor);
        LOGGER.info("respuesta de la api-->" + actualResponse);

        actor.should(
                seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                        response -> response.statusCode(code)),

                seeThat("Retorna información",
                        act -> actualResponse, notNullValue())
        );
    }


}
