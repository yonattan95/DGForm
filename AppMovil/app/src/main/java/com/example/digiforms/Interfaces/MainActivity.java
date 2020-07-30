package com.example.digiforms.Interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.digiforms.Entidades.Persona;
import com.example.digiforms.Fragments.DetallePersonaFragment;
import com.example.digiforms.Fragments.Detalle_HistorialFragment;
import com.example.digiforms.Fragments.EncuestaFragment;
import com.example.digiforms.Fragments.GraficoFragment;
import com.example.digiforms.Fragments.MainFragment;
import com.example.digiforms.Fragments.PerfilFragment;
import com.example.digiforms.R;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iComunicaFragments{

    DrawerLayout drawerLayout;
    private RequestQueue mQueue;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    SharedPreferences preferences3;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //Variables del fragment Detalle
    DetallePersonaFragment detallePersonaFragment;
    TextView tvDatTotCom,tvDatTotPen ,tvNombBarra,tvCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        tvDatTotPen  =findViewById(R.id.tvDatTotPen);
        tvDatTotCom=findViewById(R.id.tvDatTotCom);

        mQueue = Volley.newRequestQueue(this);

        //Lo siguiente se implementa luego de haber implementado NavigationView.OnNavigationItemSelectedListener

        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //Cargar fragment principal en la actividad
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment,new EncuestaFragment());
        fragmentTransaction.commit();
        preferences3 = this.getSharedPreferences("gymapp", Context.MODE_PRIVATE);
        View header = navigationView.getHeaderView(0);
        tvNombBarra = header.findViewById(R.id.tvNomBarras);
        tvCorreo = header.findViewById(R.id.tvCorreo);
        ImageView imgBarra = header.findViewById(R.id.imgBarras);

        tvNombBarra.setText(preferences3.getString("NombreBarra",""));
        tvCorreo.setText(preferences3.getString("CorreoBarra",""));
        // Picasso.with(this).load(preferences3.getString("ImagenBarra","")).fit().centerInside().into(imgBarra);
        // Picasso.with(this).load(preferences3.getString("ImagenBarra","")).resize(90,90).into(imgBarra);
        fillLista();
        fillLista2();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //Para cerrar automaticamente el menu

        SharedPreferences.Editor editor = getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
        drawerLayout.closeDrawer(GravityCompat.START);
        if(menuItem.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new MainFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.personas){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
          //  fragmentTransaction.replace(R.id.container_fragment,new PersonasFragment());
            fragmentTransaction.replace(R.id.container_fragment,new EncuestaFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.gestion){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new Detalle_HistorialFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.home2){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            editor.putString("completos",tvDatTotCom.getText().toString());
            editor.putString("pendientes",tvDatTotPen.getText().toString());
            editor.apply();
            fragmentTransaction.replace(R.id.container_fragment,new GraficoFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.home3){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new PerfilFragment());
            //fragmentTransaction.replace(R.id.container_fragment,new Detalle_HistorialFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.personas2){
            editor.clear();
            editor.commit();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        return false;
    }


    @Override
    public void enviarPersona(Persona persona) {
        //Gracias a haber implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
        //o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio
        detallePersonaFragment = new DetallePersonaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio = new Bundle();
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto",persona);
        detallePersonaFragment.setArguments(bundleEnvio);

        //Cargar fragment en el activity
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, detallePersonaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        /*
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.container_fragment, detallePersonaFragment)
                 .addToBackStack(null).commit();
        */
        //***Luego pasar a programar al fragmentdetalle
    }

    private void fillLista() {
        String url = "http://dgform.ga/forms/complete";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            tvDatTotCom.setText(String.valueOf(jsonArray.length()));

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
        mQueue.add(request);
    }

    private void fillLista2() {
        String url2 = "http://dgform.ga/forms/pending";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            tvDatTotPen.setText(String.valueOf(jsonArray.length()));
                            // pendientes =jsonArray.length();
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
        mQueue.add(request);
    }

}