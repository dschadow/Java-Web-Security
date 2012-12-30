package de.dominikschadow.webappsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.dominikschadow.webappsecurity.domain.Contact;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView showContacts() {
        return new ModelAndView("index", "command", new Contact());
    }
}
