/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asyncapi.internal_dsl.builder;

import com.asyncapi.internal_dsl.model.AsyncAPI;
import com.asyncapi.internal_dsl.model.Info;
import com.asyncapi.internal_dsl.model.License;

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

    /**
     * Start building the license section
     * 
     * @return LicenseBuilder
     */
    public LicenseBuilder license() {
        License licenseInstance;
        if (info.getLicense() != null) {
            licenseInstance = info.getLicense();
        } else {
            licenseInstance = new License();
            info.setLicense(licenseInstance);
        }
        return new LicenseBuilder(this, licenseInstance);
    }
    public AsyncAPIBuilder parent() {
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
