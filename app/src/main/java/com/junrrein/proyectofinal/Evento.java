package com.junrrein.proyectofinal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Evento {

    private String id;
    private String nombre;
    private String idUsuarioCreador;
    private Ubicacion ubicacion;
    private LocalDate fechaInicio;
    private LocalTime horaInicio;
    private String descripcion;
    private ArrayList<String> idUsuariosSuscriptos;
    private ArrayList<String> idUsuariosDislikes;

    Evento(String idEvento,
           String nombre,
           String idUsuarioCreador,
           Ubicacion ubicacion,
           LocalDate fechaInicio,
           LocalTime horaInicio) {
        this.id = idEvento;
        this.nombre = nombre;
        this.idUsuarioCreador = idUsuarioCreador;
        this.ubicacion = ubicacion;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        idUsuariosSuscriptos = new ArrayList<>();
        idUsuariosDislikes = new ArrayList<>();
    }

    Evento(String idEvento, EventoFirebase eventoFirebase) {
        this.id = idEvento;
        nombre = eventoFirebase.nombre;
        idUsuarioCreador = eventoFirebase.creador;
        ubicacion = new Ubicacion(eventoFirebase.latitud, eventoFirebase.longitud);
        fechaInicio = LocalDate.parse(eventoFirebase.fecha);
        horaInicio = LocalTime.parse(eventoFirebase.hora);
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
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
                ", fechaInicio=" + fechaInicio +
                ", horaInicio=" + horaInicio +
                ", descripcion='" + descripcion + '\'' +
                ", idUsuariosSuscriptos=" + idUsuariosSuscriptos +
                ", idUsuariosDislikes=" + idUsuariosDislikes +
                '}';
    }
}
