package Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aravindp on 20/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parameter implements Serializable{
    private String name;
    private String alias;
    private ParameterType type;
    private boolean action;
    private List<Object> dependsOn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ParameterType getType() {
        return type;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public List<Object> getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(List<Object> dependsOn) {
        this.dependsOn = dependsOn;
    }
}
