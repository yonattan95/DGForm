package com.example.digiforms.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class OpcionesPreguntas implements Parcelable {
    private String DescripcionPr,Value;
    public OpcionesPreguntas() {
    }

    public OpcionesPreguntas(String descripcionPr,String value) {
        DescripcionPr = descripcionPr;
        Value = value;
    }
    protected OpcionesPreguntas(Parcel in) {
        DescripcionPr = in.readString();
        Value = in.readString();

    }
    public static final Creator<OpcionesPreguntas> CREATOR = new Creator<OpcionesPreguntas>() {
        @Override
        public OpcionesPreguntas createFromParcel(Parcel in) {
            return new OpcionesPreguntas(in);
        }

        @Override
        public OpcionesPreguntas[] newArray(int size) {
            return new OpcionesPreguntas[size];
        }
    };

    public String getDescripcionPr() {
        return DescripcionPr;
    }

    public void setDescripcionPr(String descripcionPr) {
        DescripcionPr = descripcionPr;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(DescripcionPr);
        dest.writeString(Value);
    }
}
