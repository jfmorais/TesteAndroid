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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.curymorais.desafiosantander.R;
import br.com.curymorais.desafiosantander.Service.FundReadService;
import br.com.curymorais.desafiosantander.domain.dto.DownInfoDTO;
import br.com.curymorais.desafiosantander.domain.dto.FieldDTO;
import br.com.curymorais.desafiosantander.domain.dto.FundDTO;
import br.com.curymorais.desafiosantander.domain.dto.InfoDTO;
import br.com.curymorais.desafiosantander.domain.dto.MoreInfoDTO;
import br.com.curymorais.desafiosantander.domain.dto.ScreenDTO;

public class FundActivity extends RootActivity {
    private static final String TAG = "FUND_ACTIVITY";
    private List<FieldDTO> listaFields = new ArrayList<>();
    Typeface typeFont ;
    ScreenDTO screenDTO;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean encontrado;
            Log.i(TAG, "Receiver recebeu!");
            FundDTO fundDTO = (FundDTO) intent.getSerializableExtra("fund");
            screenDTO = fundDTO.getScreen();
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("fund"));
    }

    private void startService( Context c) {
        Intent i = new Intent(c, FundReadService.class);
        c.startService(i);
    }

    @SuppressLint("ResourceType")
    public void buildView() {
        Log.i(TAG,"Construindo a view");
        @SuppressLint("WrongViewCast") ConstraintLayout layout = findViewById(R.id.constraint_invest);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        RecyclerView recyclerViewInfo;
        RecyclerView recyclerViewDown;

        //text titles
        TextView title = findViewById(R.id.title);
        TextView fundName = findViewById(R.id.fund_name);
        TextView whatsIs = findViewById(R.id.whats_is);
        TextView definition = findViewById(R.id.definition);
        TextView riskTitle = findViewById(R.id.risk_title);
        TextView infoTitle = findViewById(R.id.info_title);

        title.setTypeface(typeFont);
        fundName.setTypeface(typeFont);
        whatsIs.setTypeface(typeFont);
        definition.setTypeface(typeFont);
        riskTitle.setTypeface(typeFont);
        infoTitle.setTypeface(typeFont);

        //risk bar
        ImageView riskUm = findViewById(R.id.risk_one);
        ImageView riskTwo = findViewById(R.id.risk_two);
        ImageView riskThree = findViewById(R.id.risk_three);
        ImageView riskFour = findViewById(R.id.risk_four);
        ImageView riskFive = findViewById(R.id.risk_five);

        riskTitle.setText(screenDTO.getRiskTitle());
        title.setText(screenDTO.getTitle());
        fundName.setText(screenDTO.getFundName());
        whatsIs.setText(screenDTO.getWhatIs());
        definition.setText(screenDTO.getDefinition());
        infoTitle.setText(screenDTO.getInfoTitle());

        riskUm.setPadding(0,20,0,0);
        riskTwo.setPadding(0,20,0,0);
        riskThree.setPadding(0,20,0,0);
        riskFour.setPadding(0,20,0,0);
        riskFive.setPadding(0,20,0,0);

        switch (screenDTO.getRisk()){
            case 1:
                riskUm.setPadding(0,0,0,0);
                break;
            case 2 :
                riskTwo.setPadding(0,0,0,0);
                break;
            case 3:
                riskThree.setPadding(0,0,0,0);
                break;
            case 4:
                riskFour.setPadding(0,0,0,0);
                break;
            case 5:
                riskFive.setPadding(0,0,0,0);
                break;
            default:break;
        }

        //investiment information
        MoreInfoDTO moreInfo = screenDTO.getMoreInfo();
        TextView monthFund = findViewById(R.id.month_fund);
        TextView monthCdi = findViewById(R.id.month_cdi);
        TextView yearCdi = findViewById(R.id.year_cdi);
        TextView yearFund = findViewById(R.id.year_fund);
        TextView twelveYear = findViewById(R.id.twelve_fund);
        TextView twelveCdi = findViewById(R.id.twelve_cdi);

        monthFund.setTypeface(typeFont);
        monthCdi.setTypeface(typeFont);
        yearCdi.setTypeface(typeFont);
        yearFund.setTypeface(typeFont);
        twelveCdi.setTypeface(typeFont);
        twelveYear.setTypeface(typeFont);

        double fundMonth = moreInfo.getMonth().getFund();
        double cdiMonth = moreInfo.getMonth().getCDI();
        double fundYear = moreInfo.getYear().getFund();
        double cdiYear = moreInfo.getYear().getCDI();
        double fundTwelve = moreInfo.getTwelve12Months().getFund();
        double cdiFund = moreInfo.getTwelve12Months().getCDI();

        monthFund.setText(String.valueOf(fundMonth));
        monthCdi.setText(String.valueOf(cdiMonth));
        yearFund.setText(String.valueOf(fundYear));
        yearCdi.setText(String.valueOf(cdiYear));
        twelveYear.setText(String.valueOf(fundTwelve));
        twelveCdi.setText(String.valueOf(cdiFund));

        //list view
        TextView infoName = findViewById(R.id.text_info);
        TextView infoData = findViewById(R.id.text_info_2);
        InfoDTO[] arrayInfo = screenDTO.getInfo();
        for (int i = 0; i < arrayInfo.length;i++){
            infoName.append(arrayInfo[i].getName());
            infoData.append(arrayInfo[i].getData());
            infoName.append("\n");
            infoData.append("\n");
        }

        //list down
        TextView downName = findViewById(R.id.text_down);
        LinearLayout downLayout =  findViewById(R.id.layout_img_down);
        DownInfoDTO[] arrayDown = screenDTO.getDownInfo();
        for (int i = 0; i < arrayDown.length;i++){
            downName.append(arrayDown[i].getName());
            downName.append("\n");

        }

    }

    public void startInvest(View v){
        Toast.makeText(getApplicationContext(),"No cookie for you!!!!",Toast.LENGTH_LONG).show();
    }
}
