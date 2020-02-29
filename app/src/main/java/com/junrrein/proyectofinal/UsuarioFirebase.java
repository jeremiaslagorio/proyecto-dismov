package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;

public class UsuarioFirebase {
    public String nombre;
    public HashMap<String, Boolean> creados = new HashMap<>();
    public HashMap<String, Boolean> interesado = new HashMap<>();
    public HashMap<String, Boolean> asiste = new HashMap<>();

    UsuarioFirebase() {
    }

    UsuarioFirebase(Usuario usuario) {
        nombre = usuario.getNombreApellido();

        for (String idEvento : usuario.getIdEventosCreados())
            creados.put(idEvento, true);

        for (String idEvento : usuario.getIdEventosInteresado())
            interesado.put(idEvento, true);

        for (String idEvento : usuario.getIdEventosAsiste())
            asiste.put(idEvento, true);
    }

    HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("creados", creados);
        result.put("interesado", interesado);
        result.put("asiste", asiste);

        return result;
    }

    @Override
    @NonNull
    @Exclude
    public String toString() {
        return "UsuarioFirebase{" +
                "nombre='" + nombre + '\'' +
                ", creados=" + creados +
                ", interesado=" + interesado +
                ", asiste=" + asiste +
                '}';
    }
}
