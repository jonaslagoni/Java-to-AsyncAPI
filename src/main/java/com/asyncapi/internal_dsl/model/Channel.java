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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lagoni
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Channel{

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
