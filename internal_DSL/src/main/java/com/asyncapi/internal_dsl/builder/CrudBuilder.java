/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;

/**
 *
 * @author Lagoni
 */
public class CrudBuilder {

    private AsyncAPIBuilder parent;
    private AsyncAPI root;

    public CrudBuilder(AsyncAPIBuilder instance, AsyncAPI root) {
        this.parent = instance;
        this.root = root;
    }

    public CrudJsonSchemaBuilder keyProperty(String propertyName) {

    }

    public CrudJsonSchemaBuilder property(String propertyName) {

    }

    public CrudBuilder reverse() {
        return this;
    }

    public AsyncAPIBuilder parent() {
        return parent;
    }

    public AsyncAPI finish() {
        return parent.finish();
    }
}
