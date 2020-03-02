/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.asyncapi.internal_dsl.model.Channel;
import com.asyncapi.internal_dsl.model.Operation;

/**
 *
 * @author Lagoni
 */
public class ChannelBuilder {

    private AsyncAPIBuilder parent;
    private Channel channel;

    public ChannelBuilder(AsyncAPIBuilder instance, Channel channel) {
        this.parent = instance;
        this.channel = channel;
    }

    /**
     * Set the description for this channel.
     * 
     * @param description to set
     * @return this ChannelBuilder instance
     */
    public ChannelBuilder description(String description) {
        channel.setDescription(description);
        return this;
    }

    /**
     * Start building the subscribe operation.
     * 
     * @return OperationBuilder
     */
    public OperationBuilder subscribe() {
        Operation newOperation = channel.getSubscribe();
        if (newOperation == null) {
            newOperation = new Operation();
            channel.setSubscribe(newOperation);
        }
        return new OperationBuilder(this, newOperation);
    }

    /**
     * Start building the publish operation.
     * 
     * @return OperationBuilder
     */
    public OperationBuilder publish() {
        Operation newOperation = channel.getPublish();
        if (newOperation == null) {
            newOperation = new Operation();
            channel.setPublish(newOperation);
        }
        return new OperationBuilder(this, newOperation);
    }

    /**
     * Return to the parent AsyncAPIBuilder
     * 
     * @return parent AsyncAPIBuilder instance
     */
    public AsyncAPIBuilder parent() {
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
