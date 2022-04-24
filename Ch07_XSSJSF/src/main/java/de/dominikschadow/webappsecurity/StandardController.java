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
package de.dominikschadow.webappsecurity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Bean representing user input into the standard protected output page
 * (no additional security related attributes).
 *
 * @author Dominik Schadow
 */
@ManagedBean(name = "standard")
@SessionScoped
public class StandardController implements Serializable {
    @Serial
    private static final long serialVersionUID = 4083596061570021965L;

    private String input = "<script>alert('XSS with JSF')</script>";

    private Map<String, String> standardMap = null;
    private Status[] standardArray = null;

    public Map<String, String> getStandardMap() {
        return standardMap;
    }

    public Status[] getStandardArray() {
        return standardArray;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;

        standardMap = new LinkedHashMap<>();
        standardMap.put(input, input);

        standardArray = new Status[1];
        standardArray[0] = new Status(input);
    }
}