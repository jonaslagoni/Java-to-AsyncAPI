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
import com.asyncapi.internal_dsl.model.Server;

/**
 *
 * @author lagoni
 */
public class ServerBuilder {

    private AsyncAPIBuilder parent;
    private Server server;

    public ServerBuilder(AsyncAPIBuilder instance, Server server) {
        this.parent = instance;
        this.server = server;
    }

    /**
     * Set the URL for this server
     * 
     * @param url to set
     * @return ServerBuilder
     */
    public ServerBuilder url(String url) {
        server.setUrl(url);
        return this;
    }

    /**
     * Set the protocol for this server
     * 
     * @param protocol to set
     * @return ServerBuilder
     */
    public ServerBuilder protocol(String protocol) {
        server.setProtocol(protocol);
        return this;
    }

    /**
     * Set the protocol version for this server.
     * 
     * @param protocolVersion to set
     * @return ServerBuilder
     */
    public ServerBuilder protocolVersion(String protocolVersion) {
        server.setProtocolVersion(protocolVersion);
        return this;
    }

    /**
     * Set the description for this server
     * 
     * @param description to set.
     * @return ServerBuilder
     */
    public ServerBuilder description(String description) {
        server.setDescription(description);
        return this;
    }

    /**
     * Return to the parent AsyncAPIBuilder builder.
     * 
     * @return AsyncAPIBuilder the parent. 
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
