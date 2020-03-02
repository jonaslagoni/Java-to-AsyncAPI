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

    public LicenseBuilder license() {
        License licenseInstance;
        if (root.getLicense() != null) {
            licenseInstance = root.getLicense();
        } else {
            licenseInstance = new License();
            root.setLicense(licenseInstance);
        }
        return new LicenseBuilder(this, licenseInstance);
    }

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

    public CrudBuilder crud(String channelName) {
        if (cruds.containsKey(channelName)) {
            return cruds.get(channelName);
        } else {
            CrudBuilder b = new CrudBuilder(this, root, channelName);
            cruds.put(channelName, b);
            return b;
        }
    }

    public AsyncAPI finish() {
        return root;
    }
}
