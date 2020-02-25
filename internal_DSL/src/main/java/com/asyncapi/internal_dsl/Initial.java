/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl;

import com.asyncapi.internal_dsl.model.implementation.AsyncAPIBuilder;
import com.asyncapi.internal_dsl.builder.JsonSchemaPropertyBuilder;

/**
 *
 * @author lagoni
 */
public class Initial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here'
        new AsyncAPIBuilder().
            info().
                title("Streetlights API").
                version("1.0.0").
                description("The Smartylighting Streetlights API allows you to remotely manage the city lights.").
                license().
                    name("Apache 2.0").
                    url("https://www.apache.org/licenses/LICENSE-2.0").
            server("mosquitto").
                url("mqtt://test.mosquitto.org").
                protocol("mqtt").
            channel("light/measured").
                publish().
                    summary("Inform about environmental lighting conditions for a particular streetlight.").
                    operationId("onLightMeasured").
                    payload().
                        object().
                            property("id").
                                type(JsonSchemaPropertyBuilder.PropertyType.String).
                                minimum(0).
                                description("Id of the streetlight.").
                            property("lumens").
                                type(JsonSchemaPropertyBuilder.PropertyType.Integers).
                                minimum(0).
                                description("Light intensity measured in lumens.").
                            property("sentAt").
                                type(JsonSchemaPropertyBuilder.PropertyType.String).
                                format("date-time").
                                description("Date and time when the message was sent.");
    }
    
}
