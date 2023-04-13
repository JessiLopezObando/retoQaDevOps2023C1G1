package com.sofkau.stepdefinitions.rest;

import com.sofkau.models.rest.Admi;
import com.sofkau.models.rest.ResponseAuthentication;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import static com.sofkau.questions.rest.ReturnUserAuthenticationJsonResponse.returnUserAuthenticationJsonResponse;
import static com.sofkau.tasks.rest.DoPost.doPost;
import static com.sofkau.utils.RestfulBookerResources.AUTHENTICATION_SUCCESSFUL_RESOURSE;
import static com.sofkau.utils.RestfulBookerResources.RESTFUL_BOOKER_BASE_URL;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;

public class AuthenticationStepDefinition extends ApiSetUp {
    public static Logger LOGGER=Logger.getLogger(AuthenticationStepDefinition.class);
    private Admi admi = new Admi();
    @Given("the admin is on the authentication page")
    public void theAdminIsOnTheAuthenticationPage() {
        try {
            setUp(RESTFUL_BOOKER_BASE_URL.getValue());
            LOGGER.info("Se inicio la automatizacion en la URL: " + RESTFUL_BOOKER_BASE_URL.getValue());
        } catch (Exception e) {
            LOGGER.error("Error al iniciar la automatizacion : Detalles: "+ e.getMessage());
            actor.should(
                    seeThatResponse("El servidor no está disponible",
                            response -> response.statusCode(HttpStatus.SC_OK))
            );
        }

    }

    @When("the administrator sends an authentication request with username {string} and password {string}")
    public void theAdministratorSendsAnAuthenticationRequestWithUsernameAndPassword(String username, String password) {
        try {
            admi.setUsername(username);
            admi.setPassword(password);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(AUTHENTICATION_SUCCESSFUL_RESOURSE.getValue())
                            .andTheRequestBody(admi)
            );
            LOGGER.info(SerenityRest.lastResponse().body().asString());
        } catch (Exception e) {
            LOGGER.error("OcurriO un error al enviar la solicitud POST: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Then("the administrator should see a status response code {int} and a token")
    public void theAdministratorShouldSeeAStatusResponseCodeAndAToken(Integer statusCode) {
        ResponseAuthentication actualResponseAuthentication = returnUserAuthenticationJsonResponse().answeredBy(actor);
        try {
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(statusCode)),
                    seeThat("Retorna información",
                            act -> actualResponseAuthentication, notNullValue())
            );
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

}
