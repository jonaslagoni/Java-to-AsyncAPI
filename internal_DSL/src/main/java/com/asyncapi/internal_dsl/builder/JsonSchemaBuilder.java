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
public interface JsonSchemaBuilder{
    public JsonSchemaObjectBuilder object();
    //Complex Inheritance
    public JsonSchemaBuilder oneOf();
    
    public JsonSchemaBuilder allOf();
    
    public JsonSchemaBuilder anyOf();
}
