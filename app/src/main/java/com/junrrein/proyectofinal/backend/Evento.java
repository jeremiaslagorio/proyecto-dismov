package com.junrrein.proyectofinal.backend;

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
    private List<String> idUsuariosInteresados = new ArrayList<>();
    private List<String> idUsuariosAsistentes = new ArrayList<>();
    private List<String> idUsuariosDislikes = new ArrayList<>();

    public Evento(String idEvento,
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

        if (!eventoRoom.idUsuariosInteresados.isEmpty())
            idUsuariosInteresados = new ArrayList<>(Arrays.asList(eventoRoom.idUsuariosInteresados.split(" ")));

        if (!eventoRoom.idUsuariosAsistentes.isEmpty())
            idUsuariosAsistentes = new ArrayList<>(Arrays.asList(eventoRoom.idUsuariosAsistentes.split(" ")));

        if (!eventoRoom.idUsuariosDislikes.isEmpty())
            idUsuariosDislikes = new ArrayList<>(Arrays.asList(eventoRoom.idUsuariosDislikes.split(" ")));
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
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

    public boolean estaInteresado(String idUsuario) {
        return idUsuariosInteresados.contains(idUsuario);
    }

    public boolean asiste(String idUsuario) {
        return idUsuariosAsistentes.contains(idUsuario);
    }

    public boolean noLeGusta(String idUsuario) {
        return idUsuariosDislikes.contains(idUsuario);
    }

    public void agregarUsuarioInteresado(String idUsuario) {
        idUsuariosInteresados.add(idUsuario);
    }

    public void quitarUsuarioInteresado(String idUsuario) {
        idUsuariosInteresados.remove(idUsuario);
    }

    public void agregarUsuarioAsistente(String idUsuario) {
        idUsuariosAsistentes.add(idUsuario);
    }

    public void quitarUsuarioAsistente(String idUsuario) {
        idUsuariosAsistentes.remove(idUsuario);
    }

    public void agregarUsuarioDislike(String idUsuario) {
        idUsuariosDislikes.add(idUsuario);
    }

    public void quitarUsuarioDislike(String idUsuario) {
        idUsuariosDislikes.remove(idUsuario);
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
