package com.example.digiforms.Interfaces;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.digiforms.Adaptadores.DetalleEncuestaAdapter;
import com.example.digiforms.Entidades.Categorias;
import com.example.digiforms.Entidades.Encuestas;
import com.example.digiforms.Entidades.Preguntas;
import com.example.digiforms.Fragments.ChecklistFragment;
import com.example.digiforms.Fragments.EditTextFragment;
import com.example.digiforms.Fragments.NumericoFragment;
import com.example.digiforms.Fragments.RadioButtonFragment;
import com.example.digiforms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Encuesta extends AppCompatActivity{

    FragmentTransaction transaction;
    FragmentManager fragmentManager;
    Fragment fragmentET,fragmentNum;
    static ArrayList<Preguntas> lista;

    private RequestQueue requestQueue;
    private int idfrom;
    private int NumeroPregunta,TipoPregunta;
    private String Descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        fragmentET = new EditTextFragment();
        fragmentNum =  new NumericoFragment();
        fragmentManager = getSupportFragmentManager();
        requestQueue = Volley.newRequestQueue(this);
        transaction = fragmentManager.beginTransaction();
        lista = new ArrayList<>();

        SharedPreferences preferences2 = getSharedPreferences("gymapp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences2.edit();
        editor.putString("PreguntaFinal", "4");
        editor.commit();
        fillPreguntas();
//        SharedPreferences sharedPreferences = getSharedPreferences("gymapp",MODE_PRIVATE);
//        idfrom = Integer.valueOf(sharedPreferences.getString("IdForm",""));
//
//
//        SharedPreferences.Editor sharedPreferences2 = this.getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
//
//        for (int i = 0; i < lista.size(); i++) {
//            NumeroPregunta = lista.get(i).getNumPregunta();
//            if (Integer.valueOf(sharedPreferences.getString("Pregunta", "")).equals(NumeroPregunta)){
//                NumeroPregunta = lista.get(i).getNumPregunta();
//                TipoPregunta = lista.get(i).getTipoPregunta();
//                Descripcion = lista.get(i).getDescripcion();
//                sharedPreferences2.putString("DescripcionPregunta", Descripcion);
//                sharedPreferences2.putString("NumeroPregunta",String.valueOf(NumeroPregunta));
//                sharedPreferences2.commit();
//                if (TipoPregunta == 1) {
//                    transaction.replace(R.id.Preguntascontainer_fragment, new EditTextFragment());
//                    transaction.commit();
//                }
//                if (TipoPregunta == 2 || TipoPregunta == 3) {
//                    transaction.replace(R.id.Preguntascontainer_fragment, new NumericoFragment());
//                    transaction.commit();
//                }
//                if (TipoPregunta == 5) {
//                    transaction.replace(R.id.Preguntascontainer_fragment, new ChecklistFragment());
//                    transaction.commit();
//                }
//                if (TipoPregunta == 4) {
//                    transaction.replace(R.id.Preguntascontainer_fragment, new RadioButtonFragment());
//                    transaction.commit();
//                }
//            }
//        }
    }

    private void fillPreguntas() {
        final String url;
        url ="http://dgform.ga/form/7/interviewer/1/questions";
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        JSONObject jsonObject = obj.getJSONObject("questionType");
                        lista.add(new Preguntas(obj.getString("description"),
                                jsonObject.getInt("id"),obj.getInt("questionNumber")));
                    }

                    SharedPreferences sharedPreferences = getSharedPreferences("gymapp",MODE_PRIVATE);
                    idfrom = Integer.valueOf(sharedPreferences.getString("IdForm",""));


                    SharedPreferences.Editor sharedPreferences2 = getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();

                    for (int i = 0; i < lista.size(); i++) {
                        NumeroPregunta = lista.get(i).getNumPregunta();
                        if (Integer.valueOf(sharedPreferences.getString("Pregunta", "")).equals(NumeroPregunta)){
                            NumeroPregunta = lista.get(i).getNumPregunta();
                            TipoPregunta = lista.get(i).getTipoPregunta();
                            Descripcion = lista.get(i).getDescripcion();
                            sharedPreferences2.putString("DescripcionPregunta", Descripcion);
                            sharedPreferences2.putString("NumeroPregunta",String.valueOf(NumeroPregunta));
                            sharedPreferences2.commit();
                            if (TipoPregunta == 1) {
                                transaction.replace(R.id.Preguntascontainer_fragment, new EditTextFragment());
                                transaction.commit();
                            }
                            if (TipoPregunta == 2 || TipoPregunta == 3) {
                                transaction.replace(R.id.Preguntascontainer_fragment, new NumericoFragment());
                                transaction.commit();
                            }
                            if (TipoPregunta == 5) {
                                transaction.replace(R.id.Preguntascontainer_fragment, new ChecklistFragment());
                                transaction.commit();
                            }
                            if (TipoPregunta == 4) {
                                transaction.replace(R.id.Preguntascontainer_fragment, new RadioButtonFragment());
                                transaction.commit();
                            }
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
        requestQueue.add(request2);
    }


}
