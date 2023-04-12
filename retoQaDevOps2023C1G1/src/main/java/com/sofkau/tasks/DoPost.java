package com.sofkau.tasks;

import com.sofkau.interactions.OurPost;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class DoPost implements Task {
    private String resource;
    private Object requestBody;


    public DoPost withTheResource(String resource){
        this.resource=resource;
        return this;
    }

    public DoPost andTheRequestBody(Object requestBody){
        this.requestBody=requestBody;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OurPost.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(requestBody)
                        )
        );

    }

    public static DoPost doPost(){
        return new DoPost();
    }
}
