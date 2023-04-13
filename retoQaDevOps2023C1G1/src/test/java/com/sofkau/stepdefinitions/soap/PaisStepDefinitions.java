package com.sofkau.stepdefinitions.soap;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static com.sofkau.models.soap.Headers.headers;
import static com.sofkau.tasks.soap.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class PaisStepDefinitions extends ApiSetUp {


    String body;
    private static final Logger LOGGER = Logger.getLogger(PaisStepDefinitions.class);


    @Given("el administrador quiere buscar un pais por el codigo internacional corresondiente")
    public void elAdministradorQuiereBuscarUnPaisPorElCodigoInternacionalCorresondiente() {
        try {
            setUp(SOAP_CAPITAL_BASE_URL.getValue());
            LOGGER.info("INICIA LA AUTOMATIZACION");
        } catch (Exception e) {
            LOGGER.info(" fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }


    @When("el administrador realiza la peticion de busqueda del pais con su {string}")
    public void elAdministradorRealizaLaPeticionDeBusquedaDelPaisConSu(String codigo) {
        loadBody(codigo);
        try {
            actor.attemptsTo(
                    doPostSoap()
                            .andTheResource(RESOURCE_CAPITAL.getValue())
                            .withTheHeaders(headers().getHeadersCollection())
                            .andTheBody(body)
            );
            LOGGER.info("Realiza la peticion");
        } catch (Exception e) {
            LOGGER.info(" fallo al momento de realizar la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    @Then("el administrador deberia ver el nombre del pais corresponiente al codigo proporcionado y un status {int}")
    public void elAdministradorDeberiaVerElNombreDelPaisCorresponienteAlCodigoProporcionadoYUnStatus(Integer code) {

        String responseBody = new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);


        try {
            LOGGER.info(responseBody);
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + code,
                            response -> response.statusCode(code))

            );
            Document documentXml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(responseBody.getBytes()));
            String country = XPathFactory.newInstance().newXPath().compile("//m").evaluate(documentXml);
            LOGGER.info("NombrePais-->" + country);
            LOGGER.info("CUMPLE");


        } catch (Exception e) {
            LOGGER.info("Error al realizar la comparacion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }


    private void loadBody(String codigo) {
        body = readFile(BODY_PATH_PAIS.getValue());
        body = String.format(body, codigo);
    }

}
