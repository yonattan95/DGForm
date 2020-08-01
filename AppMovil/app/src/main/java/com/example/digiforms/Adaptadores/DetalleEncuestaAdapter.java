package com.example.digiforms.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.digiforms.Entidades.Encuestas;
import com.example.digiforms.Interfaces.Encuesta;
import com.example.digiforms.R;
import com.google.gson.JsonIOException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetalleEncuestaAdapter extends RecyclerView.Adapter<DetalleEncuestaAdapter.ViewHolder> {

    Context context;
    ArrayList<Encuestas> lista;
    int idquiz;
    private String Token,UsuarioID;
    RequestQueue requestQueue;

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
        final String idForm;
        requestQueue = Volley.newRequestQueue(context);
        viewHolder.tvNombre.setText(""+obj.getNombre());
        viewHolder.tvDetalle.setText(""+obj.getDetalle());
        viewHolder.tvcategoria.setText(""+obj.getCategoria());
        idForm = String.valueOf(obj.getIdForm());
        SharedPreferences sharedPreferences2 = context.getSharedPreferences("gymapp", Context.MODE_PRIVATE);
        Token = sharedPreferences2.getString("Token", "");
        UsuarioID = sharedPreferences2.getString("UsuarioID","");
        viewHolder.cvDetalleEncuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    validar(sharedPreferences2.getInt("IdQuiz", 0),idForm,sharedPreferences2.getString("IdForm",""),sharedPreferences2.getInt("Atras", 0),Integer.valueOf(UsuarioID));
//                SharedPreferences.Editor sharedPreferences = context.getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
//                sharedPreferences.putString("Pregunta", "1");
//                sharedPreferences.putString("IdForm",idForm);
//                sharedPreferences.commit();
                    //context.startActivity(new Intent(context, Encuesta.class));
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDetalle,tvcategoria;
        CardView cvDetalleEncuesta;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreDetPed);
            tvDetalle = itemView.findViewById(R.id.tvPrecioDetPed);
            tvcategoria = itemView.findViewById(R.id.tvCategoria);
            cvDetalleEncuesta = itemView.findViewById(R.id.cvEncuetas);

        }
    }
    public void fillDetalle(ArrayList<Encuestas> listaDet){
        this.lista.clear();
        this.lista.addAll(listaDet);
        notifyDataSetChanged();
    }
    public void validar(int Idq, String idF,String vIdF,int NumeroAtras,int IDUSU){

        SharedPreferences.Editor sharedPreferences = context.getSharedPreferences("gymapp", Context.MODE_PRIVATE).edit();
        if (Idq == 0 || Integer.valueOf(idF) != Integer.valueOf(vIdF) ){
            int prueba = Integer.valueOf(idF);
            Map<String,Object> mapEditText = new HashMap<>();
            mapEditText.put("form",prueba);
            mapEditText.put("interviewer",IDUSU);
            JSONObject jsonObject = new JSONObject(mapEditText);
            String url ="http://dgform.ga/forms/quiz";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject Objx = response.getJSONObject("data");
                        idquiz = Objx.getInt("quizId");
                        sharedPreferences.putInt("IdQuiz",idquiz);
                        sharedPreferences.putString("IdForm",idF);
                        sharedPreferences.putString("NumeroPregunta","0");
                        sharedPreferences.putString("PreguntaFinal","0");
                        sharedPreferences.putString("Pregunta", "1");
                        sharedPreferences.putInt("Atras",0);
                        sharedPreferences.commit();
                        context.startActivity(new Intent(context, Encuesta.class));
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            })
            {
                public Map getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                    headers.put("Authorization","Bearer " + Token);
                    return headers;
                }
            };
            requestQueue.add(request);
        }else{
            idquiz =Idq;
            sharedPreferences.putInt("IdQuiz",idquiz);
            sharedPreferences.putString("IdForm",idF);
            sharedPreferences.putString("NumeroPregunta","0");
            sharedPreferences.putString("PreguntaFinal","0");
            //sharedPreferences.putString("Pregunta", "1");
            if (NumeroAtras >= 1){
                sharedPreferences.putInt("Atras",NumeroAtras);
            }else{
                sharedPreferences.putInt("Atras",0);
            }
            sharedPreferences.commit();
            context.startActivity(new Intent(context, Encuesta.class));
        }
    }
}
