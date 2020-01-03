package com.junrrein.proyectofinal;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String nombre;
    private HashMap<String, Boolean> creados = new HashMap<>();
    private HashMap<String, Boolean> suscriptos = new HashMap<>();

    public Usuario() {
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
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
