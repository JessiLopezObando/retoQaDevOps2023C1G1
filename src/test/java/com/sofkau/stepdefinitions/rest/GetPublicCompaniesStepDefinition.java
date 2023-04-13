package com.sofkau.stepdefinitions.rest;

import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static com.sofkau.tasks.rest.DoGet.doGet;
import static com.sofkau.utils.CoinGeckoResources.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;

public class GetPublicCompaniesStepDefinition extends ApiSetUp {
    public static Logger LOGGER = Logger.getLogger(GetPublicCompaniesStepDefinition.class);

    private Response response;

    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;

    @Given("user is in the coingecko web page")
    public void userIsInTheCoingeckoWebPage() {
        setUp(COIN_GECKO_BASE_URL.getValue());
        LOGGER.info("Comienza la automatizacion");
    }

    @When("the user sends a request to the Get public companies service")
    public void theUserSendsARequestToTheGetPublicCompaniesService() {
        actor.attemptsTo(
                doGet().withTheResource(GET_PUBLIC_COMPANIES_RESOURCE.getValue())
        );
        LOGGER.info(SerenityRest.lastResponse().body().asString());
    }

    @Then("the user gets a status code response Ok and sees the information about public companies investing in Bitcoin and Ethereum")
    public void theUserGetsAStatusCodeResponseOkAndSeesTheInformationAboutPublicCompaniesInvestingInBitcoinAndEthereum() {
        try {
            responseBody = (JSONObject) parser.parse(response.getBody().asString());
            Object name = responseBody.get(PUBLIC_COMPANIES_NAME);
            Object country = responseBody.get(PUBLIC_COMPANIES_COUNTRY);
            Object symbol = responseBody.get(PUBLIC_COMPANIES_SYMBOL);

            actor.should(
                    seeThatResponse(response -> response.statusCode(200)),
                    seeThat(act -> name, notNullValue()),
                    seeThat(act -> country, notNullValue()),
                    seeThat(act -> symbol, notNullValue())
            );
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }

}
