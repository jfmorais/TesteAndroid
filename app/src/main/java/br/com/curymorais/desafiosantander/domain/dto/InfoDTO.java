package br.com.curymorais.desafiosantander.domain.dto;

import java.io.Serializable;

public class InfoDTO implements Serializable {
    private String name;
    private String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
