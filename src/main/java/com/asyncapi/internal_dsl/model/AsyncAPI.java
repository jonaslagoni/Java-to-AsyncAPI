/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lagoni
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AsyncAPI {

    private Version asyncapi;
    private Info info;
    private Map<String, Channel> channels;
    private Map<String, Server> servers;
    
    public AsyncAPI(){
        asyncapi = Version.v2_0_0;
        channels = new HashMap();
    }

    public String toJson() throws JsonProcessingException {
        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();
        return Obj.writeValueAsString(this);
    }
    
    /**
     * @return the asyncapi
     */
    public Version getAsyncapi() {
        return asyncapi;
    }

    /**
     * @param asyncapi the asyncapi to set
     */
    public void setAsyncapi(Version asyncapi) {
        this.asyncapi = asyncapi;
    }

    /**
     * @return the info
     */
    public Info getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(Info info) {
        this.info = info;
    }

    /**
     * @return the channels
     */
    public Map<String, Channel> getChannels() {
        return channels;
    }

    /**
     * @param channels the channels to set
     */
    public void setChannels(Map<String, Channel> channels) {
        this.channels = channels;
    }

    /**
     * Adds a channel
     *
     * @param channelName
     * @param channel
     */
    public void addChannel(String channelName, Channel channel) {
        this.channels.put(channelName, channel);
    }

    /**
     * @return the servers
     */
    public Map<String, Server> getServers() {
        return servers;
    }

    /**
     * @param servers the servers to set
     */
    public void setServers(Map<String, Server> servers) {
        this.servers = servers;
    }

    /**
     * Adds a server
     *
     * @param name
     * @param server
     */
    public void addServer(String name, Server server) {
        if (this.servers == null) {
            this.servers = new HashMap();
        }
        this.servers.put(name, server);
    }

}
