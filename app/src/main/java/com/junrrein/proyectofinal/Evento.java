package com.junrrein.proyectofinal;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Evento {
    private String nombre;
    private String idCreador;
    private HashMap<String, Boolean> idSuscriptos;
    private Double latitud;
    private Double longitud;
    private Long fechaHora;
    private String descripcion;
    private Long dislikes;

    public Evento() {
    }

    public Evento(String nombre,
                  String idCreador,
                  HashMap<String, Boolean> idSuscriptos,
                  Double latitud,
                  Double longitud,
                  Long fechaHora,
                  String descripcion,
                  Long dislikes) {
        this.nombre = nombre;
        this.idCreador = idCreador;
        this.idSuscriptos = idSuscriptos;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
        this.dislikes = dislikes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdCreador() {
        return idCreador;
    }

    public HashMap<String, Boolean> getIdSuscriptos() {
        return idSuscriptos;
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

    public Long getDislikes() {
        return dislikes;
    }

    @Exclude
    public Date getFechaHoraComoDate() {
        return new Date(fechaHora);
    }

    @Exclude
    Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("idCreador", idCreador);
        result.put("idSuscriptos", idSuscriptos);
        result.put("latitud", latitud);
        result.put("longitud", longitud);
        result.put("fechaHora", fechaHora);
        result.put("descripcion", descripcion);
        result.put("dislikes", dislikes);

        return result;
    }
}
