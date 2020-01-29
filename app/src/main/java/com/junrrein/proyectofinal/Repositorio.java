package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class Repositorio {

    static private final Executor executor = Executors.newSingleThreadExecutor();

    static LiveData<Evento> getEvento(String idEvento) {
        refrescarEvento(idEvento);

        MediatorLiveData<Evento> data = new MediatorLiveData<>();

        data.addSource(BaseDatosLocal.getEvento(idEvento), eventoRoom -> {
            if (eventoRoom != null)
                data.setValue(new Evento(eventoRoom));
        });

        return data;
    }

    static LiveData<ArrayList<Evento>> getEventos() {
        MutableLiveData<ArrayList<Evento>> data = new MutableLiveData<>();

        BaseDatosRemota.getEventos().addOnSuccessListener(data::setValue);

        return data;
    }

    static private void refrescarEvento(String idEvento) {
        EventoRoom eventoRoom = BaseDatosLocal.getEvento(idEvento).getValue();

        if (eventoRoom != null)
            return;

        BaseDatosRemota.getEvento(idEvento)
                .addOnSuccessListener(executor, BaseDatosLocal::guardarEvento);
    }
}
