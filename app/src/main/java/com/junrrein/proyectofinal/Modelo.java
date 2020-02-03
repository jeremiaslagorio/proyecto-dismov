package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class Modelo extends ViewModel {

    LiveData<List<Evento>> getEventos() {
        return Repositorio.getEventos();
    }
}
