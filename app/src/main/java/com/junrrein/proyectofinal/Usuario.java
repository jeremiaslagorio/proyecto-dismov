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

    Usuario(String id, UsuarioPojo usuarioPojo) {
        this.id = id;
        nombreApellido = usuarioPojo.nombre;
        idEventosCreados = new ArrayList<>(usuarioPojo.creados.keySet());
        idEventosSuscriptos = new ArrayList<>(usuarioPojo.suscriptos.keySet());
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
