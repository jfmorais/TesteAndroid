package br.com.curymorais.desafiosantander.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.curymorais.desafiosantander.R;

public class FormResponseActivity extends RootActivity {
    private static final String TAG = "FORMRESPONSE_ACTIVITY";
    Typeface typeFont ;
    TextView mensagem ;
    TextView name ;

    private View.OnClickListener startFund = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(getApplicationContext(), FundActivity.class);
            startActivity(myIntent);
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
        typeFont = ResourcesCompat.getFont(getApplicationContext(), R.font.dinpromedium);
        setContentView(R.layout.form_reponse_view);
        buildView();

    }

    @SuppressLint("ResourceType")
    public void buildView() {
        ConstraintLayout layout = findViewById(R.id.form_response_view);
        ConstraintSet set = new ConstraintSet();
        Intent intent = getIntent();
        set.clone(layout);

        //name box
        name = new TextView(this);
        name.setId(100);
        name.setText(intent.getStringExtra("fieldNome completo"));
        name.setTextSize(20);
        name.setTypeface(typeFont);
        layout.addView(name);
        set.connect(name.getId(), ConstraintSet.LEFT,ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        set.connect(name.getId(), ConstraintSet.RIGHT,ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        set.connect(name.getId(), ConstraintSet.TOP,ConstraintSet.PARENT_ID, ConstraintSet.TOP, 400);
        set.constrainHeight(name.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(name.getId(), ConstraintSet.WRAP_CONTENT);
        set.applyTo(layout);

        //menssage box
        mensagem = new TextView(this);
        mensagem.setText("Mensagem Enviada com sucesso!!");
        mensagem.setTextSize(30);
        mensagem.setTypeface(typeFont);
        mensagem.setGravity(Gravity.CENTER);
        layout.addView(mensagem);
        set.connect(mensagem.getId(), ConstraintSet.LEFT,ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        set.connect(mensagem.getId(), ConstraintSet.RIGHT,ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        set.connect(mensagem.getId(), ConstraintSet.TOP,name.getId(), ConstraintSet.BOTTOM, 200);
        set.constrainHeight(mensagem.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(mensagem.getId(), ConstraintSet.WRAP_CONTENT);
        set.applyTo(layout);

        TextView btnContato = findViewById(R.id.btn_contato);
        TextView btnInvestimentos = findViewById(R.id.btn_investimento);

        btnContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FormResponseActivity.this, FormActivity.class));
                finish();
            }
        });

        btnInvestimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FormResponseActivity.this, FundActivity.class));
                finish();
            }
        });

//        investimentos.setText("investimentos");
//        investimentos.setOnClickListener(startFund);
//        investimentos.setId(2000);
//        layout.addView(investimentos);
//        set.connect(investimentos.getId(), ConstraintSet.LEFT,ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
//        set.connect(investimentos.getId(), ConstraintSet.RIGHT,contato.getId(), ConstraintSet.LEFT, 0);
//        set.connect(investimentos.getId(), ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
//        set.constrainHeight(investimentos.getId(), 200);
//        set.constrainWidth(investimentos.getId(), 700);
//        set.applyTo(layout);
//
//
//        contato.setText("contato");
//        contato.setOnClickListener(startContact);
//        layout.addView(contato);
//        set.connect(contato.getId(), ConstraintSet.LEFT,investimentos.getId(), ConstraintSet.RIGHT, 0);
//        set.connect(contato.getId(), ConstraintSet.RIGHT,ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
//        set.connect(contato.getId(), ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
//        set.constrainHeight(contato.getId(), 200);
//        set.constrainWidth(contato.getId(), 700);
//        set.applyTo(layout);
    }
}
