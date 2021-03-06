package com.example.navigationdrawerpractica.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navigationdrawerpractica.Interfaces.Encuesta;
import com.example.navigationdrawerpractica.Interfaces.MainActivity;
import com.example.navigationdrawerpractica.R;

public class ChecklistFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    int nPregunta;
    Button atras,siguiente;
    String DatoRespuesta,NumeroPregunta,PreguntaFinal;
    CheckBox Valor1,Valor2,Valor3,Valor4,Valor5,Valor6;

    public ChecklistFragment() {
        // Required empty public constructor
    }

    public static ChecklistFragment newInstance(String param1, String param2) {
        ChecklistFragment fragment = new ChecklistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("gymapp", Context.MODE_PRIVATE);
        NumeroPregunta = sharedPreferences2.getString("Pregunta", "");
        PreguntaFinal = sharedPreferences2.getString("PreguntaFinal", "");
        atras = view.findViewById(R.id.btnAtrascb);
        if (NumeroPregunta.equals("1"))
        {
            atras.setVisibility(View.INVISIBLE);
        }else{
            atras.setVisibility(View.VISIBLE);
        }

        siguiente = view.findViewById(R.id.btnSiguientecb);
        if (PreguntaFinal.equals(NumeroPregunta)){
            siguiente.setText("Terminar");
        }else{
            siguiente.setText("Siguiente");
        }
        Valor1 = view.findViewById(R.id.CbRespuesta1);
        Valor2 = view.findViewById(R.id.CbRespuesta2);
        Valor3 = view.findViewById(R.id.CbRespuesta3);
        Valor4 = view.findViewById(R.id.CbRespuesta4);
        Valor5 = view.findViewById(R.id.CbRespuesta5);
        Valor6 = view.findViewById(R.id.CbRespuesta6);

        for (int i = 1; i < 4; i++){
            if (i == 1){
                Valor1.setVisibility(View.VISIBLE);
            }
            if (i == 2){
                Valor2.setVisibility(View.VISIBLE);
            }
            if (i == 3){
                Valor3.setVisibility(View.VISIBLE);
            }
            if (i == 4){
                Valor4.setVisibility(View.VISIBLE);
            }
            if (i == 5){
                Valor5.setVisibility(View.VISIBLE);
            }
            if (i == 6){
                Valor6.setVisibility(View.VISIBLE);
            }
        }
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DatoRespuesta = ValidarRegistro();
                    if (DatoRespuesta.equals("N")){
                        Toast.makeText(getContext(),"Seleccionar respuesta",Toast.LENGTH_SHORT).show();
                    }else{
                        if (PreguntaFinal.equals(NumeroPregunta)){
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().onBackPressed();
                        }else{
                            SharedPreferences.Editor sharedPreferences = getActivity().getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
                            nPregunta = Integer.valueOf(NumeroPregunta) + 1;
                            NumeroPregunta =  String.valueOf(nPregunta);
                            sharedPreferences.putString("Pregunta",NumeroPregunta);
                            sharedPreferences.commit();
                            startActivity(new Intent(getActivity(), Encuesta.class));
                            getActivity().onBackPressed();
                        }
                    }
                }
        });

        return view;
    }

    public String ValidarRegistro(){
        String resp="N";
        if (Valor1.isChecked()){
            resp = "S";
        }
        if (Valor2.isChecked()){
            resp = "S";
        }
        if (Valor3.isChecked()){
            resp = "S";
        }
        if (Valor4.isChecked()){
            resp = "S";
        }
        if (Valor5.isChecked()){
            resp = "S";
        }
        if (Valor6.isChecked()){
            resp = "S";
        }
        return resp;
    }
}