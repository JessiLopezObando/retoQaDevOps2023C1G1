package com.sofkau.setup;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import static com.sofkau.utils.Log4jValues.LOG4J_PROPERTY_PATH;

public class ApiSetUp {
    protected Actor actor = new Actor("Usuario");

    protected void setUp(String urlBase){
        actorCallAnApi(urlBase);
    }

    private void actorCallAnApi(String urlBase){
        actor.can(CallAnApi.at(urlBase));
    }

    private void setUpLog4j(){
        PropertyConfigurator.configure(LOG4J_PROPERTY_PATH.getValue());
    }
}
