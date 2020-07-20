package com.example.navigationdrawerpractica.Interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawerpractica.R;

public class Encuesta2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta2);
    }
   public void Click(View view){
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnAtras:
                miIntent=new Intent(this, Encuesta.class);
                break;
            case R.id.btnFinal:
                miIntent=new Intent(this, MainActivity.class);
                break;
        }
        startActivity(miIntent);
   }





}
