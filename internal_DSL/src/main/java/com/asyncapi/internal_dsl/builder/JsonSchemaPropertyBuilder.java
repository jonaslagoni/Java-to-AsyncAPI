/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

/**
 *
 * @author lagoni
 */
public interface JsonSchemaPropertyBuilder{
    public enum PropertyType{
        String,
        Integer
    }
    
    public JsonSchemaPropertyBuilder type(PropertyType type);
    public JsonSchemaPropertyBuilder description(String description);  
    public JsonSchemaPropertyBuilder format(String format);  
    public JsonSchemaPropertyBuilder minimum(int minimum);
}
