package br.com.curymorais.desafiosantander.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class RootActivity extends AppCompatActivity  {

    public static final String TAG = "SantanderChalenge";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Passei pelo onCreate em: " + getClass().getSimpleName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Passei pelo onStart em: " + getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Passei pelo onResume em: " + getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Passei pelo onPause em: "  + getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Passei pelo onStop em: "  + getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Passei pelo onDestroy em: "  + getClass().getSimpleName());
    }

    protected void setOnClickActivity(int id, Class<?> c) {
        View v = findViewById(id);

        setOnClickActivity(v, c);
    }

    protected void setOnClickActivity(View v, final Class<?> c) {
        if (v != null) {
            View.OnClickListener action = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), c);
                    startActivity(i);
                }
            };
            v.setOnClickListener(action);
        }
    }

    @Override
    public void finish() {
        super.finish();


    }

    protected void populate(int idList, List<?> list) {
        populate(idList, android.R.layout.simple_list_item_1, list);
    }

    protected void populate(int idList, int idItem, List<?> list) {
        ListView lv = getViewById(idList);

        lv.setAdapter(new ArrayAdapter<>(this, idItem, list));
    }

    public <T extends View> T getViewById(int id) {
        return (T) findViewById(id);
    }
}
