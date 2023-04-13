package com.sofkau.stepdefinitions.rest;

import com.sofkau.models.rest.ResponseRegister;
import com.sofkau.models.rest.User;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.rest.ReturnRegisterSuccessfulJsonResponse.returnRegisterSuccessfulJsonResponse;
import static com.sofkau.tasks.rest.DoPost.doPost;
import static com.sofkau.utils.ReqresResources.REGISTER_SUCCESSFUL_RESOURCE;
import static com.sofkau.utils.ReqresResources.REQRES_BASE_URL;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RegisterStepDefinition extends ApiSetUp {
    public static Logger LOGGER=Logger.getLogger(RegisterStepDefinition.class);
    private User user = new User();
    @Given("the user is in the register page")
    public void theUserIsInTheRegisterPage() {
        try {
            setUp(REQRES_BASE_URL.getValue());
            LOGGER.info("Se inicio la automatizacion en la URL: " + REQRES_BASE_URL.getValue());
        } catch (Exception e) {
            LOGGER.error("Error al iniciar la automatizacion : Detalles: "+ e.getMessage());
            actor.should(
                    seeThatResponse("El servidor no está disponible",
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
        }

    }

    @When("the user send a registration request with the {string} and the {string}")
    public void theUserSendARegistrationRequestWithTheAndThe(String email, String password) {
        try {
            user.setEmail(email);
            user.setPassword(password);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(REGISTER_SUCCESSFUL_RESOURCE.getValue())
                            .andTheRequestBody(user)
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());
        } catch (Exception e) {
            LOGGER.error("OcurriO un error al enviar la solicitud POST: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Then("the user see a status {int} response code and an id with a token")
    public void theUserSeeAStatusResponseCodeAndAnIdWithAToken(Integer statusCode) {
        ResponseRegister actualResponse= returnRegisterSuccessfulJsonResponse().answeredBy(actor);
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

}
