package br.com.curymorais.desafiosantander.domain.dto;

import java.io.Serializable;

public class ScreenDTO implements Serializable {
    private String title;
    private String fundName;
    private String whatIs;
    private String definition;
    private String riskTitle;
    private int risk;
    private String infoTitle;
    private MoreInfoDTO moreInfo;
    private InfoDTO[] info;
    private DownInfoDTO[] downInfo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getWhatIs() {
        return whatIs;
    }

    public void setWhatIs(String whatIs) {
        this.whatIs = whatIs;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getRiskTitle() {
        return riskTitle;
    }

    public void setRiskTitle(String riskTitle) {
        this.riskTitle = riskTitle;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public MoreInfoDTO getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(MoreInfoDTO moreInfo) {
        this.moreInfo = moreInfo;
    }

    public InfoDTO[] getInfo() {
        return info;
    }

    public void setInfo(InfoDTO[] info) {
        this.info = info;
    }

    public DownInfoDTO[] getDownInfo() {
        return downInfo;
    }

    public void setDownInfo(DownInfoDTO[] downInfo) {
        this.downInfo = downInfo;
    }
}
