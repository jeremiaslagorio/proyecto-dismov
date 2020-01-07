package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Modelo extends ViewModel {
    public LiveData<Evento> evento = Repositorio.getEvento("15");
}
