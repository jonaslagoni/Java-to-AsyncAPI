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
import com.asyncapi.internal_dsl.model.License;

/**
 *
 * @author Lagoni
 */
public class LicenseBuilder {

    private License license;
    private InfoBuilder parent;

    public LicenseBuilder(InfoBuilder parent, License license) {
        this.license = license;
        this.parent = parent;
    }

    /**
     * Set the name for this license
     * 
     * @param name to set
     * @return this LicenseBuilder instance
     */
    public LicenseBuilder name(String name) {
        license.setName(name);
        return this;
    }

    /**
     * Set the url for this license
     * 
     * @param url to set
     * @return this LicenseBuilder instance
     */
    public LicenseBuilder url(String url) {
        license.setUrl(url);
        return this;
    }
    
    /**
     * Return to the parent InfoBuilder
     * 
     * @return parent InfoBuilder instance
     */
    public InfoBuilder parent() {
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
