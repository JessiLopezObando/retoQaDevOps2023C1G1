package com.sofkau.stepdefinitions.rest;

import com.sofkau.models.rest.Posts;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.sofkau.questions.rest.ReturnPutResponse.returnPutResponse;
import static com.sofkau.tasks.rest.DoPut.doPut;
import static com.sofkau.utils.JSONPlaceHolder.GET_POSTS;
import static com.sofkau.utils.JSONPlaceHolder.PLACE_HOLDER_BASE_URL;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class PutPostsStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(PutPostsStepDefinition.class);

    private Posts posts = new Posts();

    @Given("the JSONPlaceholder API is available to use")
    public void theJSONPlaceholderAPIIsAvailableToUse() {
        try {
            setUp(PLACE_HOLDER_BASE_URL.getValue());
            LOGGER.info("Se inicio la automatizacion en la URL: " + PLACE_HOLDER_BASE_URL.getValue());
        } catch (Exception e) {
            LOGGER.error("Error al iniciar la automatización : Detalles: "+ e.getMessage());
            actor.should(
                    seeThatResponse("El servidor no está disponible",
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
        }

    }

    @When("I make a PUT request to update the post with {string}, {string}, {string}, {int}")
    public void iMakeAPUTRequestToUpdateThePostWith(String id, String title, String body, Integer userId) {
        posts.setId(id);
        posts.setTitle(title);
        posts.setBody(body);
        posts.setUserId(userId);

        try {
            LOGGER.info("Realizando peticion PUT...");
            actor.attemptsTo(
                    doPut()
                            .withResource(GET_POSTS.getValue() + id)
                            .andTheRequestBody(posts)
            );

        } catch (Exception e) {
            LOGGER.error("Error durante PUT request: " + e.getMessage());
        }
    }

    @Then("the response status code that should be received is {int}")
    public void theResponseStatusCodeThatShouldBeReceivedIs(Integer code) {

        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code))
            );
            LOGGER.info("El codigo de respuesta es: " + lastResponse().statusCode());
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

    @Then("the response body should contain the updated post details")
    public void theResponseBodyShouldContainTheUpdatedPostDetails() throws ParseException {
        try {

            Response actualResponsePut = returnPutResponse().answeredBy(actor);

            LOGGER.info("Respuesta de la API: " + actualResponsePut.getBody().asString());

            actor.should(
                    seeThat("Retorna informacion",
                            act -> {
                                JSONObject jsonResponse = null;
                                try {
                                    jsonResponse = (JSONObject) new JSONParser().parse(actualResponsePut.getBody().asString());
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
                                String actualTitle = (String) jsonResponse.get("title");
                                String actualBody = (String) jsonResponse.get("body");
                                Long actualUserId = (Long) jsonResponse.get("userId");

                                return actualTitle.equals(posts.getTitle())
                                        && actualBody.equals(posts.getBody())
                                        && actualUserId.equals(posts.getUserId().longValue());
                            }
                    )
            );

        } catch (Exception e) {
            LOGGER.error("Error en la asercion: " + e.getMessage());
        }

    }
}
