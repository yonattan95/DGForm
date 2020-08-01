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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
            tvFechaIni = itemView.findViewById(R.id.tvNombreHistorialA);
            TvFechaFin = itemView.findViewById(R.id.tvDetalleHistorialA);
            cTotal = itemView.findViewById(R.id.tvCantidadA);
        }
    }
    public void fillDetalle(ArrayList<HistorialA> listaDet){
        this.lista.clear();
        this.lista.addAll(listaDet);
        notifyDataSetChanged();
    }
}
