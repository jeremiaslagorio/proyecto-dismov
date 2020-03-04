package com.junrrein.proyectofinal.backend;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;

public class EventoFirebase {
    public String nombre;
    public String creador;
    public String organizador;
    public Double latitud;
    public Double longitud;
    public String fechaHora;
    public String descripcion;
    public HashMap<String, Boolean> interesados = new HashMap<>();
    public HashMap<String, Boolean> asisten = new HashMap<>();
    public HashMap<String, Boolean> dislikes = new HashMap<>();

    public EventoFirebase() {
    }

    public EventoFirebase(Evento evento) {
        nombre = evento.getNombre();
        creador = evento.getIdUsuarioCreador();
        organizador = evento.getOrganizador();
        latitud = evento.getUbicacion().latitud;
        longitud = evento.getUbicacion().longitud;
        descripcion = evento.getDescripcion();

        String fecha = evento.getFechaInicio().toString();
        String hora = evento.getHoraInicio().toString();
        fechaHora = fecha + " " + hora;

        for (String idUsuario : evento.getIdUsuariosInteresados())
            interesados.put(idUsuario, true);

        for (String idUsuario : evento.getIdUsuariosAsistentes())
            asisten.put(idUsuario, true);

        for (String idUsuario : evento.getIdUsuariosDislikes())
            dislikes.put(idUsuario, true);
    }

    HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("creador", creador);
        result.put("organizador", organizador);
        result.put("latitud", latitud);
        result.put("longitud", longitud);
        result.put("fechaHora", fechaHora);
        result.put("descripcion", descripcion);
        result.put("interesados", interesados);
        result.put("asisten", asisten);
        result.put("dislikes", dislikes);

        return result;
    }

    @Override
    @NonNull
    @Exclude
    public String toString() {
        return "EventoFirebase{" +
                "nombre='" + nombre + '\'' +
                ", creador='" + creador + '\'' +
                ", organizador='" + organizador + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", fechaHora='" + fechaHora + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", interesados=" + interesados +
                ", asisten=" + asisten +
                ", dislikes=" + dislikes +
                '}';
    }
}
