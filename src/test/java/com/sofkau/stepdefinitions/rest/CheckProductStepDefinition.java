package com.sofkau.stepdefinitions.rest;

import com.sofkau.models.rest.ProductStore;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.sofkau.tasks.rest.DoGet.doGet;
import static com.sofkau.utils.FakeStoreResources.FAKE_STORE_URL;
import static com.sofkau.utils.FakeStoreResources.PRODUCTS_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckProductStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(String.valueOf(CheckProductStepDefinition.class));
    private ProductStore product = new ProductStore();

    @Given("the user has access to the Fake Store API")
    public void the_user_has_access_to_the_Fake_Store_API() {
        try {
            setUp(FAKE_STORE_URL.getValue());
            LOGGER.info("INICIO DE LA AUTOMATIZACION");
        }catch (Exception e){
            LOGGER.info("Fallo en la configuracion inicial");
            Assertions.fail(e.getMessage());
        }
    }


    @When("the user makes a GET petition with the ID {int}")
    public void the_user_makes_a_GET_petition_with_the_ID(Integer productId) {
        product.setProductID(productId);
        try{
        actor.attemptsTo(
                doGet()
                        .withTheResource(PRODUCTS_RESOURCE.getValue() + productId)

        );
            LOGGER.info("Se realiza la peticion");

        }
        catch(Exception e) {
            LOGGER.info("Fallo en la peticion realizada");
            Assertions.fail(e.getMessage());
        }

    }


    @Then("the user see a status {int} response code and can see the {string} and {double}")
    public void the_user_see_a_status_response_code_and_can_see_the_and(Integer codigo, String titulo, Double precio) {
        LOGGER.info("Step - the user see a status response code and can see the title and price");

        try {
            actor.should(
                    seeThatResponse("El código de respuesta es: " + codigo,
                            response -> response.statusCode(codigo)),
                    seeThat("Retorna información",
                            response -> response, notNullValue())
            );

            String responseBody = SerenityRest.lastResponse().body().asString();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(responseBody);

            String actualTitle = (String) jsonObject.get("title");
            assertEquals(titulo, actualTitle);

            Double actualPrice = Double.parseDouble(jsonObject.get("price").toString());
            assertEquals(precio, actualPrice);
            LOGGER.info("El producto es: " + actualTitle + " y el precio es:" + actualPrice);
            LOGGER.info("FINAL DE LA AUTOMATIZACION");

        } catch (Exception e) {
            LOGGER.error("Error en el step Selecciona los productos a comprar: " + e.getMessage());
            Assertions.fail(e.getMessage());
        }

    }
}
