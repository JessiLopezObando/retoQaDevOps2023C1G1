package com.sofkau.stepdefinitions.rest;

import com.sofkau.models.rest.Product;
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
import org.junit.jupiter.api.Assertions;

import static com.sofkau.questions.rest.ReturnQuestionProduct.returnQuestionProduct;
import static com.sofkau.tasks.rest.DoPost.doPost;
import static com.sofkau.utils.ProductResources.PRODUCT_RESOURCES_BASE_URL;
import static com.sofkau.utils.ProductResources.PRODUCT_SUCCESSFUL_RESOURCES;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.core.IsNull.notNullValue;

public class CreateProductsStepDefinition extends ApiSetUp {
    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;
    private Response response;
    public static Logger LOGGER = Logger.getLogger(GetFreetogameStepDefinition.class);

    private Product producto = new Product();


    @Given("the administrator is on the FakerProducts page in the create product section")
    public void theAdministratorIsOnTheFakerProductsPageInTheCreateProductSection() {
        setUp(PRODUCT_RESOURCES_BASE_URL.getValue());

    }


    @When("the administrator creates a new product with {string}, {double}, {string}, {string}, {string}")
    public void theAdministratorCreatesANewProductWith(String title, Double precio, String descripcion, String img, String categoria) {
        setValores(title, precio, descripcion, img, categoria);
        actor.attemptsTo(
                doPost()
                        .withTheResource(PRODUCT_SUCCESSFUL_RESOURCES.getValue())
                        .andTheRequestBody(producto)
        );
        System.out.println(lastResponse().body().asString());

    }


    @Then("the administrator should see a message with information about the new product with a {int} status")
    public void theAdministratorShouldSeeAMessageWithInformationAboutTheNewProductWithAStatus(Integer code) {
        try {
            // Obtener la respuesta del servidor con Serenity BDD
            Product actualResponse = returnQuestionProduct().answeredBy(actor);

            actor.should(
                    // Validar el código de estado HTTP con Serenity BDD
                    seeThatResponse("El codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(code)),
                    // Validar que la respuesta tenga información con Serenity BDD
                    seeThat("Retorna información",
                            act -> actualResponse.getTitle(), notNullValue())
            );
            responseBody = (JSONObject) parser.parse(lastResponse().asString());
            LOGGER.info(" esta es la respuesta ---> " + actualResponse.getTitle() + actualResponse.getCategory());

            ModeloRespuesta(actualResponse);
        } catch (AssertionError e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail("La validación de la respuesta del servidor ha fallado.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void ModeloRespuesta(Product actualResponse) {
        // Validar las propiedades "title" y "category"
        String title = (String) responseBody.get("title");
        String category = (String) responseBody.get("category");
        Assertions.assertEquals(title, actualResponse.getTitle());
        Assertions.assertEquals(category, actualResponse.getCategory());
    }


    private void setValores(String title, Double precio, String descripcion, String imagen, String categoria) {
        producto.setTitle(title);
        producto.setPrice(precio);
        producto.setDescription(descripcion);
        producto.setImage(imagen);
        producto.setCategory(categoria);
    }


}
