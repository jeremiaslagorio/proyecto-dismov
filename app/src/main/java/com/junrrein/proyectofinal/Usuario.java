package com.junrrein.proyectofinal;

import com.google.firebase.database.PropertyName;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private static final String creadosProperty = "creados";
    private static final String suscriptosProperty = "suscriptos";

    private String nombre;

    @PropertyName(creadosProperty)
    private HashMap<String, Boolean> idEventosCreados = new HashMap<>();
    @PropertyName(suscriptosProperty)
    private HashMap<String, Boolean> idEventosSuscriptos = new HashMap<>();

    public Usuario() {
    }

    Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @PropertyName(creadosProperty)
    public HashMap<String, Boolean> getIdEventosCreados() {
        return idEventosCreados;
    }

    @PropertyName(suscriptosProperty)
    public HashMap<String, Boolean> getIdEventosSuscriptos() {
        return idEventosSuscriptos;
    }

    void agregarEventoCreado(String idEvento) {
        idEventosCreados.put(idEvento, true);
    }

    void removerEventoCreado(String idEvento) {
        idEventosCreados.remove(idEvento);
    }

    void agregarEventoSuscripto(String idEvento) {
        idEventosSuscriptos.put(idEvento, true);
    }

    void removerEventoSuscripto(String idEvento) {
        idEventosSuscriptos.remove(idEvento);
    }

    Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("creados", idEventosCreados);
        result.put("suscriptos", idEventosSuscriptos);

        return result;
    }
}
