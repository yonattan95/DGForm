package com.example.navigationdrawerpractica.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Encuestas implements Parcelable {
    private String Nombre,Detalle,Categoria;

    public Encuestas() {
    }

    public Encuestas(String nombre, String detalle,String categoria) {
        Nombre = nombre;
        Detalle = detalle;
        Categoria = categoria;
    }

    protected Encuestas(Parcel in) {
        Nombre = in.readString();
        Detalle = in.readString();
        Categoria = in.readString();
    }

    public static final Creator<Encuestas> CREATOR = new Creator<Encuestas>() {
        @Override
        public Encuestas createFromParcel(Parcel in) {
            return new Encuestas(in);
        }

        @Override
        public Encuestas[] newArray(int size) {
            return new Encuestas[size];
        }
    };

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Nombre);
        dest.writeString(Detalle);
        dest.writeString(Categoria);
    }
}
