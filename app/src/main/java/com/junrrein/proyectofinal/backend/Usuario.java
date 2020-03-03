package com.junrrein.proyectofinal.backend;

import androidx.annotation.NonNull;

public class Usuario {

    private String id;
    private String nombreApellido;

    public Usuario(String id, String nombreApellido) {
        this.id = id;
        this.nombreApellido = nombreApellido;
    }

    Usuario(String id, UsuarioFirebase usuarioFirebase) {
        this.id = id;
        nombreApellido = usuarioFirebase.nombre;
    }

    Usuario(UsuarioRoom usuarioRoom) {
        id = usuarioRoom.id;
        nombreApellido = usuarioRoom.nombreApellido;
    }

    String getId() {
        return id;
    }

    String getNombreApellido() {
        return nombreApellido;
    }

    void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
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
