/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.asyncapi.internal_dsl.model.Channel;
import com.asyncapi.internal_dsl.model.Info;
import com.asyncapi.internal_dsl.model.License;
import com.asyncapi.internal_dsl.model.Server;
import com.asyncapi.internal_dsl.model.Version;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lagoni
 */
public class AsyncAPIBuilder {

    protected AsyncAPI root;
    private Map<String, CrudBuilder> cruds;

    public AsyncAPIBuilder() {
        root = new AsyncAPI();
        cruds = new HashMap();
    }

    public AsyncAPIBuilder asyncapi(Version version){
        root.setAsyncapi(version);
        return this;
    }
    /**
     * Start building the info section
     * 
     * @return InfoBuilder
     */
    public InfoBuilder info() {
        Info infoInstance;
        if (root.getInfo() != null) {
            infoInstance = root.getInfo();
        } else {
            infoInstance = new Info();
            root.setInfo(infoInstance);
        }
        return new InfoBuilder(this, infoInstance);
    }


    /**
     * Start building a specific server section
     * 
     * @param name the name of the server to build
     * @return ServerBuilder
     */
    public ServerBuilder server(String name) {
        Server serverInstance;
        if (root.getServers() != null && root.getServers().containsKey(name)) {
            serverInstance = root.getServers().get(name);
        } else {
            serverInstance = new Server();
            root.addServer(name, serverInstance);
        }
        return new ServerBuilder(this, serverInstance);
    }

    /**
     * Start building a specific channel section
     * 
     * @param channelName the name of the channel we are building.
     * @return ChannelBuilder
     */
    public ChannelBuilder channel(String channelName) {
        Channel channelInstance;
        if (root.getChannels()!= null && root.getChannels().containsKey(channelName)) {
            channelInstance = root.getChannels().get(channelName);
        } else {
            channelInstance = new Channel();
            root.addChannel(channelName, channelInstance);
        }
        return new ChannelBuilder(this, channelInstance);
    }

    /**
     * Start building a CRUD operation
     * 
     * @param channelName the channel name to use as base name for the CRUD operation. 
     * @return CrudBuilder
     */
    public CrudBuilder crud(String channelName) {
        if (cruds.containsKey(channelName)) {
            return cruds.get(channelName);
        } else {
            CrudBuilder b = new CrudBuilder(this, root, channelName);
            cruds.put(channelName, b);
            return b;
        }
    }

    /**
     * Finish the builder by returning the AsyncAPI object.
     * 
     * @return AsyncAPI
     */
    public AsyncAPI finish() {
        return root;
    }
}
