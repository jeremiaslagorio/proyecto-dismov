package com.junrrein.proyectofinal.backend;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;

public class UsuarioFirebase {
    public String nombre;

    UsuarioFirebase() {
    }

    UsuarioFirebase(Usuario usuario) {
        nombre = usuario.getNombreApellido();
    }

    HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);

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
