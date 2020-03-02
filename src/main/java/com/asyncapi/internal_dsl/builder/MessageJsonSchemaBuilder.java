/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.asyncapi.internal_dsl.model.Message;
import com.json_schema.builder.draft7.JsonSchemaBuilder;

/**
 *
 * @author Lagoni
 */
public class MessageJsonSchemaBuilder extends JsonSchemaBuilder<MessageJsonSchemaBuilder> {

    private MessageBuilder parent;
    private Message message;

    public MessageJsonSchemaBuilder(MessageBuilder parent, Message message) {
        this.parent = parent;
        this.message = message;
    }

    public MessageBuilder messageParent() {
        message.setPayload(this.build());
        return parent;
    }

    /**
     * Finish the builder by returning the AsyncAPI object.
     * 
     * @return AsyncAPI
     */
    public AsyncAPI finish() {
        message.setPayload(this.build());
        return parent.finish();
    }

}
