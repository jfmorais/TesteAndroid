package br.com.curymorais.desafiosantander.ws;

import br.com.curymorais.desafiosantander.domain.dto.CellDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by jcury on 20/07/2017.
 */

public interface SantanderEndpoint {

    //https://sag-api.herokuapp.com/ferias
    @GET("cells.json")
    Call<CellDTO>getCells();

    @GET("fund.json")
    Call<CellDTO>getFund();
}
