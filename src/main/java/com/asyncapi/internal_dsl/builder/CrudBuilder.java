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
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.asyncapi.internal_dsl.model.Channel;
import com.asyncapi.internal_dsl.model.Message;
import com.asyncapi.internal_dsl.model.Operation;
import com.asyncapi.internal_dsl.model.Parameter;
import com.json_schema.builder.draft7.JsonSchemaBuilder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lagoni
 */
public class CrudBuilder {

    private AsyncAPIBuilder parent;
    private AsyncAPI root;
    private Map<String, CrudJsonSchemaBuilder> parameters;
    private CrudJsonSchemaBuilder body;
    private String baseChannelName;
    private boolean informed = false;
    private boolean reverse = false;
    public CrudBuilder(AsyncAPIBuilder instance, AsyncAPI root, String channelName) {
        this.parent = instance;
        this.root = root;
        this.parameters = new HashMap();
        this.baseChannelName = channelName;
    }

    /**
     * Adds a parameter to the CRUD base channel i.e. "id" -> baseChannelName/{id}
     * 
     * This method can be called as many times as you want.
     * 
     * @param parameterName name of the parameter.
     * @return CrudJsonSchemaBuilder 
     */
    public CrudJsonSchemaBuilder parameter(String parameterName) {
        if(parameters.containsKey(parameterName)){
            return parameters.get(parameterName);
        }else{
            CrudJsonSchemaBuilder builder = new CrudJsonSchemaBuilder(this);
            parameters.put(parameterName, builder);
            return builder;
        }
    }

    /**
     * Start building the payload for the CRUD i.e. a user.
     * 
     * @return CrudJsonSchemaBuilder
     */
    public CrudJsonSchemaBuilder body() {
        if(body == null){
            body = new CrudJsonSchemaBuilder(this);
        }
        return body;
    }
    

    /**
     * Should the CRUD be reversed i.e. switch from "server" point of view to "client".
     * 
     * @param reverse should be reversed
     * @return this CrudBuilder instance
     */
    public CrudBuilder reverse(boolean reverse) {
        this.reverse = reverse;
        return this;
    }

    /**
     * Return to the parent AsyncAPIBuilder
     * 
     * @return parent AsyncAPIBuilder instance
     */
    public AsyncAPIBuilder parent() {
        finishCrud();
        return parent;
    }
    
    /**
     * Set whether or not the body should be included in all the operations.
     * 
     * @param informed should the body object be included
     * @return this CrudBuilder instance
     */
    public CrudBuilder informed(boolean informed){
        this.informed = informed;
        return this;
    }

    private void finishCrud(){
        String trueChannelName = realizeChannelName();
        root.addChannel(trueChannelName + "/created", generateBaseChannel());
        root.addChannel(trueChannelName + "/updated", generateBaseChannel());
        root.addChannel(trueChannelName + "/removed", generateBaseChannel());
        root.addChannel(trueChannelName + "/read", generateCRUDRead());
    }
    
    /**
     * Finish the builder by returning the AsyncAPI object.
     * 
     * @return AsyncAPI
     */
    public AsyncAPI finish() {
        finishCrud();
        return parent.finish();
    }
    
    private String realizeChannelName(){
        String currentChannelname = baseChannelName;
        for(Map.Entry<String, CrudJsonSchemaBuilder> entry : parameters.entrySet()){
            currentChannelname += "/{" +  entry.getKey() + "}";
        }
        return currentChannelname;
    }
    private Channel generateBaseChannel(){
        Message message = new Message();
        if(this.informed || this.reverse){
            message.setPayload(body.build());
        }else{
            JsonSchemaBuilder b = new JsonSchemaBuilder();
            message.setPayload(b.nullSchema().build());
        }
        Operation operation = new Operation();
        operation.setMessage(message);
        
        Channel channel = new Channel();
        if(this.reverse){
            channel.setPublish(operation);
        }else{
            channel.setSubscribe(operation);
        }
        
        for(Map.Entry<String, CrudJsonSchemaBuilder> entry : parameters.entrySet()){
            Parameter param = new Parameter();
            param.setSchema(entry.getValue().build());
            channel.addParameter(entry.getKey(), param);
        }
        
        return channel;
    }
    private Channel generateCRUDRead(){
        
        Message message = new Message();
        message.setPayload(body.build());
        Operation subscribeOperation = new Operation();
        subscribeOperation.setMessage(message);
        
        Message requestMessage = new Message();
        JsonSchemaBuilder b = new JsonSchemaBuilder();
        requestMessage.setPayload(b.nullSchema().build());
        Operation requestOperation = new Operation();
        requestOperation.setMessage(requestMessage);
        
        Channel channel = new Channel();
        if(this.reverse){
            channel.setPublish(subscribeOperation);
            channel.setSubscribe(requestOperation);
        }else{
            channel.setPublish(requestOperation);
            channel.setSubscribe(subscribeOperation);
        }
        
        for(Map.Entry<String, CrudJsonSchemaBuilder> entry : parameters.entrySet()){
            Parameter param = new Parameter();
            param.setSchema(entry.getValue().build());
            channel.addParameter(entry.getKey(), param);
        }
        return channel;
    }
}
