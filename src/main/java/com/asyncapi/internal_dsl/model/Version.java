/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author Lagoni
 */
public enum Version {
    v2_0_0("2.0.0");
    @JsonValue
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
