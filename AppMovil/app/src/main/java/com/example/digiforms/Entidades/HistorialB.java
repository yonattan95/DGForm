package com.example.digiforms.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class HistorialB implements Parcelable {
    private String Nombre,Detalle,FechaIni,FechaFin;
    private int cTotal;

    public HistorialB() {
    }

    public HistorialB(String nombre, String detalle,String fechaIni,String fechaFin,int ctotal) {
        Nombre = nombre;
        Detalle = detalle;
        FechaIni = fechaIni;
        FechaFin = fechaFin;
        cTotal = ctotal;
    }

    protected HistorialB(Parcel in) {
        Nombre = in.readString();
        Detalle = in.readString();
        FechaIni  = in.readString();
        FechaFin = in.readString();
        cTotal = in.readInt();
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

    public String getFechaIni() {
        return FechaIni;
    }

    public void setFechaIni(String fechaIni) {
        FechaIni = fechaIni;
    }

    public String getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(String fechaFin) {
        FechaFin = fechaFin;
    }

    public int getcTotal() {
        return cTotal;
    }

    public void setcTotal(int cTotal) {
        this.cTotal = cTotal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Nombre);
        dest.writeString(Detalle);
        dest.writeString(FechaIni);
        dest.writeString(FechaFin);
        dest.writeInt(cTotal);
    }
}
