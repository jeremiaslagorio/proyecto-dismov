package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Evento implements Serializable {

    private String id;
    private String nombre;
    private String idUsuarioCreador;
    private String organizador;
    private Ubicacion ubicacion;
    private LocalDate fechaInicio;
    private LocalTime horaInicio;
    private String descripcion;
    private List<String> idUsuariosInteresados;
    private List<String> idUsuariosAsistentes;
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
        idUsuariosInteresados = new ArrayList<>();
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
        idUsuariosInteresados = new ArrayList<>(eventoFirebase.interesados.keySet());
        idUsuariosAsistentes = new ArrayList<>(eventoFirebase.asisten.keySet());
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
        idUsuariosInteresados = Arrays.asList(eventoRoom.idUsuariosInteresados.split(" "));
        idUsuariosAsistentes = Arrays.asList(eventoRoom.idUsuariosAsistentes.split(" "));
        idUsuariosDislikes = Arrays.asList(eventoRoom.idUsuariosDislikes.split(" "));
    }

    String getId() {
        return id;
    }

    String getNombre() {
        return nombre;
    }

    void setNombre(String nombre) {
        this.nombre = nombre;
    }

    String getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    String getOrganizador() {
        return organizador;
    }

    void setOrganizador(String organizador) {
        this.organizador = organizador;
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

    void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    List<String> getIdUsuariosInteresados() {
        return idUsuariosInteresados;
    }

    List<String> getIdUsuariosAsistentes() {
        return idUsuariosAsistentes;
    }

    List<String> getIdUsuariosDislikes() {
        return idUsuariosDislikes;
    }

    boolean estaInteresado(String idUsuario) {
        return idUsuariosInteresados.contains(idUsuario);
    }

    boolean asiste(String idUsuario) {
        return idUsuariosAsistentes.contains(idUsuario);
    }

    boolean noLeGusta(String idUsuario) {
        return idUsuariosDislikes.contains(idUsuario);
    }

    void agregarUsuarioInteresado(String idUsuario) {
        idUsuariosInteresados.add(idUsuario);
    }

    void agregarUsuarioAsistente(String idUsuario) {
        idUsuariosAsistentes.add(idUsuario);
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
                ", idUsuariosSuscriptos=" + idUsuariosInteresados +
                ", idUsuariosDislikes=" + idUsuariosDislikes +
                '}';
    }
}
