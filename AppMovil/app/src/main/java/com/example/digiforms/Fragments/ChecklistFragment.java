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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.digiforms.Entidades.OpcionesPreguntas;
import com.example.digiforms.Interfaces.Encuesta;
import com.example.digiforms.Interfaces.MainActivity;
import com.example.digiforms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChecklistFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    int nPregunta,IdQuiz,IdPregunta;
    Button atras,siguiente;
    RequestQueue requestQueue;
    static ArrayList<OpcionesPreguntas> lista;
    String DatoRespuesta,NumeroPregunta,PreguntaFinal,DescripcionPregunta,PrsAtras;
    CheckBox Valor1,Valor2,Valor3,Valor4,Valor5,Valor6;
    TextView desPregunta,Titulo,tvIdRespuestaCb;

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
        desPregunta = view.findViewById(R.id.tvPreguntaCb);
        Titulo = view.findViewById(R.id.tvPreguntaTiCb);
        tvIdRespuestaCb = view.findViewById(R.id.tvIdRespuestaCb);
        requestQueue = Volley.newRequestQueue(getActivity());
        lista = new ArrayList<>();
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("gymapp", Context.MODE_PRIVATE);
        NumeroPregunta = sharedPreferences2.getString("NumeroPregunta", "");
        DescripcionPregunta = sharedPreferences2.getString("DescripcionPregunta", "");
        PreguntaFinal = sharedPreferences2.getString("PreguntaFinal", "");
        IdQuiz = sharedPreferences2.getInt("IdQuiz", 0);
        IdPregunta = sharedPreferences2.getInt("IdPregunta", 0);
        PrsAtras= sharedPreferences2.getString("Atras","");
        desPregunta.setText(DescripcionPregunta);
        Titulo.setText("Pregunta N°"+ NumeroPregunta);
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
        LLenarValores(IdPregunta);
        if (PrsAtras.equals("S")){
            LLenarRespuesta(IdQuiz,IdPregunta);
        }
        SharedPreferences.Editor sharedPreferences = getActivity().getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
