package br.com.curymorais.desafiosantander.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.curymorais.desafiosantander.R;
import br.com.curymorais.desafiosantander.domain.dto.FieldDTO;

public class FundActivity extends RootActivity {
    private static final String TAG = "FUND_ACTIVITY";
    private List<FieldDTO> listaFields = new ArrayList<>();
    Typeface typeFont ;
    List<View> listaObjetos;

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
        buildView();
    }

    @SuppressLint("ResourceType")
    public void buildView() {
        Log.i(TAG,"Construindo a view");
        ConstraintLayout layout = findViewById(R.id.fund_view);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        listaObjetos = new ArrayList<>();
        Button investimentos = new Button(this);
        Button contato = new Button(this);

        investimentos.setText("investimentos");
        investimentos.setId(100);
        layout.addView(investimentos);
        set.connect(investimentos.getId(), ConstraintSet.LEFT,ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        set.connect(investimentos.getId(), ConstraintSet.RIGHT,contato.getId(), ConstraintSet.LEFT, 0);
        set.connect(investimentos.getId(), ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        set.constrainHeight(investimentos.getId(), 200);
        set.constrainWidth(investimentos.getId(), 700);
        set.applyTo(layout);

        contato.setText("contato");
        contato.setOnClickListener(startContact);
        layout.addView(contato);
        set.connect(contato.getId(), ConstraintSet.LEFT,investimentos.getId(), ConstraintSet.RIGHT, 0);
        set.connect(contato.getId(), ConstraintSet.RIGHT,ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        set.connect(contato.getId(), ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        set.constrainHeight(contato.getId(), 200);
        set.constrainWidth(contato.getId(), 700);
        set.applyTo(layout);
    }
}
