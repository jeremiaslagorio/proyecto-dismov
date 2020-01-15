package com.junrrein.proyectofinal;

import java.util.ArrayList;

public class Usuario {

    private String id;
    private String nombreApellido;
    private ArrayList<String> idEventosCreados;
    private ArrayList<String> idEventosSuscriptos;

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

    String getId() {
        return id;
    }

    String getNombreApellido() {
        return nombreApellido;
    }

    ArrayList<String> getIdEventosCreados() {
        return idEventosCreados;
    }

    ArrayList<String> getIdEventosSuscriptos() {
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
