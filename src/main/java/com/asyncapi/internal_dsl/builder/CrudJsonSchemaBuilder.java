/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.json_schema.builder.draft7.JsonSchemaBuilder;
import com.json_schema.builder.model.draft7.Schema;

/**
 *
 * @author Lagoni
 */
public class CrudJsonSchemaBuilder extends JsonSchemaBuilder<CrudJsonSchemaBuilder> {

    private CrudBuilder parent;

    public CrudJsonSchemaBuilder(CrudBuilder parent) {
        this.parent = parent;
    }

    public CrudBuilder crudParent() {
        return parent;
    }

    /**
     * Finish the builder by returning the AsyncAPI object.
     * 
     * @return AsyncAPI
     */
    public AsyncAPI finish() {
        return parent.finish();
    }

}
