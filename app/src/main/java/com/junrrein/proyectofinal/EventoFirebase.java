package com.junrrein.proyectofinal;

import com.google.firebase.database.Exclude;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class EventoFirebase {
    public String nombre;
    public String creador;
    public Double latitud;
    public Double longitud;
    public String fecha;
    public String hora;
    public String descripcion;
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

        fecha = evento.getFechaInicio().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        hora = evento.getHoraInicio().format(DateTimeFormatter.ofPattern("HH-mm"));

        suscriptos = new HashMap<>();
        for (String idUsuario : evento.getIdUsuariosSuscriptos())
            suscriptos.put(idUsuario, true);

        dislikes = new HashMap<>();
        for (String idUsuario : evento.getIdUsuariosDislikes())
            dislikes.put(idUsuario, true);
    }

    HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("creador", creador);
        result.put("latitud", latitud);
        result.put("longitud", longitud);
        result.put("fecha", fecha);
        result.put("hora", hora);
        result.put("descripcion", descripcion);
        result.put("suscriptos", suscriptos);
        result.put("dislikes", dislikes);

        return result;
    }

    @Override
    @Exclude
    public String toString() {
        return "EventoFirebase{" +
                "nombre='" + nombre + '\'' +
                ", creador='" + creador + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", suscriptos=" + suscriptos +
                ", dislikes=" + dislikes +
                '}';
    }
}
