package com.sofkau.questions.rest;

import com.sofkau.models.rest.Product;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnQuestionProduct implements Question<Product> {


    @Override
    public Product answeredBy(Actor actor) {

        return SerenityRest.lastResponse().as(Product.class);
    }

    public static ReturnQuestionProduct returnQuestionProduct() {
        return new ReturnQuestionProduct();
    }

}
