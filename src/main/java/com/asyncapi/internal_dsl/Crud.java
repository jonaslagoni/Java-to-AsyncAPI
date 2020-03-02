/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl;

import com.asyncapi.internal_dsl.builder.AsyncAPIBuilder;
import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.json_schema.builder.model.draft7.SimpleType;
import com.json_schema.builder.model.draft7.StringFormat;

/**
 *
 * @author lagoni
 */
public class Crud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JsonProcessingException {
        // TODO code application logic here'
        AsyncAPI as = new AsyncAPIBuilder().
                info().
                title("Streetlights API").
                version("1.0.0").
                license().
                name("Apache 2.0").
                url("https://www.apache.org/licenses/LICENSE-2.0").parent().
                description("The Smartylighting Streetlights API allows you to remotely manage the city lights.").parent().
                server("mosquitto").
                url("mqtt://test.mosquitto.org").
                protocol("mqtt").parent().
                crud("user").parameter("id").integer().
                minimum(0).
                description("Id of the user.").crudParent().body().object().title("user").property("name", SimpleType.STRING).
                description("Name of the user").property("joined_date", SimpleType.STRING).format(StringFormat.DATE_TIME).
                description("The date-time the user joined.").finish();
        System.out.println(as.toJson());
    }

}
