package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class Repositorio {

    static private final long DIEZ_MINUTOS_EN_SEGUNDOS = 600;
    static private Instant ultimaActualizacionEventos = null;

    static LiveData<Evento> getEvento(String idEvento) {
        if (eventoEsViejo(idEvento))
            refrescarEvento(idEvento);

        MediatorLiveData<Evento> data = new MediatorLiveData<>();

        data.addSource(BaseDatosLocal.getEvento(idEvento), eventoRoom -> {
            if (eventoRoom != null)
                data.setValue(new Evento(eventoRoom));
        });

        return data;
    }

    static LiveData<List<Evento>> getEventos() {
        refrescarEventos();

        MediatorLiveData<List<Evento>> data = new MediatorLiveData<>();

        data.addSource(BaseDatosLocal.getEventosAsync(), eventosRoom -> {
            List<Evento> eventos = new ArrayList<>();

            for (EventoRoom eventoRoom : eventosRoom)
                eventos.add(new Evento(eventoRoom));

            data.setValue(eventos);
        });

        return data;
    }

    static private boolean eventoEsViejo(String idEvento) {
        if (!BaseDatosLocal.existeEvento(idEvento))
            return true;

        Instant ultimaActualizacion = BaseDatosLocal.ultimaActualizacionEvento(idEvento);
        Instant haceDiezMinutos = Instant.now().minusSeconds(DIEZ_MINUTOS_EN_SEGUNDOS);

        return ultimaActualizacion.isBefore(haceDiezMinutos);
    }

    static private void refrescarEvento(String idEvento) {
            BaseDatosRemota.getEvento(idEvento)
                    .addOnSuccessListener(BaseDatosLocal::guardarEvento);
    }

    static private void refrescarEventos() {
        Instant haceDiezMinutos = Instant.now().minusSeconds(DIEZ_MINUTOS_EN_SEGUNDOS);

        if (ultimaActualizacionEventos != null && ultimaActualizacionEventos.isAfter(haceDiezMinutos))
            return;

        BaseDatosRemota.getEventos()
                .addOnSuccessListener(eventos -> {
                    List<String> idEventosBorrados = obtenerIdsEventosBorrados(eventos);
                    BaseDatosLocal.guardarYEliminarEventosEnMasa(eventos, idEventosBorrados);
                    ultimaActualizacionEventos = Instant.now();
                });
    }

    static private List<String> obtenerIdsEventosBorrados(List<Evento> eventosActualizados) {
        List<String> idEventosViejos = new ArrayList<>();

        for (EventoRoom eventoViejo : BaseDatosLocal.getEventosSync())
            idEventosViejos.add(eventoViejo.id);

        List<String> idEventosBorrados = new ArrayList<>();

        for (Evento eventoActualizado : eventosActualizados)
            if (!idEventosViejos.contains(eventoActualizado.getId()))
                idEventosBorrados.add(eventoActualizado.getId());

        return idEventosBorrados;
    }
}
