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

    /**
     * Set the name for this message
     * 
     * @param name to set
     * @return this MessageBuilder instance
     */
    public MessageBuilder name(String name) {
        message.setName(name);
        return this;
    }

    /**
     * Set the title for this message
     * 
     * @param title to set
     * @return this MessageBuilder instance
     */
    public MessageBuilder title(String title) {
        message.setTitle(title);
        return this;
    }

    /**
     * Set the summary for this message
     * 
     * @param summary to set
     * @return this MessageBuilder instance
     */
    public MessageBuilder summary(String summary) {
        message.setSummary(summary);
        return this;
    }

    /**
     * Set the description for this message
     * 
     * @param description to set
     * @return this MessageBuilder instance
     */
    public MessageBuilder description(String description) {
        message.setDescription(description);
        return this;
    }

    /**
     * Start building the payload using the JSON Schema builder.
     * 
     * @return MessageJsonSchemaBuilder 
     */
    public MessageJsonSchemaBuilder payload() {
        return new MessageJsonSchemaBuilder(this, message);
    }

    /**
     * Return to the parent OperationBuilder
     * 
     * @return parent OperationBuilder instance
     */
    public OperationBuilder parent() {
        return parent;
    }

    /**
     * Finish the builder by returning the AsyncAPI object.
     * 
     * @return AsyncAPI
     */
    public AsyncAPI finish() {
        return parent.finish();
    }
}
