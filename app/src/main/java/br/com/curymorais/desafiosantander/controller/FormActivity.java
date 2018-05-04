package br.com.curymorais.desafiosantander.controller;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.content.res.ResourcesCompat;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import br.com.curymorais.desafiosantander.R;
import br.com.curymorais.desafiosantander.Service.FormReadService;
import br.com.curymorais.desafiosantander.domain.dto.FieldDTO;
import br.com.curymorais.desafiosantander.domain.model.EditTextSantander;
import br.com.curymorais.desafiosantander.util.FieldBuilder;

public class FormActivity extends RootActivity{
    private static final String TAG = "FORM_ACTIVITY";
    private List<FieldDTO> listaFields = new ArrayList<>();
    Typeface typeFont ;
    List<View> listaObjetos;


    private View.OnClickListener startFormResponse = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(getApplicationContext(), FormResponseActivity.class);
            boolean verified = true;
            boolean validaMail = true;

            Log.i(TAG,"Verificando conteudo dos campos...");
            for (View x: listaObjetos){
                if (x instanceof CheckBox){
                    if(((CheckBox) x).isChecked()){
                        Log.i(TAG,"Validar o email");
                        validaMail = true;
                    }else {
                        Log.i(TAG,"Nao Validar o email");
                        validaMail = false;
                    }
                }
            }
            for (View x: listaObjetos){
               if (x instanceof EditTextSantander){
                    Log.i(TAG,"email " + validaMail);
                    if(((EditTextSantander) x).isRequired() &&
                            ((EditTextSantander) x).getInputType() != InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS &&
                            ((EditTextSantander) x).getText().toString().equalsIgnoreCase("")){
                        verified = false;
                        Toast.makeText(getApplicationContext(),"Necessário preencher os campos!",Toast.LENGTH_LONG).show();
                    }
                    if(validaMail &&
                            ((EditTextSantander) x).getInputType() == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS &&
                            ((EditTextSantander) x).getText().toString().equalsIgnoreCase("") &&
                            !android.util.Patterns.EMAIL_ADDRESS.matcher(((EditTextSantander) x).getText()).matches()){
                        verified = false;
                        Toast.makeText(getApplicationContext(),"E-mail com formato invalido ou nulo!",Toast.LENGTH_LONG).show();
                    }
                    myIntent.putExtra("field"+((EditTextSantander) x).getHint(), ((EditTextSantander) x).getText().toString());
                }
            }
            Log.i(TAG,"iniciar nova atividade? "+verified);
            if (verified) {
                startActivity(myIntent);
            }
        }
    };

    private View.OnClickListener startFund = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(getApplicationContext(), FundActivity.class);
            startActivity(myIntent);
        }
    };

    private View.OnClickListener setEmail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < listaObjetos.size();i++) {
                if (listaObjetos.get(i) instanceof EditTextSantander){
                    if(((EditTextSantander) listaObjetos.get(i)).getInputType()== InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS){
                        if (listaObjetos.get(i).getVisibility() == View.GONE) {
                            Log.i(TAG,"Deixando email visivel");
                            listaObjetos.get(i).setVisibility(View.VISIBLE);
                        }else if (listaObjetos.get(i).getVisibility() == View.VISIBLE){
                            Log.i(TAG,"Escondendo email");
                            listaObjetos.get(i).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    };

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean encontrado;
            Log.i(TAG, "Receiver recebeu!");
            listaFields = (List<FieldDTO>) intent.getSerializableExtra("fields");
            encontrado = (Boolean) intent.getSerializableExtra("encontrou");

            if (encontrado){
                Log.i(TAG, "Campos encontrados! ");
            }else{
                Log.i(TAG, "Campos NAO encontrados!");
            }
            buildView();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeFont = ResourcesCompat.getFont(getApplicationContext(), R.font.dinpromedium);
        setContentView(R.layout.form_view);
        startService(getApplicationContext());
        TextView btnInvestimento = findViewById(R.id.btn_investimento);

        btnInvestimento.setOnClickListener(startFund);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("cells"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private void startService( Context c) {
        Intent i = new Intent(c, FormReadService.class);
        c.startService(i);
    }

    @SuppressLint("ResourceType")
    public void buildView() {
        Log.i(TAG,"Construindo a view");
        ConstraintLayout layout = findViewById(R.id.form_view);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        listaObjetos = new ArrayList<>();

        for (FieldDTO f : listaFields){
            switch (f.getType()) {
                case 1:
                    //listaObjetos.add(FieldBuilder.getEditTextFromField(f, this));
                    listaObjetos.add(FieldBuilder.getEditTextSantanderFromField(f, this));
                    break;
                case 2:
                    listaObjetos.add(FieldBuilder.getTextViewFromField(f, this));
                    break;
                case 4:
                    CheckBox ch = FieldBuilder.getCheckBoxFromField(f, this);
                    ch.setOnClickListener(setEmail);
                    listaObjetos.add(ch);
                    break;
                case 5:
                    Button b = FieldBuilder.getButtonViewFromField(f, this);
                    b.setOnClickListener(startFormResponse);
                    b.setBackground(getResources().getDrawable(R.drawable.background_btn));
                    b.setTextColor(getResources().getColor(R.color.colorPrimary));
                    listaObjetos.add(b);
                     break;
            }
        }

        for (int i = 0; i < listaObjetos.size();i++){
            if(i==0){
                layout.addView(listaObjetos.get(i));
                set.connect(listaObjetos.get(i).getId(), ConstraintSet.LEFT,ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                set.connect(listaObjetos.get(i).getId(), ConstraintSet.RIGHT,ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
                set.connect(listaObjetos.get(i).getId(), ConstraintSet.TOP,ConstraintSet.PARENT_ID, ConstraintSet.TOP, 600);
                set.constrainHeight(listaObjetos.get(i).getId(), ConstraintSet.WRAP_CONTENT);
                set.constrainWidth(listaObjetos.get(i).getId(), ConstraintSet.WRAP_CONTENT);
                set.applyTo(layout);
            }
            else {
                layout.addView(listaObjetos.get(i));
                set.connect(listaObjetos.get(i).getId(), ConstraintSet.LEFT,ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                set.connect(listaObjetos.get(i).getId(), ConstraintSet.RIGHT,ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
                set.connect(listaObjetos.get(i).getId(), ConstraintSet.TOP,listaObjetos.get(i-1).getId(), ConstraintSet.BOTTOM, 0);
                set.constrainHeight(listaObjetos.get(i).getId(), ConstraintSet.WRAP_CONTENT);
                set.constrainWidth(listaObjetos.get(i).getId(), ConstraintSet.WRAP_CONTENT);
                set.applyTo(layout);
            }
        }

    }

}
