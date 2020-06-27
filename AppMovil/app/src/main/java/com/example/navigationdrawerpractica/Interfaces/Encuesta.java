package com.example.navigationdrawerpractica.Interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawerpractica.R;

public class Encuesta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
    }

    public void Encu2(View view) {

        Intent encu2 = new Intent(this, Encuesta2.class);
        startActivity(encu2);
    }

}
