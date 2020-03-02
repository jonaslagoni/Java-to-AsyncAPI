/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl;

import com.asyncapi.internal_dsl.builder.AsyncAPIBuilder;
import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.json_schema.builder.model.draft7.SimpleType;
import com.json_schema.builder.model.draft7.StringFormat;

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
        AsyncAPI api = new AsyncAPIBuilder().
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
                channel("light/measured").
                publish().
                summary("Inform about environmental lighting conditions for a particular streetlight.").
                operationId("onLightMeasured").
                message().
                payload().
                object().
                property("id", SimpleType.INTEGER).
                minimum(0).
                description("Id of the streetlight.").parent().
                property("lumens", SimpleType.INTEGER).
                minimum(0).
                description("Light intensity measured in lumens.").parent().
                property("sentAt", SimpleType.STRING).
                format(StringFormat.DATE_TIME).
                description("Date and time when the message was sent.").finish();
    }

}
