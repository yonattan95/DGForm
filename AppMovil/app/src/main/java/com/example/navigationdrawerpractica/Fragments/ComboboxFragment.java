package com.example.navigationdrawerpractica.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.navigationdrawerpractica.Interfaces.Encuesta;
import com.example.navigationdrawerpractica.Interfaces.MainActivity;
import com.example.navigationdrawerpractica.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComboboxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComboboxFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Button atras,siguiente;

    public ComboboxFragment() {
        // Required empty public constructor
    }

    public static ComboboxFragment newInstance(String param1, String param2) {
        ComboboxFragment fragment = new ComboboxFragment();
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
        View view = inflater.inflate(R.layout.fragment_combobox, container, false);

        atras = view.findViewById(R.id.btnAtras);
        siguiente = view.findViewById(R.id.btnSiguiente);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor sharedPreferences = getActivity().getSharedPreferences("gymapp",Context.MODE_PRIVATE).edit();
                sharedPreferences.putString("Pregunta","1");
                sharedPreferences.commit();
                startActivity(new Intent(getActivity(), Encuesta.class));
                getActivity().onBackPressed();
            }
        });

        return view;
    }
    public interface OnMessageReadListener{
        public void onMessageRead(String Mensaje);
    }

}