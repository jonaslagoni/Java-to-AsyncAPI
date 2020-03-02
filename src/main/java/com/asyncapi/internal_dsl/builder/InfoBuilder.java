/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.asyncapi.internal_dsl.model.Info;

/**
 *
 * @author lagoni
 */
public class InfoBuilder {

    private AsyncAPIBuilder parent;
    private Info info;

    public InfoBuilder(AsyncAPIBuilder instance, Info info) {
        this.parent = instance;
        this.info = info;
    }

    public InfoBuilder title(String title) {
        info.setTitle(title);
        return this;
    }

    public InfoBuilder version(String version) {
        info.setVersion(version);
        return this;
    }

    public InfoBuilder description(String description) {
        info.setDescription(description);
        return this;
    }

    public AsyncAPIBuilder parent() {
        return parent;
    }

    public AsyncAPI finish() {
        return parent.finish();
    }
}
