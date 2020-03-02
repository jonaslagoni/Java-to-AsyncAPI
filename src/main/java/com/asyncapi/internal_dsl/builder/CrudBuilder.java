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

    public CrudJsonSchemaBuilder parameter(String propertyName) {
        if(parameters.containsKey(propertyName)){
            return parameters.get(propertyName);
        }else{
            CrudJsonSchemaBuilder builder = new CrudJsonSchemaBuilder(this);
            parameters.put(propertyName, builder);
            return builder;
        }
    }

    public CrudJsonSchemaBuilder body() {
        if(body == null){
            body = new CrudJsonSchemaBuilder(this);
        }
        return body;
    }
    

    public CrudBuilder reverse(boolean reverse) {
        this.reverse = reverse;
        return this;
    }

    public AsyncAPIBuilder parent() {
        finishCrud();
        return parent;
    }
    
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
