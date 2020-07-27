package com.example.navigationdrawerpractica.Entidades;

import android.os.Parcel;
import android.os.Parcelable;
// implements Parcelable
public class Categorias{
    private String NombreCat;
    private int IdCat;

//    protected Categorias(Parcel in) {
//        IdCat = in.readInt();
//        NombreCat = in.readString();
//    }
//
//    public static final Creator<Categorias> CREATOR = new Creator<Categorias>() {
//        @Override
//        public Categorias createFromParcel(Parcel in) {
//            return new Categorias(in);
//        }
//
//        @Override
//        public Categorias[] newArray(int size) {
//            return new Categorias[size];
//        }
//    };


    public Categorias() {
    }

//    public Categorias(String nombreCat, int idCat) {
//        NombreCat = nombreCat;
//        IdCat = idCat;
//    }
    public Categorias(String nombreCat) {
        NombreCat = nombreCat;
    }
//    public int getIdCat() {
//        return IdCat;
//    }
//
//    public void setIdCat(int idCat) {
//        IdCat = idCat;
//    }

    public String getNombreCat() {
        return NombreCat;
    }

    public void setNombreCat(String nombreCat) {
        NombreCat = nombreCat;
    }
    @Override
    public String toString(){
        return NombreCat;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }

//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(IdCat);
//        dest.writeString(NombreCat);
//    }
}
