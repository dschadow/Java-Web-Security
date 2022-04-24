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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter to add the <code>Strict-Transport-Security</code> header to every response.
 *
 * @author Dominik Schadow
 */
public class HSTSFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(HSTSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        LOGGER.info("HSTSFilter init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse) res).setHeader("Strict-Transport-Security", "max-age=12960000; includeSubdomains");
        LOGGER.info("Added Strict-Transport-Security header to response");

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
