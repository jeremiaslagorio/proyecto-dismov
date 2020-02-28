package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class ModeloUsuario extends ViewModel {

    private LiveData<Usuario> usuarioLiveData = new MutableLiveData<>();
    private LiveData<List<LiveData<Evento>>> eventosSuscriptosLiveData = new MutableLiveData<>();

    void setUsuario(String idUsuario) {
        usuarioLiveData = Repositorio.getUsuario(idUsuario);
        eventosSuscriptosLiveData = Repositorio.getEventosSuscriptos(idUsuario);
    }

    LiveData<List<LiveData<Evento>>> getEventosSuscriptos() {
        return eventosSuscriptosLiveData;
    }

    LiveData<List<Evento>> getEventos() {
        return Repositorio.getEventos();
    }
}
