package de.dominikschadow.webappsecurity.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class WebResources {
    @Produces
    @RequestScoped
    public FacesContext producesFacesContext() {
        return FacesContext.getCurrentInstance();
    }
}
