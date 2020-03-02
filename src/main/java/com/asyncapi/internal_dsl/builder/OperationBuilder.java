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

    /**
     * Set the summary for this operation.
     * 
     * @param summary to set
     * @return this OperationBuilder instance
     */
    public OperationBuilder summary(String summary) {
        operation.setSummary(summary);
        return this;
    }

    /**
     * Set the description for this operation.
     * 
     * @param description to set
     * @return this OperationBuilder instance
     */
    public OperationBuilder description(String description) {
        operation.setDescription(description);
        return this;
    }

    /**
     * Set the operation id for this operation.
     * 
     * @param operationId to set
     * @return this OperationBuilder instance
     */
    public OperationBuilder operationId(String operationId) {
        operation.setOperationId(operationId);
        return this;
    }

    /**
     * Start building the message section for this operation.
     * 
     * @return MessageBuilder
     */
    public MessageBuilder message() {
        Message newMessage = new Message();
        operation.setMessage(newMessage);
        return new MessageBuilder(this, newMessage);
    }

    /**
     * Return to the parent ChannelBuilder builder.
     * 
     * @return ChannelBuilder the parent. 
     */
    public ChannelBuilder parent() {
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
