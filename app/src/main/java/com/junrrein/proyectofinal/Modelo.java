package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


public class Modelo extends ViewModel {

    LiveData<Evento> getEvento(String idEvento) {
        return Repositorio.getEvento(idEvento);
    }
}
