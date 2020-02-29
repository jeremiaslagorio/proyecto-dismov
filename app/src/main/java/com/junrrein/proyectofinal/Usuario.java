package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Usuario {

    private String id;
    private String nombreApellido;
    private List<String> idEventosCreados;
    private List<String> idEventosInteresado;
    private List<String> idEventosAsiste;

    Usuario(String id, String nombreApellido) {
        this.id = id;
        this.nombreApellido = nombreApellido;
        idEventosCreados = new ArrayList<>();
        idEventosInteresado = new ArrayList<>();
        idEventosAsiste = new ArrayList<>();
    }

    Usuario(String id, UsuarioFirebase usuarioFirebase) {
        this.id = id;
        nombreApellido = usuarioFirebase.nombre;
        idEventosCreados = new ArrayList<>(usuarioFirebase.creados.keySet());
        idEventosInteresado = new ArrayList<>(usuarioFirebase.interesado.keySet());
        idEventosAsiste = new ArrayList<>(usuarioFirebase.interesado.keySet());
    }

    Usuario(UsuarioRoom usuarioRoom) {
        id = usuarioRoom.id;
        nombreApellido = usuarioRoom.nombreApellido;
        idEventosCreados = Arrays.asList(usuarioRoom.idEventosCreados.split(" "));
        idEventosInteresado = Arrays.asList(usuarioRoom.idEventosInteresado.split(" "));
        idEventosAsiste = Arrays.asList(usuarioRoom.idEventosAsiste.split(" "));
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

    List<String> getIdEventosInteresado() {
        return idEventosInteresado;
    }

    List<String> getIdEventosAsiste() {
        return idEventosAsiste;
    }

    void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    void agregarEventoCreado(String idEvento) {
        idEventosCreados.add(idEvento);
    }

    void agregarEventoInteresado(String idEvento) {
        idEventosInteresado.add(idEvento);
    }

    @Override
    @NonNull
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombreApellido='" + nombreApellido + '\'' +
                ", idEventosCreados=" + idEventosCreados +
                ", idEventosInteresado=" + idEventosInteresado +
                ", idEventosAsiste=" + idEventosAsiste +
                '}';
    }
}
