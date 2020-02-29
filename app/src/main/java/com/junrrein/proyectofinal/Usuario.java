package com.junrrein.proyectofinal;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Usuario {

    private String id;
    private String nombreApellido;
    private List<String> idEventosCreados = new ArrayList<>();
    private List<String> idEventosInteresado = new ArrayList<>();
    private List<String> idEventosAsiste = new ArrayList<>();

    Usuario(String id, String nombreApellido) {
        this.id = id;
        this.nombreApellido = nombreApellido;
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

        if (!usuarioRoom.idEventosCreados.isEmpty())
            idEventosCreados = new ArrayList<>(Arrays.asList(usuarioRoom.idEventosCreados.split(" ")));

        if (!usuarioRoom.idEventosInteresado.isEmpty())
            idEventosInteresado = new ArrayList<>(Arrays.asList(usuarioRoom.idEventosInteresado.split(" ")));

        if (!usuarioRoom.idEventosAsiste.isEmpty())
            idEventosAsiste = new ArrayList<>(Arrays.asList(usuarioRoom.idEventosAsiste.split(" ")));
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

    void quitarEventoInteresado(String idEvento) {
        idEventosInteresado.remove(idEvento);
    }

    void agregarEventoAsiste(String idEvento) {
        idEventosAsiste.add(idEvento);
    }

    void quitarEventoAsiste(String idEvento) {
        idEventosAsiste.remove(idEvento);
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
