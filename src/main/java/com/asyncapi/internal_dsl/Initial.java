/*
 * The MIT License
 *
 * Copyright 2020 lagoni.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.asyncapi.internal_dsl;

import com.asyncapi.internal_dsl.builder.AsyncAPIBuilder;
import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.asyncapi.internal_dsl.model.Version;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public static void main(String[] args) throws JsonProcessingException {
        // TODO code application logic here'
        AsyncAPI api = new AsyncAPIBuilder().
            asyncapi(Version.v2_0_0).
            info().
                title("Streetlights API").
                version("1.0.0").
                license().
                    name("Apache 2.0").
                    url("https://www.apache.org/licenses/LICENSE-2.0").parent().
                description("The Smartylighting Streetlights API allows you to remotely manage the city lights.").parent().
            server("production").
                url("mqtt://test.mosquitto.org").
                protocol("mqtt").
                protocolVersion("1.0.0").
                description("MQTT production server").parent().
            channel("light/measured").
                description("Channel which is used to notify when light is measured.").
                publish().
                    summary("Inform about lumens measured").
                    description("Inform about environmental lighting conditions for a particular streetlight.").
                    operationId("onLightMeasured").
                        message().
                            description("Message contains lighting measurement from a specific light with its lumen with the sentAt timestamp").
                            name("onLightMeasured").
                            title("On Light Measured").
                            summary("Contain lightning measurement").
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
        System.out.println(api.toJson());
    }

}
