package com.junrrein.proyectofinal.backend;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Entity(tableName = "eventos")
public class EventoRoom {

    @NonNull
    @PrimaryKey
    public String id = "";

    public String nombre;
    public String idUsuarioCreador;
    public String organizador;
    public Double latitud;
    public Double longitud;
    public String fechaInicio;
    public String horaInicio;
    public String descripcion;
    public String idUsuariosInteresados;
    public String idUsuariosAsistentes;
    public String idUsuariosDislikes;
    public long ultimaActualizacion;

    EventoRoom() {
    }

    EventoRoom(Evento evento) {
        id = evento.getId();
        nombre = evento.getNombre();
        idUsuarioCreador = evento.getIdUsuarioCreador();
        organizador = evento.getOrganizador();
        Ubicacion ubicacion = evento.getUbicacion();
        latitud = ubicacion.latitud;
        longitud = ubicacion.longitud;
        fechaInicio = evento.getFechaInicio().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        horaInicio = evento.getHoraInicio().format(DateTimeFormatter.ofPattern("HH:mm"));
        descripcion = evento.getDescripcion();
        idUsuariosInteresados = String.join(" ", evento.getIdUsuariosInteresados());
        idUsuariosAsistentes = String.join(" ", evento.getIdUsuariosAsistentes());
        idUsuariosDislikes = String.join(" ", evento.getIdUsuariosDislikes());
        ultimaActualizacion = Instant.now().getEpochSecond();
    }
}
