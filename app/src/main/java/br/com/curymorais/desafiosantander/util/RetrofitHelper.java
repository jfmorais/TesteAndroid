package br.com.curymorais.desafiosantander.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.curymorais.desafiosantander.R;
import br.com.curymorais.desafiosantander.ws.SantanderEndpoint;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private Context context;
    private Retrofit retrofit;

    private RetrofitHelper(Context context){
        super();
        this.context = context;
        Gson gson = new GsonBuilder().setLenient().create();
        this.retrofit = new Retrofit
                .Builder()
                .baseUrl(context.getString(R.string.cell_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public SantanderEndpoint createSantanderEndpoint(){
        return retrofit.create(SantanderEndpoint.class);
    }

    public static RetrofitHelper with(Context c){
        return new RetrofitHelper(c);
    }
}
