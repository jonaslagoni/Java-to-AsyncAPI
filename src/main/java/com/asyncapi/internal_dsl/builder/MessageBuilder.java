/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.asyncapi.internal_dsl.model.Message;

/**
 *
 * @author Lagoni
 */
public class MessageBuilder {

    private OperationBuilder parent;
    private Message message;

    public MessageBuilder(OperationBuilder parent, Message message) {
        this.parent = parent;
        this.message = message;
    }

    public MessageBuilder name(String name) {
        message.setName(name);
        return this;
    }

    public MessageBuilder title(String title) {
        message.setTitle(title);
        return this;
    }

    public MessageBuilder summary(String summary) {
        message.setSummary(summary);
        return this;
    }

    public MessageBuilder description(String description) {
        message.setDescription(description);
        return this;
    }

    public MessageJsonSchemaBuilder payload() {
        return new MessageJsonSchemaBuilder(this, message);
    }

    public OperationBuilder parent() {
        return parent;
    }

    public AsyncAPI finish() {
        return parent.finish();
    }
}
