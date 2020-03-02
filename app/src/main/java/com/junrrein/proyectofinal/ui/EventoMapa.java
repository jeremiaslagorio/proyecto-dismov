package com.junrrein.proyectofinal.ui;

import com.junrrein.proyectofinal.backend.Evento;

import java.io.Serializable;
import java.time.LocalDate;

class EventoMapa implements Serializable {

    static final String SIN_ID = "sin_id";

    String id;
    double latitud;
    double longitud;
    String nombre;
    LocalDate fecha;

    public EventoMapa(double latitud, double longitud, String nombre, LocalDate fecha) {
        id = SIN_ID;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public EventoMapa(Evento evento) {
        id = evento.getId();
        latitud = evento.getUbicacion().latitud;
        longitud = evento.getUbicacion().longitud;
        nombre = evento.getNombre();
        fecha = evento.getFechaInicio();
    }
}
