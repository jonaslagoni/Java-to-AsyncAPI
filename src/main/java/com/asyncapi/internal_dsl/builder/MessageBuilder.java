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
