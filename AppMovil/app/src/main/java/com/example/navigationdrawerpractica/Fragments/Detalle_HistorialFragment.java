package com.example.navigationdrawerpractica.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.navigationdrawerpractica.Adaptadores.DetalleHistorialA;
import com.example.navigationdrawerpractica.Adaptadores.DetalleHistorialB;
import com.example.navigationdrawerpractica.Entidades.HistorialA;
import com.example.navigationdrawerpractica.Entidades.HistorialB;
import com.example.navigationdrawerpractica.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Detalle_HistorialFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private RecyclerView rvDetalle,rvDetalle2;
    private DetalleHistorialA adapter;
    private DetalleHistorialB adapter2;
    private ArrayList<HistorialA> lista;
    private ArrayList<HistorialB> lista2;
    private RequestQueue mQueue;

    public Detalle_HistorialFragment() {
        // Required empty public constructor
    }

    public static Detalle_HistorialFragment newInstance(String param1, String param2) {
        Detalle_HistorialFragment fragment = new Detalle_HistorialFragment();
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
        View view = inflater.inflate(R.layout.fragment_detalle__historial,container,false);
        rvDetalle = view.findViewById(R.id.rvDetalleHistorialA);
        rvDetalle.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DetalleHistorialA(getActivity());
        rvDetalle.setAdapter(adapter);
        lista = new ArrayList<>();
        mQueue = Volley.newRequestQueue(getActivity());
        rvDetalle2 = view.findViewById(R.id.rvDetalleHistorialB);
        rvDetalle2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter2 = new DetalleHistorialB(getActivity());
        rvDetalle2.setAdapter(adapter2);
        lista2 = new ArrayList<>();
        fillLista();
        fillLista2();
        return view;
    }
    private void fillLista() {
        String url = "http://dgform.ga/forms/complete";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                lista.add(new HistorialA(obj.getString("name"),
                                        obj.getString("description")));
                            }
                            adapter.fillDetalle(lista);
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
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                lista2.add(new HistorialB(obj.getString("name"),
                                        obj.getString("description")));
                            }
                            adapter2.fillDetalle(lista2);
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