package com.junrrein.proyectofinal.backend;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;

public class EventoFirebase {
    public String nombre;
    public String creador;
    public Double latitud;
    public Double longitud;
    public String fechaHora;
    public Integer duracion;
    public String descripcion;
    public String tipo;
    public HashMap<String, Boolean> interesados = new HashMap<>();
    public HashMap<String, Boolean> suscriptos = new HashMap<>();
    public HashMap<String, Boolean> dislikes = new HashMap<>();

    public EventoFirebase() {
    }

    public EventoFirebase(Evento evento) {
        nombre = evento.getNombre();
        creador = evento.getIdUsuarioCreador();
        latitud = evento.getUbicacion().latitud;
        longitud = evento.getUbicacion().longitud;
        descripcion = evento.getDescripcion();
        tipo = evento.getTipo();

        String fecha = evento.getFechaInicio().toString();
        String hora = evento.getHoraInicio().toString();
        fechaHora = fecha + " " + hora;
        duracion = evento.getDuracion();

        for (String idUsuario : evento.getIdUsuariosInteresados())
            interesados.put(idUsuario, true);

        for (String idUsuario : evento.getIdUsuariosAsistentes())
            suscriptos.put(idUsuario, true);

        for (String idUsuario : evento.getIdUsuariosDislikes())
            dislikes.put(idUsuario, true);
    }

    HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("creador", creador);
        result.put("latitud", latitud);
        result.put("longitud", longitud);
        result.put("fechaHora", fechaHora);
        result.put("duracion", duracion);
        result.put("descripcion", descripcion);
        result.put("tipo", tipo);
        result.put("interesados", interesados);
        result.put("suscriptos", suscriptos);
        result.put("dislikes", dislikes);

        return result;
    }
}
