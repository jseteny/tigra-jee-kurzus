package hu.tigra.jee.model;

import javax.inject.Named;
import java.io.Serializable;

@Named
@javax.enterprise.context.SessionScoped
public class ExplodeCount implements Serializable {

    private int count;


    void increment() {
        ++count;
    }

    public String getCount() {
        return "Robbanások: " + count;
    }
}
