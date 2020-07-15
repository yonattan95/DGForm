package com.example.navigationdrawerpractica.Interfaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.navigationdrawerpractica.R;


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
                    String url = "http://dgform.ga/auth/login_interviewer";
                    validarUsuario(url);
                }
            }
        });
    }

    private  static  String token;

    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty())
                {
                    guardarPreferencias();
                    Toast.makeText(LoginActivity.this, "Ingreso :D", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Usuario o Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
           @Override
           protected Map<String, String> getParams() throws AuthFailureError{
               Map<String, String> parametros = new HashMap<String, String>();
               parametros.put("username",usuario);
               parametros.put("password",tvPassword.getText().toString());
//               parametros.put("username",tvUsuario.getText().toString());
//               parametros.put("password",tvPassword.getText().toString());
               return parametros;
           }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
