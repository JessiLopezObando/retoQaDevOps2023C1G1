package com.sofkau.questions;

import com.sofkau.models.Posts;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnPostResponse implements Question<Response> {
    @Override
    public Response answeredBy(Actor actor) {
        return (Response) SerenityRest.lastResponse().as(Posts.class);
    }

    public static ReturnPostResponse returnPostResponse() {
        return new ReturnPostResponse();
    }
}
