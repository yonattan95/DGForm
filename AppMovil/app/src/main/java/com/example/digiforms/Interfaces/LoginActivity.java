package com.example.digiforms.Interfaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.digiforms.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextView tvUsuario, tvPassword ;
    String usuario,password;
    RequestQueue requestQueue;
    SharedPreferences.Editor preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        tvUsuario = findViewById(R.id.textView1);
        tvPassword = findViewById(R.id.textView2);
        Button button = findViewById(R.id.button);

        requestQueue = Volley.newRequestQueue(this);


        //recuperarPreferencias();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = tvUsuario.getText().toString();
                password = tvPassword.getText().toString();
                if(usuario.equals("")&& password.equals("")){
                    tvPassword.setError("Este campo está vacío");
                    tvUsuario.setError("Este campo está vacío");
                }else if(usuario.equals("")){
                    tvUsuario.setError("Este campo está vacío");
                }else if (password.equals("")){
                    tvPassword.setError("Este campo está vacío");
                }else {
                    String url = "http://dgform.ga/interviewer_auth/login";
                    validarUsuario(url);
                }
            }
        });
    }

    private  static  String token;

    private void validarUsuario(String URL){
        Map<String,String> mapLogin = new HashMap<>();
        mapLogin.put("username",usuario);
        mapLogin.put("password",tvPassword.getText().toString());
        JSONObject jsonObject = new JSONObject(mapLogin);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (String.valueOf(response.getInt("status")).equals("1")) {
                        JSONObject jsonObject = response.getJSONObject("data");
                        guardarPreferencias();
                        String url2 = "http://dgform.ga/interviewers/"+ String.valueOf(jsonObject.getInt("interviewerId"));
                        fillBarras(url2,String.valueOf(jsonObject.getInt("interviewerId")));
                        // preferences.putString("UsuarioID", String.valueOf(jsonObject.getInt("interviewerId")));

                         //preferences.commit();


                        Toast.makeText(LoginActivity.this, "¡Bienvenido! :)", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    }else{
                       Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
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
    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("gymapp",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario",usuario);
        editor.putBoolean("session",true);
        editor.commit();
    }
    private void recuperarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("gymapp",Context.MODE_PRIVATE);
        tvUsuario.setText(preferences.getString("usuario",""));

    }
    private void fillBarras(String url,final String IdUsu) {
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject obj = response.getJSONObject("data");
                    preferences = getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
                    preferences.putString("CorreoBarra", obj.getString("email"));
                    preferences.putString("NombreBarra", obj.getString("name"));
                    preferences.putString("ImagenBarra", obj.getString("image"));
                    preferences.putString("UsuarioID", IdUsu);
                    preferences.commit();
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
