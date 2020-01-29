package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class Modelo extends ViewModel {

    LiveData<Evento> getEvento(String idEvento) {
        return Repositorio.getEvento(idEvento);
    }

    LiveData<List<Evento>> getEventos() {
        return Repositorio.getEventos();
    }
}
