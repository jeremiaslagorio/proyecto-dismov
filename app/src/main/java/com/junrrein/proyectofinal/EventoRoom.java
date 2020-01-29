package com.junrrein.proyectofinal;

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

    String nombre;
    String idUsuarioCreador;
    String organizador;
    Double latitud;
    Double longitud;
    String fechaInicio;
    String horaInicio;
    String descripcion;
    String idUsuariosSuscriptos;
    String idUsuariosDislikes;
    long ultimaActualizacion;

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
        idUsuariosSuscriptos = String.join(" ", evento.getIdUsuariosSuscriptos());
        idUsuariosDislikes = String.join(" ", evento.getIdUsuariosDislikes());
        ultimaActualizacion = Instant.now().getEpochSecond();
    }
}
