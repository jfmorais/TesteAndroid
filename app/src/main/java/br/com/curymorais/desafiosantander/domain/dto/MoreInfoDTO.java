package br.com.curymorais.desafiosantander.domain.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MoreInfoDTO implements Serializable {
    private MonthDTO month;
    private YearDTO year;
    @SerializedName("12Month")
    private Is12Month is12Month;

    public MonthDTO getMonth() {
        return month;
    }

    public void setMonth(MonthDTO month) {
        this.month = month;
    }

    public YearDTO getYear() {
        return year;
    }

    public void setYear(YearDTO year) {
        this.year = year;
    }

    public Is12Month getIs12Month() {
        return is12Month;
    }

    public void setIs12Month(Is12Month is12Month) {
        this.is12Month = is12Month;
    }
}
