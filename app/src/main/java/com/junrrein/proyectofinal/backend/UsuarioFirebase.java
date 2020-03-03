package com.junrrein.proyectofinal.backend;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;

public class UsuarioFirebase {
    public String nombre;
    public HashMap<String, Boolean> dispositivos = new HashMap<>();

    UsuarioFirebase() {
    }

    UsuarioFirebase(Usuario usuario) {
        nombre = usuario.getNombreApellido();

        for (String idDispositivo : usuario.getIdDispositivos())
            dispositivos.put(idDispositivo, true);
    }

    HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("dispositivos", dispositivos);

        return result;
    }

    @Override
    @NonNull
    @Exclude
    public String toString() {
        return "UsuarioFirebase{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
