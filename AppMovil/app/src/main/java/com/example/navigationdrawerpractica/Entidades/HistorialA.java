package com.example.navigationdrawerpractica.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class HistorialA implements Parcelable {
    private String Nombre,Detalle;

    public HistorialA() {
    }

    public HistorialA(String nombre, String detalle) {
        Nombre = nombre;
        Detalle = detalle;
    }

    protected HistorialA(Parcel in) {
        Nombre = in.readString();
        Detalle = in.readString();
    }

    public static final Creator<HistorialA> CREATOR = new Creator<HistorialA>() {
        @Override
        public HistorialA createFromParcel(Parcel in) {
            return new HistorialA(in);
        }

        @Override
        public HistorialA[] newArray(int size) {
            return new HistorialA[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Nombre);
        dest.writeString(Detalle);
    }
}
