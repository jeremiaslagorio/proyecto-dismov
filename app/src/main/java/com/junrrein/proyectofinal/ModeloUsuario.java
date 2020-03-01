package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.junrrein.proyectofinal.backend.Evento;
import com.junrrein.proyectofinal.backend.Repositorio;
import com.junrrein.proyectofinal.backend.Usuario;

import java.util.List;


public class ModeloUsuario extends ViewModel {

    public String idUsuario;
    private LiveData<Usuario> usuarioLiveData = new MutableLiveData<>();
    private LiveData<List<LiveData<Evento>>> eventosSuscriptosLiveData = new MutableLiveData<>();

    void setUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
        usuarioLiveData = Repositorio.getUsuario(idUsuario);
        eventosSuscriptosLiveData = Repositorio.getEventosSuscriptos(idUsuario);
    }

    public LiveData<List<LiveData<Evento>>> getEventosSuscriptos() {
        return eventosSuscriptosLiveData;
    }

    LiveData<List<Evento>> getEventos() {
        return Repositorio.getEventos();
    }
}
