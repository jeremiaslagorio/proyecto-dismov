package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ModeloEvento extends ViewModel {

    private LiveData<Evento> evento = null;

    void setEvento(String idEvento) {
        evento = Repositorio.getEvento(idEvento);
    }

    LiveData<Evento> getEvento() {
        return evento;
    }

    void guardarEvento(Evento evento) {
        Repositorio.guardarEvento(evento);
    }
}
