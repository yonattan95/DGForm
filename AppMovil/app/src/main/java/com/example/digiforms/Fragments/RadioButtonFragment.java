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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.digiforms.Entidades.Categorias;
import com.example.digiforms.Entidades.Encuestas;
import com.example.digiforms.Entidades.OpcionesPreguntas;
import com.example.digiforms.Interfaces.Encuesta;
import com.example.digiforms.Interfaces.MainActivity;
import com.example.digiforms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RadioButtonFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Button atras,siguiente;
    static ArrayList<OpcionesPreguntas> lista;
    private String Token;
    RequestQueue requestQueue;
    String DatoRespuesta,NumeroPregunta,PreguntaFinal,DescripcionPregunta;
    int nPregunta,IdQuiz,IdPregunta,PrsAtras;
    RadioButton Valor1,Valor2,Valor3,Valor4,Valor5,Valor6;
    TextView desPregunta,Titulo,tvIdRespuestaRb;

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
        tvIdRespuestaRb = view.findViewById(R.id.tvIdRespuestaRb);
        requestQueue = Volley.newRequestQueue(getActivity());
        lista = new ArrayList<>();
        SharedPreferences sharedPreferences2 = getActivity().getSharedPreferences("gymapp", Context.MODE_PRIVATE);
        NumeroPregunta = sharedPreferences2.getString("NumeroPregunta", "");
        DescripcionPregunta = sharedPreferences2.getString("DescripcionPregunta", "");
        PreguntaFinal = sharedPreferences2.getString("PreguntaFinal", "");
        IdQuiz = sharedPreferences2.getInt("IdQuiz", 0);
        IdPregunta = sharedPreferences2.getInt("IdPregunta", 0);
        PrsAtras= sharedPreferences2.getInt("Atras",0);
        Token = sharedPreferences2.getString("Token", "");
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

        LLenarValores(IdPregunta);
        if (PrsAtras >= 1){
            LLenarRespuesta(IdQuiz,IdPregunta);
        }
        SharedPreferences.Editor sharedPreferences = getActivity().getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
//        for (int i = 0; i < 3; i++){
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
                int numeroAtras = PrsAtras + 1;
                sharedPreferences.putInt("Atras",numeroAtras);
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
                }else {
                    if (PreguntaFinal.equals(NumeroPregunta)){
                        Registro(IdPregunta,IdQuiz);    //cambiar
                        sharedPreferences.putInt("IdQuiz",0);
                        sharedPreferences.commit();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().onBackPressed();
                    }else{
                        if (PrsAtras == 0) {
                            Registro(IdPregunta,IdQuiz);    //cambiar
                        }else{
                            ActualizarDato();
                            int numeroAtras = PrsAtras - 1;
                            sharedPreferences.putInt("Atras",numeroAtras);

                        }
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
    public void Registro(int IdPreg,int idQ){
        String url ="http://dgform.ga/forms/answer/";
        String Respuesta="";
        if (Valor1.isChecked()){
            Respuesta = Valor1.getText().toString();
        }
        if (Valor2.isChecked()){
            Respuesta = Valor2.getText().toString();
        }
        if (Valor3.isChecked()){
            Respuesta = Valor3.getText().toString();
        }
        if (Valor4.isChecked()){
            Respuesta = Valor4.getText().toString();
        }
        if (Valor5.isChecked()){
            Respuesta = Valor5.getText().toString();
        }
        if (Valor6.isChecked()){
            Respuesta = Valor6.getText().toString();
        }

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
        })
        {
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization","Bearer " + Token);
                return headers;
            }
        };
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
                    for (int i = 0; i < lista.size(); i++){
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
        })
        {
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization","Bearer " + Token);
                return headers;
            }
        };
        requestQueue.add(request);
    }
    public void LLenarRespuesta(int xIdQuiz, int xIdPregunta){
        String xURL ="http://dgform.ga/forms/quiz/"+xIdQuiz+"/answer/"+xIdPregunta;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, xURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject obj = response.getJSONObject("data");
                    tvIdRespuestaRb.setText(obj.getString("id"));
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

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        })
        {
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization","Bearer " + Token);
                return headers;
            }
        };
        requestQueue.add(request);
    }
    public void ActualizarDato(){
        String cURL ="http://dgform.ga/forms/answer/" + tvIdRespuestaRb.getText().toString();
        String Respuesta="";
        if (Valor1.isChecked()){
            Respuesta = Valor1.getText().toString();
        }
        if (Valor2.isChecked()){
            Respuesta = Valor2.getText().toString();
        }
        if (Valor3.isChecked()){
            Respuesta = Valor3.getText().toString();
        }
        if (Valor4.isChecked()){
            Respuesta = Valor4.getText().toString();
        }
        if (Valor5.isChecked()){
            Respuesta = Valor5.getText().toString();
        }
        if (Valor6.isChecked()){
            Respuesta = Valor6.getText().toString();
        }
        Map<String,String> mapEditText = new HashMap<>();
        mapEditText.put("value",Respuesta);
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
        }){
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization","Bearer " + Token);
                return headers;
            }
        };
        requestQueue.add(request);
    }
}