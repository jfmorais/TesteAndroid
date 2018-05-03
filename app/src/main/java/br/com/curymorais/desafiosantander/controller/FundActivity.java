package br.com.curymorais.desafiosantander.controller;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.curymorais.desafiosantander.R;
import br.com.curymorais.desafiosantander.Service.FormReadService;
import br.com.curymorais.desafiosantander.Service.FundReadService;
import br.com.curymorais.desafiosantander.domain.dto.FieldDTO;

public class FundActivity extends RootActivity {
    private static final String TAG = "FUND_ACTIVITY";
    private List<FieldDTO> listaFields = new ArrayList<>();
    Typeface typeFont ;
    List<View> listaObjetos;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean encontrado;
            Log.i(TAG, "Receiver recebeu!");
//            listaFields = (List<FieldDTO>) intent.getSerializableExtra("fields");
            encontrado = (Boolean) intent.getSerializableExtra("encontrou");

            if (encontrado){
                Log.i(TAG, "Campos encontrados! ");
            }else{
                Log.i(TAG, "Campos NAO encontrados!");
            }
            buildView();
        }
    };

    private View.OnClickListener startContact = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(getApplicationContext(), FormActivity.class);
            startActivity(myIntent);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fund_view);

        TextView btnContato = findViewById(R.id.btn_contato);

        btnContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FundActivity.this, FormActivity.class));
                finish();
            }
        });
        startService(getApplicationContext());
//        buildView();
    }

    private void startService( Context c) {
        Intent i = new Intent(c, FundReadService.class);
        c.startService(i);
    }

    @SuppressLint("ResourceType")
    public void buildView() {
        Log.i(TAG,"Construindo a view");
        @SuppressLint("WrongViewCast") ConstraintLayout layout = findViewById(R.id.fund_view);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        listaObjetos = new ArrayList<>();

    }
}
