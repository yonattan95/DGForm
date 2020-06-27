package com.tenorio.digitform;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.tenorio.digitform.Adaptadores.AdapterEncuesta;
import com.tenorio.digitform.modulos.entidadEncuesta;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EncuestaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EncuestaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView lvItems;
    private AdapterEncuesta adaptador;
    RecyclerView recyclerViewEncuesta;
    ArrayList<entidadEncuesta> listaEncuesta;

    public EncuestaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EncuestaFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_encuesta,container);
        recyclerViewEncuesta = view.findViewById(R.id.lvLista);
        listaEncuesta = new ArrayList<>();
        //cargar la lista
        cargarLista();
        //mostrar data
        mostrarData();
        return view;
    }

    public void cargarLista(){
        listaEncuesta.add(new entidadEncuesta(R.drawable.encuestas_persona,"Encuesta1","Prueba 1"));
        listaEncuesta.add(new entidadEncuesta(R.drawable.encuestas_persona,"Encuesta2","Prueba 2"));
        listaEncuesta.add(new entidadEncuesta(R.drawable.encuestas_persona,"Encuesta3","Prueba 3"));
        listaEncuesta.add(new entidadEncuesta(R.drawable.encuestas_persona,"Encuesta4","Prueba 4"));
        listaEncuesta.add(new entidadEncuesta(R.drawable.encuestas_persona,"Encuesta5","Prueba 5"));
    }
    public void mostrarData(){
        recyclerViewEncuesta.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptador = new AdapterEncuesta(getContext(), listaEncuesta);
        recyclerViewEncuesta.setAdapter(adaptador);

        adaptador.setOnClickLister(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo =  listaEncuesta.get(recyclerViewEncuesta.getChildAdapterPosition(v)).getTitulo();
                Toast.makeText(getContext(), "Selecciono: " + titulo, Toast.LENGTH_SHORT).show();
            }
        });
    }
}