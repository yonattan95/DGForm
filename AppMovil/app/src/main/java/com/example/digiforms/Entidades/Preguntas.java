package com.example.digiforms.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Preguntas implements Parcelable {
    private String Descripcion;
    private int TipoPregunta,NumPregunta,IdPregunta;

    public Preguntas() {
    }

    public Preguntas(String descripcion,int tipoPregunta,int numPregunta,int idPregunta) {
        Descripcion = descripcion;
        TipoPregunta = tipoPregunta;
        NumPregunta = numPregunta;
        IdPregunta = idPregunta;
    }

    protected Preguntas(Parcel in) {
        Descripcion = in.readString();
        TipoPregunta = in.readInt();
        NumPregunta = in.readInt();
        IdPregunta = in.readInt();
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getTipoPregunta() {
        return TipoPregunta;
    }

    public void setTipoPregunta(int tipoPregunta) {
        TipoPregunta = tipoPregunta;
    }

    public int getNumPregunta() {
        return NumPregunta;
    }

    public void setNumPregunta(int numPregunta) {
        NumPregunta = numPregunta;
    }

    public int getIdPregunta() {
        return IdPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        IdPregunta = idPregunta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Descripcion);
        dest.writeInt(NumPregunta);
        dest.writeInt(TipoPregunta);
        dest.writeInt(IdPregunta);
    }
}
