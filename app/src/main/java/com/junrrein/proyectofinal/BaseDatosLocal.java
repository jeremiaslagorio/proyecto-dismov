package com.junrrein.proyectofinal;

import androidx.lifecycle.LiveData;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class BaseDatosLocal {

    static private AppDatabase database = MiAplicacion.getDatabase();
    static private EventoRoomDao eventoRoomDao = database.eventoRoomDao();

    static void guardarEvento(Evento evento) {
        eventoRoomDao.save(new EventoRoom(evento));
    }

    static void eliminarEvento(Evento evento) {
        eventoRoomDao.delete(evento.getId());
    }

    static void guardarYEliminarEventosEnMasa(List<Evento> aGuardar, List<String> idEventosABorrar) {
        List<EventoRoom> aGuardarRoom = new ArrayList<>();

        for (Evento evento : aGuardar)
            aGuardarRoom.add(new EventoRoom(evento));

        eventoRoomDao.batchSaveAndDelete(aGuardarRoom, idEventosABorrar);
    }

    static LiveData<EventoRoom> getEvento(String idEvento) {
        return eventoRoomDao.loadById(idEvento);
    }

    static LiveData<List<EventoRoom>> getEventosAsync() {
        return eventoRoomDao.loadAllAsync();
    }

    static List<EventoRoom> getEventosSync() {
        return eventoRoomDao.loadAllSync();
    }

    static boolean existeEvento(String idEvento) {
        return eventoRoomDao.exists(idEvento) != 0;
    }

    static Instant ultimaActualizacionEvento(String idEvento) {
        return Instant.ofEpochSecond(eventoRoomDao.ultimaActualizacion(idEvento));
    }
}
