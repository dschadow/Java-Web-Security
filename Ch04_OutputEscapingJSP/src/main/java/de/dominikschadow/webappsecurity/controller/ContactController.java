/*
 * Copyright (C) 2013 Dominik Schadow, dominikschadow@gmail.com
 *
 * This file is part of Java-Web-Security
.
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
package de.dominikschadow.webappsecurity.controller;

import de.dominikschadow.webappsecurity.domain.Contact;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Dominik Schadow
 */
@Controller
@SessionAttributes
public class ContactController {
    private static final Logger LOGGER = Logger.getLogger(ContactController.class);

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public ModelAndView addContact(@ModelAttribute("contact") Contact contact, BindingResult result) {
        LOGGER.info("Firstname: " + contact.getFirstname() + ", Lastname: " + contact.getLastname());

        return new ModelAndView("contact", "command", contact);
    }
}
