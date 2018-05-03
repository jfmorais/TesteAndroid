package br.com.curymorais.desafiosantander.Service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.curymorais.desafiosantander.domain.dto.CellDTO;
import br.com.curymorais.desafiosantander.domain.dto.FieldDTO;
import br.com.curymorais.desafiosantander.util.RetrofitHelper;
import br.com.curymorais.desafiosantander.ws.SantanderEndpoint;
import retrofit2.Call;
import retrofit2.Response;

public class FormReadService extends IntentService {

    private static final String TAG = "CELL_SERVICE";
    SantanderEndpoint santanderEndpoint;

    public FormReadService() {
        super("FormReadService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        santanderEndpoint = RetrofitHelper.with(this).createSantanderEndpoint();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent resposta = new Intent("cells");
        if (intent != null) {
            Log.i(TAG, "intent nao e nulo.");
            Call<CellDTO> call = santanderEndpoint.getCells();
            Response<CellDTO> response;

            try{

                response = call.execute();

                if(response.isSuccessful()){
                    Log.i(TAG, "resposta com sucesso!!!");
                    CellDTO cellsDTO = response.body();
                    List<FieldDTO> listFields = new ArrayList<FieldDTO>();
                    listFields = cellsDTO.getCells();

                    resposta.putExtra("encontrou", Boolean.TRUE);
                    resposta.putExtra("fields", (Serializable) listFields);
                } else{
                    resposta.putExtra("encontrou", Boolean.FALSE);
                }
                LocalBroadcastManager.getInstance(this).sendBroadcast(resposta);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        sendBroadcast(resposta);
    }
}
