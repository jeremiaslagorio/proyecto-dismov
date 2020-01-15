package com.junrrein.proyectofinal;

import com.google.firebase.database.Exclude;

import java.util.HashMap;

public class UsuarioFirebase {
    public String nombre;
    public HashMap<String, Boolean> creados = new HashMap<>();
    public HashMap<String, Boolean> suscriptos = new HashMap<>();

    UsuarioFirebase() {
    }

    UsuarioFirebase(Usuario usuario) {
        nombre = usuario.getNombreApellido();

        creados = new HashMap<>();
        for (String idEvento : usuario.getIdEventosCreados())
            creados.put(idEvento, true);

        suscriptos = new HashMap<>();
        for (String idEvento : usuario.getIdEventosSuscriptos())
            suscriptos.put(idEvento, true);
    }

    HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("creados", creados);
        result.put("suscriptos", suscriptos);

        return result;
    }

    @Override
    @Exclude
    public String toString() {
        return "UsuarioFirebase{" +
                "nombre='" + nombre + '\'' +
                ", creados=" + creados +
                ", suscriptos=" + suscriptos +
                '}';
    }
}
