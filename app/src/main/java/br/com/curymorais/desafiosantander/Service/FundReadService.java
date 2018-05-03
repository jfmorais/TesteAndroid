package br.com.curymorais.desafiosantander.Service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import br.com.curymorais.desafiosantander.domain.dto.FundDTO;
import br.com.curymorais.desafiosantander.util.RetrofitHelper;
import br.com.curymorais.desafiosantander.ws.SantanderEndpoint;
import retrofit2.Call;
import retrofit2.Response;

public class FundReadService extends IntentService {

    private static final String TAG = "FUND_SERVICE";
    SantanderEndpoint santanderEndpoint;

    public FundReadService() {
        super("FundReadService");
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
        Intent resposta = new Intent("fund");
        if (intent != null) {
            Log.i(TAG, "intent nao e nulo.");
            Call<FundDTO> call = santanderEndpoint.getFund();
            Response<FundDTO> response;

            try{

                response = call.execute();

                if(response.isSuccessful()){
                    Log.i(TAG, "resposta com sucesso!!!");
//                    FundDTO fundDTO = response.body();


                    resposta.putExtra("encontrou", Boolean.TRUE);
//                    resposta.putExtra("fund", (Serializable) listFields);
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
