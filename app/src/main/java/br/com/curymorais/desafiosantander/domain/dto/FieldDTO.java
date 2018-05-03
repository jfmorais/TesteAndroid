package br.com.curymorais.desafiosantander.domain.dto;

import java.io.Serializable;

public class FieldDTO implements Serializable {
    private int id;
    private int type;
    private String message;
    private String typefield;
    private boolean hidden;
    private double topSpaceing;
    private int show;
    private boolean required;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypefield() {
        return typefield;
    }

    public void setTypefield(String typefield) {
        this.typefield = typefield;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public double getTopSpaceing() {
        return topSpaceing;
    }

    public void setTopSpaceing(double topSpaceing) {
        this.topSpaceing = topSpaceing;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
