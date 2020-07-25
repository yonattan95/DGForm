package com.tenorio.digitform.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tenorio.digitform.R;
import com.tenorio.digitform.modulos.entidadEncuesta;

import java.util.ArrayList;

public class AdapterEncuesta extends RecyclerView.Adapter<AdapterEncuesta.ViewHolder> implements View.OnClickListener {
    LayoutInflater inflater;
    ArrayList<entidadEncuesta> model;

    //listener
    private View.OnClickListener listener;
    public AdapterEncuesta(Context context, ArrayList<entidadEncuesta> model){
        this.inflater = LayoutInflater.from(context);
        this.model=model;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_encuesta,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }
    public void setOnClickLister(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String titulo = model.get(position).getTitulo();
        String contenido = model.get(position).getContenido();
        int imagen = model.get(position).getImgFoto();
        holder.Titulo.setText(titulo);
        holder.Contenido.setText(contenido);
        holder.imagen.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Titulo, Contenido;
        ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo = itemView.findViewById(R.id.encTitulo);
            Contenido = itemView.findViewById(R.id.encContenido);
            imagen = itemView.findViewById(R.id.imgFoto);
        }
    }
}
