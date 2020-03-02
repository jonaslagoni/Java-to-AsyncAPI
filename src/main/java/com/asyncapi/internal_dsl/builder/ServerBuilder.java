/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
