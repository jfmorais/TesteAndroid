package br.com.curymorais.desafiosantander.domain.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MoreInfoDTO implements Serializable {
    private MonthDTO month;
    private YearDTO year;

    @SerializedName("12months")
    private twelve12Months twelve12Months;

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

    public twelve12Months getTwelve12Months() {
        return twelve12Months;
    }

    public void setTwelve12Months(twelve12Months twelve12Months) {
        this.twelve12Months = twelve12Months;
    }
}
