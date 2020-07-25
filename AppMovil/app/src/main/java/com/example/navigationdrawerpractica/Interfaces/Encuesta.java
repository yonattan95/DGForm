package com.example.navigationdrawerpractica.Interfaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.navigationdrawerpractica.Fragments.ChecklistFragment;
import com.example.navigationdrawerpractica.Fragments.EditTextFragment;
import com.example.navigationdrawerpractica.Fragments.NumericoFragment;
import com.example.navigationdrawerpractica.Fragments.RadioButtonFragment;
import com.example.navigationdrawerpractica.R;

public class Encuesta extends AppCompatActivity{


    FragmentTransaction transaction;
    FragmentManager fragmentManager;
    Fragment fragmentET,fragmentNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        fragmentET = new EditTextFragment();
        fragmentNum =  new NumericoFragment();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        SharedPreferences preferences2 = getSharedPreferences("gymapp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences2.edit();
        editor.putString("PreguntaFinal", "4");
        editor.commit();

        SharedPreferences sharedPreferences = getSharedPreferences("gymapp",MODE_PRIVATE);
        if (sharedPreferences.getString("Pregunta","").equals("1")){
            transaction.replace(R.id.Preguntascontainer_fragment,new EditTextFragment());
            transaction.commit();
        }
        if (sharedPreferences.getString("Pregunta","").equals("2")){
            transaction.replace(R.id.Preguntascontainer_fragment,new NumericoFragment());
            transaction.commit();
        }
        if (sharedPreferences.getString("Pregunta","").equals("3")){
            transaction.replace(R.id.Preguntascontainer_fragment,new ChecklistFragment());
            transaction.commit();
        }
        if (sharedPreferences.getString("Pregunta","").equals("4")){
            transaction.replace(R.id.Preguntascontainer_fragment,new RadioButtonFragment());
            transaction.commit();
        }

    }


}
