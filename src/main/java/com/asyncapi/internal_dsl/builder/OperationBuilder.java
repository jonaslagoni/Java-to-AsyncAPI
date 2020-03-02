/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.asyncapi.internal_dsl.model.Message;
import com.asyncapi.internal_dsl.model.Operation;

/**
 *
 * @author Lagoni
 */
public class OperationBuilder {

    private ChannelBuilder parent;
    private Operation operation;

    public OperationBuilder(ChannelBuilder instance, Operation operation) {
        this.parent = instance;
        this.operation = operation;
    }

    public OperationBuilder summary(String summary) {
        operation.setSummary(summary);
        return this;
    }

    public OperationBuilder description(String description) {
        operation.setDescription(description);
        return this;
    }

    public OperationBuilder operationId(String operationId) {
        operation.setOperationId(operationId);
        return this;
    }

    public MessageBuilder message() {
        Message newMessage = new Message();
        operation.setMessage(newMessage);
        return new MessageBuilder(this, newMessage);
    }

    public ChannelBuilder parent() {
        return parent;
    }

    public AsyncAPI finish() {
        return parent.finish();
    }
}
