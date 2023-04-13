package com.sofkau.tasks.rest;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;



public class DoDelete implements Task {
    private String resource;
    private String numero;
    public DoDelete withTheResource(String resource){
        this.resource=resource;
        return this;
    }
    public DoDelete andWithTheNumber(String numero){
        this.numero=numero;
        return this;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
              Delete.from(resource+numero)
        );
    }

    public static DoDelete doDelete() {
        return new DoDelete();
    }
}
