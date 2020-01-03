package com.junrrein.proyectofinal;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String nombre;
    private String correo;

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

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("correo", correo);

        return result;
    }
}
