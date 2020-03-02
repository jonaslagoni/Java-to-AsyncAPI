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

    /**
     * Set the title for this info
     * 
     * @param title to set
     * @return this InfoBuilder instance
     */
    public InfoBuilder title(String title) {
        info.setTitle(title);
        return this;
    }

    /**
     * Set the version for this info
     * 
     * @param version to set
     * @return this InfoBuilder instance
     */
    public InfoBuilder version(String version) {
        info.setVersion(version);
        return this;
    }

    
    /**
     * Set the description for this info
     * 
     * @param description to set
     * @return this InfoBuilder instance
     */
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
    
    /**
     * Return to the parent AsyncAPIBuilder
     * 
     * @return parent AsyncAPIBuilder instance
     */
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
