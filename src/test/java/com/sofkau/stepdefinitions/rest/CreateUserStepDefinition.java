package com.sofkau.stepdefinitions.rest;


import com.sofkau.models.rest.CreateUser;

import com.sofkau.setup.ApiSetUp;

import com.sofkau.tasks.rest.DoPost;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.utils.ReqresResources.CREATE_USERS;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;


public class CreateUserStepDefinition extends ApiSetUp{
    public static Logger LOGGER = Logger.getLogger(String.valueOf(CreateUserStepDefinition.class));
    CreateUser create = new CreateUser();

    @Given("the manager is in the API")
    public void theManagerIsInTheAPI() {
        setUp(REQRES_BASE_URL.getValue());

    }

    @When("the administrator sends a POST request with a {string} and a {string}")
    public void theAdministratorSendsAPOSTRequestWithAAndA(String name, String job) {
        create.setName(name);
        create.setJob(job);
        try {
            actor.attemptsTo(
                    DoPost.doPost().withTheResource(CREATE_USERS.getValue())
                            .andTheRequestBody(create)
            );
            LOGGER.info("se ha enviado la peticion correctamente");
        } catch (Exception e) {
            LOGGER.warn("Se ha producido un error al enviar la solicitud POST: " + e.getMessage());
        }


    }

    @Then("The admin then sees a status response {int} and the JSON structure")
    public void theAdminThenSeesAStatusResponseAndTheJSONStructure(Integer statusCode) {
        try {

            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode))
            );

            LOGGER.info("Se ha validado el codigo de respuesta : " + statusCode);

            String responseBody = SerenityRest.lastResponse().getBody().asString();
            JsonPath jsonPath = new JsonPath(responseBody);

            actor.should(
                    seeThat("El campo 'name' esta presente", value -> jsonPath.getString("name"), notNullValue())
                            .orComplainWith(AssertionError.class, "El campo 'name' no esta presente o es nulo"),
                    seeThat("El campo 'job' esta presente", value -> jsonPath.getString("job"), notNullValue())
                            .orComplainWith(AssertionError.class, "El campo 'job' no esta presente o es nulo"),
                    seeThat("El campo 'id' esta presente", value -> jsonPath.getString("id"), notNullValue())
                            .orComplainWith(AssertionError.class, "El campo 'id' no esta presente o es nulo"),
                    seeThat("El campo 'createdAt' esta presente", value -> jsonPath.getString("createdAt"), notNullValue())
                            .orComplainWith(AssertionError.class, "El campo 'createdAt' no esta presente o es nulo")
            );

            LOGGER.info("La asercion para el contenido del json se ha completado correctamente, SI CUMPLE ");

        } catch (Exception e) {
            LOGGER.warn("Se ha producido un error al verificar la respuesta de la solicitud POST: " + e.getMessage());
        }
}}
