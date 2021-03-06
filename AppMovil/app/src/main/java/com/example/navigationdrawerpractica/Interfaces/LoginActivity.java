package com.example.navigationdrawerpractica.Interfaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.os.Bundle;
import android.text.LoginFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.navigationdrawerpractica.R;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class LoginActivity extends AppCompatActivity {

    private TextView tvUsuario, tvPassword ;
    private Button button;
    String usuario,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvUsuario = findViewById(R.id.textView1);
        tvPassword = findViewById(R.id.textView2);
        button = findViewById(R.id.button);

        recuperarPreferencias();
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
                        SharedPreferences preferences = getSharedPreferences("gymapp", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("UsuarioID", String.valueOf(jsonObject.getInt("interviewerId")));
                        editor.commit();
                        guardarPreferencias();
                        Toast.makeText(LoginActivity.this, "Ingreso :D", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Usuario o Contraseña incorrecta", Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
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

}
