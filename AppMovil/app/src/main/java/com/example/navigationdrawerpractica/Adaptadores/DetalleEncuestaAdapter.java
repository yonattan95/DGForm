package com.example.navigationdrawerpractica.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Entidades.Encuestas;
import com.example.navigationdrawerpractica.Interfaces.Encuesta;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class DetalleEncuestaAdapter extends RecyclerView.Adapter<DetalleEncuestaAdapter.ViewHolder> {

    Context context;
    ArrayList<Encuestas> lista;

    public DetalleEncuestaAdapter(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public DetalleEncuestaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_encuesta,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetalleEncuestaAdapter.ViewHolder viewHolder, int i) {
        final Encuestas obj = lista.get(i);
        viewHolder.tvNombre.setText(""+obj.getNombre());
        viewHolder.tvDetalle.setText(""+obj.getDetalle());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDetalle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreDetPed);
            tvDetalle = itemView.findViewById(R.id.tvPrecioDetPed);
        }
    }
    public void fillDetalle(ArrayList<Encuestas> listaDet){
        this.lista.clear();
        this.lista.addAll(listaDet);
        notifyDataSetChanged();
    }
}
