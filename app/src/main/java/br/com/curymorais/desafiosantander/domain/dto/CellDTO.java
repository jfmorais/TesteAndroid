package br.com.curymorais.desafiosantander.domain.dto;

import java.io.Serializable;
import java.util.List;

public class CellDTO implements Serializable {
    private List<FieldDTO> cells;

    public List<FieldDTO> getCells() {
        return cells;
    }

    public void setCells(List<FieldDTO> cells) {
        this.cells = cells;
    }
}
