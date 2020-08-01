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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
//        viewHolder.tvFechaIni.setText(""+obj.getNombre());
//        viewHolder.TvFechaFin.setText(""+obj.getDetalle());
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateOjb  = format1.parse(obj.getFechaIni());
            SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
            viewHolder.tvFechaIni.setText(format2.format(dateOjb));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //viewHolder.tvFechaIni.setText(""+format1.parse(obj.getFechaIni().toString()));
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateOjb  = format3.parse(obj.getFechaFin());
            SimpleDateFormat format4 = new SimpleDateFormat("dd/MM/yyyy");
            viewHolder.TvFechaFin.setText(format4.format(dateOjb));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.cTotal.setText(""+obj.getcTotal());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDetalle,tvFechaIni,TvFechaFin,cTotal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFechaIni = itemView.findViewById(R.id.tvNombreHistorialB);
            TvFechaFin = itemView.findViewById(R.id.tvDetalleHistorialB);
            cTotal = itemView.findViewById(R.id.tvCantidadB);
        }
    }
    public void fillDetalle(ArrayList<HistorialB> listaDet){
        this.lista.clear();
        this.lista.addAll(listaDet);
        notifyDataSetChanged();
    }
}
