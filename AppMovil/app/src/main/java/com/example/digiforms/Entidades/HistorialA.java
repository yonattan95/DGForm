package com.example.digiforms.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class HistorialA implements Parcelable {
    private String Nombre,Detalle,FechaIni,FechaFin;
    private int cTotal;


    public HistorialA() {
    }

    public HistorialA(String nombre, String detalle,String fechaIni,String fechaFin, int ctotal) {
        Nombre = nombre;
        Detalle = detalle;
        FechaIni = fechaIni;
        FechaFin = fechaFin;
        cTotal = ctotal;
    }

    protected HistorialA(Parcel in) {
        Nombre = in.readString();
        Detalle = in.readString();
        FechaIni  = in.readString();
        FechaFin = in.readString();
        cTotal = in.readInt();
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
