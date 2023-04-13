package com.sofkau.stepdefinitions.rest;

import com.sofkau.models.rest.Posts;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.sofkau.tasks.rest.DoPost.doPost;
import static com.sofkau.utils.JSONPlaceHolder.GET_POSTS;
import static com.sofkau.utils.JSONPlaceHolder.PLACE_HOLDER_BASE_URL;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


public class PostPostsStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(PostPostsStepDefinition.class);

    Posts posts = new Posts();

    @Given("I am on the JSONPlaceholder API")
    public void iAmOnTheJSONPlaceholderAPI() {

        try {
            setUp(PLACE_HOLDER_BASE_URL.getValue());
            LOGGER.info("Se inicio la automatizacion en la URL: " + PLACE_HOLDER_BASE_URL.getValue());
        } catch (Exception e) {
            LOGGER.error("Error al iniciar la automatizacion : Detalles: "+ e.getMessage());
            actor.should(
                    seeThatResponse("El servidor no está disponible",
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
        }
    }

    @When("I create a new post with the {string}, {string} and the {int}")
    public void iCreateANewPostWithTheAndThe(String title, String body, Integer userId) {

        posts.setTitle(title);
        posts.setBody(body);
        posts.setUserId(userId);

        try {
            actor.attemptsTo(

                    doPost()
                            .withTheResource(GET_POSTS.getValue())
                            .andTheRequestBody(posts)
            );
            LOGGER.info("Respuesta de la API con la peticion POST: " + lastResponse().asString());
        } catch (Exception e) {
            LOGGER.error("OcurriO un error al enviar la solicitud POST: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Then("the response status code should be displayed as {int}")
    public void theResponseStatusCodeShouldBeDisplayedAs(Integer code) {

        try {

            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code))
            );
            LOGGER.info("El código de respuesta es: " + lastResponse().statusCode());
        } catch (Exception e){
          LOGGER.warn(e.getMessage());
        }

    }

    @Then("the response should contain the new post")
    public void theResponseShouldContainTheNewPost() throws ParseException {

        try {

            //Analiza la respuesta de la solicitud HTTP y convierte su contenido a un objeto JSONObject
            JSONObject jsonResponse = (JSONObject) new JSONParser().parse(lastResponse().asString());

            //Esta línea indica el comienzo de una aserción que utiliza el patrón de diseño Actor
            actor.should(
                    seeThat("El post creado se encuentra en la respuesta",
                            //Comienzo de la función lambda que implementa la aserción. La función lambda toma un objeto Actor como argumento y devuelve un valor booleano.
                            act -> {
                                String actualTitle = (String) jsonResponse.get("title");
                                String actualBody = (String) jsonResponse.get("body");
                                Long actualUserId = (Long) jsonResponse.get("userId");

                                return actualTitle.equals(posts.getTitle())
                                        && actualBody.equals(posts.getBody())
                                        && actualUserId.equals(posts.getUserId().longValue());
                            }
                    )
            );

            LOGGER.info("El post creado se encuentra en la respuesta " + jsonResponse.toJSONString());

        } catch (ParseException e) {
            LOGGER.error("Ocurrio un error: " + e.getMessage());
        }

    }

}
