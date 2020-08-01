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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;


public class NumericoFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Button atras,siguiente;
    RequestQueue requestQueue;
    String DatoRespuesta,NumeroPregunta,PreguntaFinal,Respuesta,DescripcionPregunta;
    int nPregunta,IdQuiz,IdPregunta,PrsAtras;
    EditText edRespuestaNumerico;
    TextView desPregunta,Titulo,tvIdRespuestaNum;
    private String Token;

    public NumericoFragment() {
        // Required empty public constructor
    }


    public static NumericoFragment newInstance(String param1, String param2) {
        NumericoFragment fragment = new NumericoFragment();
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
        View view = inflater.inflate(R.layout.fragment_numerico, container, false);
        desPregunta = view.findViewById(R.id.tvPreguntaN);
        Titulo = view.findViewById(R.id.tvPreguntaTiTNum);
        tvIdRespuestaNum = view.findViewById(R.id.tvIdRespuestaNum);
        requestQueue = Volley.newRequestQueue(getActivity());
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
        atras = view.findViewById(R.id.btnAtrasNum);
        if (NumeroPregunta.equals("1"))
        {
            atras.setVisibility(View.INVISIBLE);
        }else{
            atras.setVisibility(View.VISIBLE);
        }
        siguiente = view.findViewById(R.id.btnSiguienteNum);
        if (PreguntaFinal.equals(NumeroPregunta)){
            siguiente.setText("Terminar");
        }else{
            siguiente.setText("Siguiente");
        }
        SharedPreferences.Editor sharedPreferences = getActivity().getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
        edRespuestaNumerico = view.findViewById(R.id.edtRespuestaNum);
        if (PrsAtras >= 1){
            LLenarRespuesta(IdQuiz,IdPregunta);
        }
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
                Respuesta = edRespuestaNumerico.getText().toString();
                DatoRespuesta =  ValidarRegistro(Respuesta);

                if (DatoRespuesta.equals("N")){
                    edRespuestaNumerico.setError("Este campo está vacío");
                }
                else {
                    if (PreguntaFinal.equals(NumeroPregunta)){
                        Registro(edRespuestaNumerico.getText().toString(),IdPregunta,IdQuiz);
                        sharedPreferences.putInt("IdQuiz",0);
                        sharedPreferences.commit();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().onBackPressed();
                    }else{
                        if (PrsAtras == 0){
                            Registro(edRespuestaNumerico.getText().toString(),IdPregunta,IdQuiz);
                        }
                        else
                        {
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
        }){
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
                    tvIdRespuestaNum.setText(obj.getString("id"));
                    edRespuestaNumerico.setText(obj.getString("value"));

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
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
    public void ActualizarDato(){
        String cURL ="http://dgform.ga/forms/answer/" + tvIdRespuestaNum.getText().toString();
        String Respuesta = edRespuestaNumerico.getText().toString();

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