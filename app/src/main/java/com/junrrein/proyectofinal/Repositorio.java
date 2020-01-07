package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
}
