package com.sofkau.stepdefinitions.rest;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;

import static com.sofkau.tasks.rest.DoDelete.doDelete;
import static com.sofkau.utils.Path.DELETE_PRODUCTS;
import static com.sofkau.utils.Path.DELETE_PRODUCTS_BASE;


public class ProductsStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(ProductsStepDefinition.class));

    @Given("I'm on the dummyJSON API as an administrator")
    public void iMOnTheDummyJSONAPIAsAnAdministrator() {
        try{
            setUp(DELETE_PRODUCTS_BASE.getValue());
            LOGGER.info("inicio de la automatizacion en :" + DELETE_PRODUCTS_BASE);

        } catch (Exception e){
            LOGGER.info("se ha producido un error" + e);
        }

    }

    @When("I send a DELETE request with the specific {string} of a product")
    public void iSendADELETERequestWithTheSpecificOfAProduct(String id) {
        try{
            actor.attemptsTo(
            doDelete().withTheResource(DELETE_PRODUCTS.getValue() + id)
            );
            System.out.println(SerenityRest.lastResponse().body().asString());
        } catch (Exception e){
            LOGGER.info("ocurrio un error haciendo la peticion" + e);
        }

    }

    @Then("I see a status response {int} and a JSON structure")
    public void iSeeAStatusResponseAndAJSONStructure(Integer expectedStatusCode) {
        try {
            SerenityRest.lastResponse().then().statusCode(expectedStatusCode);
            LOGGER.info("Se ha validado correctamente el codigo de estado :"+ expectedStatusCode);


        } catch (Exception e) {
            LOGGER.info("ocurrio un error validando el codigo de estado: " + e);
        }

    }

}
