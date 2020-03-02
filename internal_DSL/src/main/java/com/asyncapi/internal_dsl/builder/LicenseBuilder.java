/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.asyncapi.internal_dsl.model.License;

/**
 *
 * @author Lagoni
 */
public class LicenseBuilder {

    private License license;
    private AsyncAPIBuilder parent;

    public LicenseBuilder(AsyncAPIBuilder parent, License license) {
        this.license = license;
        this.parent = parent;
    }

    public LicenseBuilder name(String name) {
        license.setName(name);
        return this;
    }

    public LicenseBuilder url(String url) {
        license.setUrl(url);
        return this;
    }

    public AsyncAPIBuilder parent() {
        return parent;
    }

    public AsyncAPI finish() {
        return parent.finish();
    }
}
