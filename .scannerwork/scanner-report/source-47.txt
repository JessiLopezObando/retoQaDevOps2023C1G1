package com.sofkau.questions.rest;

import com.sofkau.models.rest.ResponseGame;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnQuestionGames implements Question<ResponseGame> {


    @Override
    public ResponseGame answeredBy(Actor actor) {

        return SerenityRest.lastResponse().as(ResponseGame.class);
    }

    public static ReturnQuestionGames returnQuestionGames() {
        return new ReturnQuestionGames();
    }


}
