package com.sofkau.stepdefinitions.rest;

import com.sofkau.setup.ApiSetUp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;

import static com.sofkau.questions.rest.ReturnGetResponse.returnGetResponse;
import static com.sofkau.tasks.rest.DoGet.doGet;
import static com.sofkau.utils.JSONPlaceHolder.GET_RESOURCE;
import static com.sofkau.utils.JSONPlaceHolder.PLACE_HOLDER_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;


public class ConsultaPublicacionesStepDefinition extends ApiSetUp {

    private static final Logger LOGGER = Logger.getLogger(ConsultaPublicacionesStepDefinition.class.getName());

    @Given("That the user is on the registration page")
    public void that_the_user_is_on_the_registration_page() {
        setUp(PLACE_HOLDER_BASE_URL.getValue());
    }

    @When("The user sends a GET request with the {string}")
    public void the_user_sends_a_GET_request_with_the(String id) {
        actor.attemptsTo(
                doGet()
                        .withTheResource(GET_RESOURCE.getValue()+id)
        );
        System.out.println(SerenityRest.lastResponse().body().asString());
    }

    @Then("The user sees a status response code of {int} and the information of the publication")
    public void the_user_sees_a_status_response_code_of_and_the_information_of_the_publication(Integer codigo) {
        try{
            Response actuallResponse = returnGetResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("****** El codigo de respuesta de la peticion seria --> " + codigo,
                            response -> response.statusCode(codigo)),
                    seeThat("Retorna información",
                            act -> actuallResponse, notNullValue())
            );
            LOGGER.info("Respuesta status code: " + codigo);
        }catch (Exception e){
            LOGGER.error("****** Se tiene el siguiente error -->: ", e);
            throw e;
        }
    }

}
