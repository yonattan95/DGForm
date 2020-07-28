package com.example.digiforms.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.digiforms.Interfaces.Encuesta;
import com.example.digiforms.Interfaces.MainActivity;
import com.example.digiforms.R;


public class EditTextFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    int nPregunta;
    Button atras,siguiente;
    String DatoRespuesta,NumeroPregunta,PreguntaFinal,DescripcionPregunta;
    EditText edRespuestaEditText;
    TextView desPregunta,Titulo;

    public EditTextFragment() {
        // Required empty public constructor
    }

    public static EditTextFragment newInstance(String param1, String param2) {
        EditTextFragment fragment = new EditTextFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_text, container, false);
        desPregunta = view.findViewById(R.id.tvPregunta);
        Titulo = view.findViewById(R.id.tvPreguntaET);
        atras = view.findViewById(R.id.btnAtras);
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("gymapp", Context.MODE_PRIVATE);
        NumeroPregunta = sharedPreferences2.getString("NumeroPregunta", "");
        DescripcionPregunta = sharedPreferences2.getString("DescripcionPregunta", "");
        PreguntaFinal = sharedPreferences2.getString("PreguntaFinal", "");
        desPregunta.setText(DescripcionPregunta);
        Titulo.setText("Pregunta N°"+ NumeroPregunta);
        if (NumeroPregunta.equals("1"))
        {
            atras.setVisibility(View.INVISIBLE);
        }else{
            atras.setVisibility(View.VISIBLE);
        }
        siguiente = view.findViewById(R.id.btnSiguiente);
        if (PreguntaFinal.equals(NumeroPregunta)){
            siguiente.setText("Terminar");
        }else{
            siguiente.setText("Siguiente");
        }
        edRespuestaEditText =view.findViewById(R.id.edRespuesta);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatoRespuesta =  ValidarRegistro(edRespuestaEditText.getText().toString());
                if (DatoRespuesta.equals("N")){
                    edRespuestaEditText.setError("Este campo está vacío");
                }
                else {
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
    public String ValidarRegistro(String Dato){
        final String xResp;
        if (Dato.equals("")){
            xResp = "N";
        }
        else{
            xResp = "S";
        }
        return xResp;
    }
    public void buscarDatos(){
        
    }

}