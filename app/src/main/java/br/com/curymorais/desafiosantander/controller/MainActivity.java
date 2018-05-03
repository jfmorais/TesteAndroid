package br.com.curymorais.desafiosantander.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import br.com.curymorais.desafiosantander.R;

public class MainActivity extends RootActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
    }

    public void startContact(View v){
        Intent i = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(i);
    }

    public void startFund(View v){
        Intent i = new Intent(getApplicationContext(), FundActivity.class);
        startActivity(i);
    }
}
