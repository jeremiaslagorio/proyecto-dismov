package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Usuario {

    private String id;
    private String nombreApellido;
    private List<String> idEventosCreados;
    private List<String> idEventosSuscriptos;

    Usuario(String id, String nombreApellido) {
        this.id = id;
        this.nombreApellido = nombreApellido;
        idEventosCreados = new ArrayList<>();
        idEventosSuscriptos = new ArrayList<>();
    }

    Usuario(String id, UsuarioFirebase usuarioFirebase) {
        this.id = id;
        nombreApellido = usuarioFirebase.nombre;
        idEventosCreados = new ArrayList<>(usuarioFirebase.creados.keySet());
        idEventosSuscriptos = new ArrayList<>(usuarioFirebase.suscriptos.keySet());
    }

    Usuario(UsuarioRoom usuarioRoom) {
        id = usuarioRoom.id;
        nombreApellido = usuarioRoom.nombreApellido;
        idEventosCreados = Arrays.asList(usuarioRoom.idEventosCreados.split(" "));
        idEventosSuscriptos = Arrays.asList(usuarioRoom.idEventosSuscriptos.split(" "));
    }

    String getId() {
        return id;
    }

    String getNombreApellido() {
        return nombreApellido;
    }

    List<String> getIdEventosCreados() {
        return idEventosCreados;
    }

    List<String> getIdEventosSuscriptos() {
        return idEventosSuscriptos;
    }

    void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    void agregarEventoCreado(Evento evento) {
        idEventosCreados.add(evento.getId());
    }

    void agregarEventoSuscripto(Evento evento) {
        idEventosSuscriptos.add(evento.getId());
    }

    @NonNull
    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombreApellido='" + nombreApellido + '\'' +
                ", idEventosCreados=" + idEventosCreados +
                ", idEventosSuscriptos=" + idEventosSuscriptos +
                '}';
    }
}
