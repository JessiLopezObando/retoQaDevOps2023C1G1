package com.sofkau.stepdefinitions.rest;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static com.sofkau.tasks.rest.DoDelete.doDelete;
import static com.sofkau.utils.JSONPlaceHolder.GET_RESOURCE;
import static com.sofkau.utils.JSONPlaceHolder.JSON_PLACE_HOLDER;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class JsonPlaceHolderDelete extends ApiSetUp{
    public static Logger LOGGER = Logger.getLogger(JsonPlaceHolderDelete.class);
    @Given("the user is in the JSON place holder post page")
    public void theUserIsInTheJSONPlaceHolderPostPage() {
        try{
            setUp(JSON_PLACE_HOLDER.getValue());
            LOGGER.info("Se inicia la automatizacion");
        }catch (Exception e){
            LOGGER.info("Fallo la configuracion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @When("the user sends the  that he wants to delete")
    public void theUserSendsTheThatHeWantsToDelete() {
        try{
            actor.attemptsTo(
                    doDelete().withTheResource(GET_RESOURCE.getValue())
                            .andWithTheNumber("")
            );
        }catch (Exception e){
            LOGGER.info("Fallo la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @When("the user sends the {int} that he wants to delete")
    public void theUserSendsTheThatHeWantsToDelete(Integer intUno) {
        try{
            actor.attemptsTo(
                    doDelete().withTheResource(GET_RESOURCE.getValue())
                            .andWithTheNumber(intUno+"")
            );
        }catch (Exception e){
            LOGGER.info("Fallo la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("the user sees a status {int}")
    public void theUserSeesAStatus(Integer intUno) {
        try{
            actor.should(
                    seeThatResponse("El codigo de respuesta es: " + SerenityRest.lastResponse().statusCode(),
                            response -> response.statusCode(intUno))
            );
        }catch (Exception e){
            LOGGER.info("Fallo la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }finally {
            LOGGER.info("| Esperado | Obtenido | Valor |");
            status(intUno);
        }
    }
    private void status(Integer intUno) {
        if(SerenityRest.lastResponse().statusCode()== intUno)
            LOGGER.info("| "+ intUno +" | "+SerenityRest.lastResponse().statusCode()+" | cumple |");
        else
            LOGGER.info("| "+ intUno +" | "+SerenityRest.lastResponse().statusCode()+" | no cumple |");
    }
}
