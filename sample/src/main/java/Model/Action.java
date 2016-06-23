package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aravindp on 20/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Action implements Serializable {
    private String name;
    private List<List<Parameter>> mandatory;
    private List<List<Parameter>> optional;
    private String next;
    private List<Object> preConditions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<Parameter>> getMandatory() {
        return mandatory;
    }

    public void setMandatory(List<List<Parameter>> mandatory) {
        this.mandatory = mandatory;
    }

    public Object getOptional() {
        return optional;
    }

    public void setOptional(List<List<Parameter>> optional) {
        this.optional = optional;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<Object> getPreConditions() {
        return preConditions;
    }

    public void setPreConditions(List<Object> preConditions) {
        this.preConditions = preConditions;
    }
}
