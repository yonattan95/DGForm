package com.example.digiforms.Interfaces;

import com.example.digiforms.Entidades.Persona;

public interface iComunicaFragments {
    //esta interface se encarga de realizar la comunicacion entre la lista de personas y el detalle
    public void enviarPersona(Persona persona); //se transportara un objeto de tipo persona
    //(En la clase Persona se implementa Serializable para poder transportar un objeteo a otro)
}
