package com.example.digiforms.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digiforms.Adaptadores.AdapterHistorial;
import com.example.digiforms.Entidades.Historial;
import com.example.digiforms.Interfaces.iComunicaFragments;
import com.example.digiforms.R;

import java.util.ArrayList;

public class HistorailFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;


    AdapterHistorial adapterPersonas;
    RecyclerView recyclerViewPersonas;
    ArrayList<Historial> listaPersonas;

    EditText txtnombre;

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.historial_fragment,container,false);
        txtnombre = view.findViewById(R.id.txtnombre);

        recyclerViewPersonas = view.findViewById(R.id.recyclerViewHistorial);
        listaPersonas = new ArrayList<>();
        cargarLista();
        mostrarData();
        return view;
    }
    public void cargarLista(){
        listaPersonas.add(new Historial("Gohan","31-05-1994",R.drawable.encuestas_persona));
        listaPersonas.add(new Historial("Goku","31-05-1994",R.drawable.encuestas_persona));
        listaPersonas.add(new Historial("Goten","31-05-1994",R.drawable.encuestas_persona));
        listaPersonas.add(new Historial("Krilin","31-05-1994",R.drawable.encuestas_persona));
        listaPersonas.add(new Historial("Picoro","31-05-1994",R.drawable.picoro_cara5));
        listaPersonas.add(new Historial("Trunks","31-05-1994",R.drawable.trunks_cara6));
        listaPersonas.add(new Historial("Vegueta","31-05-1994",R.drawable.vegueta_cara7));
    }
    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPersonas = new AdapterHistorial(getContext(), listaPersonas);
        recyclerViewPersonas.setAdapter(adapterPersonas);

//        adapterPersonas.setOnclickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               String nombre = listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)).getNombre();
//               txtnombre.setText(nombre);
//               Toast.makeText(getContext(), "Seleccionó: "+listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
//                //enviar mediante la interface el objeto seleccionado al detalle
//                //se envia el objeto completo
//                //se utiliza la interface como puente para enviar el objeto seleccionado
//               // interfaceComunicaFragments.enviarPersona(listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)));
//                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
//            }
//        });
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        //esto es necesario para establecer la comunicacion entre la lista y el detalle
//        //si el contexto que le esta llegando es una instancia de un activity:
//        if(context instanceof Activity){
//        //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
//            this.actividad= (Activity) context;
//            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
//            interfaceComunicaFragments= (iComunicaFragments) this.actividad;
//            //esto es necesario para establecer la comunicacion entre la lista y el detalle
//        }
//
//       /* if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }*/
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
