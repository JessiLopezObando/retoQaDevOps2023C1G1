package com.sofkau.stepdefinitions.rest;

import com.sofkau.models.rest.ResponseExchange;
import com.sofkau.setup.ApiSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import org.apache.log4j.Logger;

import static com.sofkau.questions.rest.ReturnExchangeResponse.returnExchangeResponse;
import static com.sofkau.tasks.rest.DoGet.doGet;
import static com.sofkau.utils.CoinGeckoResources.COIN_GECKO_BASE_URL;
import static com.sofkau.utils.CoinGeckoResources.GET_EXCHANGES_RESOURCE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.notNullValue;


public class GetExchangesStepDefinition extends ApiSetUp {

    public static Logger LOGGER = Logger.getLogger(GetExchangesStepDefinition.class);

    @Given("the user is in the coingecko web page")
    public void theUserIsInTheCoingeckoWebPage() {
        setUp(COIN_GECKO_BASE_URL.getValue());
        LOGGER.info("Inicio automatizacion");
    }

    @When("the user sends a request to the Get Exchanges service")
    public void theUserSendsARequestToTheGetExchangesService() {
        actor.attemptsTo(
            doGet().withTheResource(GET_EXCHANGES_RESOURCE.getValue())
        );
        LOGGER.info(SerenityRest.lastResponse().body().asString());
    }

    @Then("the user gets a status code response OK")
    public void theUserGetsAStatusCodeResponseOK() {
        try {
            ResponseExchange responseExchange = returnExchangeResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse(response -> response.statusCode(200)),
                    seeThat(act -> responseExchange.getName(), notNullValue()),
                    seeThat(act -> responseExchange.getCountry(), notNullValue()),
                    seeThat(act -> responseExchange.getTrustScore(), notNullValue()),
                    seeThat(act -> responseExchange.getYearEstablished(), notNullValue())
            );
        } catch (Exception e){
            LOGGER.warn(e.getMessage());
        }
    }
}
