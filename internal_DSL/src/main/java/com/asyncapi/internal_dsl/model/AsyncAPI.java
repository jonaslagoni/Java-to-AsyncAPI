/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.model;

import java.nio.channels.Channel;
import java.util.Map;

/**
 *
 * @author lagoni
 */
public class AsyncAPI {
    public enum Version{
        v2_0_0("2.0.0");
        private String asyncapiVersion;
        
        private Version(String asyncapiVersion) {
            this.asyncapiVersion = asyncapiVersion;
        }

        /**
         * @return the asyncapiVersion
         */
        public String getAsyncapiVersion() {
            return asyncapiVersion;
        }

        /**
         * @param asyncapiVersion the asyncapiVersion to set
         */
        public void setAsyncapiVersion(String asyncapiVersion) {
            this.asyncapiVersion = asyncapiVersion;
        }
    }
    
    private Version asyncapi;
    private Info info;
    private Map<String, Channel> channels;
    
}
