/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lagoni
 */
public class Channel {

    private String description;
    private Map<String, Parameter> parameters;
    private Operation subscribe;
    private Operation publish;

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the subscribe
     */
    public Operation getSubscribe() {
        return subscribe;
    }

    /**
     * @param subscribe the subscribe to set
     */
    public void setSubscribe(Operation subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * @return the publish
     */
    public Operation getPublish() {
        return publish;
    }

    /**
     * @param publish the publish to set
     */
    public void setPublish(Operation publish) {
        this.publish = publish;
    }

    /**
     * @return the parameters
     */
    public Map<String, Parameter> getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Map<String, Parameter> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String parameterName, Parameter parameter) {
        if (this.parameters == null) {
            this.parameters = new HashMap();
        }
        this.parameters.put(parameterName, parameter);
    }
}
