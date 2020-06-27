package com.example.navigationdrawerpractica.Interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawerpractica.R;


public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

  public void Gologin(View view){
       Intent gologin=new Intent(this, MainActivity.class);
        startActivity(gologin);
      finish();
    }

}
