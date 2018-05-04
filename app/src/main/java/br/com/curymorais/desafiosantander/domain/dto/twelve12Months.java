package br.com.curymorais.desafiosantander.domain.dto;

import java.io.Serializable;

public class twelve12Months implements Serializable{
    private double fund;
    private double CDI;

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public double getCDI() {
        return CDI;
    }

    public void setCDI(double CDI) {
        this.CDI = CDI;
    }
}
