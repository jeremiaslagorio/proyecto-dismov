package com.junrrein.proyectofinal;

import com.google.firebase.database.PropertyName;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Evento {
    private static final String suscriptosProperty = "suscriptos";
    private static final String dislikesProperty = "dislikes";

    private String nombre;
    private String idCreador;
    private Double latitud;
    private Double longitud;
    private Long fechaHora;
    private String descripcion;

    @PropertyName(suscriptosProperty)
    private HashMap<String, Boolean> idUsuariosSuscriptos = new HashMap<>();
    @PropertyName(dislikesProperty)
    private HashMap<String, Boolean> idUsuariosDislike = new HashMap<>();

    public Evento() {
    }

    public Evento(String nombre,
                  String idCreador,
                  Double latitud,
                  Double longitud,
                  Long fechaHora,
                  String descripcion) {
        this.nombre = nombre;
        this.idCreador = idCreador;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;

        idUsuariosSuscriptos.put(idCreador, true);
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdCreador() {
        return idCreador;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public Long getFechaHora() {
        return fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @PropertyName(suscriptosProperty)
    public HashMap<String, Boolean> getIdUsuariosSuscriptos() {
        return idUsuariosSuscriptos;
    }

    @PropertyName(dislikesProperty)
    public HashMap<String, Boolean> getIdUsuariosDislike() {
        return idUsuariosDislike;
    }

    Date getFechaHoraComoDate() {
        return new Date(fechaHora);
    }

    void agregarUsuarioSuscripto(String idUsuario) {
        idUsuariosSuscriptos.put(idUsuario, true);
    }

    void removerUsuarioSuscripto(String idUsuario) {
        idUsuariosSuscriptos.remove(idUsuario);
    }

    void agregarUsuarioDislike(String idUsuario) {
        idUsuariosDislike.put(idUsuario, true);
    }

    void removerUsuarioDislike(String idUsuario) {
        idUsuariosDislike.remove(idUsuario);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "nombre='" + nombre + '\'' +
                ", idCreador='" + idCreador + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", fechaHora=" + getFechaHoraComoDate() +
                ", descripcion='" + descripcion + '\'' +
                ", idUsuariosSuscriptos=" + idUsuariosSuscriptos +
                ", idUsuariosDislike=" + idUsuariosDislike +
                '}';
    }

    Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("idCreador", idCreador);
        result.put("suscriptos", idUsuariosSuscriptos);
        result.put("latitud", latitud);
        result.put("longitud", longitud);
        result.put("fechaHora", fechaHora);
        result.put("descripcion", descripcion);
        result.put("dislikes", idUsuariosDislike);

        return result;
    }
}