//        for (int i = 1; i < 4; i++){
//            if (i == 1){
//                Valor1.setVisibility(View.VISIBLE);
//            }
//            if (i == 2){
//                Valor2.setVisibility(View.VISIBLE);
//            }
//            if (i == 3){
//                Valor3.setVisibility(View.VISIBLE);
//            }
//            if (i == 4){
//                Valor4.setVisibility(View.VISIBLE);
//            }
//            if (i == 5){
//                Valor5.setVisibility(View.VISIBLE);
//            }
//            if (i == 6){
//                Valor6.setVisibility(View.VISIBLE);
//            }
//        }
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nPregunta = Integer.valueOf(NumeroPregunta) - 1;
                NumeroPregunta =  String.valueOf(nPregunta);
                sharedPreferences.putString("Atras","S");
                sharedPreferences.putString("Pregunta",NumeroPregunta);
                sharedPreferences.commit();
                startActivity(new Intent(getActivity(), Encuesta.class));
                getActivity().onBackPressed();
            }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DatoRespuesta = ValidarRegistro();
                    if (DatoRespuesta.equals("N")){
                        Toast.makeText(getContext(),"Seleccionar respuesta",Toast.LENGTH_SHORT).show();
                    }else{
                        if (PreguntaFinal.equals(NumeroPregunta)){
                            Registro(IdPregunta,IdQuiz);    //cambiar por la canidad
                            sharedPreferences.putInt("IdQuiz",0);
                            sharedPreferences.commit();
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().onBackPressed();
                        }else{
                            if (PrsAtras.equals("N")) {
                                Registro(IdPregunta,IdQuiz);    //cambiar por la cantida
                            }else{
                                ActualizarDato();
                            }
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
    public void Registro(int IdPreg,int idQ){
        int Contre= 0;
        if (Valor1.isChecked()){
            Contre = Contre + 1;
        }
        if (Valor2.isChecked()){
            Contre = Contre + 1;
        }
        if (Valor3.isChecked()){
            Contre = Contre + 1;
        }
        if (Valor4.isChecked()){
            Contre = Contre + 1;
        }
        if (Valor5.isChecked()){
            Contre = Contre + 1;
        }
        if (Valor6.isChecked()){
            Contre = Contre + 1;
        }

        String Respuesta[] = new String[Contre];
        if (Valor1.isChecked()){
            Respuesta[0] = Valor1.getText().toString();
        }
        if (Valor2.isChecked()){
            Respuesta[1] = Valor1.getText().toString();
        }
        if (Valor3.isChecked()){
            Respuesta[2] = Valor1.getText().toString();
        }
        if (Valor4.isChecked()){
            Respuesta[3] = Valor1.getText().toString();
        }
        if (Valor5.isChecked()){
            Respuesta[4] = Valor1.getText().toString();
        }
        if (Valor6.isChecked()){
            Respuesta[5] = Valor1.getText().toString();
        }
        String url ="http://dgform.ga/forms/answer/";
        Map<String,String> mapEditText = new HashMap<>();
        mapEditText.put("question",String.valueOf(IdPreg));
        mapEditText.put("quiz",String.valueOf(idQ));
        mapEditText.put("value",Respuesta[Contre]);
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
    public void LLenarValores(int idQues){
        String URL ="http://dgform.ga/forms/question/" + idQues + "/options";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        lista.add(new OpcionesPreguntas(obj.getString("description"),
                                obj.getString("value")));
                    }
                    for (int i = 1; i < lista.size(); i++){
                        if (i == 0){
                            Valor1.setText(lista.get(i).getDescripcionPr());
                            Valor1.setVisibility(View.VISIBLE);
                        }
                        if (i == 1){
                            Valor2.setText(lista.get(i).getDescripcionPr());
                            Valor2.setVisibility(View.VISIBLE);
                        }
                        if (i == 2){
                            Valor3.setText(lista.get(i).getDescripcionPr());
                            Valor3.setVisibility(View.VISIBLE);
                        }
                        if (i == 3){
                            Valor4.setText(lista.get(i).getDescripcionPr());
                            Valor4.setVisibility(View.VISIBLE);
                        }
                        if (i == 4){
                            Valor5.setText(lista.get(i).getDescripcionPr());
                            Valor5.setVisibility(View.VISIBLE);
                        }
                        if (i == 5){
                            Valor6.setText(lista.get(i).getDescripcionPr());
                            Valor6.setVisibility(View.VISIBLE);
                        }
                    }

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
    public void LLenarRespuesta(int xIdQuiz, int xIdPregunta){
        String xURL ="http://dgform.ga/forms/quiz/"+xIdQuiz+"/answer/"+xIdPregunta;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, xURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        tvIdRespuestaCb.setText(obj.getString("id"));
                        if ( Valor1.getText().equals(obj.getString("value"))){
                            Valor1.isChecked();
                        }
                        if (Valor2.getText().equals(obj.getString("value"))){
                            Valor2.isChecked();
                        }
                        if (Valor3.getText().equals(obj.getString("value"))){
                            Valor3.isChecked();
                        }
                        if (Valor4.getText().equals(obj.getString("value"))){
                            Valor4.isChecked();
                        }
                        if (Valor5.getText().equals(obj.getString("value"))){
                            Valor5.isChecked();
                        }
                        if (Valor6.getText().equals(obj.getString("value"))){
                            Valor6.isChecked();
                        }
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
    public void ActualizarDato(){
        String cURL ="http://dgform.ga/forms/answer/" + tvIdRespuestaCb.getText().toString();
        int Contre= 0;
        if (Valor1.isChecked()){
            Contre = Contre + 1;
        }
        if (Valor2.isChecked()){
            Contre = Contre + 1;
        }
        if (Valor3.isChecked()){
            Contre = Contre + 1;
        }
        if (Valor4.isChecked()){
            Contre = Contre + 1;
        }
        if (Valor5.isChecked()){
            Contre = Contre + 1;
        }
        if (Valor6.isChecked()){
            Contre = Contre + 1;
        }

        String Respuesta[] = new String[Contre];
        if (Valor1.isChecked()){
            Respuesta[0] = Valor1.getText().toString();
        }
        if (Valor2.isChecked()){
            Respuesta[1] = Valor1.getText().toString();
        }
        if (Valor3.isChecked()){
            Respuesta[2] = Valor1.getText().toString();
        }
        if (Valor4.isChecked()){
            Respuesta[3] = Valor1.getText().toString();
        }
        if (Valor5.isChecked()){
            Respuesta[4] = Valor1.getText().toString();
        }
        if (Valor6.isChecked()){
            Respuesta[5] = Valor1.getText().toString();
        }
        Map<String,String> mapEditText = new HashMap<>();
        mapEditText.put("value",Respuesta[Contre]);
        JSONObject jsonObject = new JSONObject(mapEditText);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, cURL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (String.valueOf(response.getInt("status")).equals("1")) {
                        Toast.makeText(getActivity(), "Registro Actualizado", Toast.LENGTH_SHORT).show();
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