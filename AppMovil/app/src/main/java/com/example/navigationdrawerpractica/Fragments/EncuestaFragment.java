package com.example.navigationdrawerpractica.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import com.example.navigationdrawerpractica.Adaptadores.DetalleEncuestaAdapter;
import com.example.navigationdrawerpractica.Entidades.Encuestas;
import com.example.navigationdrawerpractica.Interfaces.Encuesta;
import com.example.navigationdrawerpractica.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EncuestaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EncuestaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView rvDetalle;
    private DetalleEncuestaAdapter adapter;
    private ArrayList<Encuestas> lista;
    private RequestQueue mQueue;
    private View.OnFocusChangeListener mListener;

    public EncuestaFragment() {
        // Required empty public constructor
    }


    public static EncuestaFragment newInstance(String param1, String param2) {
        EncuestaFragment fragment = new EncuestaFragment();
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
        View view = inflater.inflate(R.layout.fragment_encuesta, container, false);
        rvDetalle = view.findViewById(R.id.rvDetalle);
        rvDetalle.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DetalleEncuestaAdapter(getActivity());
        rvDetalle.setAdapter(adapter);
        lista = new ArrayList<>();
        mQueue = Volley.newRequestQueue(getActivity());
        fillLista();
        return view;
    }

    private void fillLista() {
        String url = "http://dgform.ga/forms/pending";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        lista.add(new Encuestas(obj.getString("name"),
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
}
