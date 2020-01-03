package com.junrrein.proyectofinal;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String nombre;
    private String correo;
    private HashMap<String, Boolean> creados;
    private HashMap<String, Boolean> suscriptos;

    public Usuario() {
    }

    Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
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

    Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("correo", correo);
        result.put("creados", creados);
        result.put("suscriptos", suscriptos);

        return result;
    }
}
