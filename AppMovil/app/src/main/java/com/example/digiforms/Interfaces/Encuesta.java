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
import com.example.digiforms.Fragments.DoubleFragment;
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
    private int idfrom,IDUsu;
    private int NumeroPregunta,TipoPregunta,IdPregunta;
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

        fillPreguntas();
    }

    private void fillPreguntas() {
        final String url;
        SharedPreferences sharedPreferences = getSharedPreferences("gymapp",MODE_PRIVATE);
        idfrom = Integer.valueOf(sharedPreferences.getString("IdForm",""));
        IDUsu = Integer.valueOf(sharedPreferences.getString("UsuarioID",""));
        url ="http://dgform.ga/form/"+ idfrom +"/interviewer/"+ IDUsu +"/questions";
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject Objx = response.getJSONObject("data");
                    JSONArray jsonArray = Objx.getJSONArray("questionList");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        JSONObject jsonObject = obj.getJSONObject("questionType");
                        lista.add(new Preguntas(obj.getString("name"),
                                jsonObject.getInt("id"),obj.getInt("questionNumber"),obj.getInt("id")));
                    }


                    SharedPreferences.Editor sharedPreferences2 = getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
                    sharedPreferences2.putString("PreguntaFinal", String.valueOf(lista.size()));


                    for (int i = 0; i < lista.size(); i++) {
                        NumeroPregunta = lista.get(i).getNumPregunta();
                        if (Integer.valueOf(sharedPreferences.getString("Pregunta", "")).equals(NumeroPregunta)){
                            NumeroPregunta = lista.get(i).getNumPregunta();
                            TipoPregunta = lista.get(i).getTipoPregunta();
                            Descripcion = lista.get(i).getDescripcion();
                            IdPregunta = lista.get(i).getIdPregunta();
                            sharedPreferences2.putString("DescripcionPregunta", Descripcion);
                            sharedPreferences2.putString("NumeroPregunta",String.valueOf(NumeroPregunta));
                            sharedPreferences2.putInt("IdPregunta",IdPregunta);
                            sharedPreferences2.putString("PreguntaFinal", String.valueOf(lista.size()));
                            sharedPreferences2.commit();
                            if (TipoPregunta == 1) {
                                transaction.replace(R.id.Preguntascontainer_fragment, new EditTextFragment());
                                transaction.commit();
                                break;
                            }
                            if (TipoPregunta == 2  ) {
                                transaction.replace(R.id.Preguntascontainer_fragment, new NumericoFragment());
                                transaction.commit();
                                break;
                            }
                            if (TipoPregunta == 3){
                                transaction.replace(R.id.Preguntascontainer_fragment, new DoubleFragment());
                                transaction.commit();
                                break;
                            }
                            if (TipoPregunta == 4) {
                                transaction.replace(R.id.Preguntascontainer_fragment, new RadioButtonFragment());
                                transaction.commit();
                                break;
                            }
                            if (TipoPregunta == 5) {
                                transaction.replace(R.id.Preguntascontainer_fragment, new ChecklistFragment());
                                transaction.commit();
                                break;
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
