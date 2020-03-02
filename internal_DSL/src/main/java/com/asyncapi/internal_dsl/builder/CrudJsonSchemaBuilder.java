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
    private AsyncAPI root;

    public CrudJsonSchemaBuilder(CrudBuilder parent, AsyncAPI root) {
        this.parent = parent;
        this.root = root;
    }

    public CrudBuilder crudParent() {
        Schema rootSchema = this.build();

        return parent;
    }

    public AsyncAPI finish() {
        Schema rootSchema = this.build();

        return parent.finish();
    }

}
