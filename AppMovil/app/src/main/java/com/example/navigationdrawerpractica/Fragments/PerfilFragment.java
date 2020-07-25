package com.example.navigationdrawerpractica.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.navigationdrawerpractica.Entidades.Usuario;
import com.example.navigationdrawerpractica.Interfaces.LoginActivity;
import com.example.navigationdrawerpractica.Interfaces.MainActivity;
import com.example.navigationdrawerpractica.R;
import com.squareup.picasso.Picasso;


import org.json.JSONObject;


public class PerfilFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    SharedPreferences preferences;
    ImageView imagen_PerfilUsuario;

    RequestQueue request;
    TextView tvCod_usu,tvemail_usuario,tvnombre_usuario,apellidos;
    JsonObjectRequest jsonObjectRequest;
    ProgressDialog progreso;

    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        tvCod_usu = view.findViewById(R.id.tvCod_usuario);
        tvemail_usuario = view.findViewById(R.id.tvemail_usuario);
        tvnombre_usuario = view.findViewById(R.id.tvnombre_usuario);
        imagen_PerfilUsuario = view.findViewById(R.id.imagen_PerfilUsuario);
        apellidos = view.findViewById(R.id.tvApellidos);
        preferences = ((MainActivity)getActivity()).getSharedPreferences("gymapp", Context.MODE_PRIVATE);
        request = Volley.newRequestQueue(getContext());
        fillLista();
        return view;
    }
    private void fillLista() {
        progreso = new ProgressDialog(getContext());
        progreso.setMessage("Usuario...");
        progreso.show();


        String url = "http://dgform.ga/interviewers/"+ preferences.getString("UsuarioID","");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(),"No se pudo registrar"+error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Usuario miUsuario = new Usuario();
        JSONObject jsonObject = response.optJSONObject("data");
        miUsuario.setxNombre(jsonObject.optString("name"));
        miUsuario.setEmail(jsonObject.optString("email"));
        miUsuario.setUsuario(jsonObject.optString("username"));
        miUsuario.setApellidoA(jsonObject.optString("surname1"));
        miUsuario.setApellidoP(jsonObject.optString("surname2"));
        miUsuario.setImagen(jsonObject.optString("image"));
        tvCod_usu.setText(miUsuario.getUsuario());
        tvemail_usuario.setText(miUsuario.getEmail());
        tvnombre_usuario.setText(miUsuario.getxNombre());
        apellidos.setText(miUsuario.getApellidoA() +" " + miUsuario.getApellidoP());
        Picasso.with(getActivity()).load(miUsuario.getImagen()).fit().centerInside().into(imagen_PerfilUsuario);
    }
}