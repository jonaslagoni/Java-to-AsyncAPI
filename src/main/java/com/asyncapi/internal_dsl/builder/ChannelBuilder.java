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
