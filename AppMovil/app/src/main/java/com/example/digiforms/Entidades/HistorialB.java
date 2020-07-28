package com.example.digiforms.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class HistorialB implements Parcelable {
    private String Nombre,Detalle;

    public HistorialB() {
    }

    public HistorialB(String nombre, String detalle) {
        Nombre = nombre;
        Detalle = detalle;
    }

    protected HistorialB(Parcel in) {
        Nombre = in.readString();
        Detalle = in.readString();
    }

    public static final Creator<HistorialB> CREATOR = new Creator<HistorialB>() {
        @Override
        public HistorialB createFromParcel(Parcel in) {
            return new HistorialB(in);
        }

        @Override
        public HistorialB[] newArray(int size) {
            return new HistorialB[size];
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
