package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.core.Repo;

import java.util.ArrayList;


public class Modelo extends ViewModel {

    LiveData<Evento> getEvento(String idEvento) {
        return Repositorio.getEvento(idEvento);
    }

    LiveData<ArrayList<Evento>> getEventos() {
        return Repositorio.getEventos();
    }
}
