package com.example.navigationdrawerpractica.Interfaces;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.navigationdrawerpractica.Fragments.ComboboxFragment;
import com.example.navigationdrawerpractica.Fragments.Detalle_HistorialFragment;
import com.example.navigationdrawerpractica.Fragments.EditTextFragment;
import com.example.navigationdrawerpractica.R;

public class Encuesta extends AppCompatActivity{


    FragmentTransaction transaction;
    FragmentManager fragmentManager;
    Fragment fragmentET,fragmentCbb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        fragmentET = new EditTextFragment();
        fragmentCbb =  new ComboboxFragment();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.Preguntascontainer_fragment,new Detalle_HistorialFragment());
//        transaction.commit();
        SharedPreferences sharedPreferences = getSharedPreferences("gymapp",MODE_PRIVATE);
        if (sharedPreferences.getString("Pregunta","") == "1"){
            transaction.replace(R.id.Preguntascontainer_fragment,new EditTextFragment());
            transaction.commit();
        }else{
            transaction.replace(R.id.Preguntascontainer_fragment,new ComboboxFragment());
            transaction.commit();

        }


    }

    public void Encu2(View view) {

        Intent encu2 = new Intent(this, Encuesta2.class);
        startActivity(encu2);
    }

}
