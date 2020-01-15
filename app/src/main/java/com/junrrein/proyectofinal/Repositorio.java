package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

class Repositorio {
    static LiveData<Usuario> getUsuario(String idUsuario) {
        MutableLiveData<Usuario> data = new MutableLiveData<>();

        BaseDatosRemota.getUsuario(idUsuario).addOnSuccessListener(data::setValue);

        return data;
    }

    static LiveData<Evento> getEvento(String idEvento) {
        MutableLiveData<Evento> data = new MutableLiveData<>();

        BaseDatosRemota.getEvento(idEvento).addOnSuccessListener(data::setValue);

        return data;
    }

    static LiveData<ArrayList<Evento>> getEventos() {
        MutableLiveData<ArrayList<Evento>> data = new MutableLiveData<>();

        BaseDatosRemota.getEventos().addOnSuccessListener(data::setValue);

        return data;
    }
}
