package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class Repositorio {

    static private final long DIEZ_MINUTOS_EN_SEGUNDOS = 600;
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
        executor.execute(() -> {
            if (BaseDatosLocal.existeEvento(idEvento) && eventoEstaRefrescado(idEvento))
                return;

            BaseDatosRemota.getEvento(idEvento)
                    .addOnSuccessListener(executor, BaseDatosLocal::guardarEvento);
        });
    }

    static private boolean eventoEstaRefrescado(String idEvento) {
        Instant ultimaActualizacion = BaseDatosLocal.ultimaActualizacionEvento(idEvento);
        Instant haceDiezMinutos = Instant.now().minusSeconds(DIEZ_MINUTOS_EN_SEGUNDOS);

        return ultimaActualizacion.isAfter(haceDiezMinutos);
    }
}
