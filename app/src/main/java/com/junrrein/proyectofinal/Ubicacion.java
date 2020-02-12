package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Ubicacion implements Serializable {
    Double latitud;
    Double longitud;

    Ubicacion(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @NonNull
    @Override
    public String toString() {
        return "Ubicacion{" +
                "latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
