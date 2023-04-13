package com.sofkau.questions.rest;


import com.sofkau.models.rest.ResponseRegister;

import com.sofkau.models.rest.Response;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;


public class ReturnRegisterSuccessfulJsonResponse implements Question<ResponseRegister> {
    @Override
    public ResponseRegister answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(ResponseRegister.class);

    }
    public static ReturnRegisterSuccessfulJsonResponse returnRegisterSuccessfulJsonResponse(){
        return new ReturnRegisterSuccessfulJsonResponse();
    }
}