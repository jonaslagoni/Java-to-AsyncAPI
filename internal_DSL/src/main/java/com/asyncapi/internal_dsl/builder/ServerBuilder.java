/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

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

    public ServerBuilder url(String url) {
        server.setUrl(url);
        return this;
    }

    public ServerBuilder protocol(String protocol) {
        server.setProtocol(protocol);
        return this;
    }

    public ServerBuilder protocolVersion(String protocolVersion) {
        server.setProtocolVersion(protocolVersion);
        return this;
    }

    public ServerBuilder description(String description) {
        server.setDescription(description);
        return this;
    }

    public AsyncAPIBuilder parent() {
        return parent;
    }
}
