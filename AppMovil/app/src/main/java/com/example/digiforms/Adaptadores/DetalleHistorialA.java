package com.example.digiforms.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digiforms.Entidades.HistorialA;
import com.example.digiforms.R;

import java.util.ArrayList;

public class DetalleHistorialA extends RecyclerView.Adapter<DetalleHistorialA.ViewHolder>{
    Context context;
    ArrayList<HistorialA> lista;

    public DetalleHistorialA(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }
    @NonNull
    @Override
    public DetalleHistorialA.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_historial_a,viewGroup,false);
        return new DetalleHistorialA.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetalleHistorialA.ViewHolder viewHolder, int i) {
        final HistorialA obj = lista.get(i);
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
            tvNombre = itemView.findViewById(R.id.tvNombreHistorialA);
            tvDetalle = itemView.findViewById(R.id.tvDetalleHistorialA);
        }
    }
    public void fillDetalle(ArrayList<HistorialA> listaDet){
        this.lista.clear();
        this.lista.addAll(listaDet);
        notifyDataSetChanged();
    }
}
