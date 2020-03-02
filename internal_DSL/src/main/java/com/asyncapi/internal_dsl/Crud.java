/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl;

import com.asyncapi.internal_dsl.builder.AsyncAPIBuilder;

/**
 *
 * @author lagoni
 */
public class Crud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here'
        new AsyncAPIBuilder().
                info().
                title("Streetlights API").
                version("1.0.0").
                description("The Smartylighting Streetlights API allows you to remotely manage the city lights.").parent().
                license().
                name("Apache 2.0").
                url("https://www.apache.org/licenses/LICENSE-2.0").parent().
                server("mosquitto").
                url("mqtt://test.mosquitto.org").
                protocol("mqtt").parent().
                crud("user").
                keyProperty("id")
        type(JsonSchemaPropertyBuilder.PropertyType.Integer).
                minimum(0).
                description("Id of the user.").
                schema().object().property("name").
                type().
                minimum(0).
                description("Name of the user");
    }

}
