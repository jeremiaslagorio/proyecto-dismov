package com.junrrein.proyectofinal;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Evento {

    private String id;
    private String nombre;
    private String idUsuarioCreador;
    private Ubicacion ubicacion;
    private OffsetDateTime fechaHoraInicio;
    private String descripcion;
    private ArrayList<String> idUsuariosSuscriptos;
    private ArrayList<String> idUsuariosDislikes;

    Evento(String id,
                   String nombre,
                   Usuario usuarioCreador,
                   Ubicacion ubicacion,
                   OffsetDateTime fechaHoraInicio) {
        this.id = id;
        this.nombre = nombre;
        idUsuarioCreador = usuarioCreador.getId();
        this.ubicacion = ubicacion;
        this.fechaHoraInicio = fechaHoraInicio;
        idUsuariosSuscriptos = new ArrayList<>();
        idUsuariosDislikes = new ArrayList<>();

        usuarioCreador.agregarEventoCreado(this);
    }

    Evento(String id, EventoPojo eventoPojo) {
        this.id = id;
        nombre = eventoPojo.nombre;
        idUsuarioCreador = eventoPojo.creador;
        ubicacion = new Ubicacion(eventoPojo.latitud, eventoPojo.longitud);
        fechaHoraInicio = OffsetDateTime.ofInstant(
                Instant.ofEpochSecond(eventoPojo.fechaHora),
                ZoneId.of("America/Argentina/Buenos_Aires"));
        descripcion = eventoPojo.descripcion;
        idUsuariosSuscriptos = new ArrayList<>(eventoPojo.suscriptos.keySet());
        idUsuariosDislikes = new ArrayList<>(eventoPojo.dislikes.keySet());
    }

    String getId() {
        return id;
    }

    String getNombre() {
        return nombre;
    }

    String getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    Ubicacion getUbicacion() {
        return ubicacion;
    }

    OffsetDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    String getDescripcion() {
        return descripcion;
    }

    ArrayList<String> getIdUsuariosSuscriptos() {
        return idUsuariosSuscriptos;
    }

    ArrayList<String> getIdUsuariosDislikes() {
        return idUsuariosDislikes;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", idUsuarioCreador='" + idUsuarioCreador + '\'' +
                ", ubicacion=" + ubicacion +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", descripcion='" + descripcion + '\'' +
                ", idUsuariosSuscriptos=" + idUsuariosSuscriptos +
                ", idUsuariosDislikes=" + idUsuariosDislikes +
                '}';
    }
}
