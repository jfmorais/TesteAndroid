package br.com.curymorais.desafiosantander.domain.dto;

import java.io.Serializable;

public class FundDTO implements Serializable {
    private ScreenDTO screen;

    public ScreenDTO getScreen() {
        return screen;
    }

    public void setScreen(ScreenDTO screen) {
        this.screen = screen;
    }
}
