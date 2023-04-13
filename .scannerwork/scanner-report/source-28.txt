package com.sofkau.interactions.rest;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

public class OurPost extends RestInteraction {

    private final String resource;
    public OurPost(String resource){
        this.resource=resource;
    }
    @Step("{0} executes a POST on the resource #resource")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().post(as(actor).resolve(resource)).then();
    }

    public static OurPost to(String resource){
        return instrumented(OurPost.class,resource);
    }
}
