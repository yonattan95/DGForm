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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.digiforms.Interfaces.Encuesta;
import com.example.digiforms.Interfaces.MainActivity;
import com.example.digiforms.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class RadioButtonFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Button atras,siguiente;
    RequestQueue requestQueue;
    String DatoRespuesta,NumeroPregunta,PreguntaFinal,DescripcionPregunta;
    int nPregunta,IdQuiz,IdPregunta;
    RadioButton Valor1,Valor2,Valor3,Valor4,Valor5,Valor6;
    TextView desPregunta,Titulo;

    public RadioButtonFragment() {
        // Required empty public constructor
    }

    public static RadioButtonFragment newInstance(String param1, String param2) {
        RadioButtonFragment fragment = new RadioButtonFragment();
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
        View view = inflater.inflate(R.layout.fragment_radio_button, container, false);
        desPregunta = view.findViewById(R.id.tvPreguntarb);
        Titulo = view.findViewById(R.id.tvPreguntaTitrb);
        requestQueue = Volley.newRequestQueue(getActivity());
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("gymapp", Context.MODE_PRIVATE);
        NumeroPregunta = sharedPreferences2.getString("NumeroPregunta", "");
        DescripcionPregunta = sharedPreferences2.getString("DescripcionPregunta", "");
        PreguntaFinal = sharedPreferences2.getString("PreguntaFinal", "");
        IdQuiz = sharedPreferences2.getInt("IdQuiz", 0);
        IdPregunta = sharedPreferences2.getInt("IdPregunta", 0);
        desPregunta.setText(DescripcionPregunta);
        Titulo.setText("Pregunta N°"+ NumeroPregunta);
        atras = view.findViewById(R.id.btnAtrasrb);
        if (NumeroPregunta.equals("1"))
        {
            atras.setVisibility(View.INVISIBLE);
        }else{
            atras.setVisibility(View.VISIBLE);
        }
        siguiente = view.findViewById(R.id.btnSiguienterb);
        if (PreguntaFinal.equals(NumeroPregunta)){
            siguiente.setText("Terminar");
        }else{
            siguiente.setText("Siguiente");
        }
        Valor1 = view.findViewById(R.id.rbRespuesta1);
        Valor2 = view.findViewById(R.id.rbRespuesta2);
        Valor3 = view.findViewById(R.id.rbRespuesta3);
        Valor4 = view.findViewById(R.id.rbRespuesta4);
        Valor5 = view.findViewById(R.id.rbRespuesta5);
        Valor6 = view.findViewById(R.id.rbRespuesta6);


        for (int i = 0; i < 3; i++){
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
                SharedPreferences.Editor sharedPreferences = getActivity().getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
                if (DatoRespuesta.equals("N")){
                    Toast.makeText(getContext(),"Seleccionar respuesta",Toast.LENGTH_SHORT).show();
                }else {
                    if (PreguntaFinal.equals(NumeroPregunta)){
                        Registro(Valor2.getText().toString(),IdPregunta,IdQuiz);    //cambiar
                        sharedPreferences.putInt("IdQuiz",0);
                        sharedPreferences.commit();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().onBackPressed();
                    }else{
                        Registro(Valor2.getText().toString(),IdPregunta,IdQuiz);    //cambiar

                        nPregunta = Integer.valueOf(NumeroPregunta) + 1;
                        NumeroPregunta =  String.valueOf(nPregunta);
                        sharedPreferences.putString("Pregunta", NumeroPregunta);
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
    public void Registro(String Respuesta,int IdPreg,int idQ){
        String url ="http://dgform.ga/forms/answer/";
        Map<String,String> mapEditText = new HashMap<>();
        mapEditText.put("question",String.valueOf(IdPreg));
        mapEditText.put("quiz",String.valueOf(idQ));
        mapEditText.put("value",Respuesta);
        JSONObject jsonObject = new JSONObject(mapEditText);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (String.valueOf(response.getInt("status")).equals("1")) {
                        Toast.makeText(getActivity(), "Registro Correctamente", Toast.LENGTH_SHORT).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }
}