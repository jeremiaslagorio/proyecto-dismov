package com.junrrein.proyectofinal.backend;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Usuario {

    private String id;
    private String nombreApellido;
    private Set<String> idDispositivos = new HashSet<>();

    public Usuario(String id, String nombreApellido) {
        this.id = id;
        this.nombreApellido = nombreApellido;
    }

    Usuario(String id, UsuarioFirebase usuarioFirebase) {
        this.id = id;
        nombreApellido = usuarioFirebase.nombre;
        idDispositivos = usuarioFirebase.dispositivos.keySet();
    }

    Usuario(UsuarioRoom usuarioRoom) {
        id = usuarioRoom.id;
        nombreApellido = usuarioRoom.nombreApellido;

        if (!usuarioRoom.idDispositivos.isEmpty())
            idDispositivos = new HashSet<>(Arrays.asList(usuarioRoom.idDispositivos.split(" ")));
    }

    String getId() {
        return id;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    Set<String> getIdDispositivos() {
        return idDispositivos;
    }

    public boolean estaIdDispositivo(String idDispositivo) {
        return idDispositivos.contains(idDispositivo);
    }

    public void agregarIdDispositivo(String idDispositivo) {
        idDispositivos.add(idDispositivo);
    }

    @Override
    @NonNull
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombreApellido='" + nombreApellido + '\'' +
                '}';
    }
}
