package com.sofkau.tasks;

import com.sofkau.interactions.OurGet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class DoGet implements Task {
    private String resource;

    public DoGet withTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OurGet.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()

                        )
        );
    }

    public static DoGet doGet(){
        return new DoGet();
    }
}
