/*
 * The MIT License
 *
 * Copyright 2020 lagoni.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
