package hu.tigra.jee.model;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class EffectView {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void echo() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You said:'" + text + "'"));
    }
}