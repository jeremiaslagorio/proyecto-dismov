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

    Evento(String idEvento,
           String nombre,
           String idUsuarioCreador,
           Ubicacion ubicacion,
           OffsetDateTime fechaHoraInicio) {
        this.id = idEvento;
        this.nombre = nombre;
        this.idUsuarioCreador = idUsuarioCreador;
        this.ubicacion = ubicacion;
        this.fechaHoraInicio = fechaHoraInicio;
        idUsuariosSuscriptos = new ArrayList<>();
        idUsuariosDislikes = new ArrayList<>();
    }

    Evento(String idEvento, EventoFirebase eventoFirebase) {
        this.id = idEvento;
        nombre = eventoFirebase.nombre;
        idUsuarioCreador = eventoFirebase.creador;
        ubicacion = new Ubicacion(eventoFirebase.latitud, eventoFirebase.longitud);
        fechaHoraInicio = OffsetDateTime.ofInstant(
                Instant.ofEpochSecond(eventoFirebase.fechaHora),
                ZoneId.of("America/Argentina/Buenos_Aires"));
        descripcion = eventoFirebase.descripcion;
        idUsuariosSuscriptos = new ArrayList<>(eventoFirebase.suscriptos.keySet());
        idUsuariosDislikes = new ArrayList<>(eventoFirebase.dislikes.keySet());
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

    void agregarUsuarioSuscripto(String idUsuario) {
        idUsuariosSuscriptos.add(idUsuario);
    }

    void agregarUsuarioDislike(String idUsuario) {
        idUsuariosDislikes.add(idUsuario);
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
