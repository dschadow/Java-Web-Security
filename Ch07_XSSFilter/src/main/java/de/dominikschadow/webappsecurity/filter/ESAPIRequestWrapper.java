/*
 * Copyright (C) 2015 Dominik Schadow, info@dominikschadow.de
 *
 * This file is part of the Java-Web-Security project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.dominikschadow.webappsecurity.filter;

import org.owasp.esapi.ESAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Filter HTML tags which can be used for a XSS attack with the ESAPI library.<br/>
 *
 * @author Dominik Schadow
 */
public class ESAPIRequestWrapper extends HttpServletRequestWrapper {
    public ESAPIRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];

        for (int i = 0; i < count; i++) {
            encodedValues[i] = escapeHTML(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return escapeHTML(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return escapeHTML(value);
    }

    // Modified stripXSS without blacklist
    private String escapeHTML(String value) {
        if (value != null) {
            value = ESAPI.encoder().canonicalize(value);
            value = ESAPI.encoder().encodeForHTML(value);
        }

        return value;
    }
}