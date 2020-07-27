package com.example.digiforms.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.digiforms.Entidades.HistorialB;
import com.example.digiforms.R;

import java.util.ArrayList;

public class DetalleHistorialB extends RecyclerView.Adapter<DetalleHistorialB.ViewHolder>{
    Context context;
    ArrayList<HistorialB> lista;

    public DetalleHistorialB(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public DetalleHistorialB.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_historial_b,viewGroup,false);
        return new DetalleHistorialB.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final HistorialB obj = lista.get(i);
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
            tvNombre = itemView.findViewById(R.id.tvNombreHistorialB);
            tvDetalle = itemView.findViewById(R.id.tvDetalleHistorialB);
        }
    }
    public void fillDetalle(ArrayList<HistorialB> listaDet){
        this.lista.clear();
        this.lista.addAll(listaDet);
        notifyDataSetChanged();
    }
}
