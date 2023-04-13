package com.sofkau.stepdefinitions.soap;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

import static com.google.common.base.Predicates.notNull;
import static com.sofkau.models.soap.HeadersCurrency.headersCurrency;
import static com.sofkau.questions.soap.ResponseSoap.responseSoap;
import static com.sofkau.tasks.soap.DoPostSoap.doPostSoap;
import static com.sofkau.utils.ManageFile.readFile;
import static com.sofkau.utils.Path.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

public class ConversionStepDefinitions extends ApiSetUp{
    private static final Logger LOGGER = Logger.getLogger(ConversionStepDefinitions.class);
    private String body;
    @Given("a user that wants to know the exchange of their money in the {string} to {string} the amount of {int}")
    public void aUserThatWantsToKnowTheExchangeOfTheirMoneyInTheToTheAmountOf(String string, String string2, Integer int1) {
        try {
            setUp(SOAP_DIVISAS_BASE_URL.getValue());
            LOGGER.info("Se inicia la automatizacion");
            loadBody(string,string2,int1);
        }catch (Exception e){
            LOGGER.info("Fallo la configuracion inicial");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @When("the user sends the request to the currency api")
    public void theUserSendsTheRequestToTheCurrencyApi() {
        try{
            actor.attemptsTo(
                    doPostSoap().withTheHeaders(headersCurrency().getHeadersCollection())
                            .andTheBody(body)
                            .andTheResource(RESOURCE_CURRENCY.getValue())
            );
            LOGGER.info("Se realiza la peticion");
        }catch (Exception e){
            LOGGER.info("Fallo la peticion");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }
    @Then("the user gets the money exchange at {string}")
    public void theUserGetsTheMoneyExchangeAt(String string) throws ParserConfigurationException, IOException, SAXException {
        try{
            actor.should(
                    seeThatResponse("el codigo de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("el monto es",
                            responseSoap(), notNull())
            );
        }catch (Exception e){
            LOGGER.info("Error al comparar");
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }finally {
            LOGGER.info("| Esperado | Obtenido | Valor |");
            status();
            divisa(string);
        }
    }
    private void divisa(String string) throws ParserConfigurationException, IOException, SAXException {
        String responseString = LastResponse.received().answeredBy(actor).asString();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(responseString)));
        String convertedValue = doc.getElementsByTagName("ConvertToNumResult").item(0).getTextContent().trim();
        if (convertedValue.equalsIgnoreCase(string))
            LOGGER.info("| "+ string +" | "+convertedValue+" | cumple |");
        else
            LOGGER.info("| "+ string +" | "+convertedValue+" | no cumple |");
    }
    private void status() {
        if(LastResponse.received().answeredBy(actor).statusCode()==HttpStatus.SC_OK)
            LOGGER.info("| "+HttpStatus.SC_OK+" | "+LastResponse.received().answeredBy(actor).statusCode()+" | cumple |");
        else
            LOGGER.info("| "+HttpStatus.SC_OK+" | "+LastResponse.received().answeredBy(actor).statusCode()+" | no cumple |");
    }
    private void loadBody(String string, String string2,Integer int1) {
        body = readFile(CURRENCY_BODY_PATH.getValue());
        body = String.format(body, string,string2,int1);
    }
}
