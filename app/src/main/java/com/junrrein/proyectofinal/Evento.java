package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Evento {

    private String id;
    private String nombre;
    private String idUsuarioCreador;
    private String organizador;
    private Ubicacion ubicacion;
    private LocalDate fechaInicio;
    private LocalTime horaInicio;
    private String descripcion;
    private List<String> idUsuariosSuscriptos;
    private List<String> idUsuariosDislikes;

    Evento(String idEvento,
           String nombre,
           String idUsuarioCreador,
           String organizador,
           Ubicacion ubicacion,
           LocalDate fechaInicio,
           LocalTime horaInicio) {
        this.id = idEvento;
        this.nombre = nombre;
        this.idUsuarioCreador = idUsuarioCreador;
        this.organizador = organizador;
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
        organizador = eventoFirebase.organizador;
        ubicacion = new Ubicacion(eventoFirebase.latitud, eventoFirebase.longitud);
        fechaInicio = LocalDate.parse(eventoFirebase.fecha);
        horaInicio = LocalTime.parse(eventoFirebase.hora);
        descripcion = eventoFirebase.descripcion;
        idUsuariosSuscriptos = new ArrayList<>(eventoFirebase.suscriptos.keySet());
        idUsuariosDislikes = new ArrayList<>(eventoFirebase.dislikes.keySet());
    }

    Evento(EventoRoom eventoRoom) {
        id = eventoRoom.id;
        nombre = eventoRoom.nombre;
        idUsuarioCreador = eventoRoom.idUsuarioCreador;
        organizador = eventoRoom.organizador;
        ubicacion = new Ubicacion(eventoRoom.latitud, eventoRoom.longitud);
        fechaInicio = LocalDate.parse(eventoRoom.fechaInicio);
        horaInicio = LocalTime.parse(eventoRoom.horaInicio);
        descripcion = eventoRoom.descripcion;
        idUsuariosSuscriptos = Arrays.asList(eventoRoom.idUsuariosSuscriptos.split(" "));
        idUsuariosDislikes = Arrays.asList(eventoRoom.idUsuariosDislikes.split(" "));
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

    String getOrganizador() {
        return organizador;
    }

    Ubicacion getUbicacion() {
        return ubicacion;
    }

    LocalDate getFechaInicio() {
        return fechaInicio;
    }

    LocalTime getHoraInicio() {
        return horaInicio;
    }

    String getDescripcion() {
        return descripcion;
    }

    List<String> getIdUsuariosSuscriptos() {
        return idUsuariosSuscriptos;
    }

    List<String> getIdUsuariosDislikes() {
        return idUsuariosDislikes;
    }

    void agregarUsuarioSuscripto(String idUsuario) {
        idUsuariosSuscriptos.add(idUsuario);
    }

    void agregarUsuarioDislike(String idUsuario) {
        idUsuariosDislikes.add(idUsuario);
    }

    @NonNull
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
