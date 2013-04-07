package de.dominikschadow.webappsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import de.dominikschadow.webappsecurity.domain.Contact;

@Controller
@SessionAttributes
public class ContactController {
    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public ModelAndView addContact(@ModelAttribute("contact") Contact contact, BindingResult result) {
        System.out.println("Firstname: " + contact.getFirstname() + ", Lastname: " + contact.getLastname());

        return new ModelAndView("contact", "command", contact);
    }
}
