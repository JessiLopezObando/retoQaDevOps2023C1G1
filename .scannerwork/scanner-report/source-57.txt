package com.sofkau.tasks.soap;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

public class DoPostSoap implements Task {

    private Map<String, Object> headers;
    private String body;
    private String resource;

    public DoPostSoap withTheHeaders(Map<String, Object> headers){
        this.headers=headers;
        return this;
    }

    public DoPostSoap andTheResource(String resource){
        this.resource=resource;
        return this;
    }

    public DoPostSoap andTheBody(String body){
        this.body=body;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(resource)
                        .with(requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                .headers(headers)
                                .body(body)
                        )
        );
    }

    public static  DoPostSoap doPostSoap(){
        return new DoPostSoap();
    }
}
