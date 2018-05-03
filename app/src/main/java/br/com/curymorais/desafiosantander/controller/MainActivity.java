package br.com.curymorais.desafiosantander.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.curymorais.desafiosantander.R;

public class MainActivity extends RootActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        TextView btnContato = findViewById(R.id.btn_contato);
        TextView btnInvestimento = findViewById(R.id.btn_investimento);

        btnContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FormActivity.class));
                finish();
            }
        });

        btnInvestimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FundActivity.class));
                finish();
            }
        });
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
