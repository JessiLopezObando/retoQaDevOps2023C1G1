package com.sofkau.stepdefinitions.rest;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import static com.sofkau.tasks.rest.DoDelete.doDelete;
import static com.sofkau.utils.FakeStoreResources.FAKE_STORE_URL;
import static com.sofkau.utils.FakeStoreResources.USERS_RESOURCE;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class DeleteStoreUserStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(DeleteStoreUserStepDefinition.class));
    private String usuario;

    @Given("the user has access to Fake Store API")
    public void the_user_has_access_to_Fake_Store_API() {
        try {
            setUp(FAKE_STORE_URL.getValue());
            LOGGER.info("INICIO DE LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info("Fallo la configuracion");
            Assertions.fail(e.getMessage());
        }
    }


    @When("the user makes a DELETE request with the {string} of the user to delete")
    public void the_user_makes_a_DELETE_request_with_the_of_the_user_to_delete(String user) {
        usuario = user;
        try {
            actor.attemptsTo(
                    doDelete().withTheResource((USERS_RESOURCE.getValue()))
                            .andWithTheNumber(user)
            );
            LOGGER.info("Se ejecuta la peticion");
        } catch (Exception e) {
            LOGGER.info("Fallo en la peticion realizada");
            Assertions.fail(e.getMessage());
        }
    }


    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer code) {
        try {
            actor.should(
                    seeThatResponse("El código de respuesta es: " + code,
                            response -> response.statusCode(code)));
            if (code.equals(200)) {
                LOGGER.info("El Usuario " + usuario + " ha sido borrado satisfactoriamente");
            }
            else if (code.equals(400)) {
                LOGGER.info("El Usuario " + usuario + " no existe");
            } else {
                LOGGER.info("Código de respuesta inesperado");
            }
        } catch (Exception e) {
            LOGGER.error("Error en el step: " + e.getMessage());
            Assertions.fail(e.getMessage());
        }
    }

}
