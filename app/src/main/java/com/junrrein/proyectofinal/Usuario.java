package com.junrrein.proyectofinal;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String nombre;
    private HashMap<String, Boolean> creados;
    private HashMap<String, Boolean> suscriptos;

    public Usuario() {
    }

    public Usuario(String nombre,
                   HashMap<String, Boolean> creados,
                   HashMap<String, Boolean> suscriptos) {
        this.nombre = nombre;
        this.creados = creados;
        this.suscriptos = suscriptos;
    }

    public String getNombre() {
        return nombre;
    }

    public HashMap<String, Boolean> getCreados() {
        return creados;
    }

    public HashMap<String, Boolean> getSuscriptos() {
        return suscriptos;
    }

    @Exclude
    Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("creados", creados);
        result.put("suscriptos", suscriptos);

        return result;
    }
}
