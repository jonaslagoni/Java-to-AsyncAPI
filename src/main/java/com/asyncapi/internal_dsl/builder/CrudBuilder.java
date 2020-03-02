/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        if(informed){
            message.setPayload(body.build());
        }else{
            JsonSchemaBuilder b = new JsonSchemaBuilder();
            message.setPayload(b.nullSchema().build());
        }
        Operation operation = new Operation();
        operation.setMessage(message);
        
        Channel channel = new Channel();
        if(this.reverse){
            channel.setSubscribe(operation);
        }else{
            channel.setPublish(operation);
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
        Operation publishOperation = new Operation();
        publishOperation.setMessage(message);
        
        Message responseMessage = new Message();
        JsonSchemaBuilder b = new JsonSchemaBuilder();
        responseMessage.setPayload(b.nullSchema().build());
        Operation responseOperation = new Operation();
        responseOperation.setMessage(responseMessage);
        
        Channel channel = new Channel();
        if(this.reverse){
            channel.setPublish(responseOperation);
            channel.setSubscribe(publishOperation);
        }else{
            channel.setPublish(publishOperation);
            channel.setSubscribe(responseOperation);
        }
        
        for(Map.Entry<String, CrudJsonSchemaBuilder> entry : parameters.entrySet()){
            Parameter param = new Parameter();
            param.setSchema(entry.getValue().build());
            channel.addParameter(entry.getKey(), param);
        }
        return channel;
    }
}