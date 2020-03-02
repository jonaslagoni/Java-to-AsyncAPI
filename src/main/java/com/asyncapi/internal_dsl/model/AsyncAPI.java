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
